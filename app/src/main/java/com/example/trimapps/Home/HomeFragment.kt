package com.example.trimapps.Home

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trimapps.R
import com.example.trimapps.databinding.FragmentHomeBinding
import javax.security.auth.callback.Callback


class HomeFragment : Fragment() {
    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!
    private lateinit var adapter: HomeAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val HomeViewModel = ViewModelProvider(this)[HomeViewModel::class.java]
        _binding = FragmentHomeBinding.inflate(inflater,container, false)
        val root: View = binding.root

        val recyclerView = binding.recycleViewer
        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        adapter = HomeAdapter(mutableListOf())
        recyclerView.adapter = adapter
        getUser()
        return root
    }

    private fun getUser() {
        val client = apiConfig.getApiService().getListUsers("1")

        client.enqueue(object : Callback<ResponseUser> {

    fun newInstance(param1: String, param2: String) =
            HomeFragment().apply {
                arguments = Bundle().apply {

                }

    }
}