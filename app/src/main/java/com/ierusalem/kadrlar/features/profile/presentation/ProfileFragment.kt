package com.ierusalem.kadrlar.features.profile.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.fragment.findNavController
import com.ierusalem.kadrlar.R
import com.ierusalem.kadrlar.core.ui.theme.KadrlarTheme
import com.ierusalem.kadrlar.core.utils.executeWithLifecycle
import com.ierusalem.kadrlar.core.utils.shortToast
import com.ierusalem.kadrlar.features.profile.domain.ProfileScreenNavigation
import com.ierusalem.kadrlar.features.profile.domain.ProfileViewModel
import com.ierusalem.kadrlar.features.super_user.home.domain.HomeScreenNavigation

class ProfileFragment : Fragment() {

    private val viewModel: ProfileViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val uiState by viewModel.state.collectAsStateWithLifecycle()

                KadrlarTheme {
                    ProfileUiScreen(
                        uiState = uiState,
                        eventHandler = viewModel::handleClickIntents
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

    private fun executeNavigation(navigation: ProfileScreenNavigation) {
        when (navigation) {
            ProfileScreenNavigation.OnNavIconClicked -> {
                findNavController().popBackStack()
            }
            ProfileScreenNavigation.OnFailure -> {

            }
        }
    }


}