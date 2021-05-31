package com.example.ktorfetchingdata.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.ktorfetchingdata.databinding.RowPostsBinding
import com.example.ktorfetchingdata.model.PostItem
import javax.inject.Inject

class PostAdapter @Inject constructor() : RecyclerView.Adapter<PostAdapter.ViewHolder>() {

    private val diffCallback = object : DiffUtil.ItemCallback<PostItem>() {
        override fun areItemsTheSame(oldItem: PostItem, newItem: PostItem): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: PostItem, newItem: PostItem): Boolean {
            return oldItem == newItem
        }
    }

    val differ = AsyncListDiffer(this, diffCallback)

    inner class ViewHolder(private val rowPostsBinding: RowPostsBinding) :
        RecyclerView.ViewHolder(rowPostsBinding.root) {
        fun bind(post: PostItem) {
            rowPostsBinding.apply {
                userId.text = post.userId.toString()
                id.text = post.id.toString()
                title.text = post.title
                body.text = post.body

            }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RowPostsBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val post = differ.currentList[position]
        holder.bind(post)
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }


}