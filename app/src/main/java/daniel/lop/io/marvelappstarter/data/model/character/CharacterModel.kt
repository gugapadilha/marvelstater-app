package daniel.lop.io.marvelappstarter.data.model.character

import com.google.gson.annotations.SerializedName
import daniel.lop.io.marvelappstarter.data.model.ThumbnailModel
import java.io.Serializable

data class CharacterModel(
    @SerializedName("id") //apenas para garantir que o "id" venha corretamente
    val id: Int,
    @SerializedName("name")
    val name: String,
    @SerializedName("description")
    val description: String,
    @SerializedName("thumbnail")
    val thumbnailModel: ThumbnailModel
): Serializable
