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

class DialogoAgregarReto {

    companion object {

        fun show(
            context: Context,
            retoViewModel: RetoViewModel,
            onRetoGuardado: () -> Unit
        ) {

            val inflater = LayoutInflater.from(context)
            val view = inflater.inflate(R.layout.dialog_agregar_reto, null)

            val alertDialog = AlertDialog.Builder(context).create()
            alertDialog.setView(view)
            alertDialog.setCancelable(false)

            val etReto = view.findViewById<EditText>(R.id.etReto)
            val btnCancelar = view.findViewById<Button>(R.id.btnCancelarAgregar)
            val btnGuardar = view.findViewById<Button>(R.id.btnGuardarAgregar)

            btnGuardar.isEnabled = false

            etReto.addTextChangedListener(object : TextWatcher {

                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {
                }

                override fun onTextChanged(
                    s: CharSequence?,
                    start: Int,
                    before: Int,
                    count: Int
                ) {
                }

                override fun afterTextChanged(s: Editable?) {

                    val hayTexto = !s.isNullOrBlank()

                    btnGuardar.isEnabled = hayTexto

                    btnGuardar.backgroundTintList =
                        ColorStateList.valueOf(
                            if (hayTexto)
                                0xFFFF9800.toInt()
                            else
                                0xFFBDBDBD.toInt()
                        )
                }
            })

            btnCancelar.setOnClickListener {
                alertDialog.dismiss()
            }

            btnGuardar.setOnClickListener {

                val descripcion = etReto.text.toString().trim()

                if (descripcion.isEmpty()) {
                    etReto.error = "Ingrese un reto"
                    return@setOnClickListener
                }

                val reto = Reto(
                    descripcion = descripcion
                )

                retoViewModel.saveReto(reto) { mensaje ->

                    Toast.makeText(
                        context,
                        mensaje,
                        Toast.LENGTH_SHORT
                    ).show()

                    onRetoGuardado()

                    alertDialog.dismiss()
                }
            }

            alertDialog.show()
        }
    }
}