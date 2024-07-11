package com.ierusalem.kadrlar.features.home.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.compose.BackHandler
import androidx.compose.material3.DrawerValue
import androidx.compose.material3.rememberDrawerState
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.ierusalem.kadrlar.core.ui.theme.KadrlarTheme
import com.ierusalem.kadrlar.features.home.domain.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {

                val uiState by viewModel.state.collectAsStateWithLifecycle()
                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                val drawerOpen by viewModel.drawerShouldBeOpened
                    .collectAsStateWithLifecycle()
                val scope = rememberCoroutineScope()

                if (drawerOpen) {
                    // Open drawer and reset state in VM.
                    LaunchedEffect(Unit) {
                        // wrap in try-finally to handle interruption whiles opening drawer
                        try {
                            drawerState.open()
                        } finally {
                            viewModel.resetOpenDrawerAction()
                        }
                    }
                }

                // Intercepts back navigation when the drawer is open
                if (drawerState.isOpen) {
                    BackHandler {
                        scope.launch {
                            drawerState.close()
                        }
                    }
                }

                KadrlarTheme {
                    HomeUiScreen(
                        uiState = uiState,
                        eventHandler = viewModel::handleClickIntents
                    )
                }
            }
        }
    }
}