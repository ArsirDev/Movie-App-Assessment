package com.example.movieappassessment.utils

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.preferencesDataStore
import com.example.movieappassessment.utils.LocalReference.FIRST_INSTALL
import com.example.movieappassessment.utils.LocalReference.FIRST_INSTALL_RESOURCE
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SessionManager @Inject constructor(
   context: Context
) {
    private val firstInstallInstance = context.dataStoreFirstInstall

    suspend fun setFirstInstall(state: Boolean) {
        firstInstallInstance.edit { result ->
            result[INSTALL] = state
        }
    }

    val getFirstInstall: Flow<Boolean>
        get() = firstInstallInstance.data.map { preferences ->
            preferences[INSTALL] ?: false
        }

    companion object {
        private val Context.dataStoreFirstInstall: DataStore<Preferences> by preferencesDataStore(
            name = FIRST_INSTALL_RESOURCE
        )

        val INSTALL = booleanPreferencesKey(
            name = FIRST_INSTALL
        )
    }
}