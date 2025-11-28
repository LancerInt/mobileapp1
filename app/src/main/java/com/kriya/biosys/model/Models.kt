package com.kriya.biosys.model

import com.google.gson.annotations.SerializedName

// Data classes representing local JSON structures

data class Product(
    val id: String,
    val name: String,
    val category: String,
    val shortDescription: String,
    val fullDescription: String,
    val applications: List<String>,
    val specs: Map<String, String>,
    val imageName: String
)

data class TechnologyEntry(
    val id: String,
    val title: String,
    val summary: String,
    val details: String,
    val highlights: List<String>,
    val imageName: String,
    val type: String // e.g., "Karyo" or "Wynn"
)

data class TeamMember(
    val id: String,
    val name: String,
    val role: String,
    val photoName: String,
    val bio: String
)

data class CSRActivity(
    val id: String,
    val title: String,
    val date: String,
    val description: String,
    val imageNames: List<String>,
    val year: Int
)

data class GalleryVideo(
    val id: String,
    val title: String,
    val videoResName: String
)

data class GalleryImage(
    val id: String,
    val title: String,
    val imageName: String
)

data class GalleryData(
    val factoryVideos: List<GalleryVideo>,
    val factoryPhotos: List<GalleryImage>,
    val productPhotos: List<GalleryImage>,
    val dispatchPhotos: List<GalleryImage>
)

data class DocumentItem(
    val id: String,
    val title: String,
    val type: String,
    val fileName: String,
    val description: String
)

data class ContactInfo(
    val corporateAddress: String,
    val factoryAddresses: List<String>,
    val phones: List<String>,
    val emails: List<String>,
    val qrImageName: String? = null
)
