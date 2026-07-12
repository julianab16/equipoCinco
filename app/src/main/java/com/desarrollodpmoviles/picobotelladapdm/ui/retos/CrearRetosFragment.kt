package com.desarrollodpmoviles.picobotelladapdm.ui.retos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.desarrollodpmoviles.picobotelladapdm.R

class CrearRetosFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_crear_retos, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val btnVolver = view.findViewById<ImageButton>(R.id.btnVolver)

        btnVolver.setOnClickListener { findNavController().navigate(R.id.action_crearRetosFragment_to_homeFragment) }
    }
}