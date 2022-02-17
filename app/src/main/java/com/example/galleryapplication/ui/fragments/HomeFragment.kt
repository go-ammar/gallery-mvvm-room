package com.example.galleryapplication.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.SearchView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.FragmentNavigatorExtras
import androidx.navigation.fragment.findNavController
import androidx.transition.TransitionInflater
import com.example.galleryapplication.R
import com.example.galleryapplication.databinding.FragmentHomeBinding
import com.example.galleryapplication.models.Hit
import com.example.galleryapplication.ui.adapters.ImagesAdapter
import com.example.galleryapplication.ui.viewmodels.ImagesViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.*
import java.util.concurrent.TimeUnit


@AndroidEntryPoint
class HomeFragment : Fragment() {
    private lateinit var binding: FragmentHomeBinding

    private lateinit var imagesModelList: List<Hit>
    private val imagesViewModel : ImagesViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = DataBindingUtil.inflate(inflater, R.layout.fragment_home, container, false)

        sharedElementEnterTransition =
            TransitionInflater.from(context).inflateTransition(android.R.transition.move)
        postponeEnterTransition(250, TimeUnit.MILLISECONDS)

        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

//        val imagesViewModel = ViewModelProvider(this).get(ImagesViewModel::class.java)

        val adapter = ImagesAdapter { hit, image ->

            val action =
                HomeFragmentDirections.actionHomeFragmentToImageFragment(
                    hit
                )
            val extras = FragmentNavigatorExtras(
                image to hit.webformatURL!!
            )
            findNavController().navigate(action, extras)

        }


        binding.recyclerView.adapter = adapter


        binding.darkMode.setOnClickListener {
            if (binding.darkMode.isChecked)
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
            else
                AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
        }


        binding.searchView.setOnQueryTextListener(object : SearchView.OnQueryTextListener {

            private val coroutineScope: CoroutineScope = CoroutineScope(Dispatchers.Main)
            private var job: Job? = null
            override fun onQueryTextChange(s: String): Boolean {
                if (binding.searchView.hasFocus()) {
                    job?.cancel()
                    job = coroutineScope.launch {
                        delay(1000)
                        imagesViewModel.searchApi(
                            binding.searchView.query.toString().replace(" ", "+")
                        )
                    }

                }
                return true
            }

            override fun onQueryTextSubmit(s: String): Boolean {
                return true
            }
        })

        imagesViewModel.getImagesListObserver()?.observe(viewLifecycleOwner, {
            imagesModelList = emptyList()
            if (it.isEmpty()) {
                Toast.makeText(context, "No Images found", Toast.LENGTH_SHORT).show()
            }
            imagesModelList = it
            adapter.submitList(it)
            binding.recyclerView.scheduleLayoutAnimation()
        })

    }

}