package com.android.apps.appFakeCall.features.contacts

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.android.apps.appFakeCall.data.entities.ContactEntity
import com.android.base.BaseFragment
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

        }
    }

    override fun onContactClicked(contact: ContactEntity) {

    }
}