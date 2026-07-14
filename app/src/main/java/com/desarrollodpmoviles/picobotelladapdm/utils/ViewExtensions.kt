package com.desarrollodpmoviles.picobotelladapdm.utils

import android.view.View

fun View.animarClickYLuego(accion: () -> Unit) {
    animate()
        .scaleX(0.9f)
        .scaleY(0.9f)
        .setDuration(80)
        .withEndAction {
            animate()
                .scaleX(1f)
                .scaleY(1f)
                .setDuration(80)
                .withEndAction { accion() }
        }
}