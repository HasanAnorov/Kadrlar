package com.ierusalem.kadrlar.features.chat.data.remote

import com.ierusalem.kadrlar.core.utils.Resource
import com.ierusalem.kadrlar.core.utils.log
import com.ierusalem.kadrlar.features.chat.data.remote.dto.MessageDto
import com.ierusalem.kadrlar.features.chat.domain.model.Message
import io.ktor.client.HttpClient
import io.ktor.client.features.websocket.webSocketSession
import io.ktor.client.request.url
import io.ktor.http.cio.websocket.Frame
import io.ktor.http.cio.websocket.WebSocketSession
import io.ktor.http.cio.websocket.close
import io.ktor.http.cio.websocket.readText
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.filter
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.isActive
import kotlinx.serialization.decodeFromString
import kotlinx.serialization.json.Json

class ChatSocketServiceImpl(
    private val client: HttpClient
) : ChatSocketService {

    private var socket: WebSocketSession? = null

    override suspend fun initSession(userId: Int): Resource<Unit> {
        log("initSession, userId: $userId")
        return try {
//            val sd = client.webSocket("${ChatSocketService.Endpoints.ChatSocket.url}$userId/", request = {
//                header(HttpHeaders.Origin, "http://213.230.126.222:9998/")
//            }) {
//
//            }
            socket = client.webSocketSession {
                url("${ChatSocketService.Endpoints.ChatSocket.url}$userId/")
            }
            if (socket?.isActive == true) {
                Resource.Success(Unit)
            } else {
                Resource.Error("Couldn't establish a connection")
            }
        } catch (e: Exception) {
            e.printStackTrace()
            Resource.Error(e.localizedMessage ?: "Unknown error")
        }
    }


    override suspend fun sendMessages(message: String) {
        try {
            socket?.send(Frame.Text(message))
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    override fun observeMessages(): Flow<Message> {
        return try {
            socket?.incoming
                ?.receiveAsFlow()
                ?.filter { it is Frame.Text }
                ?.map {
                    val json = (it as? Frame.Text)?.readText() ?: ""
                    val messageDto = Json.decodeFromString<MessageDto>(json)
                    messageDto.toMessage()
                } ?: flow { }

        } catch (e: Exception) {
            e.printStackTrace()
            flow { }
        }
    }

    override suspend fun closeSession() {
        socket?.close()
    }
}