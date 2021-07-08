package com.example.mymvvmapplication.data.remote

import retrofit2.Response

interface NetworkCallListener {

    fun onSuccess(response: Response<*>?)
    fun onFailure()
}