package com.example.galleryapplication.ui.adapters

import androidx.databinding.ViewDataBinding
import androidx.databinding.library.baseAdapters.BR
import androidx.recyclerview.widget.RecyclerView

class DataBindingViewHolder<T>(private val binding: ViewDataBinding) :
    RecyclerView.ViewHolder(binding.root) {


    fun bind(item: T) {

        binding.setVariable(BR.image, item)
        binding.setVariable(BR.hit, item)

        binding.executePendingBindings()
    }

}
