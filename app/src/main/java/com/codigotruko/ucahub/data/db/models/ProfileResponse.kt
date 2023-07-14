package com.codigotruko.ucahub.data.db.models

import com.google.gson.annotations.SerializedName

data class ProfileResponse(
    @SerializedName("profile") val profile: Profile
)
