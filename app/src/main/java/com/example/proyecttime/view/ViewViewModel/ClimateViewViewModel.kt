package com.example.proyecttime.view.ViewViewModel

import DataClimate
import Main
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proyecttime.domian.GetClimateUseCase
import kotlinx.coroutines.launch

class ClimateViewViewModel : ViewModel() {

    var getClimateUseCase = GetClimateUseCase()

    val climateModel = MutableLiveData<DataClimate>()
    val climateModelMain = MutableLiveData<Main>()

    val isLoading = MutableLiveData<Boolean>()

    fun onCreate(lat: Double, lon: Double) {
        viewModelScope.launch {
            isLoading.postValue(true)
            val result = getClimateUseCase(lat, lon)
            climateModel.postValue(result)
            isLoading.postValue(false)
        }

    }
}