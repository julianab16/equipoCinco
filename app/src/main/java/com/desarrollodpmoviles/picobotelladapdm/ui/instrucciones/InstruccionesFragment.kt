package com.desarrollodpmoviles.picobotelladapdm.ui.instrucciones

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.fragment.app.Fragment
import com.desarrollodpmoviles.picobotelladapdm.R

class InstruccionesFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_instrucciones, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val trofeo = view.findViewById<ImageView>(R.id.imgVictoria)

        val animacion = AnimationUtils.loadAnimation(
            requireContext(),
            R.anim.rebote
        )

        trofeo.startAnimation(animacion)
    }
}