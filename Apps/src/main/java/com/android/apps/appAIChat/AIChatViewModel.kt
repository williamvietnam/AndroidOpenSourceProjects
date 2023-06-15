package com.android.apps.appAIChat

import android.content.Context
import com.android.R
import com.android.commons.base.BaseViewModel

class AIChatViewModel : BaseViewModel() {
    fun getDefaultAnswer(message: Message, context: Context): String {
        var defaultAnswer = ""
        when (message.defaultQuestion) {
            Message.DEFAULT_QUESTION_1 -> {
                defaultAnswer = context.getString(R.string.aiChatDefaultAnswer1)
            }

            Message.DEFAULT_QUESTION_2 -> {
                defaultAnswer = context.getString(R.string.aiChatDefaultAnswer2)
            }

            Message.DEFAULT_QUESTION_3 -> {
                defaultAnswer = context.getString(R.string.aiChatDefaultAnswer3)
            }

            Message.DEFAULT_QUESTION_4 -> {
                defaultAnswer = context.getString(R.string.aiChatDefaultAnswer4)
            }

            Message.DEFAULT_QUESTION_5 -> {
                defaultAnswer = context.getString(R.string.aiChatDefaultAnswer5)
            }

        }
        return defaultAnswer
    }

    fun questionsListData(): ArrayList<Message> {
        val messages = ArrayList<Message>()

        val messageQuestion1 = Message(
            message = "Who are you?",
            defaultQuestion = Message.DEFAULT_QUESTION_1
        )
        messages.add(messageQuestion1)

        val messageQuestion2 = Message(
            message = "Tell me an interesting story",
            defaultQuestion = Message.DEFAULT_QUESTION_2
        )
        messages.add(messageQuestion2)

        val messageQuestion3 = Message(
            message = "What food is good for health?",
            defaultQuestion = Message.DEFAULT_QUESTION_3
        )
        messages.add(messageQuestion3)

        val messageQuestion4 = Message(
            message = "What game genre has the most players?",
            defaultQuestion = Message.DEFAULT_QUESTION_4
        )
        messages.add(messageQuestion4)

        val messageQuestion5 = Message(
            message = "Who was the first man to go to the moon?",
            defaultQuestion = Message.DEFAULT_QUESTION_5
        )
        messages.add(messageQuestion5)

        return messages
    }
}