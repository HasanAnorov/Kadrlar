package com.ierusalem.kadrlar.features.user.diploma.presentation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.ComposeView
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.setFragmentResult
import androidx.fragment.app.viewModels
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.fragment.findNavController
import com.google.gson.Gson
import com.ierusalem.kadrlar.R
import com.ierusalem.kadrlar.core.ui.theme.KadrlarTheme
import com.ierusalem.kadrlar.core.utils.Constants
import com.ierusalem.kadrlar.core.utils.executeWithLifecycle
import com.ierusalem.kadrlar.features.user.diploma.domain.DiplomaScreenNavigation
import com.ierusalem.kadrlar.features.user.diploma.domain.DiplomaViewModel

class DiplomaFragment : Fragment() {

    private val viewModel: DiplomaViewModel by viewModels()

    //gson to convert message object to string
    private lateinit var gson: Gson

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        gson = Gson()
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {
                val uiState by viewModel.state.collectAsStateWithLifecycle()

                KadrlarTheme {
                    DiplomaUiScreen(
                        eventHandler = viewModel::handleClickIntents,
                        uiState = uiState
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

    private fun executeNavigation(navigation: DiplomaScreenNavigation) {
        when (navigation) {
            DiplomaScreenNavigation.NavigateToHome -> {
                findNavController().popBackStack()
            }

            is DiplomaScreenNavigation.NavigateToHomeWithDiploma -> {
                val diplomaStringForm = gson.toJson(navigation.diploma)
                setFragmentResult(
                    Constants.DIPLOMA_REQUEST_KEY,
                    bundleOf(Constants.DIPLOMA_BUNDLE_KEY to diplomaStringForm)
                )
                findNavController().popBackStack()
            }

            DiplomaScreenNavigation.HasEmptyFields -> {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.please_fill_the_fields_to_save_diploma), Toast.LENGTH_SHORT
                ).show()
            }
        }
    }


}