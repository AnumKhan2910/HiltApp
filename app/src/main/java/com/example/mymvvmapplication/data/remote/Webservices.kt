package com.example.mymvvmapplication.data.remote

import com.example.mymvvmapplication.model.CovidDataModel
import retrofit2.Call
import retrofit2.http.GET

interface Webservices {

    @GET("/repos/mralexgray/ACEView/commits")
    fun getCovidData() : Call<List<CovidDataModel>>
}