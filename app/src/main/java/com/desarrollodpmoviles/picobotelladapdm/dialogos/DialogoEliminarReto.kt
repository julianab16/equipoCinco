package com.desarrollodpmoviles.picobotelladapdm.dialogos

import android.content.Context
import android.view.LayoutInflater
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import com.desarrollodpmoviles.picobotelladapdm.R

class DialogoEliminarReto {
    companion object {
        fun show(context: Context, textoReto: String) {
            val inflater = LayoutInflater.from(context)
            val view = inflater.inflate(R.layout.dialog_eliminar_reto, null)

            val alertDialog = AlertDialog.Builder(context).create()
            alertDialog.setView(view)
            alertDialog.setCancelable(false)

            view.findViewById<TextView>(R.id.txtDescripcionEliminar).text = textoReto

            view.findViewById<TextView>(R.id.txtNo).setOnClickListener {
                alertDialog.dismiss()
            }

            view.findViewById<TextView>(R.id.txtSi).setOnClickListener {
                // TODO: eliminar el reto de la base de datos local (Room) cuando se implemente HU 9.0 a nivel de lógica
                alertDialog.dismiss()
            }

            alertDialog.show()
        }
    }
}