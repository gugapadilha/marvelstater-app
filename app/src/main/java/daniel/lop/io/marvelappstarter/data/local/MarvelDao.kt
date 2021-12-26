package daniel.lop.io.marvelappstarter.data.local

import androidx.room.*
import daniel.lop.io.marvelappstarter.data.model.character.CharacterModel
import kotlinx.coroutines.flow.Flow

@Dao //objeto de acesso a dados / acessar o banco de dados
interface MarvelDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE) //não duplica caso seja o mesmo id
    suspend fun insert(characterModel: CharacterModel) : Long

    @Query("SELECT * FROM characterModel ORDER BY id")
    fun getAll(): Flow<List<CharacterModel>>

    @Delete
    suspend fun delete(characterModel: CharacterModel)
}