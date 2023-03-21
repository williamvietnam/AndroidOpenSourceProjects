package com.android.container

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.android.databinding.FragmentLibrariesBinding

class LibrariesFragment : Fragment() {
    private lateinit var adapter: CasesAdapter

    private var _binding: FragmentLibrariesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentLibrariesBinding.inflate(inflater, container, false)
        return this.binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        this.adapter = CasesAdapter(
            cases = initializeCasesData(),
            callback = object : CasesAdapter.CaseCallBack {
                override fun onCaseClicked(case: Case) {
                    when (case.id) {
                        Constants.ANIMATED_BOTTOM_BAR -> {

                        }

                        Constants.CORNER_CUT_LINEAR_LAYOUT -> {

                        }

                        Constants.DECORATOR -> {

                        }

                        Constants.DETERMINATE_PROGRESS_VIEW -> {

                        }

                        Constants.LIGHT_PROGRESS -> {

                        }

                        Constants.MOTION_TOAST -> {

                        }

                        Constants.RATE_BOTTOM_SHEET -> {

                        }
                    }
                }
            }
        )

        this.binding.recyclerViewLibraries.adapter = adapter
    }

    private fun initializeCasesData(): List<Case> {
        val cases = ArrayList<Case>()

        val case1 = Case(
            id = Constants.ANIMATED_BOTTOM_BAR,
            text = "AnimatedBottomBar"
        )
        cases.add(case1)

        val case2 = Case(
            id = Constants.CORNER_CUT_LINEAR_LAYOUT,
            text = "CornerCutLinearLayout"
        )
        cases.add(case2)

        val case3 = Case(
            id = Constants.DECORATOR,
            text = "Decorator"
        )
        cases.add(case3)

        val case4 = Case(
            id = Constants.DETERMINATE_PROGRESS_VIEW,
            text = "DeterminateProgressView"
        )
        cases.add(case4)

        val case5 = Case(
            id = Constants.LIGHT_PROGRESS,
            text = "LightProgress"
        )
        cases.add(case5)

        val case6 = Case(
            id = Constants.MOTION_TOAST,
            text = "MotionToast"
        )
        cases.add(case6)

        val case7 = Case(
            id = Constants.RATE_BOTTOM_SHEET,
            text = "RateBottomSheet"
        )
        cases.add(case7)

        return cases
    }
}