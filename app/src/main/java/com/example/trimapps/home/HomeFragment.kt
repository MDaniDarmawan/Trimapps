package com.example.trimapps.home

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SearchView
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trimapps.R
import com.example.trimapps.database.SettingPreferences
import com.example.trimapps.databinding.HomeActivityBinding
import com.example.trimapps.detail.DetailActivity
import com.example.trimapps.home.Model.responseUserGithub
import com.example.trimapps.home.remote.userAdapter
import com.example.trimapps.utils.Result

class HomeFragment : Fragment(R.layout.home_activity) {

    private lateinit var binding: HomeActivityBinding
    private val adapter by lazy {
        userAdapter { user ->
            Intent(requireContext(), DetailActivity::class.java).apply {
                putExtra("item", user)
                startActivity(this)
            }
        }
    }
    private val viewModel by viewModels<HomeViewModel> {
        HomeViewModel.Factory(SettingPreferences(requireContext()))
    }

    private var lastQuery = ""

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = HomeActivityBinding.bind(view)

        viewModel.getTheme().observe(viewLifecycleOwner) {
            if (it) {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            } else {
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
            }
        }

        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.setHasFixedSize(true)
        binding.recyclerView.adapter = adapter

        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                if (!newText.isNullOrBlank()) {
                    if (newText != lastQuery) {
                        lastQuery = newText
                        viewModel.getUser("in:header $newText")
                    }
                } else {
                    lastQuery = ""
                    viewModel.getUser()
                }
                return true
            }
        })


        viewModel.resultUser.observe(viewLifecycleOwner) {
            when (it) {
                is Result.Success<*> -> {
                    adapter.setData(it.data as MutableList<responseUserGithub.Item>)
                }
                is Result.Error -> {
                    Toast.makeText(requireContext(), it.exception.message.toString(), Toast.LENGTH_SHORT).show()
                }
                is Result.Loading -> {
                    binding.progressBar.isVisible = it.isLoading
                }
            }
        }

        viewModel.getUser()

        }
}
