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
import androidx.navigation.fragment.findNavController
import com.desarrollodpmoviles.picobotelladapdm.R
import kotlin.random.Random
import android.content.Intent
import android.net.Uri
import com.desarrollodpmoviles.picobotelladapdm.utils.animarClickYLuego

class HomeFragment : Fragment() {
    private var mediaPlayerFondo: MediaPlayer? = null
    private var audioEncendido = true

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
        val btnCompartir = view.findViewById<ImageButton>(R.id.btnCompartir)
        val btnCalificar = view.findViewById<ImageButton>(R.id.btnCalificar)
        val boton = view.findViewById<Button>(R.id.btnPresioname)
        val btnAudio = view.findViewById<ImageButton>(R.id.btnAudio)
        val btnInstrucciones = view.findViewById<ImageButton>(R.id.btnInstrucciones)
        val btnRetos = view.findViewById<ImageButton>(R.id.btnRetos)

        imgBotella = view.findViewById(R.id.imgBotella)

        contador.text = "3"

        val animacionParpadeo = AnimationUtils.loadAnimation(requireContext(), R.anim.parpadeo)
        boton.startAnimation(animacionParpadeo)

        btnRetos.setOnClickListener {
            it.animarClickYLuego {
                findNavController().navigate(R.id.action_homeFragment_to_retosFragment)
            }
        }
        btnInstrucciones.setOnClickListener {
            it.animarClickYLuego {
                findNavController().navigate(R.id.action_homeFragment_to_instruccionesFragment)
            }
        }

        mediaPlayerFondo = MediaPlayer.create(requireContext(), R.raw.musica_fondo)
        mediaPlayerFondo?.isLooping = true
        mediaPlayerFondo?.start()

        btnAudio.setOnClickListener {
            it.animarClickYLuego {
                if (audioEncendido) {
                    mediaPlayerFondo?.pause()
                    btnAudio.setImageResource(R.drawable.ic_volume_off)
                } else {
                    mediaPlayerFondo?.start()
                    btnAudio.setImageResource(R.drawable.ic_volume_on)
                }
                audioEncendido = !audioEncendido
            }
        }

        mediaPlayerGiro = MediaPlayer.create(requireContext(), R.raw.bottle_rolling).apply {
            isLooping = true   // Suena en bucle mientras gira
            setVolume(1.0f, 1.0f)   // Izquierda y derecha al máximo (1.0)

        }

        boton.setOnClickListener {
            if (isGirando) return@setOnClickListener
            boton.clearAnimation()  // Detener la animación de parpadeo
            boton.visibility = View.INVISIBLE
            boton.isEnabled = false

            contador.visibility = View.INVISIBLE  // Oculto MIENTRAS gira

            iniciarGiro(contador, boton, animacionParpadeo)
        }

        btnCalificar.setOnClickListener {
            it.animarClickYLuego {
                abrirGooglePlay()
            }
        }

        btnCompartir.setOnClickListener {
            it.animarClickYLuego {
                compartirAplicacion()
            }
        }
    }


    private fun iniciarGiro(contador: TextView, boton: Button, animacionParpadeo: android.view.animation.Animation) {

        mediaPlayerFondo?.stop()

        isGirando = true

        val vueltasMinimas = 1080
        val offsetAleatorio = Random.nextInt(0, 360)
        val anguloFinal = rotacionActual + vueltasMinimas + offsetAleatorio

        val duracionGiroMs = 4000L

        mediaPlayerGiro?.apply {
            if (!isPlaying) {
                seekTo(0)
                start()
            }
        }

        animatorGiro = android.animation.ObjectAnimator.ofFloat(
            imgBotella,
            "rotation",
            rotacionActual,
            anguloFinal
        ).apply {
            duration = duracionGiroMs
            interpolator = android.view.animation.AccelerateDecelerateInterpolator()
            doOnEnd {
                // Criterio 5 HU 11: la botella ya se detuvo -> AHORA arranca la cuenta regresiva
                detenerGiro()
                iniciarCuentaRegresiva(contador, boton, animacionParpadeo)
            }
            start()
        }
    }

    private fun iniciarCuentaRegresiva(contador: TextView, boton: Button, animacionParpadeo: android.view.animation.Animation) {

        contador.visibility = View.VISIBLE

        val duracionCuentaMs = 4000L // 3, 2, 1, 0 (4 segundos = 4 valores)

        object : CountDownTimer(duracionCuentaMs, 1000) {
            override fun onTick(millisUntilFinished: Long) {
                contador.text = (millisUntilFinished / 1000).toString()
            }

            override fun onFinish() {
                contador.text = "0"

                // TODO (HU 12, pendiente): mostrar el diálogo con el reto y el pokémon aleatorio

                boton.visibility = View.VISIBLE
                boton.isEnabled = true
                boton.startAnimation(animacionParpadeo)
            }
        }.start()
    }

    private fun detenerGiro() {
        // Detener la animación
        animatorGiro?.cancel()
        animatorGiro = null

        // Detener el sonido del giro (Criterio 2)
        mediaPlayerGiro?.apply {
            if (isPlaying) {
                pause()
                seekTo(0)   // Rebobinar para la próxima vez
            }
        }

        val rotacionActualFinal = imgBotella.rotation
        rotacionActual = rotacionActualFinal

        isGirando = false
    }

    private fun abrirGooglePlay() {

        val intent = Intent(
            Intent.ACTION_VIEW,
            Uri.parse("https://play.google.com/store/apps/details?id=com.nequi.MobileApp")
        )

        startActivity(intent)
    }

    private fun compartirAplicacion() {

        val mensaje = """
        App PicoBotella.
        Solo los valientes lo juegan!!
        https://play.google.com/store/apps/details?id=com.nequi.MobileApp
    """.trimIndent()

        val intent = Intent(Intent.ACTION_SEND).apply {

            type = "text/plain"
            putExtra(Intent.EXTRA_TEXT, mensaje)

        }

        startActivity(Intent.createChooser(intent, "Compartir con"))
    }

    override fun onDestroyView() {
        super.onDestroyView()

        // Liberar música de fondo
        mediaPlayerFondo?.stop()
        mediaPlayerFondo?.release()
        mediaPlayerFondo = null

        // Liberar sonido de giro
        mediaPlayerGiro?.stop()
        mediaPlayerGiro?.release()
        mediaPlayerGiro = null

        // Cancelar animación
        animatorGiro?.cancel()
        animatorGiro = null
    }
}