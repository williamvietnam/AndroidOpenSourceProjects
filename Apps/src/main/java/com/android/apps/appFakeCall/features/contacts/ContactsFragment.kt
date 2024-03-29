package com.android.apps.appFakeCall.features.contacts

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.android.R
import com.android.apps.appFakeCall.data.entities.ContactEntity
import com.android.commons.base.BaseFragment
import com.android.commons.utilities.Constants
import com.android.databinding.FragmentContactsBinding

class ContactsFragment : BaseFragment<FragmentContactsBinding, ContactsViewModel>(),
    ContactsAdapter.ContactsCallBack {

    private lateinit var adapter: ContactsAdapter

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentContactsBinding {
        return FragmentContactsBinding.inflate(inflater, container, false)
    }

    override fun createViewModel(): ContactsViewModel {
        return ViewModelProvider(this)[ContactsViewModel::class.java]
    }

    override fun initializeViews() {
        adapter = ContactsAdapter(this)
        binding.recyclerview.adapter = adapter
        viewModel.allContactsData.observe(viewLifecycleOwner) {
            adapter.loadData(it)
        }
        viewModel.getAllContacts(requireContext())
    }

    override fun initializeEvents() {
        binding.buttonBack.setOnClickListener {
            findNavController().popBackStack()
        }

        binding.buttonAddNewContact.setOnClickListener {
            findNavController().navigate(R.id.action_contacts_to_newContact)
        }
    }

    override fun onContactClicked(contact: ContactEntity) {
        val bundle = Bundle()
        bundle.putSerializable(Constants.FAKE_CALL_INFORMATION, contact)
        findNavController().navigate(R.id.action_contacts_to_contactInfo, bundle)
    }
}