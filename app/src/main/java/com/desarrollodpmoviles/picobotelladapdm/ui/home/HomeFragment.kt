package com.desarrollodpmoviles.picobotelladapdm.ui.home

import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.desarrollodpmoviles.picobotelladapdm.R
import android.media.MediaPlayer
import android.widget.ImageButton
import androidx.navigation.fragment.findNavController

class HomeFragment : Fragment() {

    private var mediaPlayer: MediaPlayer? = null
    private var audioEncendido = true

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val contador = view.findViewById<TextView>(R.id.txtContador)
        val boton = view.findViewById<Button>(R.id.btnPresioname)
        val btnAudio = view.findViewById<ImageButton>(R.id.btnAudio)
        val btnInstrucciones = view.findViewById<ImageButton>(R.id.btnInstrucciones)

        // El contador inicia mostrando el 3
        contador.text = "3"

        // Animación de parpadeo del botón
        val animacionParpadeo = AnimationUtils.loadAnimation(
            requireContext(),
            R.anim.parpadeo
        )

        boton.startAnimation(animacionParpadeo)

        // Al presionar el botón inicia el contador
        boton.setOnClickListener {
            iniciarContador(contador)
        }

        btnInstrucciones.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_instruccionesFragment)
        }

        mediaPlayer = MediaPlayer.create(requireContext(), R.raw.musica_fondo)
        mediaPlayer?.isLooping = true
        mediaPlayer?.start()

        btnAudio.setOnClickListener {

            if (audioEncendido) {

                mediaPlayer?.pause()
                btnAudio.setImageResource(R.drawable.ic_volume_off)

            } else {

                mediaPlayer?.start()
                btnAudio.setImageResource(R.drawable.ic_volume_on)

            }

            audioEncendido = !audioEncendido
        }
    }

    private fun iniciarContador(contador: TextView) {

        object : CountDownTimer(4000, 1000) {

            override fun onTick(millisUntilFinished: Long) {
                contador.text = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                contador.text = "0"

                // Aquí más adelante iniciaremos el juego
            }

        }.start()
    }
    override fun onDestroyView() {
        super.onDestroyView()

        mediaPlayer?.stop()
        mediaPlayer?.release()
        mediaPlayer = null
    }

}