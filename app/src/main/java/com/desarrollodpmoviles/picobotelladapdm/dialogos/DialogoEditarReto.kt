package com.desarrollodpmoviles.picobotelladapdm.dialogos

import android.content.Context
import android.content.res.ColorStateList
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.desarrollodpmoviles.picobotelladapdm.R
import com.desarrollodpmoviles.picobotelladapdm.model.Reto
import com.desarrollodpmoviles.picobotelladapdm.viewmodel.RetoViewModel

class DialogoEditarReto {

    companion object {

        fun show(
            context: Context,
            reto: Reto,
            viewModel: RetoViewModel,
            onEditar: () -> Unit
        ) {

            val view = LayoutInflater.from(context)
                .inflate(R.layout.dialog_editar_reto, null)

            val dialog = AlertDialog.Builder(context).create()
            dialog.setView(view)
            dialog.setCancelable(false)

            val etReto = view.findViewById<EditText>(R.id.etRetoEditar)
            val btnGuardar = view.findViewById<Button>(R.id.btnGuardarEditar)
            val btnCancelar = view.findViewById<Button>(R.id.btnCancelarEditar)

            etReto.setText(reto.descripcion)

            etReto.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}

                override fun afterTextChanged(s: Editable?) {

                    val habilitado = !s.isNullOrBlank()

                    btnGuardar.isEnabled = habilitado

                    btnGuardar.backgroundTintList =
                        ColorStateList.valueOf(
                            if (habilitado)
                                0xFFFF9800.toInt()
                            else
                                0xFFBDBDBD.toInt()
                        )
                }
            })

            btnCancelar.setOnClickListener {
                dialog.dismiss()
            }

            btnGuardar.setOnClickListener {

                val nuevoTexto = etReto.text.toString().trim()

                val retoActualizado = reto.copy(
                    descripcion = nuevoTexto
                )

                viewModel.updateReto(retoActualizado)

                Toast.makeText(
                    context,
                    "Reto actualizado",
                    Toast.LENGTH_SHORT
                ).show()

                dialog.dismiss()

                onEditar()
            }

            dialog.show()
        }
    }
}