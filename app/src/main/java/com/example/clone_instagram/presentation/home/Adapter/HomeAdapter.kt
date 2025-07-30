package com.example.clone_instagram.presentation.home.Adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.clone_instagram.data.model.FeedModel
import com.example.clone_instagram.databinding.ItemRecyclerviewHomeBinding

class HomeAdapter : ListAdapter<FeedModel, HomeAdapter.HomeAdapterViewHolder>(diffUtil) {


    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): HomeAdapterViewHolder {
        return HomeAdapterViewHolder(
            ItemRecyclerviewHomeBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: HomeAdapterViewHolder, position: Int) {
        holder.bind(currentList[position])
    }

    inner class HomeAdapterViewHolder(private val binding: ItemRecyclerviewHomeBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(model: FeedModel) {
            Glide.with(binding.root)
                .load(model.feedImage)
                .centerCrop()
                .into(binding.ivFeedImage)
        }
    }

    companion object {
        val diffUtil = object : DiffUtil.ItemCallback<FeedModel>() {
            override fun areItemsTheSame(oldItem: FeedModel, newItem: FeedModel): Boolean {
                return oldItem.Uid == newItem.Uid
            }

            override fun areContentsTheSame(oldItem: FeedModel, newItem: FeedModel): Boolean {
                return oldItem == newItem
            }
        }
    }
}