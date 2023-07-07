package com.project.mygithub.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.mygithub.R
import com.project.mygithub.database.FavoriteEntity
import com.project.mygithub.ui.detail.DetailActivity
import com.project.mygithub.ui.favorite.FavoriteDiffCallback


class FavoriteAdapter : RecyclerView.Adapter<FavoriteAdapter.ViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback
    private val listFavorites = ArrayList<FavoriteEntity>()

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int) =
        ViewHolder(
            LayoutInflater.from(viewGroup.context).inflate(R.layout.item_row_user, viewGroup, false)
        )

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val users = listFavorites[position]
        viewHolder.tvItem.text = users.login
        Glide.with(viewHolder.itemView.context)
            .load(users.avatarUrl)
            .circleCrop()
            .into(viewHolder.ivProfile)

        viewHolder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(listFavorites[viewHolder.adapterPosition])
        }

        viewHolder.itemView.setOnClickListener {
            val intent = Intent(viewHolder.itemView.context, DetailActivity::class.java)
            intent.putExtra("USERNAME", listFavorites[viewHolder.position].login)
            intent.putExtra("KEY_ID", listFavorites[viewHolder.position].id)
            intent.putExtra("KEY_AVATAR", listFavorites[viewHolder.position].avatarUrl)
            viewHolder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount() = listFavorites.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvItem: TextView = view.findViewById(R.id.tvItem)
        val ivProfile: ImageView = view.findViewById(R.id.ivProfile)
    }

    fun setListFavorite(listFavorites: ArrayList<FavoriteEntity>) {
        val diffCallback = FavoriteDiffCallback(this.listFavorites, listFavorites)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        this.listFavorites.clear()
        this.listFavorites.addAll(listFavorites)
        diffResult.dispatchUpdatesTo(this)

    }


    interface OnItemClickCallback {
        fun onItemClicked(data: FavoriteEntity)
    }
}