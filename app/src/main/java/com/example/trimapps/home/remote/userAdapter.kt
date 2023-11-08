package com.example.trimapps.home.remote
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.trimapps.databinding.ItemUserBinding
import com.example.trimapps.home.Model.responseUserGithub

class userAdapter(
    private val data: MutableList<responseUserGithub.Item> = mutableListOf(),
    private val listener: (responseUserGithub.Item) -> Unit
) :
    RecyclerView.Adapter<userAdapter.UserViewHolder>() {

    fun setData(data: MutableList<responseUserGithub.Item>) {
        this.data.clear()
        this.data.addAll(data)
        notifyDataSetChanged()
    }

    class UserViewHolder(private val v: ItemUserBinding) : RecyclerView.ViewHolder(v.root) {
        fun bind(item: responseUserGithub.Item) {
            v.image.load(item.avatar_url) {
                transformations(CircleCropTransformation())
            }

            v.username.text = item.login
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UserViewHolder =
        UserViewHolder(ItemUserBinding.inflate(LayoutInflater.from(parent.context), parent, false))

    override fun onBindViewHolder(holder: UserViewHolder, position: Int) {
        val item = data[position]
        holder.bind(item)
        holder.itemView.setOnClickListener {
            listener(item)
        }
    }

    override fun getItemCount(): Int = data.size
}