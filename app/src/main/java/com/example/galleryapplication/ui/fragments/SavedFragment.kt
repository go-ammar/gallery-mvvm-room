package com.example.galleryapplication.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.transition.TransitionInflater
import com.example.galleryapplication.R
import com.example.galleryapplication.databinding.FragmentSavedBinding
import com.example.galleryapplication.models.ImageTable
import com.example.galleryapplication.ui.adapters.SaveImagesAdapter
import com.example.galleryapplication.ui.viewmodels.SavedImageViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.util.concurrent.TimeUnit

@AndroidEntryPoint
class SavedFragment : Fragment() {

    lateinit var binding: FragmentSavedBinding
    private val viewModel: SavedImageViewModel by viewModels()

    private lateinit var imageList: List<ImageTable>

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_saved, container, false)
//        imageDatabase = ImageDataBase(requireContext())
//        repository = ImageRepository(imageDatabase)
//        factory = SavedImageViewModelFactory(repository)
//        viewModel = ViewModelProvider(this, factory)[SavedImageViewModel::class.java]

        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        postponeEnterTransition(250, TimeUnit.MILLISECONDS)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        CoroutineScope(Dispatchers.Main).launch {
            viewModel.getAllImages().observe(viewLifecycleOwner, {

                imageList = it

                if (it.isNotEmpty()) {
                    val adapter =
                        SaveImagesAdapter { imageTable, image ->

                            image.transitionName = imageTable.url
                            val action =
                                SavedFragmentDirections.actionSavedFragmentToImageFragment2(
                                    imageTable.url,
                                    imageTable.id.toString()
                                )
                            val extras = FragmentNavigatorExtras(
                                image to imageTable.url
                            )
                            findNavController().navigate(action, extras)

                        }

                    if (binding.recyclerView.adapter == null)
                        binding.recyclerView.adapter = adapter

                    adapter.submitList(it)
                }
                //do action here, and now you have data in list (notes)
            })
        }

    }


}