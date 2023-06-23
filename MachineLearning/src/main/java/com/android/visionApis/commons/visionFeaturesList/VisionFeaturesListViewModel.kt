package com.android.visionApis.commons.visionFeaturesList

import androidx.lifecycle.ViewModel
import com.android.R

class VisionFeaturesListViewModel : ViewModel() {
    fun createFeaturesList(): MutableList<VisionFeatureModel> {
        val featuresList: MutableList<VisionFeatureModel> = ArrayList()

        val objectDetection = VisionFeatureModel(
            id = VisionFeatureModel.OBJECT_DETECTION_ID,
            title = "Object Detection",
            description = "Detect, track, and classify objects in real time and static images",
            background = R.drawable.background_item_object_detect
        )
        featuresList.add(objectDetection)

        val faceDetection = VisionFeatureModel(
            id = VisionFeatureModel.FACE_DETECTION_ID,
            title = "Face Detection",
            description = "Detect faces in real time and static images",
            background = R.drawable.background_item_face_detect
        )
        featuresList.add(faceDetection)

        val faceMeshDetection = VisionFeatureModel(
            id = VisionFeatureModel.OBJECT_DETECTION_ID,
            title = "Face Mesh Detection",
            description = "Detect, track, and classify objects in real time and static images",
            background = R.drawable.background_item_face_mesh_detect
        )
        featuresList.add(faceMeshDetection)

        val textRecognition = VisionFeatureModel(
            id = VisionFeatureModel.FACE_DETECTION_ID,
            title = "Text Recognition",
            description = "Recognize text in real time and static images",
            background = R.drawable.background_item_text_recognition
        )
        featuresList.add(textRecognition)

        val barcodeScanning = VisionFeatureModel(
            id = VisionFeatureModel.OBJECT_DETECTION_ID,
            title = "Barcode Scanning",
            description = "Scan barcodes in real time and static images",
            background = R.drawable.background_item_barcode_scanning
        )
        featuresList.add(barcodeScanning)

        val imageLabeling = VisionFeatureModel(
            id = VisionFeatureModel.FACE_DETECTION_ID,
            title = "Image Labeling",
            description = "Label images in real time and static images",
            background = R.drawable.background_item_image_labeling
        )
        featuresList.add(imageLabeling)

        val poseDetection = VisionFeatureModel(
            id = VisionFeatureModel.OBJECT_DETECTION_ID,
            title = "Pose Detection",
            description = " Detect the position of the human body in real time",
            background = R.drawable.background_item_pose_detect
        )
        featuresList.add(poseDetection)

        val selfieSegmentation = VisionFeatureModel(
            id = VisionFeatureModel.FACE_DETECTION_ID,
            title = "Selfie Segmentation",
            description = "Segment people from the background in real time",
            background = R.drawable.background_item_selfie_segmentation
        )
        featuresList.add(selfieSegmentation)

        return featuresList
    }
}