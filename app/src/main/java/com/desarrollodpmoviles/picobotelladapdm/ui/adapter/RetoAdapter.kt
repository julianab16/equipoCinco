package com.desarrollodpmoviles.picobotelladapdm.ui.adapter
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.desarrollodpmoviles.picobotelladapdm.databinding.ItemRetoBinding
import com.desarrollodpmoviles.picobotelladapdm.model.Reto
import com.desarrollodpmoviles.picobotelladapdm.ui.viewholder.RetoViewHolder

class RetoAdapter(
    private val listReto: MutableList<Reto>,
    private val onEditar: (Reto) -> Unit,
    private val onEliminar: (Reto) -> Unit
) : RecyclerView.Adapter<RetoViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RetoViewHolder {
        val binding = ItemRetoBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
        return RetoViewHolder(binding)
    }

    override fun getItemCount() = listReto.size

    override fun onBindViewHolder(holder: RetoViewHolder, position: Int) {
        holder.setItemReto(
            listReto[position],
            onEditar,
            onEliminar
        )
    }

    fun actualizarLista(nuevaLista: MutableList<Reto>) {
        listReto.clear()
        listReto.addAll(nuevaLista)
        notifyDataSetChanged()
    }
}