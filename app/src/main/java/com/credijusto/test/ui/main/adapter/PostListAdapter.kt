package com.credijusto.test.ui.main.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.credijusto.test.data.model.Post
import com.credijusto.test.databinding.ItemPostBinding

/**
 * Adapter class [RecyclerView.Adapter] for [RecyclerView] which binds [Post] along with [PostViewHolder]
 * @param onItemClicked which will receive callback when item is clicked.
 */
class PostListAdapter : ListAdapter<Post, PostListAdapter.PostViewHolder>(DIFF_CALLBACK) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = PostViewHolder (
        ItemPostBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(
        holder: PostViewHolder,
        position: Int
    ) {
        holder.bind(getItem(position))
    }

    companion object {
        private val DIFF_CALLBACK = object : DiffUtil.ItemCallback<Post>() {
            override fun areItemsTheSame(oldItem: Post, newItem: Post): Boolean =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: Post, newItem: Post): Boolean =
                oldItem == newItem
        }
    }

    override fun getItemCount(): Int {
        return super.getItemCount()
    }
    private var setPostClickListener : ((post: Post) -> Unit)? = null

    fun onPostClicked(listener:(Post) -> Unit){
        setPostClickListener = listener
    }

    inner class PostViewHolder(private val binding: ItemPostBinding) : RecyclerView.ViewHolder(binding.root) {

        fun bind(post: Post) {
            binding.tvTitle.text = post.title

            binding.cvPost.setOnClickListener{
                setPostClickListener?.let {
                    it(post)
                }
            }
        }

    }

}