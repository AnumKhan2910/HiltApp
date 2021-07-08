package com.example.mymvvmapplication.model

import com.google.gson.annotations.SerializedName


class CovidDataModel {

    @SerializedName("country")
    var country : String = ""

    @SerializedName("code")
    var code : String = ""

    @SerializedName("confirmed")
    var confirmed : String = ""

    @SerializedName("recovered")
    var recovered : String = ""

    @SerializedName("critical")
    var critical : String = ""

    @SerializedName("commit.author.name")
    var name : String = ""

    override fun equals(other: Any?): Boolean {
        return super.equals(other)
    }

}