package com.praneeth.assignment.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.praneeth.assignment.R
import com.praneeth.assignment.data.ImagesDao
import com.praneeth.assignment.data.ImagesRoomDataBase
import com.praneeth.assignment.repository.Repository
import com.praneeth.assignment.viewmodels.MyViewModel
import com.praneeth.assignment.viewmodels.MyViewModelFactory


class PreviewFragment() : Fragment(R.layout.fragment_preview) {

    lateinit var viewModel : MyViewModel
    lateinit var imagesDb : ImagesRoomDataBase
    lateinit var imagesDao : ImagesDao


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()

    }

    private fun initViews() {

        imagesDb= ImagesRoomDataBase.getImageDataBase(this.requireContext())
        imagesDao = imagesDb.getImagesDao()
        val repository = Repository(imagesDao)
        val viewModelFactory = MyViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MyViewModel::class.java)

    }

}