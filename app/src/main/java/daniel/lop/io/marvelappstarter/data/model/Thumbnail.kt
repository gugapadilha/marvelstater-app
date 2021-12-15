package daniel.lop.io.marvelappstarter.data.model

import com.google.gson.annotations.SerializedName

data class Thumbnail(
    @SerializedName("path")
    val path: String,
    @SerializedName("extension")
    val extension: String
)
