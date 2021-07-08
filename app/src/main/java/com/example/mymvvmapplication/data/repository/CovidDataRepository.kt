package com.example.mymvvmapplication.data.repository

import com.example.mymvvmapplication.data.remote.NetworkCallListener
import com.example.mymvvmapplication.data.remote.Webservices
import com.example.mymvvmapplication.model.CovidDataModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import javax.inject.Inject

class CovidDataRepository @Inject constructor(var webservices: Webservices) {

    fun getCovidData(networkCallListener: NetworkCallListener){
        val call: Call<List<CovidDataModel>> = webservices.getCovidData()
        call.enqueue(object : Callback<List<CovidDataModel>>{
            override fun onFailure(call: Call<List<CovidDataModel>>, t: Throwable) {
                networkCallListener.onFailure()
            }

            override fun onResponse(
                call: Call<List<CovidDataModel>>,
                response: Response<List<CovidDataModel>>
            ) {
                if (response.isSuccessful){
                    networkCallListener.onSuccess(response)
                } else networkCallListener.onFailure()
            }
        })
    }
}