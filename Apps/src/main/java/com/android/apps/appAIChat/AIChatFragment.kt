package com.android.apps.appAIChat

import android.app.AlertDialog
import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Handler
import android.os.Looper
import android.speech.tts.TextToSpeech
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import com.android.R
import com.android.commons.base.BaseFragment
import com.android.commons.utilities.Constants
import com.android.commons.utilities.Preferences
import com.android.databinding.AiChatDialogBinding
import com.android.databinding.FragmentAIChatBinding
import com.bumptech.glide.Glide
import okhttp3.*
import okhttp3.MediaType.Companion.toMediaTypeOrNull
import org.json.JSONArray
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import java.util.*
import java.util.concurrent.TimeUnit

class AIChatFragment : BaseFragment<FragmentAIChatBinding, AIChatViewModel>(),
    AIChatQuestionsListAdapter.AIChatQuestionCallBack,
    TextToSpeech.OnInitListener, AIChatAdapter.AIChatCallBack {

    private lateinit var questionsAdapter: AIChatQuestionsListAdapter
    private lateinit var chatAdapter: AIChatAdapter
    private lateinit var client: OkHttpClient
    private var textToSpeech: TextToSpeech? = null

    companion object {
        private val JSON: MediaType = "application/json; charset=utf-8".toMediaTypeOrNull()!!
        private const val API_URL = "https://api.openai.com/v1/chat/completions"
        private const val YOUR_AI_KEY = "register chatGPT account to create key"
    }

    override fun createViewModel(): AIChatViewModel {
        client = OkHttpClient.Builder()
            .connectTimeout(60, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(30, TimeUnit.SECONDS)
            .build()
        return ViewModelProvider(this)[AIChatViewModel::class.java]
    }

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentAIChatBinding {
        return FragmentAIChatBinding.inflate(inflater, container, false)
    }


    override fun initializeViews() {

        if (Preferences.instance.get(Constants.AI_CHAT_IS_AUDIO_ON, true) as Boolean) {
            binding.buttonRight.setImageResource(R.drawable.ic_ai_chat_audio_active)
        } else {
            binding.buttonRight.setImageResource(R.drawable.ic_ai_chat_audio_inactive)
        }

        if (!(Preferences.instance.get(
                Constants.AI_CHAT_OPEN_SCREEN_FIRST_TIME, false
            ) as Boolean)
        ) {
            Preferences.instance.set(Constants.AI_CHAT_OPEN_SCREEN_FIRST_TIME, true)
            Preferences.instance.set(Constants.AI_CHAT_REMAINING_MESSAGES, 3)
        }
        Glide.with(requireContext()).load(
            viewModel.getImageFromAsset("app_ai_chat/AiBot.gif", requireContext())
        ).into(binding.imvAiChatBot)
        binding.llQuestionsList.visibility = View.VISIBLE
        setupStartOpenScreen()

        //setup views for messages list
        chatAdapter = AIChatAdapter(this)
        binding.recyclerview.adapter = chatAdapter

        // textToSpeech
        textToSpeech = TextToSpeech(requireContext(), this)

        // show remaining messages
        binding.tvCountRemainingMessages.text = (Preferences.instance.get(
            Constants.AI_CHAT_REMAINING_MESSAGES, 0
        ) as Int).toString()

        binding.edtSendMessage.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
            override fun afterTextChanged(s: Editable?) {
                if (s != null && s.toString().trim().isNotEmpty()) {
                    binding.buttonSendMessage.setImageResource(R.drawable.ic_send_message)
                } else if (s != null && s.toString().trim().isEmpty()) {
                    binding.buttonSendMessage.setImageResource(R.drawable.ic_send_message_inactive)
                } else {
                    binding.buttonSendMessage.setImageResource(R.drawable.ic_send_message_inactive)
                }
            }
        })
    }

    override fun initializeEvents() {
        binding.buttonBack.setOnClickListener {
//            activity?.supportFragmentManager?.beginTransaction()?.hide(this)?.commitNow()
        }

        binding.buttonRight.setOnClickListener {
            if (Preferences.instance.get(Constants.AI_CHAT_IS_AUDIO_ON, true) as Boolean) {
                Preferences.instance.set(Constants.AI_CHAT_IS_AUDIO_ON, false)
                binding.buttonRight.setImageResource(R.drawable.ic_ai_chat_audio_inactive)
                textToSpeech?.stop()
            } else {
                Preferences.instance.set(Constants.AI_CHAT_IS_AUDIO_ON, true)
                binding.buttonRight.setImageResource(R.drawable.ic_ai_chat_audio_active)
            }
        }

        binding.tvGoPremium.setOnClickListener {
            Toast.makeText(requireContext(), "Developing", Toast.LENGTH_SHORT).show()
        }

        binding.buttonSendMessage.setOnClickListener {
            if (binding.edtSendMessage.text?.trim().toString().isNotEmpty()) {
                if (binding.llQuestionsList.visibility == View.VISIBLE) {
                    binding.llQuestionsList.visibility = View.GONE
                }
                var countRemainingMessages =
                    Preferences.instance.get(Constants.AI_CHAT_REMAINING_MESSAGES, 0) as Int
                if (countRemainingMessages > 0) {
                    val question = binding.edtSendMessage.text.toString().trim()
                    addToChat(question, Message.SENT_BY_ME)
                    binding.edtSendMessage.setText("")
                    callAPI(question)
                    countRemainingMessages -= 1
                    binding.tvCountRemainingMessages.text =
                        countRemainingMessages.toString()
                    Preferences.instance.set(
                        Constants.AI_CHAT_REMAINING_MESSAGES,
                        countRemainingMessages
                    )
                } else {
                    showWatchAds()
                }
            }
        }
    }

    private fun setupStartOpenScreen() {
        questionsAdapter = AIChatQuestionsListAdapter(viewModel.questionsListData(), this)
        binding.rcvQuestionList.adapter = questionsAdapter
    }

    private fun addToChat(message: String?, sentBy: String?) {
        requireActivity().runOnUiThread(Runnable {
            chatAdapter.loadMessage(Message(message, sentBy))
            binding.recyclerview.smoothScrollToPosition(chatAdapter.itemCount)
        })
    }

    fun addResponse(response: String?) {
        chatAdapter.removeMessage()
        addToChat(response, Message.SENT_BY_BOT)
    }

    private fun callAPI(question: String) {
        //ok http
        chatAdapter.loadMessage(Message("Typing...", Message.SENT_BY_BOT))
        val jsonBody = JSONObject()
        try {
            val messageArr = JSONArray()
            val obj = JSONObject()
            obj.put("role", "user")
            obj.put("content", question)
            messageArr.put(obj)
            jsonBody.put("messages", messageArr)
            jsonBody.put("model", "gpt-3.5-turbo")
            jsonBody.put("max_tokens", 1000)
        } catch (exception: JSONException) {
            exception.printStackTrace()
        }
        val body: RequestBody = RequestBody.create(JSON, jsonBody.toString())
        val request = Request.Builder()
            .url("\n" + API_URL)
            .header(
                "Authorization",
                "Bearer $YOUR_AI_KEY"
            )
            .post(body)
            .build()
        client.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                addResponse("Failed to load response due to " + e.message)
            }

            @Throws(IOException::class)
            override fun onResponse(call: Call, response: Response) {
                if (response.isSuccessful) {
                    val jsonObject: JSONObject?
                    try {
                        jsonObject = JSONObject(response.body!!.string())
                        val jsonArray = jsonObject.getJSONArray("choices")
                        val result = jsonArray.getJSONObject(0)
                            .getJSONObject("message")
                            .getString("content")
                        addResponse(result.trim())
                    } catch (e: JSONException) {
                        e.printStackTrace()
                    }
                } else {
                    addResponse("Failed to load response due to " + response.body!!.string())
                }
            }
        })
    }

    override fun onAIChatQuestionClicked(message: Message) {
        var countRemainingMessages = Preferences.instance.get(
            Constants.AI_CHAT_REMAINING_MESSAGES, 0
        ) as Int
        if (countRemainingMessages > 0) {
            countRemainingMessages -= 1
            addToChat(message.message, Message.SENT_BY_ME)
            requestDefaultAnswers(message)
            binding.llQuestionsList.visibility = View.GONE
            binding.tvCountRemainingMessages.text = countRemainingMessages.toString()
            Preferences.instance.set(Constants.AI_CHAT_REMAINING_MESSAGES, countRemainingMessages)
        } else {
            showWatchAds()
        }
    }

    private fun requestDefaultAnswers(message: Message) {
        val answer = viewModel.getDefaultAnswer(message, requireContext())
        chatAdapter.loadMessage(Message("Typing...", Message.SENT_BY_BOT))
        Handler(Looper.getMainLooper()).postDelayed({ addResponse(answer) }, 1500)
    }

    override fun onInit(status: Int) {
        if (status == TextToSpeech.SUCCESS) {
            val result = textToSpeech?.setLanguage(Locale.US)
            if (result == TextToSpeech.LANG_MISSING_DATA || result == TextToSpeech.LANG_NOT_SUPPORTED) {
                Log.e(
                    AIChatFragment::class.java.name,
                    "TextToSpeech: The Language not supported!"
                )
            } else {
                Log.e(
                    AIChatFragment::class.java.name,
                    "TextToSpeech: The Language is supported!"
                )
            }
        }
    }

    private fun showWatchAds() {
        textToSpeech?.stop()
        var countRemainingMessages = Preferences.instance.get(
            Constants.AI_CHAT_REMAINING_MESSAGES, 0
        ) as Int
        val builder = AlertDialog.Builder(requireContext())
        val dialogBinding = AiChatDialogBinding.inflate(LayoutInflater.from(requireContext()))
        builder.setView(dialogBinding.root)
        val dialog = builder.create()
        dialog.setCancelable(false)
        dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        dialogBinding.buttonClose.setOnClickListener {
            dialog.dismiss()
        }
        dialogBinding.buttonWatchAds.setOnClickListener {
            countRemainingMessages += 2
            binding.tvCountRemainingMessages.text = countRemainingMessages.toString()
            Preferences.instance.set(
                Constants.AI_CHAT_REMAINING_MESSAGES,
                countRemainingMessages
            )
            dialog.dismiss()
        }
        dialog.show()
    }

    override fun onDestroyView() {
        textToSpeech?.stop()
        textToSpeech?.shutdown()
        super.onDestroyView()
    }

    override fun onTextToSpeechAIChat(message: String) {
        if (Preferences.instance.get(
                Constants.AI_CHAT_IS_AUDIO_ON,
                true
            ) as Boolean
        ) {
            textToSpeech?.speak(message, TextToSpeech.QUEUE_FLUSH, null, "")
        }
    }

    override fun onCopyTextClicked(text: String) {
        Toast.makeText(requireContext(), "Copied", Toast.LENGTH_LONG).show()
        val clipboard = requireContext().getSystemService(
            Context.CLIPBOARD_SERVICE
        ) as ClipboardManager
        val clip = ClipData.newPlainText("Copied Text", text)
        clipboard.setPrimaryClip(clip)
    }

}