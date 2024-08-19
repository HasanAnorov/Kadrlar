package com.ierusalem.kadrlar.features.home.presentation

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.os.Environment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.compose.BackHandler
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
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
import com.ierusalem.kadrlar.core.ui.components.KadrlarDrawer
import com.ierusalem.kadrlar.core.ui.theme.KadrlarTheme
import com.ierusalem.kadrlar.core.utils.Constants
import com.ierusalem.kadrlar.core.utils.Constants.generateAppSpecificFileName
import com.ierusalem.kadrlar.core.utils.Constants.generateUniqueFileName
import com.ierusalem.kadrlar.core.utils.executeWithLifecycle
import com.ierusalem.kadrlar.core.utils.getFileNameFromUri
import com.ierusalem.kadrlar.core.utils.getFileNameWithoutExtension
import com.ierusalem.kadrlar.core.utils.log
import com.ierusalem.kadrlar.features.home.domain.HomeScreenNavigation
import com.ierusalem.kadrlar.features.home.domain.HomeViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import java.io.File
import java.io.FileOutputStream

@AndroidEntryPoint
class HomeFragment : Fragment() {

    private val viewModel: HomeViewModel by viewModels()

    private val getFilesLauncher: ActivityResultLauncher<Intent> = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        when (result.resultCode) {
            Activity.RESULT_CANCELED -> {
                log("onActivityResult: RESULT CANCELED ")
            }

            Activity.RESULT_OK -> {
                val contentResolver = activity?.contentResolver!!
                val intent: Intent = result.data!!
                val uri = intent.data!!

                val resourceDirectory = Environment.getExternalStoragePublicDirectory(
                    Environment.DIRECTORY_DOWNLOADS + "/${Constants.FOLDER_NAME_FOR_RESOURCES}"
                )!!
                if(!resourceDirectory.exists()){
                    resourceDirectory.mkdir()
                }

                val fileName = uri.getFileNameFromUri(contentResolver)
                val appSpecificFileName = generateAppSpecificFileName(fileName)
                log("app specific file name: $appSpecificFileName")
                var file = File(resourceDirectory, appSpecificFileName)
                if (file.exists()) {
                    val fileNameWithoutExt = appSpecificFileName.getFileNameWithoutExtension()
                    val uniqueFileName =
                        generateUniqueFileName(
                            resourceDirectory.toString(),
                            fileNameWithoutExt,
                            file.extension
                        )
                    file = File(uniqueFileName)
                }

                val inputStream = requireContext().contentResolver.openInputStream(uri)
                val fileOutputStream = FileOutputStream(file)
                inputStream?.copyTo(fileOutputStream)
                fileOutputStream.close()

            }
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return ComposeView(requireContext()).apply {
            setContent {

                val uiState by viewModel.state.collectAsStateWithLifecycle()
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
                    KadrlarDrawer(
                        drawerState = drawerState,
                        content = {
                            HomeUiScreen(
                                uiState = uiState,
                                eventHandler = viewModel::handleClickIntents
                            )
                        },
                        onDrawerItemClick = viewModel::handleClickIntents
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

    private fun executeNavigation(navigation: HomeScreenNavigation) {
        when (navigation) {
            HomeScreenNavigation.NavigateToSettings -> {
                findNavController().navigate(R.id.action_homeFragment_to_settingsFragment)
            }

            HomeScreenNavigation.NavigateToSupport -> {
                findNavController().navigate(R.id.action_homeFragment_to_supportFragment)
            }

            HomeScreenNavigation.SelectFile -> {
                showFileChooser()
            }
        }
    }

    private fun showFileChooser() {
        val intent = Intent()
            .setType("*/*")
            .setAction(Intent.ACTION_GET_CONTENT)
        intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true)
        intent.addCategory(Intent.CATEGORY_OPENABLE)
        intent.flags =
            Intent.FLAG_GRANT_READ_URI_PERMISSION or Intent.FLAG_GRANT_WRITE_URI_PERMISSION

        try {
            getFilesLauncher.launch(intent)
        } catch (e: Exception) {
            Toast.makeText(
                requireContext(),
                getString(R.string.please_install_a_file_manager),
                Toast.LENGTH_SHORT
            ).show()
            e.printStackTrace()
        }
    }

}