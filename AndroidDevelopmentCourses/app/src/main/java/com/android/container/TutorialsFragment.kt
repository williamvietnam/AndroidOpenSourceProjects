package com.android.container

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.R
import com.android.databinding.FragmentTutorialsBinding

class TutorialsFragment : Fragment() {
    private lateinit var tutorialsAdapter: TutorialsAdapter
    private var _binding: FragmentTutorialsBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTutorialsBinding.inflate(inflater, container, false)
        return this.binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        tutorialsAdapter = TutorialsAdapter(
            useCaseList = initializeUseCasesList(),
            callBack = object : UseCaseCallBack {
                override fun useCaseOnClicked(useCase: UseCase) {
                    when (useCase.id) {
                        Constants.LIFECYCLE_TUTORIAL -> {
                            Toast.makeText(context, useCase.text, Toast.LENGTH_LONG).show()
                        }
                        Constants.RECYCLERVIEW_TUTORIAL -> {
                            Toast.makeText(context, useCase.text, Toast.LENGTH_LONG).show()
                        }
                        Constants.SHARED_PREFERENCES_TUTORIAL -> {
                            Toast.makeText(context, useCase.text, Toast.LENGTH_LONG).show()
                        }
                        Constants.BROADCAST_RECEIVER_TUTORIAL -> {
                            Toast.makeText(context, useCase.text, Toast.LENGTH_LONG).show()
                        }
                        Constants.PERSISTENT_BOTTOM_SHEET_TUTORIAL -> {
                            Toast.makeText(context, useCase.text, Toast.LENGTH_LONG).show()
                        }
                        Constants.MODAL_BOTTOM_SHEET_DIALOG_TUTORIAL -> {
                            Toast.makeText(context, useCase.text, Toast.LENGTH_LONG).show()
                        }
                        Constants.MODAL_BOTTOM_SHEET_DIALOG_FRAGMENT_TUTORIAL -> {
                            Toast.makeText(context, useCase.text, Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        )

        this.binding.recyclerViewTutorials.adapter = tutorialsAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initializeUseCasesList(): List<UseCase> {
        val useCases = ArrayList<UseCase>()

        val useCase1 = UseCase(
            Constants.LIFECYCLE_TUTORIAL,
            R.drawable.image_tutorial,
            "Lifecycle"
        )
        useCases.add(useCase1)

        val useCase2 = UseCase(
            Constants.RECYCLERVIEW_TUTORIAL,
            R.drawable.image_tutorial,
            "RecyclerView"
        )
        useCases.add(useCase2)

        val useCase3 = UseCase(
            Constants.SHARED_PREFERENCES_TUTORIAL,
            R.drawable.image_tutorial,
            "Shared Preferences"
        )
        useCases.add(useCase3)

        val useCase4 = UseCase(
            Constants.BROADCAST_RECEIVER_TUTORIAL,
            R.drawable.image_tutorial,
            "Broadcast Receiver"
        )
        useCases.add(useCase4)

        val useCase5 = UseCase(
            Constants.PERSISTENT_BOTTOM_SHEET_TUTORIAL,
            R.drawable.image_tutorial,
            "Persistent Bottom Sheet"
        )
        useCases.add(useCase5)

        val useCase6 = UseCase(
            Constants.MODAL_BOTTOM_SHEET_DIALOG_TUTORIAL,
            R.drawable.image_tutorial,
            "Modal Bottom Sheet Dialog"
        )
        useCases.add(useCase6)

        val useCase7 = UseCase(
            Constants.MODAL_BOTTOM_SHEET_DIALOG_FRAGMENT_TUTORIAL,
            R.drawable.image_tutorial,
            "Modal Bottom Sheet Dialog Fragment"
        )
        useCases.add(useCase7)

        return useCases
    }
}