package com.ierusalem.kadrlar.core.app

import android.app.Application
import android.app.LocaleManager
import android.app.UiModeManager
import android.os.Build
import android.os.LocaleList
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.os.LocaleListCompat
import com.ierusalem.kadrlar.core.preferences.DataStorePreferenceRepository
import com.ierusalem.kadrlar.core.utils.log
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.DelicateCoroutinesApi
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltAndroidApp
class KadrlarApp : Application() {

    @Inject
    lateinit var dataStorePreferenceRepository: DataStorePreferenceRepository

    companion object {
        var applicationScope = MainScope()
    }

    @OptIn(DelicateCoroutinesApi::class)
    override fun onCreate() {
        super.onCreate()

        GlobalScope.launch(Dispatchers.IO) {
            dataStorePreferenceRepository.getTheme.collect { isSystemInDarkMode ->
                if (isSystemInDarkMode) {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        applicationContext.getSystemService(UiModeManager::class.java)
                            .setApplicationNightMode(UiModeManager.MODE_NIGHT_YES)
                    } else {
                        log("setting dark mode")
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
                    }
                } else {
                    if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                        applicationContext.getSystemService(UiModeManager::class.java)
                            .setApplicationNightMode(UiModeManager.MODE_NIGHT_NO)
                    } else {
                        log("setting light mode")
                        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
                    }
                }
            }
        }

        applicationScope.launch {
            dataStorePreferenceRepository.getLanguage.collect { languageCode ->
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    applicationContext.getSystemService(LocaleManager::class.java).applicationLocales =
                        LocaleList.forLanguageTags(languageCode)
                } else {
                    AppCompatDelegate.setApplicationLocales(
                        LocaleListCompat.forLanguageTags(languageCode)
                    )
                }
            }
        }
    }

    override fun onLowMemory() {
        super.onLowMemory()
        applicationScope.cancel("onLowMemory() called by system")
        applicationScope = MainScope()
    }

}