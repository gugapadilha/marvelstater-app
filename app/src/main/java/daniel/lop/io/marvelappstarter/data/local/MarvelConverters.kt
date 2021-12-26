package daniel.lop.io.marvelappstarter.data.local

import androidx.room.TypeConverter
import com.google.gson.Gson
import daniel.lop.io.marvelappstarter.data.model.ThumbnailModel

class MarvelConverters {
    //precisamos ensinar o room a converter nossa Thumbnail(em CharacterModel) a ser de um tipo primitivo

    @TypeConverter
    fun fromThumbnail(thumbnailModel: ThumbnailModel): String = Gson().toJson(thumbnailModel)

    @TypeConverter
    fun toThumbnail(thumbnailModel: String) : ThumbnailModel =
        Gson().fromJson(thumbnailModel, ThumbnailModel::class.java)

}