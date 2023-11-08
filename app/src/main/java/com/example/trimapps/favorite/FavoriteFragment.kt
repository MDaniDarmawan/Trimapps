package com.example.trimapps.favorite

import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.trimapps.R
import com.example.trimapps.database.DbModule
import com.example.trimapps.databinding.ActivityFavoriteBinding
import com.example.trimapps.detail.DetailActivity
import com.example.trimapps.home.remote.userAdapter


class FavoriteFragment : Fragment(R.layout.activity_favorite) {
    private lateinit var binding: ActivityFavoriteBinding
    private val adapter by lazy {
        userAdapter { user ->
            Intent(requireContext(), DetailActivity::class.java).apply {
                putExtra("item", user)
                startActivity(this)
            }
        }
    }

    private val viewModel by viewModels<FavoriteViewModel> {
        FavoriteViewModel.Factory(DbModule(requireContext()))
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = ActivityFavoriteBinding.bind(view)
        requireActivity().actionBar?.setDisplayHomeAsUpEnabled(true)

        binding.rvFavorite.layoutManager = LinearLayoutManager(requireContext())
        binding.rvFavorite.adapter = adapter

        viewModel.getUserFavorite().observe(viewLifecycleOwner) {
            adapter.setData(it)
        }
    }


    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> {
                requireActivity().onBackPressed()
            }
        }
        return super.onOptionsItemSelected(item)
        }
}
