package com.ierusalem.kadrlar.features.super_user.home.presentation

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
import androidx.navigation.fragment.findNavController
import com.ierusalem.kadrlar.R
import com.ierusalem.kadrlar.core.ui.components.KadrlarHostDrawer
import com.ierusalem.kadrlar.core.ui.theme.KadrlarTheme
import com.ierusalem.kadrlar.core.utils.executeWithLifecycle
import com.ierusalem.kadrlar.core.utils.shortToast
import com.ierusalem.kadrlar.features.super_user.home.domain.SuperHomeScreenNavigation
import com.ierusalem.kadrlar.features.super_user.home.domain.SuperHomeViewModel
import kotlinx.coroutines.launch

class SuperHomeFragment : Fragment() {

    private val viewModel: SuperHomeViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val uiState = viewModel.state.collectAsStateWithLifecycle()

                val drawerState = rememberDrawerState(initialValue = DrawerValue.Closed)
                val drawerOpen by viewModel.drawerShouldBeOpened.collectAsStateWithLifecycle()
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
                    KadrlarHostDrawer(
                        drawerState = drawerState,
                        onDrawerItemClick = viewModel::handleClickIntents,
                        content = {
                            SuperHomeUiScreen(
                                uiState = uiState.value,
                                eventHandler = viewModel::handleClickIntents
                            )
                        }
                    )
                }
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

        private fun executeNavigation(navigation: SuperHomeScreenNavigation) {
            when (navigation) {
                SuperHomeScreenNavigation.OnFailure -> {
                    shortToast(getString(R.string.something_went_wrong_please_try_again_later))
                }
                SuperHomeScreenNavigation.OnProfileClicked -> {
                    findNavController().navigate(R.id.action_homeFragment2_to_profileFragment)
                }
                SuperHomeScreenNavigation.OnSettingsClicked -> {
                    findNavController().navigate(R.id.action_homeFragment2_to_settingsFragment)
                }
            }
        }

    }