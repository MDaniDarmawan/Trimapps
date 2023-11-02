package com.example.trimapps

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class FavoriteActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var adapter: FavoriteAdapter
    private lateinit var dao: FavoriteDao

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        recyclerView = findViewById(R.id.recycleViewer)
        recyclerView.layoutManager = LinearLayoutManager(this)

        dao = AppDatabase.getDatabase(this).favoriteDao()

        // Ambil daftar pengguna favorit dari Room DB
        val favorites = dao.getAll()

        // Buat adapter untuk menampilkan daftar pengguna favorit
        adapter = FavoriteAdapter(favorites)
        recyclerView.adapter = adapter

        // Tambahkan listener untuk menghapus pengguna dari daftar
        adapter.onItemClickListener = {
            dao.delete(it)
            adapter.notifyDataSetChanged()
        }
    }
}