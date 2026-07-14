package com.desarrollodpmoviles.picobotelladapdm.ui.home

import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import android.widget.ImageButton
import android.widget.ImageView
import android.widget.TextView
import androidx.core.animation.doOnEnd
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.desarrollodpmoviles.picobotelladapdm.R
import com.desarrollodpmoviles.picobotelladapdm.dialogos.DialogoRetoAleatorio
import com.desarrollodpmoviles.picobotelladapdm.viewmodel.RetoViewModel
import kotlinx.coroutines.launch
import kotlin.random.Random

class HomeFragment : Fragment() {
    private var mediaPlayerFondo: MediaPlayer? = null
    private var audioEncendido = true
    private var musicaPausadaPorJuego = false

    private lateinit var retoViewModel: RetoViewModel

    private lateinit var imgBotella: ImageView
    private var mediaPlayerGiro: MediaPlayer? = null
    private var animatorGiro: android.animation.ObjectAnimator? = null
    private var rotacionActual = 0f
    private var isGirando = false

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
        val btnRetos = view.findViewById<ImageButton>(R.id.btnRetos)

        retoViewModel = ViewModelProvider(this).get(RetoViewModel::class.java)

        imgBotella = view.findViewById(R.id.imgBotella)

        contador.text = "3"

        val animacionParpadeo = AnimationUtils.loadAnimation(requireContext(), R.anim.parpadeo)
        boton.startAnimation(animacionParpadeo)

        btnRetos.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_retosFragment)
        }
        btnInstrucciones.setOnClickListener {
            findNavController().navigate(R.id.action_homeFragment_to_instruccionesFragment)
        }

        // Música de fondo
        mediaPlayerFondo = MediaPlayer.create(requireContext(), R.raw.musica_fondo)
        mediaPlayerFondo?.isLooping = true
        mediaPlayerFondo?.start()

        btnAudio.setOnClickListener {
            if (audioEncendido) {
                mediaPlayerFondo?.pause()
                btnAudio.setImageResource(R.drawable.ic_volume_off)
            } else {
                mediaPlayerFondo?.start()
                btnAudio.setImageResource(R.drawable.ic_volume_on)
            }
            audioEncendido = !audioEncendido
        }

        // Sonido de giro
        mediaPlayerGiro = MediaPlayer.create(requireContext(), R.raw.bottle_rolling).apply {
            isLooping = true
            setVolume(1.0f, 1.0f)
        }

        // Botón principal
        boton.setOnClickListener {
            if (isGirando) return@setOnClickListener

            boton.clearAnimation()
            boton.visibility = View.INVISIBLE
            boton.isEnabled = false
            contador.visibility = View.VISIBLE

            iniciarGiroYContador(contador, boton, animacionParpadeo)
        }
    }

    private fun mostrarDialogoRetoAleatorio(
        boton: Button,
        animacionParpadeo: android.view.animation.Animation,
        contador: TextView,
        onDialogClosed: () -> Unit) {

        contador.visibility = View.INVISIBLE

        // Asegurar que el fragmento está activo
        if (!isAdded) {
            restaurarUI(boton, animacionParpadeo)
            return
        }

        lifecycleScope.launch {
            try {
                val retoDescripcion = retoViewModel.obtenerRetoAleatorio() ?: "¡Completa un reto!"

                DialogoRetoAleatorio.show(
                    requireContext(),
                    retoDescripcion,
                    onClose = {
                        if (musicaPausadaPorJuego && audioEncendido) {
                            mediaPlayerFondo?.start()
                            musicaPausadaPorJuego = false
                        }
                        restaurarUI(boton, animacionParpadeo)
                        onDialogClosed.invoke()
                    }
                )
            } catch (e: Exception) {
                e.printStackTrace()
                if (musicaPausadaPorJuego && audioEncendido) {
                    mediaPlayerFondo?.start()
                    musicaPausadaPorJuego = false
                }
                restaurarUI(boton, animacionParpadeo)
            }
        }
    }

    private fun restaurarUI(boton: Button, animacionParpadeo: android.view.animation.Animation) {
        boton.visibility = View.VISIBLE
        boton.isEnabled = true
        boton.startAnimation(animacionParpadeo)
    }

    private fun iniciarGiroYContador(
        contador: TextView,
        boton: Button,
        animacionParpadeo: android.view.animation.Animation
    ) {
        if (mediaPlayerFondo?.isPlaying == true) {
            mediaPlayerFondo?.pause()
            musicaPausadaPorJuego = true
        }

        isGirando = true

        val vueltasMinimas = 1080
        val offsetAleatorio = Random.nextInt(0, 360)
        val anguloFinal = rotacionActual + vueltasMinimas + offsetAleatorio
        val duracionMs = 4000L

        mediaPlayerGiro?.apply {
            if (!isPlaying) {
                seekTo(0)
                start()
            }
        }

        // Animación de giro
        animatorGiro = android.animation.ObjectAnimator.ofFloat(
            imgBotella,
            "rotation",
            rotacionActual,
            anguloFinal
        ).apply {
            duration = duracionMs
            interpolator = android.view.animation.AccelerateDecelerateInterpolator()
            doOnEnd {
                detenerGiro()
            }
            start()
        }

        // Contador regresivo
        object : CountDownTimer(duracionMs, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                contador.text = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                contador.text = "0"

                detenerGiro()

                mostrarDialogoRetoAleatorio(boton, animacionParpadeo, contador) {
                }

            }
        }.start()
    }

    private fun detenerGiro() {
        animatorGiro?.cancel()
        animatorGiro = null

        mediaPlayerGiro?.apply {
            if (isPlaying) {
                pause()
                seekTo(0)
            }
        }

        rotacionActual = imgBotella.rotation
        isGirando = false
    }

    override fun onDestroyView() {
        super.onDestroyView()

        mediaPlayerFondo?.stop()
        mediaPlayerFondo?.release()
        mediaPlayerFondo = null

        mediaPlayerGiro?.stop()
        mediaPlayerGiro?.release()
        mediaPlayerGiro = null

        animatorGiro?.cancel()
        animatorGiro = null
    }
}