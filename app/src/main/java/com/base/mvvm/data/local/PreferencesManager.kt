package com.base.mvvm.data.local

import android.content.Context
import android.content.SharedPreferences
import com.base.mvvm.App

class PreferencesManager private constructor(context: Context?) {
    private val sharedPreferences: SharedPreferences
    private val editor: SharedPreferences.Editor
    private fun setPrefString(key: String, value: String) {
        editor.putString(key, value)
        editor.apply()
    }

    private fun getPrefString(key: String, defValue: String): String {
        return sharedPreferences.getString(key, defValue)
    }

    var prefToken: String
        get() = getPrefString(PREF_TOKEN, "")
        set(token) {
            setPrefString(PREF_TOKEN, token)
        }

    var prefUsername: String
        get() = getPrefString(PREF_USERNAME, "")
        set(token) {
            setPrefString(PREF_USERNAME, token)
        }

    var prefEmail: String
        get() = getPrefString(PREF_EMAIL, "")
        set(token) {
            setPrefString(PREF_EMAIL, token)
        }

    companion object {
        var instance: PreferencesManager? = null
            get() {
                val context: Context? = App.appContext
                if (field == null) {
                    field = PreferencesManager(context)
                }
                return field
            }
            private set
        private const val SHARE_PREFERENCES = "share_preference"
        private const val PREF_USERNAME = "pref_username"
        private const val PREF_TOKEN = "pref_token"
        private const val PREF_EMAIL = "pref_email"
    }

    init {
        sharedPreferences = context!!.getSharedPreferences(SHARE_PREFERENCES, 0)
        editor = sharedPreferences.edit()
        editor.apply()
    }
}