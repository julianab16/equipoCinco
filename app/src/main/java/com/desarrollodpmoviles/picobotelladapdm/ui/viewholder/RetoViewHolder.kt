package com.desarrollodpmoviles.picobotelladapdm.ui.viewholder
import android.os.Bundle
import androidx.navigation.NavController
import androidx.recyclerview.widget.RecyclerView
import com.desarrollodpmoviles.picobotelladapdm.databinding.ItemRetoBinding
//import com.example.clase8.R
import com.desarrollodpmoviles.picobotelladapdm.model.Reto

class RetoViewHolder(
    binding: ItemRetoBinding
) : RecyclerView.ViewHolder(binding.root) {

    val bindingItem = binding

    fun setItemReto(
        reto: Reto,
//        onEdit: (Reto) -> Unit,
//        onDelete: (Reto) -> Unit
    ) {
        bindingItem.txtReto.text = reto.descripcion

//        bindingItem.btnEditar.setOnClickListener {
//            onEdit(reto)
//        }
//
//        bindingItem.btnEliminar.setOnClickListener {
//            onDelete(reto)
//        }
    }
}