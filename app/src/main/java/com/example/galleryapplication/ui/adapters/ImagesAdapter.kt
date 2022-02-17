package com.example.galleryapplication.ui.adapters

import android.widget.ImageView
import androidx.recyclerview.widget.DiffUtil
import com.example.galleryapplication.R
import com.example.galleryapplication.models.Hit

class ImagesAdapter(private val listener: (Hit, ImageView) -> Unit) : DataBindingAdapter<Hit>(
    DiffCallback()
)  {

    class DiffCallback : DiffUtil.ItemCallback<Hit>() {
        override fun areItemsTheSame(oldItem: Hit, newItem: Hit): Boolean {
            return oldItem.webformatURL == newItem.webformatURL
        }

        override fun areContentsTheSame(oldItem: Hit, newItem: Hit): Boolean {
            return oldItem.webformatURL == newItem.webformatURL
        }
    }

    override fun onBindViewHolder(holder: DataBindingViewHolder<Hit>, position: Int) {
        super.onBindViewHolder(holder, position)
        holder.itemView.setOnClickListener {
            listener(getItem(position), it as ImageView)
        }
    }

    override fun getItemViewType(position: Int) = R.layout.item_images

}