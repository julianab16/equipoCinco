package com.desarrollodpmoviles.picobotelladapdm.data
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.desarrollodpmoviles.picobotelladapdm.model.Reto

@Dao
interface RetoDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun saveReto(reto: Reto)

    @Query("SELECT * FROM reto ORDER BY id DESC")
    fun getListReto(): MutableList<Reto>

    @Delete
    suspend fun deleteReto(reto: Reto)
    @Update
    suspend fun updateReto(reto: Reto)
}