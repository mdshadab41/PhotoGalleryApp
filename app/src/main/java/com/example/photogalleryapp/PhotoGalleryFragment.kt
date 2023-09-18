package com.example.photogalleryapp

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.GridLayoutManager
import com.example.photogalleryapp.databinding.FragmentPhotoGalleryBinding
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch



private const val TAG = "PhotoGalleryFragment"
class PhotoGalleryFragment: Fragment() {
    private var _binding: FragmentPhotoGalleryBinding? = null
    private val binding
        get() = checkNotNull(_binding){ "Cannot access binding because it is null. Is the view visible?"

        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =
            FragmentPhotoGalleryBinding.inflate(inflater, container, false)
        binding.photoGrid.layoutManager = GridLayoutManager(context,3)
        return  binding.root
    }
private val photoGalleryViewModel: PhotoGalleryViewModel by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewLifecycleOwner.lifecycleScope.launch {

//            try {
//                val response = PhotoRepository().fetchPhotos()
//                Log.d(TAG, "Response received: $response")
//            }catch (ex: Exception){
//                Log.e(TAG, "Failed to fetch gallery items", ex)
//            }
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED){
                photoGalleryViewModel.galleryItems.collect{items ->
                    //Log.d(TAG, "Response received: $items")
                    binding.photoGrid.adapter = PhotoListAdapter(items)
                }
            }
        }
    }

}