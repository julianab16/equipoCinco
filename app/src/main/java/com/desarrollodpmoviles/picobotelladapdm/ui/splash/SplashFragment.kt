package com.desarrollodpmoviles.picobotelladapdm.ui.splash

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.desarrollodpmoviles.picobotelladapdm.R
import android.view.animation.AnimationUtils
import android.widget.ImageView

class SplashFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.fragment_splash, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val botella = view.findViewById<ImageView>(R.id.imgBotella)

        val animacion = AnimationUtils.loadAnimation(
            requireContext(),
            R.anim.botella_anim
        )

        botella.startAnimation(animacion)

        Handler(Looper.getMainLooper()).postDelayed({

            findNavController().navigate(
                R.id.action_splashFragment_to_homeFragment,
                null,
                androidx.navigation.NavOptions.Builder()
                    .setPopUpTo(R.id.splashFragment, true)
                    .build()
            )

        }, 5000)
    }
}