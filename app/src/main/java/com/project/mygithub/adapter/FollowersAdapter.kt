package com.project.mygithub.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.mygithub.FollowersItem
import com.project.mygithub.R
import com.project.mygithub.ui.detail.DetailActivity

class FollowersAdapter(private val followers: List<FollowersItem>) :
    RecyclerView.Adapter<FollowersAdapter.ViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_row_user, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val (name, profile) = followers[position]
        viewHolder.tvItem.text = name
        Glide.with(viewHolder.itemView.context)
            .load(profile)
            .circleCrop()
            .into(viewHolder.ivProfile)

        viewHolder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(followers[viewHolder.adapterPosition])
        }

        viewHolder.itemView.setOnClickListener {
            val intent = Intent(viewHolder.itemView.context, DetailActivity::class.java)
            intent.putExtra("USERNAME", followers[viewHolder.position].login)
            intent.putExtra("USERNAME", followers[viewHolder.position].login)
            intent.putExtra("KEY_ID", followers[viewHolder.position].id)
            intent.putExtra("KEY_AVATAR", followers[viewHolder.position].avatarUrl)
            viewHolder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount() = followers.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvItem: TextView = view.findViewById(R.id.tvItem)
        val ivProfile: ImageView = view.findViewById(R.id.ivProfile)
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: FollowersItem)
    }
}