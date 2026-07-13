package com.desarrollodpmoviles.picobotelladapdm.dialogos

import android.content.Context
import android.view.LayoutInflater
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import com.desarrollodpmoviles.picobotelladapdm.R
import com.desarrollodpmoviles.picobotelladapdm.model.Reto
import com.desarrollodpmoviles.picobotelladapdm.viewmodel.RetoViewModel

class DialogoEliminarReto {

    companion object {

        fun show(
            context: Context,
            reto: Reto,
            viewModel: RetoViewModel,
            onEliminar: () -> Unit
        ) {

            val view = LayoutInflater.from(context)
                .inflate(R.layout.dialog_eliminar_reto, null)

            val dialog = AlertDialog.Builder(context).create()
            dialog.setView(view)
            dialog.setCancelable(false)

            view.findViewById<TextView>(R.id.txtDescripcionEliminar).text =
                reto.descripcion

            view.findViewById<TextView>(R.id.txtNo).setOnClickListener {
                dialog.dismiss()
            }

            view.findViewById<TextView>(R.id.txtSi).setOnClickListener {

                viewModel.deleteReto(reto)

                Toast.makeText(
                    context,
                    "Reto eliminado",
                    Toast.LENGTH_SHORT
                ).show()

                dialog.dismiss()

                onEliminar()
            }

            dialog.show()
        }
    }
}