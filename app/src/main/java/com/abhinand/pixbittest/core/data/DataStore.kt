package com.abhinand.pixbittest.core.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map

class DataStore(private val context: Context) {

    companion object {
        private val Context.dataStore: DataStore<Preferences> by preferencesDataStore("user_prefs")
        val USER_TOKEN = stringPreferencesKey("user_token")
    }

    suspend fun saveToken(token: String) {
        context.dataStore.edit {
            it[USER_TOKEN] = token
        }
    }

    fun getToken() = context.dataStore.data.map {
        it[USER_TOKEN]
    }

}