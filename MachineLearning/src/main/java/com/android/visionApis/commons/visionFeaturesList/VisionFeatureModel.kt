package com.android.visionApis.commons.visionFeaturesList

data class VisionFeatureModel(
    var id: String,

    var title: String,

    var description: String,

    var background: Int
) {
    companion object {
        const val OBJECT_DETECTION_ID = "object.detection.id"
        const val FACE_DETECTION_ID = "face.detection.id"
        const val FACE_MESH_DETECTION_ID = "face.mesh.detection.id"
        const val TEXT_RECOGNITION_ID = "text.recognition.id"
        const val BARCODE_DETECTION_ID = "barcode.detection.id"
        const val IMAGE_LABELING_ID = "image.labeling.id"
        const val POSE_DETECTION_ID = "pose.detection.id"
        const val SELFIE_SEGMENTATION_ID = "selfie.segmentation.id"
    }
}