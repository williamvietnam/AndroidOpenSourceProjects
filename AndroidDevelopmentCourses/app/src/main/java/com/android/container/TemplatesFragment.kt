package com.android.container

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.databinding.FragmentTemplatesBinding

class TemplatesFragment : Fragment() {
    private lateinit var adapter: CasesAdapter
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
        adapter = CasesAdapter(
            cases = initializeCasesList(),
            callback = object : CasesAdapter.CaseCallBack {
                override fun onCaseClicked(case: Case) {
                    when (case.id) {
                        Constants.RETROFIT_RECYCLERVIEW_TEMPLATE -> {
                            Toast.makeText(context, case.text, Toast.LENGTH_SHORT).show()
                        }
                        Constants.ROOM_RECYCLERVIEW_TEMPLATE -> {
                            Toast.makeText(context, case.text, Toast.LENGTH_SHORT).show()
                        }
                        Constants.BOTTOM_SHEET_RECYCLERVIEW_TEMPLATE -> {
                            Toast.makeText(context, case.text, Toast.LENGTH_SHORT).show()
                        }
                    }
                }
            }
        )

        this.binding.recyclerViewTemplates.adapter = adapter
    }

    private fun initializeCasesList(): List<Case> {
        val cases = ArrayList<Case>()

        val case1 = Case(
            Constants.RETROFIT_RECYCLERVIEW_TEMPLATE,
            "RetrofitRecyclerView"
        )
        cases.add(case1)

        val case2 = Case(
            Constants.ROOM_RECYCLERVIEW_TEMPLATE,
            "RoomRecyclerView"
        )
        cases.add(case2)

        val case3 = Case(
            Constants.BOTTOM_SHEET_RECYCLERVIEW_TEMPLATE,
            "BottomSheetRecyclerView"
        )
        cases.add(case3)

        return cases
    }
}