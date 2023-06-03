package com.android.tutorials.component_content_provider

import android.content.ContentValues
import android.os.Bundle
import android.provider.UserDictionary
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.databinding.FragmentContentProviderBinding

class ContentProviderComponentFragment : Fragment() {

    private var _binding: FragmentContentProviderBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentContentProviderBinding.inflate(inflater, container, false)
        return this.binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


    }

    private fun insertDictionary(dictionary: Dictionary) {
        val contentValue = ContentValues()

        contentValue.put(UserDictionary.Words.LOCALE, dictionary.locale)
        contentValue.put(UserDictionary.Words.WORD, dictionary.word)

        context?.contentResolver?.insert(UserDictionary.Words.CONTENT_URI, contentValue)
    }

    private fun getAllDictionary(): List<Dictionary> {
        val dictionaries = ArrayList<Dictionary>()
        String[] projection = { UserDictionary.Words.LOCALE, UserDictionary.Words.WORD }

        return dictionaries
    }

}