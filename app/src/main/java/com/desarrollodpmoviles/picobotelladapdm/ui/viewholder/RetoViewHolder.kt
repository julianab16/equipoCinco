package com.desarrollodpmoviles.picobotelladapdm.ui.viewholder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.desarrollodpmoviles.picobotelladapdm.databinding.ItemRetoBinding
import com.desarrollodpmoviles.picobotelladapdm.model.Reto

class RetoViewHolder(
    private val binding: ItemRetoBinding
) : RecyclerView.ViewHolder(binding.root) {

    fun setItemReto(
        reto: Reto,
        onEditar: (Reto) -> Unit,
        onEliminar: (Reto) -> Unit
    ) {

        binding.txtReto.text = reto.descripcion

        binding.btnEditar.setOnClickListener {
            animarBoton(it) {
                onEditar(reto)
            }
        }

        binding.btnEliminar.setOnClickListener {
            animarBoton(it) {
                onEliminar(reto)
            }
        }
    }

    private fun animarBoton(view: View, accion: () -> Unit) {
        view.animate()
            .scaleX(0.9f)
            .scaleY(0.9f)
            .setDuration(80)
            .withEndAction {
                view.animate()
                    .scaleX(1f)
                    .scaleY(1f)
                    .setDuration(80)
                    .withEndAction {
                        accion()
                    }
            }
    }
}