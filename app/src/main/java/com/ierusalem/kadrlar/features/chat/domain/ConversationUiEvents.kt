package com.ierusalem.kadrlar.features.chat.domain

sealed interface ConversationUiEvents {
    data object NavIconClick : ConversationUiEvents
    data class OnMessageSent(val message: String) : ConversationUiEvents
}