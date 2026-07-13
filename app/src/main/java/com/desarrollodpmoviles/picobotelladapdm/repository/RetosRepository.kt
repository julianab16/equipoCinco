package com.desarrollodpmoviles.picobotelladapdm.repository
import android.content.Context
import com.desarrollodpmoviles.picobotelladapdm.data.RetoDAO
import com.desarrollodpmoviles.picobotelladapdm.data.RetoDB
import com.desarrollodpmoviles.picobotelladapdm.model.Reto
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class RetoRepository(val context: Context){
    private var retoDAO: RetoDAO = RetoDB.getDatabase(context).retoDao()
    suspend fun saveReto(reto: Reto, messageResponse: (String) -> Unit) {
        try {
          withContext(Dispatchers.IO) {
              retoDAO.saveReto(reto)
          }
          messageResponse("Reto guardado correctamente")
      } catch (e: Exception) {
          messageResponse("Error al guardar el reto: ${e.message}")
      }
  }

    suspend fun getListReto(): MutableList<Reto>{
        return withContext(Dispatchers.IO){
            retoDAO.getListReto()
        }
    }

    suspend fun deleteReto(reto: Reto){
        withContext(Dispatchers.IO){
            retoDAO.deleteReto(reto)
        }
    }

    suspend fun updateReto(reto: Reto){
        withContext(Dispatchers.IO){
            retoDAO.updateReto(reto)
        }
    }
}