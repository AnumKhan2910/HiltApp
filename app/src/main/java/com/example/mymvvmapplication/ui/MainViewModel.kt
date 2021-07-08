package com.example.mymvvmapplication.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.mymvvmapplication.data.remote.NetworkCallListener
import com.example.mymvvmapplication.data.repository.CovidDataRepository
import com.example.mymvvmapplication.model.CovidDataModel
import dagger.hilt.android.lifecycle.HiltViewModel
import retrofit2.Response
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(var covidDataRepository: CovidDataRepository) : ViewModel() {

    private var _uiState = MutableLiveData<UIState>()
    var uiStateLiveData: LiveData<UIState> = _uiState

    private var _covidDate = MutableLiveData<List<CovidDataModel>>()
    var covidLiveData: LiveData<List<CovidDataModel>> = _covidDate

    init {
        getCovidData()
    }

    private fun getCovidData() {
        _uiState.value = UIState.LoadingState
        covidDataRepository.getCovidData(object : NetworkCallListener{
            override fun onSuccess(response: Response<*>?) {
                val data  = response?.body() as List<CovidDataModel>
                _covidDate.postValue(data)
                _uiState.value = UIState.DataState
            }

            override fun onFailure() {
                _uiState.value = UIState.ErrorState
            }
        })
    }
}