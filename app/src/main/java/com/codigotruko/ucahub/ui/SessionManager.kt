package com.codigotruko.ucahub.ui

import android.content.SharedPreferences
import androidx.core.content.edit

class SessionManager(val preferences: SharedPreferences) {
    companion object {
        const val PREF_KEY_USERNAME = "username"
        const val PREF_KEY_PASSWORD = "password"
    }

    var isLoggedIn: Boolean
        get() = preferences.contains(PREF_KEY_USERNAME) && preferences.contains(PREF_KEY_PASSWORD)
        private set(value) = throw UnsupportedOperationException("Read-only property")

    fun saveCredentials(username: String, password: String) {
        preferences.edit {
            putString(PREF_KEY_USERNAME, username)
            putString(PREF_KEY_PASSWORD, password)
        }
    }

    fun clearCredentials() {
        preferences.edit {
            remove(PREF_KEY_USERNAME)
            remove(PREF_KEY_PASSWORD)
        }
    }
}