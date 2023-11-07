package com.example.trimapps.Home.remote

import android.content.ClipData.Item
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.trimapps.Home.Model.responseUserGithub
import com.example.trimapps.Home.Model.responseUserGithubItem
import com.example.trimapps.databinding.ItemUserBinding
import coil.load
import coil.transform.CircleCropTransformation

class userAdapter(private val data:MutableList<responseUserGithubItem> = mutableListOf()) : RecyclerView.Adapter<userAdapter.UserViewHolder>() {

    fun setData(data: MutableList<responseUserGithubItem>){
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()

    }

    class UserViewHolder(private val v: ItemUserBinding) : RecyclerView.ViewHolder(v.root) {
        fun bind(item: responseUserGithubItem) {
    v.imageV.load(item.avatar_url) {
        transformations(CircleCropTransformation())
    }
        v.username.text = item.login
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder =
        UserViewHolder(ItemUserBinding.inflate(LayoutInflater.from(parent.context),parent,false))

    override fun getItemCount(): Int = data.size

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
    }
}
