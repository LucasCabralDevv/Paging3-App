package com.lucascabral.paging3app

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.lucascabral.paging3app.network.CharacterData

class RickMortyAdapter: PagingDataAdapter<CharacterData, RickMortyAdapter.MyViewHolder>(DiffUtilCallBack()) {

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = getItem(position)
        item?.let {
            holder.bind(it)
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.recycler_row, parent, false)
        return MyViewHolder(view)
    }

    inner class MyViewHolder(view: View): RecyclerView.ViewHolder(view) {

        private val imageView: ImageView = view.findViewById(R.id.imageView)
        private val nameTextView: TextView = view.findViewById(R.id.nameTextView)
        private val descTextView: TextView = view.findViewById(R.id.descTextView)

        fun bind(data: CharacterData) {
            nameTextView.text = data.name
            descTextView.text = data.species

            Glide.with(imageView).load(data.image).circleCrop().into(imageView)

            itemView.setOnClickListener {
                val intent = Intent(it.context, MainActivity2::class.java)
                intent.putExtra("name", data.name)
                intent.putExtra("image", data.image)
                intent.putExtra("species", data.species)
                it.context.startActivity(intent)
            }
        }
    }

    class DiffUtilCallBack: DiffUtil.ItemCallback<CharacterData>() {
        override fun areItemsTheSame(oldItem: CharacterData, newItem: CharacterData): Boolean {
            return oldItem.name == newItem.name
        }

        override fun areContentsTheSame(oldItem: CharacterData, newItem: CharacterData): Boolean {
            return oldItem.name == newItem.name && oldItem.species == newItem.species
        }

    }
}