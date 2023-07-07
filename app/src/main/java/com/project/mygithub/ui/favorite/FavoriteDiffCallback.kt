package com.project.mygithub.ui.favorite

import androidx.recyclerview.widget.DiffUtil
import com.project.mygithub.database.FavoriteEntity

class FavoriteDiffCallback(
    private val oldList: List<FavoriteEntity>,
    private val newList: List<FavoriteEntity>
) : DiffUtil.Callback() {

    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition].id == newList[newItemPosition].id
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }
}
