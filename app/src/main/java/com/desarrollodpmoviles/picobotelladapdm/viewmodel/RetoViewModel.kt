package com.desarrollodpmoviles.picobotelladapdm.viewmodel
import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.desarrollodpmoviles.picobotelladapdm.model.Reto
import com.desarrollodpmoviles.picobotelladapdm.repository.RetoRepository
import kotlinx.coroutines.launch

class RetoViewModel (application: Application) : AndroidViewModel(application) {
    val context = getApplication<Application>()

    private val retoRepository = RetoRepository(context)

    private val _listReto = MutableLiveData<MutableList<Reto>>()
    val listReto: LiveData<MutableList<Reto>> get() = _listReto

    private val _progresState = MutableLiveData(false)
    val progresState: LiveData<Boolean> = _progresState

    fun saveReto(reto: Reto, message:(String)-> Unit) {
        viewModelScope.launch {
            _progresState.value = true
            try {
                retoRepository.saveReto(reto) { msg ->
                    message(msg)
                }
                _progresState.value = false
            } catch (e: Exception) {
                _progresState.value = false
            }
        }
    }
    fun getListReto() {
        viewModelScope.launch {
            _progresState.value = true
            try {
                _listReto.value = retoRepository.getListReto()
                _progresState.value = false
            } catch (e: Exception) {
                _progresState.value = false
            }

        }
    }

    fun deleteReto(reto: Reto) {
        viewModelScope.launch {
            _progresState.value = true
            try {
                retoRepository.deleteReto(reto)
                _progresState.value = false
            } catch (e: Exception) {
                _progresState.value = false
            }

        }
    }

    fun updateReto(reto: Reto) {
        viewModelScope.launch {
            _progresState.value = true
            try {
                retoRepository.updateReto(reto)
                _progresState.value = false
            } catch (e: Exception) {
                _progresState.value = false
            }
        }
    }
}

