package com.mumbicodes.local.di

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore
import com.mumbicodes.local.preferences.RecentSearchesPreferences
import com.mumbicodes.local.preferences.RecentSearchesPreferencesImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object LocalModule {

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "searches")

    @Provides
    fun providesDataStore(@ApplicationContext context: Context) = context.dataStore

    @Provides
    fun providesRecentSearchesPreferences(dataStore: DataStore<Preferences>): RecentSearchesPreferences =
        RecentSearchesPreferencesImpl(dataStore)
}