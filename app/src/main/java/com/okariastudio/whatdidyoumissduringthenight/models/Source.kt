package com.okariastudio.whatdidyoumissduringthenight.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

data class Source(
    @SerializedName("id") @Expose var id : String,
    @SerializedName("name") @Expose var name : String
)