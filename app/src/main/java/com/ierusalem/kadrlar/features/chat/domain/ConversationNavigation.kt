package com.ierusalem.kadrlar.features.chat.domain

sealed interface ConversationNavigation {
    data object NavIconClick: ConversationNavigation
}