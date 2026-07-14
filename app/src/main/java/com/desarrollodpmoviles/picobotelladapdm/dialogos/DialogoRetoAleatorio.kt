package com.desarrollodpmoviles.picobotelladapdm.dialogos

import android.app.AlertDialog
import android.content.Context
import android.view.LayoutInflater
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.bumptech.glide.Glide
import com.desarrollodpmoviles.picobotelladapdm.R
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Path

class DialogoRetoAleatorio {

    companion object {
        private var alertDialog: AlertDialog? = null

        // API de Pokémon
        interface PokeApiService {
            @GET("pokemon/{id}")
            suspend fun getPokemon(@Path("id") id: Int): PokemonResponse
        }

        data class PokemonResponse(
            val sprites: Sprites
        )

        data class Sprites(
            val front_default: String?
        )

        private val retrofit = Retrofit.Builder()
            .baseUrl("https://pokeapi.co/api/v2/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        private val pokeApiService = retrofit.create(PokeApiService::class.java)

        fun show(context: Context, reto: String, onClose: () -> Unit) {
            val inflater = LayoutInflater.from(context)
            val view = inflater.inflate(R.layout.dialog_reto_aleatorio, null)

            val txtReto = view.findViewById<TextView>(R.id.txtReto)
            val imgPokemon = view.findViewById<ImageView>(R.id.imgPokemon)
            val btnCerrar = view.findViewById<Button>(R.id.btnCerrar)

            txtReto.text = reto

            cargarPokemonAleatorio(imgPokemon)

            alertDialog = AlertDialog.Builder(context)
                .setView(view)
                .setCancelable(false)
                .create()

            btnCerrar.setOnClickListener {
                alertDialog?.dismiss()
                alertDialog = null
                onClose.invoke()
            }
            alertDialog?.window?.setDimAmount(0.1f)

            alertDialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
            alertDialog?.show()
        }

        private fun cargarPokemonAleatorio(imageView: ImageView) {
            val randomId = (1..898).random()

            CoroutineScope(Dispatchers.IO).launch {
                try {
                    val response = pokeApiService.getPokemon(randomId)
                    val imageUrl = response.sprites.front_default
                    withContext(Dispatchers.Main) {
                        if (imageUrl != null) {
                            Glide.with(imageView.context)
                                .load(imageUrl)
                                .placeholder(R.drawable.ic_pokeball)
                                .error(R.drawable.ic_pokeball)
                                .into(imageView)
                        } else {
                            imageView.setImageResource(R.drawable.ic_pokeball)
                        }
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    withContext(Dispatchers.Main) {
                        imageView.setImageResource(R.drawable.ic_pokeball)
                    }
                }
            }
        }
    }
}