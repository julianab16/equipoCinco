package com.desarrollodpmoviles.picobotelladapdm.ui.retos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.desarrollodpmoviles.picobotelladapdm.R
import com.desarrollodpmoviles.picobotelladapdm.dialogos.DialogoAgregarReto
import com.desarrollodpmoviles.picobotelladapdm.dialogos.DialogoEditarReto
import com.desarrollodpmoviles.picobotelladapdm.dialogos.DialogoEliminarReto
import com.google.android.material.floatingactionbutton.FloatingActionButton

class RetosFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_retos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // Criterio 3 HU 6.0: la flecha regresa al home
        view.findViewById<ImageButton>(R.id.btnVolver).setOnClickListener {
            findNavController().navigateUp()
        }

        // Criterio 8 HU 6.0: FAB abre el diálogo de agregar reto (HU 7.0)
        view.findViewById<FloatingActionButton>(R.id.fabAgregarReto).setOnClickListener {
            DialogoAgregarReto.show(requireContext())
        }

        configurarItem(view, R.id.txtReto1, R.id.btnEditarReto1, R.id.btnEliminarReto1)
        configurarItem(view, R.id.txtReto2, R.id.btnEditarReto2, R.id.btnEliminarReto2)
        configurarItem(view, R.id.txtReto3, R.id.btnEditarReto3, R.id.btnEliminarReto3)
    }

    // Criterios 9 y 10 HU 6.0: editar/eliminar abren HU 8.0 y HU 9.0
    private fun configurarItem(view: View, txtId: Int, btnEditarId: Int, btnEliminarId: Int) {
        val txtReto = view.findViewById<TextView>(txtId)

        view.findViewById<ImageButton>(btnEditarId).setOnClickListener {
            DialogoEditarReto.show(requireContext(), txtReto.text.toString())
        }

        view.findViewById<ImageButton>(btnEliminarId).setOnClickListener {
            DialogoEliminarReto.show(requireContext(), txtReto.text.toString())
        }
    }
}