package com.example.galleryapplication.ui.fragments

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.example.galleryapplication.R
import com.example.galleryapplication.databinding.FragmentImageBinding
import com.example.galleryapplication.models.Hit
import com.example.galleryapplication.models.ImageTable
import com.example.galleryapplication.ui.viewmodels.SavedImageViewModel
import com.example.galleryapplication.utils.ImageStorageManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.ByteArrayOutputStream
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class ImageFragment : Fragment() {

    lateinit var binding: FragmentImageBinding
    private val args: ImageFragmentArgs by navArgs()

    private val viewModel: SavedImageViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_image, container,
            false
        )
        binding.url = args.imageUrl

        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        postponeEnterTransition(250, TimeUnit.MILLISECONDS)


        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getCount(args.imageUrl.id.toString())
//        CoroutineScope(Dispatchers.Main).launch {
        viewModel.count.observe(viewLifecycleOwner, {
            Log.d("TAG", "onViewCreated: "+it)
            if (it) {
                binding.savedBtn.setImageResource(R.drawable.saved_icon)
            } else {
                binding.savedBtn.setImageResource(R.drawable.save_icon)
            }
        })
//        }

        binding.savedBtn.setOnClickListener {
            ifExists(args.imageUrl)
        }

    }

    override fun onDestroy() {
        super.onDestroy()
        exitTransition
    }

    private fun ifExists(url: Hit) {

        CoroutineScope(Dispatchers.Main).launch {
            viewModel.count.observe(viewLifecycleOwner, {
                val bitmap = (binding.image.drawable as BitmapDrawable).bitmap
                if (it) {
                    deleteImage(url)
                } else {
                    insertData(url, bitmap)
                }
            })
        }

    }

    //CREATE (INSERT)
    private fun insertData(url: Hit, bitmap: Bitmap) {

        val image = ImageTable(url = url.webformatURL!!, url.id!!)
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.insertImage(image).also {
                val baos = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos)
                ImageStorageManager.saveToInternalStorage(
                    requireContext(),
                    bitmap,
                    url.id.toString()
                )
                binding.savedBtn.setImageResource(R.drawable.saved_icon)
                Toast.makeText(context, "Added to favs", Toast.LENGTH_SHORT).show()

            }
        }
    }


    //DELETE (delete one row note)
    private fun deleteImage(url: Hit) {

        CoroutineScope(Dispatchers.Main).launch {
            viewModel.deleteImage(url.id.toString()).also {
                binding.savedBtn.setImageResource(R.drawable.save_icon)
                ImageStorageManager.deleteImageFromInternalStorage(
                    requireContext(),
                    url.id.toString()
                )
                Toast.makeText(context, "Removed from favs", Toast.LENGTH_SHORT).show()
            }
        }
    }

}