package com.android.apps.appLieDetector.questionpack

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.android.core.base.SharedViewModel
import com.android.databinding.FragmentLieDetectorQuestionPacksBinding
import com.prank.sounds.fake.videocall.screens.liedetector.models.QuestionPack

class LieDetectorQuestionsPacksFragment : Fragment(),
    LieDetectorQuestionPacksAdapter.QuestionPacksCallBack {

    private lateinit var binding: FragmentLieDetectorQuestionPacksBinding
    private lateinit var viewModel: LieDetectorQuestionsPacksViewModel
    private lateinit var adapter: LieDetectorQuestionPacksAdapter
    private lateinit var sharedViewModel: SharedViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[LieDetectorQuestionsPacksViewModel::class.java]
        sharedViewModel = ViewModelProvider(requireActivity())[SharedViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentLieDetectorQuestionPacksBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
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