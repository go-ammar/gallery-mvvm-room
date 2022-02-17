package com.example.galleryapplication.ui.adapters

import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.Priority
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.galleryapplication.R
import com.example.galleryapplication.models.ImageTable
import com.example.galleryapplication.utils.ImageStorageManager
import kotlinx.coroutines.MainScope
import kotlinx.coroutines.launch


class BindingAdapter {

    companion object {

        @JvmStatic
        @BindingAdapter
            ("loadUrl", "singleImage")
        fun ImageView.loadImage(hit: String, singleImage: Boolean) {
            val iv = this
            MainScope().launch {
                Glide.with(context)
                    .load(hit)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .override(-1, -1)
                    .priority(Priority.HIGH)
                    .thumbnail(
                        if (singleImage) {
                            Glide.with(context).load(R.raw.loading_gif)
                        } else {
                            null
                        }
                    )
                    .into(iv)
            }

        }

        @JvmStatic
        @BindingAdapter
            ("loadBitmap")
        fun ImageView.loadImageWithBitmap(image: ImageTable) {
            val iv = this
            MainScope().launch {

                try {
                    val bitmap = ImageStorageManager.getImageFromInternalStorage(
                        context,
                        image.id.toString()
                    )
                    Glide.with(context).asBitmap().load(bitmap).into(object : CustomTarget<Bitmap?>() {
                        override fun onLoadCleared(placeholder: Drawable?) {}
                        override fun onResourceReady(
                            resource: Bitmap,
                            transition: Transition<in Bitmap?>?
                        ) {
                            iv.setImageBitmap(bitmap)
                        }

                    })
                } catch ( e: NoSuchFileException){

                }


            }

        }


    }
}