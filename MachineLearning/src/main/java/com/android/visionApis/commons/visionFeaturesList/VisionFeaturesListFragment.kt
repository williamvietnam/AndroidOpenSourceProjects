package com.android.visionApis.commons.visionFeaturesList

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.android.R
import com.android.databinding.FragmentVisionFeaturesListBinding

class VisionFeaturesListFragment : Fragment(), VisionFeaturesAdapter.IVisionFeaturesCallBack {
    private lateinit var binding: FragmentVisionFeaturesListBinding
    private lateinit var viewModel: VisionFeaturesListViewModel
    private lateinit var adapter: VisionFeaturesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel = ViewModelProvider(this)[VisionFeaturesListViewModel::class.java]
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentVisionFeaturesListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = VisionFeaturesAdapter(viewModel.createFeaturesList(), this)
        binding.recyclerview.adapter = adapter
    }

    override fun onFeatureClick(feature: VisionFeatureModel) {
        when (feature.id) {
            VisionFeatureModel.OBJECT_DETECTION_ID -> {
                findNavController().navigate(R.id.actionFeaturesListToObjectDetection)
            }

            VisionFeatureModel.FACE_DETECTION_ID -> {
                findNavController().navigate(R.id.actionFeaturesListToFaceDetection)
            }

            VisionFeatureModel.FACE_MESH_DETECTION_ID -> {
                findNavController().navigate(R.id.actionFeaturesListToFaceMeshDetection)
            }

            VisionFeatureModel.TEXT_RECOGNITION_ID -> {
                findNavController().navigate(R.id.actionFeaturesListToTextRecognition)
            }

            VisionFeatureModel.BARCODE_DETECTION_ID -> {
                findNavController().navigate(R.id.actionFeaturesListToBarcodeScanning)
            }

            VisionFeatureModel.IMAGE_LABELING_ID -> {
                findNavController().navigate(R.id.actionFeaturesListToImageLabeling)
            }

            VisionFeatureModel.POSE_DETECTION_ID -> {
                findNavController().navigate(R.id.actionFeaturesListToPoseDetection)
            }

            VisionFeatureModel.SELFIE_SEGMENTATION_ID -> {
                findNavController().navigate(R.id.actionFeaturesListToSelfieSegmentation)
            }
        }
    }
}