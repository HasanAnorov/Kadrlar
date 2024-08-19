package com.ierusalem.kadrlar.core.utils

import com.ierusalem.kadrlar.core.app.AppLanguage
import java.io.File

object Constants {

    const val DATA_STORE_NAME = "AppDataStore"

    const val FOLDER_NAME_FOR_RESOURCES = "Kadrlar"
    private const val FILE_NAME_FOR_RESOURCES = "_kadrlar"

    const val BASE_URL = "https://kadr.samduuf.uz/"

    const val PREFERENCE_LANGUAGE = "device_language"
    const val PREFERENCE_THEME = "device_theme"
    const val PREFERENCE_ACCESS_TOKEN = "device_access_token"
    const val PREFERENCE_REFRESH_TOKEN = "device_refresh_token"

    private const val ENGLISH_LOCALE = "en"
    private const val RUSSIAN_LOCALE = "ru"

    const val DEFAULT_THEME = false
    const val DEFAULT_LOCALE = RUSSIAN_LOCALE
    const val DEFAULT_ACCESS_TOKEN = ""
    const val DEFAULT_REFRESH_TOKEN = ""

    const val MINIMUM_LOGIN_LENGTH = 3

    fun getLanguageCode(language: AppLanguage): String {
        return when (language) {
            AppLanguage.English -> ENGLISH_LOCALE
            AppLanguage.Russian -> RUSSIAN_LOCALE
        }
    }

    fun getLanguageFromCode(languageCode: String): AppLanguage {
        return when (languageCode) {
            ENGLISH_LOCALE -> AppLanguage.English
            RUSSIAN_LOCALE -> AppLanguage.Russian
            else -> AppLanguage.Russian
        }
    }

    fun generateUniqueFileName(directory: String, baseFileName: String, extension: String): String {
        var count = 0
        var newFileName: String
        val baseNameWithExtension = "$baseFileName.$extension"

        do {
            count++
            newFileName = if (count == 1) {
                "$directory/$baseNameWithExtension"
            } else {
                "$directory/$baseFileName($count).$extension"
            }
        } while (File(newFileName).exists())

        return newFileName
    }

    fun generateAppSpecificFileName(fileName: String): String {
        val fileNameWithoutExt = fileName.getFileNameWithoutExtension()
        val fileExtension = fileName.getExtensionFromFilename()
        val uniqueFileName = "$fileNameWithoutExt$FILE_NAME_FOR_RESOURCES.$fileExtension"
        return uniqueFileName
    }

}