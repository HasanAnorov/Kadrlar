package com.ierusalem.kadrlar.features.chat.domain

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.ierusalem.androchat.core.ui.navigation.DefaultNavigationEventDelegate
import com.ierusalem.androchat.core.ui.navigation.emitNavigation
import com.ierusalem.kadrlar.core.preferences.DataStorePreferenceRepository
import com.ierusalem.kadrlar.core.ui.navigation.NavigationEventDelegate
import com.ierusalem.kadrlar.core.utils.Constants
import com.ierusalem.kadrlar.core.utils.Resource
import com.ierusalem.kadrlar.core.utils.log
import com.ierusalem.kadrlar.features.chat.data.remote.ChatSocketService
import com.ierusalem.kadrlar.features.chat.data.remote.ChatsRequestBody
import com.ierusalem.kadrlar.features.chat.data.remote.MessagesRepository
import com.ierusalem.kadrlar.features.chat.domain.model.Message
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import javax.inject.Inject

@HiltViewModel
class ConversationViewModel
@Inject constructor(
    private val messagesRepository: MessagesRepository,
    private val chatSocketService: ChatSocketService,
    private val dataStorePreferenceRepository: DataStorePreferenceRepository
) : ViewModel(),
    NavigationEventDelegate<ConversationNavigation> by DefaultNavigationEventDelegate() {

    private val _state: MutableStateFlow<ConversationState> = MutableStateFlow(ConversationState())
    val state = _state.asStateFlow()

    fun handleEvents(events: ConversationUiEvents) {
        when (events) {
            ConversationUiEvents.NavIconClick -> {
                emitNavigation(ConversationNavigation.NavIconClick)
            }

            is ConversationUiEvents.OnMessageSent -> {
                sendMessage(events.message)
            }
        }
    }

    fun connectToChat() {
        log("connectToChat")
        val chatId = getAllMessagesWithChatId()
        log("chatId - $chatId")
        if (chatId != -1) {
            viewModelScope.launch {
                when (val result = chatSocketService.initSession(chatId)) {
                    is Resource.Success -> {
                        log("success")
                        chatSocketService.observeMessages()
                            .onEach { message ->
                                val newList = state.value.messages.toMutableList().apply {
                                    add(0, message)
                                }
                                _state.value = state.value.copy(
                                    messages = newList
                                )
                            }.launchIn(viewModelScope)
                    }

                    is Resource.Error -> {
                        //error occurred, show error message or dialog
                        log("error ${result.message}")
                    }
                }
            }
        } else {
            //error occurred, show error message or dialog
            log("error chat id is $chatId")
        }
    }

    private fun disconnect() {
        viewModelScope.launch {
            chatSocketService.closeSession()
        }
    }

    private fun getAllMessagesWithChatId(): Int {
        _state.value = _state.value.copy(
            isLoading = true
        )

//        val accessToken = runBlocking {
//            dataStorePreferenceRepository.getAccessToken.first()
//        }
        val token = Constants.TEMP_TOKEN

        val chatsResponse = runBlocking {
            messagesRepository.getAllMessagesWithChatId(
                accessToken = token,
                body = ChatsRequestBody(
                    user = 2,
                    admin = 1
                )
            )
        }
        log("chatsResponse $chatsResponse")

        return if (chatsResponse.isSuccessful) {
            _state.value = _state.value.copy(
                messages = chatsResponse.body()!!.messages.map { it.toChatMessage() },
                isLoading = false
            )
            chatsResponse.body()!!.id
        } else {
            log("some thing went wrong")
            -1
        }
    }

    private fun sendMessage(message: String) {
        viewModelScope.launch {
            chatSocketService.sendMessages(message = message)
        }
    }

    override fun onCleared() {
        super.onCleared()
        disconnect()
    }


}

data class ConversationState(
    val messages: List<Message> = emptyList(),
    val isLoading: Boolean = false
)