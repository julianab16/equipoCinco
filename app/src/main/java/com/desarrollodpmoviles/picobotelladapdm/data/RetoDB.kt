package com.desarrollodpmoviles.picobotelladapdm.data
import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.desarrollodpmoviles.picobotelladapdm.model.Reto
import com.desarrollodpmoviles.picobotelladapdm.utils.Constants.NAME_BD

@Database(entities = [Reto::class], version = 1, exportSchema = false)
abstract class RetoDB : RoomDatabase() {
    abstract fun retoDao(): RetoDAO
    companion object{
        fun getDatabase(context: Context): RetoDB {
            return Room.databaseBuilder(
                context.applicationContext,
                RetoDB::class.java,
                NAME_BD
            ).build()
        }
    }
}