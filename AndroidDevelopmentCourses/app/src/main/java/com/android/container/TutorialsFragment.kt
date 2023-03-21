package com.android.container

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.android.databinding.FragmentTutorialsBinding

class TutorialsFragment : Fragment() {
    private lateinit var adapter: CasesAdapter
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

        adapter = CasesAdapter(
            cases = initializeCasesList(),
            callback = object : CasesAdapter.CaseCallBack {
                override fun onCaseClicked(case: Case) {
                    when (case.id) {
                        Constants.LIFECYCLE_TUTORIAL -> {
                            Toast.makeText(context, case.text, Toast.LENGTH_LONG).show()
                        }
                        Constants.RECYCLERVIEW_TUTORIAL -> {
                            Toast.makeText(context, case.text, Toast.LENGTH_LONG).show()
                        }
                        Constants.SHARED_PREFERENCES_TUTORIAL -> {
                            Toast.makeText(context, case.text, Toast.LENGTH_LONG).show()
                        }
                        Constants.BROADCAST_RECEIVER_TUTORIAL -> {
                            Toast.makeText(context, case.text, Toast.LENGTH_LONG).show()
                        }
                        Constants.PERSISTENT_BOTTOM_SHEET_TUTORIAL -> {
                            Toast.makeText(context, case.text, Toast.LENGTH_LONG).show()
                        }
                        Constants.MODAL_BOTTOM_SHEET_DIALOG_TUTORIAL -> {
                            Toast.makeText(context, case.text, Toast.LENGTH_LONG).show()
                        }
                        Constants.MODAL_BOTTOM_SHEET_DIALOG_FRAGMENT_TUTORIAL -> {
                            Toast.makeText(context, case.text, Toast.LENGTH_LONG).show()
                        }
                    }
                }
            }
        )

        this.binding.recyclerViewTutorials.adapter = adapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initializeCasesList(): List<Case> {
        val cases = ArrayList<Case>()

        val case1 = Case(
            Constants.LIFECYCLE_TUTORIAL,
            "Lifecycle"
        )
        cases.add(case1)

        val case2 = Case(
            Constants.RECYCLERVIEW_TUTORIAL,
            "RecyclerView"
        )
        cases.add(case2)

        val case3 = Case(
            Constants.SHARED_PREFERENCES_TUTORIAL,
            "Shared Preferences"
        )
        cases.add(case3)

        val case4 = Case(
            Constants.BROADCAST_RECEIVER_TUTORIAL,
            "Broadcast Receiver"
        )
        cases.add(case4)

        val case5 = Case(
            Constants.PERSISTENT_BOTTOM_SHEET_TUTORIAL,
            "Persistent Bottom Sheet"
        )
        cases.add(case5)

        val case6 = Case(
            Constants.MODAL_BOTTOM_SHEET_DIALOG_TUTORIAL,
            "Modal Bottom Sheet Dialog"
        )
        cases.add(case6)

        val case7 = Case(
            Constants.MODAL_BOTTOM_SHEET_DIALOG_FRAGMENT_TUTORIAL,
            "Modal Bottom Sheet Dialog Fragment"
        )
        cases.add(case7)

        return cases
    }
}