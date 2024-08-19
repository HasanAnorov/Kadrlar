package com.ierusalem.kadrlar.features.chat.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.ierusalem.kadrlar.core.ui.theme.KadrlarTheme
import com.ierusalem.kadrlar.core.utils.executeWithLifecycle
import com.ierusalem.kadrlar.features.chat.data.exampleUiState
import com.ierusalem.kadrlar.features.chat.domain.ConversationNavigation
import com.ierusalem.kadrlar.features.chat.domain.ConversationViewModel

class SupportFragment : Fragment() {

    private val viewModel: ConversationViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View = ComposeView(inflater.context).apply {
        setContent {
            KadrlarTheme {
                ConversationContent(
                    uiState = exampleUiState,
                    eventHandler = viewModel::handleEvents
                )
            }
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.screenNavigation.executeWithLifecycle(
            lifecycle = viewLifecycleOwner.lifecycle,
            action = ::executeNavigation
        )
    }

    private fun executeNavigation(navigation: ConversationNavigation) {
        when (navigation) {
            ConversationNavigation.NavIconClick -> {
                findNavController().popBackStack()
            }
        }
    }

}