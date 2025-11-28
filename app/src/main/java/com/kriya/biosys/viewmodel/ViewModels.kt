package com.kriya.biosys.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.kriya.biosys.data.LocalJsonRepository
import com.kriya.biosys.model.*
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

open class BaseViewModel(application: Application) : AndroidViewModel(application) {
    protected val repository = LocalJsonRepository(application)
}

class HomeViewModel(application: Application) : BaseViewModel(application) {
    val contactInfo: ContactInfo = repository.getContactInfo()
}

class ProductsViewModel(application: Application) : BaseViewModel(application) {
    private val _products = MutableStateFlow<List<Product>>(emptyList())
    val products: StateFlow<List<Product>> = _products

    init {
        viewModelScope.launch {
            _products.value = repository.getProducts()
        }
    }
}

class TechnologyViewModel(application: Application) : BaseViewModel(application) {
    private val _techItems = MutableStateFlow<List<TechnologyEntry>>(emptyList())
    val techItems: StateFlow<List<TechnologyEntry>> = _techItems

    init {
        viewModelScope.launch {
            _techItems.value = repository.getTechnology()
        }
    }
}

class TeamViewModel(application: Application) : BaseViewModel(application) {
    private val _team = MutableStateFlow<List<TeamMember>>(emptyList())
    val team: StateFlow<List<TeamMember>> = _team

    init {
        viewModelScope.launch {
            _team.value = repository.getTeam()
        }
    }
}

class CSRViewModel(application: Application) : BaseViewModel(application) {
    private val _activities = MutableStateFlow<List<CSRActivity>>(emptyList())
    val activities: StateFlow<List<CSRActivity>> = _activities

    init {
        viewModelScope.launch {
            _activities.value = repository.getCsr()
        }
    }
}

class GalleryViewModel(application: Application) : BaseViewModel(application) {
    private val _gallery = MutableStateFlow<GalleryData?>(null)
    val gallery: StateFlow<GalleryData?> = _gallery

    init {
        viewModelScope.launch {
            _gallery.value = repository.getGallery()
        }
    }
}

class DocumentsViewModel(application: Application) : BaseViewModel(application) {
    private val _documents = MutableStateFlow<List<DocumentItem>>(emptyList())
    val documents: StateFlow<List<DocumentItem>> = _documents

    init {
        viewModelScope.launch {
            _documents.value = repository.getDocuments()
        }
    }
}

class ContactViewModel(application: Application) : BaseViewModel(application) {
    val contactInfo: ContactInfo = repository.getContactInfo()
}
