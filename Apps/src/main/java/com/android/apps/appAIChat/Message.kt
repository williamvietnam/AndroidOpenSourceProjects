package com.android.apps.appAIChat

data class Message(
    var message: String? = null,
    var sentBy: String? = null,
    var defaultQuestion: String? = null,
) {
    companion object {
        const val SENT_BY_ME = "me"
        const val SENT_BY_BOT = "bot"

        const val DEFAULT_QUESTION_1 = "default_question_1"
        const val DEFAULT_QUESTION_2 = "default_question_2"
        const val DEFAULT_QUESTION_3 = "default_question_3"
        const val DEFAULT_QUESTION_4 = "default_question_4"
        const val DEFAULT_QUESTION_5 = "default_question_5"
    }
}