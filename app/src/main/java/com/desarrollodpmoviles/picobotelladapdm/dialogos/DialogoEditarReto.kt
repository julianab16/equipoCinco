package com.desarrollodpmoviles.picobotelladapdm.dialogos

import android.content.Context
import android.view.LayoutInflater
import android.widget.Button
import android.widget.EditText
import androidx.appcompat.app.AlertDialog
import com.desarrollodpmoviles.picobotelladapdm.R

class DialogoEditarReto {
    companion object {
        fun show(context: Context, textoActual: String) {
            val inflater = LayoutInflater.from(context)
            val view = inflater.inflate(R.layout.dialog_editar_reto, null)

            val alertDialog = AlertDialog.Builder(context).create()
            alertDialog.setView(view)
            alertDialog.setCancelable(false)

            view.findViewById<EditText>(R.id.etRetoEditar).setText(textoActual)

            view.findViewById<Button>(R.id.btnCancelarEditar).setOnClickListener {
                alertDialog.dismiss()
            }

            view.findViewById<Button>(R.id.btnGuardarEditar).setOnClickListener {
                // TODO: actualizar el reto en la base de datos local (Room) cuando se implemente HU 8.0 a nivel de lógica
                alertDialog.dismiss()
            }

            alertDialog.show()
        }
    }
}