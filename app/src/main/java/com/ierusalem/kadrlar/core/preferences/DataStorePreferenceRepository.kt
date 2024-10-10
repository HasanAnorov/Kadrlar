package com.ierusalem.kadrlar.core.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import com.ierusalem.kadrlar.core.utils.Constants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class DataStorePreferenceRepository(context: Context) {

    private val Context.dataStore by preferencesDataStore(Constants.DATA_STORE_NAME)
    private val dataStore: DataStore<Preferences> = context.dataStore
    private val defaultLanguage = Constants.DEFAULT_LOCALE
    private val defaultTheme = Constants.DEFAULT_THEME
    private val defaultAccessToken = Constants.DEFAULT_ACCESS_TOKEN
    private val defaultRefreshToken = Constants.DEFAULT_REFRESH_TOKEN

    companion object {
        val PREF_LANGUAGE = stringPreferencesKey(name = Constants.PREFERENCE_LANGUAGE)
        val PREF_THEME = booleanPreferencesKey(name = Constants.PREFERENCE_THEME)
        val PREF_USER_ID = intPreferencesKey(name = Constants.PREFERENCE_USER_ID)
        val PREF_ACCESS_TOKEN = stringPreferencesKey(name = Constants.PREFERENCE_ACCESS_TOKEN)
        val PREF_REFRESH_TOKEN = stringPreferencesKey(name = Constants.PREFERENCE_REFRESH_TOKEN)

        private var INSTANCE: DataStorePreferenceRepository? = null

        fun getInstance(context: Context): DataStorePreferenceRepository {
            return INSTANCE ?: synchronized(this) {
                INSTANCE?.let {
                    return it
                }
                val instance = DataStorePreferenceRepository(context)
                INSTANCE = instance
                instance
            }
        }
    }

    suspend fun saveUserId(userId: Int){
        dataStore.edit { preferences ->
            preferences[PREF_USER_ID] = userId
        }
    }

    val getUserId: Flow<Int> = dataStore.data
        .map { preferences ->
            preferences[PREF_USER_ID] ?: -1
        }

    suspend fun saveAccessToken(accessToken: String) {
        dataStore.edit { preferences ->
            preferences[PREF_ACCESS_TOKEN] = accessToken
        }
    }

    val getAccessToken: Flow<String> = dataStore.data
        .map { preferences ->
            preferences[PREF_ACCESS_TOKEN] ?: defaultAccessToken
        }

    suspend fun saveRefreshToken(refreshToken: String) {
        dataStore.edit { preferences ->
            preferences[PREF_REFRESH_TOKEN] = refreshToken
        }
    }

    val getRefreshToken: Flow<String> = dataStore.data
        .map { preferences ->
            preferences[PREF_REFRESH_TOKEN] ?: defaultRefreshToken
        }


    suspend fun setTheme(isSystemInDarkMode: Boolean) {
        dataStore.edit { preferences ->
            preferences[PREF_THEME] = isSystemInDarkMode
        }
    }

    val getTheme: Flow<Boolean> = dataStore.data
        .map { preferences ->
            preferences[PREF_THEME] ?: defaultTheme
        }

    suspend fun setLanguage(language: String) {
        dataStore.edit { preferences ->
            preferences[PREF_LANGUAGE] = language
        }
    }

    val getLanguage: Flow<String> = dataStore.data
        .map { preferences ->
            preferences[PREF_LANGUAGE] ?: defaultLanguage
        }

}