package com.example.amphibians.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.example.amphibians.data.AmphibianRepository
import com.example.amphibians.data.Resource
import kotlinx.coroutines.Dispatchers

class AmphibianViewModel(private val repository: AmphibianRepository) : ViewModel() {

    val amphibians = liveData(Dispatchers.IO) {
        emit(Resource.Loading(data = null))
        try {
            val data = repository.getAmphibians()
            emit(Resource.Success(data = data))
        } catch (exception: Exception) {
            emit(Resource.Error(data = null, message = exception.message ?: "Error Occurred!"))
        }
    }
}
