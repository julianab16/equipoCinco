package com.desarrollodpmoviles.picobotelladapdm.ui.retos

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageButton
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.desarrollodpmoviles.picobotelladapdm.R
import com.desarrollodpmoviles.picobotelladapdm.databinding.FragmentRetosBinding
import com.desarrollodpmoviles.picobotelladapdm.dialogos.DialogoAgregarReto
import com.desarrollodpmoviles.picobotelladapdm.dialogos.DialogoEditarReto
import com.desarrollodpmoviles.picobotelladapdm.dialogos.DialogoEliminarReto
import com.desarrollodpmoviles.picobotelladapdm.viewmodel.RetoViewModel
import com.desarrollodpmoviles.picobotelladapdm.ui.adapter.RetoAdapter

class RetosFragment : Fragment() {

    private var _binding: FragmentRetosBinding? = null
    private val binding get() = _binding!!
    private val retoViewModel: RetoViewModel by viewModels()

    private lateinit var adapter: RetoAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentRetosBinding.inflate(inflater, container, false)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        configurarRecycler()

        observarDatos()

        retoViewModel.getListReto()

        binding.btnVolver.setOnClickListener {
            findNavController().navigateUp()
        }

        binding.fabAgregarReto.setOnClickListener {

            DialogoAgregarReto.show(
                requireContext(),
                retoViewModel
            ) {
                retoViewModel.getListReto()
            }

        }
    }

private fun configurarRecycler() {

    adapter = RetoAdapter(

        mutableListOf(),

        onEditar = { reto ->

            DialogoEditarReto.show(
                requireContext(),
                reto,
                retoViewModel
            ) {
                retoViewModel.getListReto()
            }

        },

        onEliminar = { reto ->

            DialogoEliminarReto.show(
                requireContext(),
                reto,
                retoViewModel
            ) {
                retoViewModel.getListReto()
            }

        }

    )

    binding.rvRetos.layoutManager = LinearLayoutManager(requireContext())
    binding.rvRetos.adapter = adapter
}
    private fun observarDatos() {

        retoViewModel.listReto.observe(viewLifecycleOwner) { lista ->

            adapter.actualizarLista(lista)

        }
    }

    override fun onResume() {
        super.onResume()

        retoViewModel.getListReto()
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}