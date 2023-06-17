package com.android.apps.appWAStickers.core

import android.app.Activity
import android.app.Dialog
import android.content.ActivityNotFoundException
import android.content.DialogInterface
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.android.BuildConfig
import com.android.R
import com.android.apps.appWAStickers.screens.stickers.WAStickersFragment.Companion.EXTRA_STICKER_PACK_AUTHORITY
import com.android.apps.appWAStickers.screens.stickers.WAStickersFragment.Companion.EXTRA_STICKER_PACK_ID
import com.android.apps.appWAStickers.screens.stickers.WAStickersFragment.Companion.EXTRA_STICKER_PACK_NAME

abstract class AddStickerPackFragment : BaseStickerFragment() {
    companion object {
        private const val ADD_PACK = 200
        private const val TAG = "AddStickerPackActivity"
    }

    fun addStickerPackToWhatsApp(identifier: String, stickerPackName: String) {
        try {
            //if neither WhatsApp Consumer or WhatsApp Business is installed, then tell user to install the apps.
            if (!WhitelistCheck.isWhatsAppConsumerAppInstalled(requireContext().packageManager)
                && !WhitelistCheck.isWhatsAppSmbAppInstalled(requireContext().packageManager)
            ) {
                Toast.makeText(
                    requireContext(),
                    R.string.add_pack_fail_prompt_update_whatsapp,
                    Toast.LENGTH_LONG
                ).show()
                return
            }
            val stickerPackWhitelistedInWhatsAppConsumer =
                WhitelistCheck.isStickerPackWhitelistedInWhatsAppConsumer(
                    requireContext(), identifier
                )
            val stickerPackWhitelistedInWhatsAppSmb =
                WhitelistCheck.isStickerPackWhitelistedInWhatsAppSmb(requireContext(), identifier)
            if (!stickerPackWhitelistedInWhatsAppConsumer && !stickerPackWhitelistedInWhatsAppSmb) {
                //ask users which app to add the pack to.
                launchIntentToAddPackToChooser(identifier, stickerPackName)
            } else if (!stickerPackWhitelistedInWhatsAppConsumer) {
                launchIntentToAddPackToSpecificPackage(
                    identifier,
                    stickerPackName,
                    WhitelistCheck.CONSUMER_WHATSAPP_PACKAGE_NAME
                )
            } else if (!stickerPackWhitelistedInWhatsAppSmb) {
                launchIntentToAddPackToSpecificPackage(
                    identifier,
                    stickerPackName,
                    WhitelistCheck.SMB_WHATSAPP_PACKAGE_NAME
                )
            } else {
                Toast.makeText(
                    requireContext(),
                    R.string.add_pack_fail_prompt_update_whatsapp,
                    Toast.LENGTH_LONG
                ).show()
            }
        } catch (e: Exception) {
            Log.e(TAG, "error adding sticker pack to WhatsApp", e)
            Toast.makeText(
                requireContext(),
                R.string.add_pack_fail_prompt_update_whatsapp,
                Toast.LENGTH_LONG
            )
                .show()
        }
    }

    private fun launchIntentToAddPackToSpecificPackage(
        identifier: String,
        stickerPackName: String,
        whatsappPackageName: String
    ) {
        val intent = createIntentToAddStickerPack(identifier, stickerPackName)
        intent.setPackage(whatsappPackageName)
        try {
            startActivityForResult(intent, ADD_PACK)
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(
                requireContext(),
                R.string.add_pack_fail_prompt_update_whatsapp,
                Toast.LENGTH_LONG
            )
                .show()
        }
    }

    //Handle cases either of WhatsApp are set as default app to handle this intent. We still want users to see both options.
    private fun launchIntentToAddPackToChooser(identifier: String, stickerPackName: String) {
        val intent = createIntentToAddStickerPack(identifier, stickerPackName)
        try {
            startActivityForResult(
                Intent.createChooser(
                    intent,
                    getString(R.string.add_to_whatsapp)
                ), ADD_PACK
            )
        } catch (e: ActivityNotFoundException) {
            Toast.makeText(
                requireContext(),
                R.string.add_pack_fail_prompt_update_whatsapp,
                Toast.LENGTH_LONG
            )
                .show()
        }
    }

    private fun createIntentToAddStickerPack(
        identifier: String,
        stickerPackName: String
    ): Intent {
        val intent = Intent()
        intent.action = "com.whatsapp.intent.action.ENABLE_STICKER_PACK"
        intent.putExtra(EXTRA_STICKER_PACK_ID, identifier)
        intent.putExtra(
            EXTRA_STICKER_PACK_AUTHORITY,
            BuildConfig.CONTENT_PROVIDER_AUTHORITY
        )
        intent.putExtra(EXTRA_STICKER_PACK_NAME, stickerPackName)
        return intent
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == ADD_PACK) {
            if (resultCode == Activity.RESULT_CANCELED) {
                if (data != null) {
                    val validationError = data.getStringExtra("validation_error")
                    if (validationError != null) {
                        if (BuildConfig.DEBUG) {
                            //validation error should be shown to developer only, not users.
                            MessageDialogFragment.newInstance(
                                R.string.title_validation_error,
                                validationError
                            ).show(requireActivity().supportFragmentManager, "validation error")
                        }
                        Log.e(TAG, "Validation failed:$validationError")
                    }
                } else {
                    StickerPackNotAddedMessageFragment().show(
                        requireActivity().supportFragmentManager, "sticker_pack_not_added"
                    )
                }
            }
        }
    }

    class StickerPackNotAddedMessageFragment : DialogFragment() {
        override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
            val dialogBuilder = AlertDialog.Builder(
                requireActivity()
            )
                .setMessage(R.string.add_pack_fail_prompt_update_whatsapp)
                .setCancelable(true)
                .setPositiveButton(
                    android.R.string.ok
                ) { dialog: DialogInterface?, which: Int -> dismiss() }
                .setNeutralButton(R.string.add_pack_fail_prompt_update_play_link) { dialog, which -> launchWhatsAppPlayStorePage() }
            return dialogBuilder.create()
        }

        private fun launchWhatsAppPlayStorePage() {
            if (activity != null) {
                val packageManager = requireActivity().packageManager
                val whatsAppInstalled = WhitelistCheck.isPackageInstalled(
                    WhitelistCheck.CONSUMER_WHATSAPP_PACKAGE_NAME,
                    packageManager
                )
                val smbAppInstalled = WhitelistCheck.isPackageInstalled(
                    WhitelistCheck.SMB_WHATSAPP_PACKAGE_NAME,
                    packageManager
                )
                val playPackageLinkPrefix = "http://play.google.com/store/apps/details?id="
                if (whatsAppInstalled && smbAppInstalled) {
                    launchPlayStoreWithUri("https://play.google.com/store/apps/developer?id=WhatsApp+LLC")
                } else if (whatsAppInstalled) {
                    launchPlayStoreWithUri(playPackageLinkPrefix + WhitelistCheck.CONSUMER_WHATSAPP_PACKAGE_NAME)
                } else if (smbAppInstalled) {
                    launchPlayStoreWithUri(playPackageLinkPrefix + WhitelistCheck.SMB_WHATSAPP_PACKAGE_NAME)
                }
            }
        }

        private fun launchPlayStoreWithUri(uriString: String) {
            val intent = Intent(Intent.ACTION_VIEW)
            intent.data = Uri.parse(uriString)
            intent.setPackage("com.android.vending")
            try {
                startActivity(intent)
            } catch (e: ActivityNotFoundException) {
                Toast.makeText(activity, R.string.cannot_find_play_store, Toast.LENGTH_LONG).show()
            }
        }
    }
}