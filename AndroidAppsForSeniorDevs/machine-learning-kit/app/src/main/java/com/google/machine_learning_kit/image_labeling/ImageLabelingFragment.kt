package com.google.machine_learning_kit.image_labeling

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.machine_learning_kit.databinding.FragmentImageLabelingBinding
import com.google.mlkit.vision.common.InputImage
import com.google.mlkit.vision.label.ImageLabeling
import com.google.mlkit.vision.label.defaults.ImageLabelerOptions

class ImageLabelingFragment : Fragment() {
    private var _binding: FragmentImageLabelingBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentImageLabelingBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun labelImages(image: InputImage) {
        val options = ImageLabelerOptions.Builder()
            .setConfidenceThreshold(0.8f)
            .build()

        val labeler = ImageLabeling.getClient(options)

        // [START run_detector]
        val result = labeler.process(image)
            .addOnSuccessListener { labels ->
                // Task completed successfully
                // [START_EXCLUDE]
                // [START get_labels]
                for (label in labels) {
                    val text = label.text
                    val confidence = label.confidence
                }
                // [END get_labels]
                // [END_EXCLUDE]
            }
            .addOnFailureListener { e ->
                // Task failed with an exception
                // ...
            }
        // [END run_detector]
    }

    private fun configureAndRunImageLabeler(image: InputImage) {
        // [START on_device_image_labeler]
        // To use default options:
        val labeler = ImageLabeling.getClient(ImageLabelerOptions.DEFAULT_OPTIONS)

        // Or, to set the minimum confidence required:
        // val options = ImageLabelerOptions.Builder()
        //     .setConfidenceThreshold(0.7f)
        //     .build()
        // val labeler = ImageLabeling.getClient(options)

        // [END on_device_image_labeler]

        // Process image with custom onSuccess() example
        // [START process_image]
        labeler.process(image)
            .addOnSuccessListener { labels ->
                // Task completed successfully
                // ...
            }
            .addOnFailureListener { e ->
                // Task failed with an exception
                // ...
            }
        // [END process_image]

        // Process image with example onSuccess()
        labeler.process(image)
            .addOnSuccessListener { labels ->
                // [START get_image_label_info]
                for (label in labels) {
                    val text = label.text
                    val confidence = label.confidence
                    val index = label.index
                }
                // [END get_image_label_info]
            }
    }
}