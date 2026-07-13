package com.desarrollodpmoviles.picobotelladapdm.dialogos

import android.content.Context
import android.content.res.ColorStateList
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.desarrollodpmoviles.picobotelladapdm.R

class DialogoAgregarReto {
    companion object {
        fun show(context: Context) {
            val inflater = LayoutInflater.from(context)
            val view = inflater.inflate(R.layout.dialog_agregar_reto, null)

            val alertDialog = AlertDialog.Builder(context).create()
            alertDialog.setView(view)
            alertDialog.setCancelable(false) // Criterio 7 HU 7.0

            val etReto = view.findViewById<EditText>(R.id.etReto)
            val btnCancelar = view.findViewById<Button>(R.id.btnCancelarAgregar)
            val btnGuardar = view.findViewById<Button>(R.id.btnGuardarAgregar)

            // Criterio 5 HU 7.0: Guardar solo se habilita si hay texto
            etReto.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
                override fun afterTextChanged(s: Editable?) {
                    val hayTexto = !s.isNullOrBlank()
                    btnGuardar.isEnabled = hayTexto
                    btnGuardar.backgroundTintList = ColorStateList.valueOf(
                        if (hayTexto) 0xFFFF9800.toInt() else 0xFFBDBDBD.toInt()
                    )
                }
            })

            btnCancelar.setOnClickListener {
                alertDialog.dismiss()
            }

            btnGuardar.setOnClickListener {
                // TODO: guardar el reto en la base de datos local (Room) cuando se implemente HU 7.0 a nivel de lógica
                alertDialog.dismiss()
            }

            alertDialog.show()
        }
    }
}