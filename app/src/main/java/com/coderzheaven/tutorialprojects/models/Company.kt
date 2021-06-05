package com.coderzheaven.tutorialprojects.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Company {
    @SerializedName("name")
    @Expose
    var name: String? = null

    @SerializedName("catchPhrase")
    @Expose
    var catchPhrase: String? = null

    @SerializedName("bs")
    @Expose
    var bs: String? = null
}