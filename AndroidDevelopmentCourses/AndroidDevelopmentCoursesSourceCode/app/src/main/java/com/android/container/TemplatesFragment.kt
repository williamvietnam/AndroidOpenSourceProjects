package com.android.container

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.R
import com.android.databinding.FragmentTemplatesBinding

class TemplatesFragment : Fragment() {
    private lateinit var templatesAdapter: TemplatesAdapter
    private var _binding: FragmentTemplatesBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentTemplatesBinding.inflate(inflater, container, false)
        return this.binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        templatesAdapter = TemplatesAdapter(
            useCaseList = initializeUseCasesList(),
            callBack = object : UseCaseCallBack {
                override fun useCaseOnClicked(useCase: UseCase) {
                    when (useCase.id) {
                        Constants.RETROFIT_RECYCLERVIEW_TEMPLATE -> {
                            Toast.makeText(context, useCase.text, Toast.LENGTH_SHORT).show()
                        }
                        Constants.ROOM_RECYCLERVIEW_TEMPLATE -> {
                            Toast.makeText(context, useCase.text, Toast.LENGTH_SHORT).show()
                        }
                        Constants.BOTTOM_SHEET_RECYCLERVIEW_TEMPLATE -> {
                            Toast.makeText(context, useCase.text, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        )

        this.binding.recyclerViewTemplates.adapter = templatesAdapter
    }

    private fun initializeUseCasesList(): List<UseCase> {
        val useCases = ArrayList<UseCase>()

        val useCase1 = UseCase(
            Constants.RETROFIT_RECYCLERVIEW_TEMPLATE,
            R.drawable.image_template,
            "RetrofitRecyclerView"
        )
        useCases.add(useCase1)

        val useCase2 = UseCase(
            Constants.ROOM_RECYCLERVIEW_TEMPLATE,
            R.drawable.image_template,
            "RoomRecyclerView"
        )
        useCases.add(useCase2)

        val useCase3 = UseCase(
            Constants.BOTTOM_SHEET_RECYCLERVIEW_TEMPLATE,
            R.drawable.image_template,
            "BottomSheetRecyclerView"
        )
        useCases.add(useCase3)

        return useCases
    }
}