package com.example.galleryapplication.ui.fragments

import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.navArgs
import androidx.transition.TransitionInflater
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.galleryapplication.R
import com.example.galleryapplication.databinding.FragmentSavedImageBinding
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
class SavedImageFragment : Fragment() {

    val args: SavedImageFragmentArgs by navArgs()
    lateinit var binding: FragmentSavedImageBinding
    private val viewModel: SavedImageViewModel by viewModels()


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_saved_image, container, false)

        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        postponeEnterTransition(250, TimeUnit.MILLISECONDS)

        binding.image.transitionName = args.url

//        imageDatabase = ImageDataBase(requireContext())
//        repository = ImageRepository(imageDatabase)
//        factory = SavedImageViewModelFactory(repository)
//        viewModel = ViewModelProvider(this, factory)[SavedImageViewModel::class.java]

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        viewModel.getCount(args.id)

        val bitmap = ImageStorageManager.getImageFromInternalStorage(requireContext(), args.id)
        Glide.with(requireContext()).asBitmap().load(bitmap).into(object : CustomTarget<Bitmap?>() {
            override fun onLoadCleared(placeholder: Drawable?) {}
            override fun onResourceReady(
                resource: Bitmap,
                transition: Transition<in Bitmap?>?
            ) {
                binding.image.setImageBitmap(bitmap)
            }
        })

        CoroutineScope(Dispatchers.Main).launch {
            viewModel.count.observe(viewLifecycleOwner, {
                if (it) {
                    binding.savedBtn.setImageResource(R.drawable.saved_icon)
                } else {
                    binding.savedBtn.setImageResource(R.drawable.save_icon)
                }
            })


        }

        binding.savedBtn.setOnClickListener {
            ifExists(args.id)
        }

    }

    private fun ifExists(url: String) {
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.count.observe(viewLifecycleOwner, {
                val bitmap = (binding.image.drawable as BitmapDrawable).bitmap
                if (it) {
                    deleteImage()
                } else {
                    insertData(bitmap)
                }
            })

        }

    }

    //CREATE (INSERT)
    private fun insertData(bitmap: Bitmap) {

        val image = ImageTable(url = args.url, args.id.toInt())
        CoroutineScope(Dispatchers.Main).launch {
            viewModel.insertImage(image).also {
                val baos = ByteArrayOutputStream()
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, baos)
                ImageStorageManager.saveToInternalStorage(
                    requireContext(),
                    bitmap,
                    args.id
                )
                binding.savedBtn.setImageResource(R.drawable.saved_icon)
                Toast.makeText(context, "Added to favs", Toast.LENGTH_SHORT).show()

            }
        }
    }


    //DELETE (delete one row note)
    private fun deleteImage() {

        CoroutineScope(Dispatchers.Main).launch {
            viewModel.deleteImage(args.id).also {
                binding.savedBtn.setImageResource(R.drawable.save_icon)
                ImageStorageManager.deleteImageFromInternalStorage(
                    requireContext(),
                    args.id
                )
                Toast.makeText(context, "Removed from favs", Toast.LENGTH_SHORT).show()
            }
        }
    }
}