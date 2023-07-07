package com.project.mygithub.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.project.mygithub.FollowingItem
import com.project.mygithub.R
import com.project.mygithub.ui.detail.DetailActivity

class FollowingAdapter(private val following: List<FollowingItem>) :
    RecyclerView.Adapter<FollowingAdapter.ViewHolder>() {

    private lateinit var onItemClickCallback: OnItemClickCallback

    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.item_row_user, viewGroup, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {
        val (name, profile) = following[position]
        viewHolder.tvItem.text = name
        Glide.with(viewHolder.itemView.context)
            .load(profile)
            .circleCrop()
            .into(viewHolder.ivProfile)

        viewHolder.itemView.setOnClickListener {
            onItemClickCallback.onItemClicked(following[viewHolder.adapterPosition])
        }

        viewHolder.itemView.setOnClickListener {
            val intent = Intent(viewHolder.itemView.context, DetailActivity::class.java)
            intent.putExtra("USERNAME", following[viewHolder.position].login)
            intent.putExtra("KEY_ID", following[viewHolder.position].id)
            intent.putExtra("KEY_AVATAR", following[viewHolder.position].avatarUrl)
            viewHolder.itemView.context.startActivity(intent)
        }
    }

    override fun getItemCount() = following.size

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvItem: TextView = view.findViewById(R.id.tvItem)
        val ivProfile: ImageView = view.findViewById(R.id.ivProfile)
    }

    interface OnItemClickCallback {
        fun onItemClicked(data: FollowingItem)
    }
}