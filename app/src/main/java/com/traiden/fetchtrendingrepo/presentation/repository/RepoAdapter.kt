package com.traiden.fetchtrendingrepo.presentation.repository

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.facebook.shimmer.ShimmerFrameLayout
import com.traiden.fetchtrendingrepo.R
import com.traiden.fetchtrendingrepo.base.MyApp.Companion.isLoading
import com.traiden.fetchtrendingrepo.domain.Repository


class RepoAdapter (context: Context, private val repositories: List<Repository>) : RecyclerView.Adapter<RepoAdapter.ViewHolder>() {

    val mContext = context

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.item_repository, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val repository = repositories[position]
        if (isLoading) {
            holder.startShimmerAnimation()
        } else {
            holder.stopShimmerAnimation()
            // Bind data to the repository item layout
            loadImageUsingGlide(repository.ownerAvatar,holder.ownerAvatar)
            holder.ownerName.text = repository.owner
            holder.repoName.text = repository.name
            holder.description.text = repository.description
            holder.language.text = repository.language
            holder.watchers.text = repository.watchers_count.toString()

        }
    }

    private fun loadImageUsingGlide(ownerAvatar: String, imageView: ImageView) {
        Log.d(RepoAdapter::class.simpleName, "loadImageUsingGlide: ")
        val options: RequestOptions = RequestOptions()
            .centerCrop()
            .placeholder(R.mipmap.ic_launcher_round)
            .error(R.mipmap.ic_launcher_round)
        Glide.with(mContext).load(ownerAvatar).apply(options).into(imageView)
    }

    // ...

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        private val shimmerLayout: ShimmerFrameLayout = itemView.findViewById(R.id.shimmerLayout)
        val ownerAvatar = itemView.findViewById(R.id.iv_profile) as ImageView
        val ownerName = itemView.findViewById(R.id.tv_owner_name) as TextView
        val repoName = itemView.findViewById(R.id.tv_repo_name) as TextView
        val description = itemView.findViewById(R.id.tv_desc) as TextView
        val language = itemView.findViewById(R.id.tv_language) as TextView
        val watchers = itemView.findViewById(R.id.tv_watchers) as TextView


        fun startShimmerAnimation() {
            shimmerLayout.startShimmer()
        }

        fun stopShimmerAnimation() {
            shimmerLayout.stopShimmer()
        }
    }

    override fun getItemCount(): Int {
        return repositories.size
    }
}