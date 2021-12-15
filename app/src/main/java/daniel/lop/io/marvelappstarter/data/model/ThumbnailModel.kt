package daniel.lop.io.marvelappstarter.data.model

import com.google.gson.annotations.SerializedName

data class ThumbnailModel(
    @SerializedName("path") //apenas para garantir que o "path" venha corretamente
    val path: String,
    @SerializedName("extension") //apenas para garantir que o "extension" venha corretamente
    val extension: String
)
