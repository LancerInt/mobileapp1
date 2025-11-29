package com.kriya.biosys.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.kriya.biosys.model.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader

class LocalJsonRepository(private val context: Context, private val gson: Gson = Gson()) {

    private suspend inline fun <reified T> loadJson(fileName: String): T = withContext(Dispatchers.IO) {
        val assetManager = context.assets
        assetManager.open(fileName).use { inputStream ->
            BufferedReader(InputStreamReader(inputStream)).use { reader ->
                val json = reader.readText()
                return@withContext gson.fromJson(json, object : TypeToken<T>() {}.type)
            }
        }
    }

    suspend fun getProducts(): List<Product> = loadJson("products.json")
    suspend fun getTechnology(): List<TechnologyEntry> = loadJson("technology.json")
    suspend fun getTeam(): List<TeamMember> = loadJson("team.json")
    suspend fun getCsr(): List<CSRActivity> = loadJson("csr.json")
    suspend fun getGallery(): GalleryData = loadJson("gallery.json")
    suspend fun getDocuments(): List<DocumentItem> = loadJson("documents.json")

    // Contact info can be static or also loaded via JSON if preferred
    fun getContactInfo(): ContactInfo = ContactInfo(
        corporateAddress = "Kriya Biosys Pvt. Ltd., A-26, Sector 83, Noida, Uttar Pradesh 201305",
        factoryAddresses = listOf(
            "Integrated Fermentation Facility: Malur, Bengaluru Rural, Karnataka",
            "Processing & Packing: Sriperumbudur Industrial Area, Tamil Nadu"
        ),
        phones = listOf("+91 98804 10077", "+91 99102 13618"),
        emails = listOf("info@kriyabiosys.com", "sales@kriyabiosys.com"),
        qrImageName = "ic_map_qr"
    )
}
