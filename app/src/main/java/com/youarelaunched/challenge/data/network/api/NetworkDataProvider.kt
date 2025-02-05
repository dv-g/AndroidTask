package com.youarelaunched.challenge.data.network.api

import android.content.Context
import com.google.gson.Gson
import com.youarelaunched.challenge.data.network.models.NetworkVendor
import com.youarelaunched.challenge.data.network.models.NetworkVendorsData
import com.youarelaunched.challenge.di.DispatcherIo
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class NetworkDataProvider @Inject constructor(
    @DispatcherIo private val workDispatcher: CoroutineDispatcher,
    @ApplicationContext private val appContext: Context
) : ApiVendors {

    override suspend fun getVendors(companyName : String): List<NetworkVendor> = withContext(workDispatcher) {
        val json = appContext.assets
            .open("vendors.json")
            .bufferedReader()
            .use {
                it.readText()
            }
        var result =
            Gson()
                .fromJson(json, NetworkVendorsData::class.java)
                .vendors
        if (companyName.isNotEmpty()) {
            result = result.filter { it.companyName.contains(companyName, ignoreCase = true) }
        }
        result
    }
}