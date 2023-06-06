package com.android.apps.appFakeCall.contacts

import android.content.Context
import android.graphics.drawable.Drawable
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.android.apps.appFakeCall.data.entities.ContactEntity
import com.android.databinding.ItemContactBinding
import com.bumptech.glide.Glide
import java.io.IOException

class ContactsAdapter(
    private val callback: ContactsCallBack
) : RecyclerView.Adapter<ContactsAdapter.ContactsViewHolder>() {

    private var contactList: MutableList<ContactEntity> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ContactsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val binding = ItemContactBinding.inflate(inflater, parent, false)
        return ContactsViewHolder(binding = binding)
    }

    override fun onBindViewHolder(holder: ContactsViewHolder, position: Int) {
        holder.onBind(position = position)
    }

    override fun getItemCount(): Int {
        return contactList.size
    }


    inner class ContactsViewHolder(
        private val binding: ItemContactBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun onBind(position: Int) {
            val contact = contactList?.get(position)
            if (contact != null) {
                if (contact.isDataBase) {
                    val uriDatabase = Uri.parse("${contact.contactIcon}")
                    binding.image.setImageURI(uriDatabase)
                } else {
                    val drawableImage = getImageFromAsset(
                        fileName = "images/fakecall/${contact.contactIcon}.jpg",
                        context = itemView.context
                    )
                    Glide.with(this.itemView.context).load(drawableImage).into(this.binding.image)
                }
                binding.textName.text = contact.contactName
                binding.textNumberPhone.text = contact.contactNumber

                binding.root.setOnClickListener {
                    callback.onContactClicked(contact)
                }
            }
        }
    }

    private fun getImageFromAsset(fileName: String, context: Context): Drawable? {
        var result: Drawable? = null
        try {
            val stream = context.assets.open(fileName)
            result = Drawable.createFromStream(stream, null)
        } catch (ioException: IOException) {
            ioException.printStackTrace()
            return null
        }
        return result
    }

    interface ContactsCallBack {
        fun onContactClicked(contact: ContactEntity)
    }
}