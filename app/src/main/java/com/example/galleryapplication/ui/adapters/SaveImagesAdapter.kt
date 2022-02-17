package com.example.galleryapplication.ui.adapters

import android.annotation.SuppressLint
import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import com.example.galleryapplication.R
import com.example.galleryapplication.models.ImageTable

class SaveImagesAdapter(
    private val listener: (ImageTable, ImageView) -> Unit
) : DataBindingAdapter<ImageTable>(
    DiffCallback()
) {

    class DiffCallback : DiffUtil.ItemCallback<ImageTable>() {
        override fun areItemsTheSame(oldItem: ImageTable, newItem: ImageTable): Boolean {
            return oldItem.url == newItem.url
        }

        override fun areContentsTheSame(oldItem: ImageTable, newItem: ImageTable): Boolean {
            return oldItem.url == newItem.url
        }
    }

    override fun onBindViewHolder(
        holder: DataBindingViewHolder<ImageTable>,
        @SuppressLint("RecyclerView") position: Int
    ) {
        super.onBindViewHolder(holder, position)

        holder.itemView.setOnClickListener {
            listener(getItem(position), it as ImageView)
        }
    }

    override fun getItemViewType(position: Int) = R.layout.item_saved_images

}
