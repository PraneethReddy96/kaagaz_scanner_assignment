package com.praneeth.assignment.ui.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.praneeth.assignment.ui.FullViewActivity
import com.praneeth.assignment.R
import com.praneeth.assignment.adapters.allPhotosAdapter
import com.praneeth.assignment.data.ImagesDao
import com.praneeth.assignment.data.ImagesEntity
import com.praneeth.assignment.data.ImagesRoomDataBase
import com.praneeth.assignment.repository.Repository
import com.praneeth.assignment.utils.onImageClicked
import com.praneeth.assignment.viewmodels.MyViewModel
import com.praneeth.assignment.viewmodels.MyViewModelFactory
import kotlinx.android.synthetic.main.fragment_preview.*


class PreviewFragment() : Fragment(R.layout.fragment_preview), onImageClicked {

    lateinit var viewModel: MyViewModel
    lateinit var imagesDb: ImagesRoomDataBase
    lateinit var imagesDao: ImagesDao
    var dataList = mutableListOf<ImagesEntity>()
    lateinit var allPhotosAdapter: allPhotosAdapter
    lateinit var rvAllPhotosRecyclerView : RecyclerView


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        imagesDb = ImagesRoomDataBase.getImageDataBase(this.requireContext())
        imagesDao = imagesDb.getImagesDao()
        val repository = Repository(imagesDao)
        val viewModelFactory = MyViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MyViewModel::class.java)



        rvAllPhotosRecyclerView= view.findViewById(R.id.rvAllPhotosRecyclerView)

        val llManager = GridLayoutManager(requireContext(), 2)
        allPhotosAdapter =allPhotosAdapter(dataList, this)
        rvAllPhotosRecyclerView.layoutManager = llManager
        rvAllPhotosRecyclerView.adapter = allPhotosAdapter



        viewModel.getAllImages().observe(requireActivity(), Observer {


            dataList.clear()
            dataList.addAll(it)
            allPhotosAdapter.notifyDataSetChanged()


        })


    }

    override fun getUri(imagesEntity: ImagesEntity) {

        var uri = imagesEntity.image
        val intent = Intent(requireContext(), FullViewActivity::class.java)
        intent.putExtra("uri",uri)
        startActivity(intent)

    }

}