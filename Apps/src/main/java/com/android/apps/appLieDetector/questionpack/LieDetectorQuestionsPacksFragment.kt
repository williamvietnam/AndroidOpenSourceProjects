package com.android.apps.appLieDetector.questionpack

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.commons.base.BaseFragment
import com.android.databinding.FragmentLieDetectorQuestionPacksBinding
import com.prank.sounds.fake.videocall.screens.liedetector.models.QuestionPack

class LieDetectorQuestionsPacksFragment : BaseFragment<FragmentLieDetectorQuestionPacksBinding,
        LieDetectorQuestionsPacksViewModel>(),
    LieDetectorQuestionPacksAdapter.QuestionPacksCallBack {

    private lateinit var adapter: LieDetectorQuestionPacksAdapter

    override fun createViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentLieDetectorQuestionPacksBinding {
        return FragmentLieDetectorQuestionPacksBinding.inflate(inflater, container, false)
    }

    override fun createViewModel(): LieDetectorQuestionsPacksViewModel {
        return ViewModelProvider(this)[LieDetectorQuestionsPacksViewModel::class.java]
    }

    override fun initializeViews() {
        viewModel.getQuestionsPacks(requireContext())
        viewModel.data.observe(viewLifecycleOwner) {
            adapter = LieDetectorQuestionPacksAdapter(it, this)
            val dividerItemDecoration = DividerItemDecoration(
                binding.recyclerview.context,
                (binding.recyclerview.layoutManager as LinearLayoutManager).orientation
            )
            binding.recyclerview.addItemDecoration(dividerItemDecoration)
            binding.recyclerview.adapter = adapter
            if (it.size > 0) {
                viewModel.setQuestionPackName(it[0])
            }
        }
    }

    override fun initializeEvents() {
        binding.buttonClose.setOnClickListener {
            activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commitNow()
        }

        binding.buttonContinue.setOnClickListener {
            sharedViewModel.setQuestionPack(viewModel.getQuestionPackName())
            activity?.supportFragmentManager?.beginTransaction()?.remove(this)?.commitNow()
        }
    }

    override fun onQuestionPackClick(questionPack: QuestionPack) {
        viewModel.setQuestionPackName(questionPack)
    }
}