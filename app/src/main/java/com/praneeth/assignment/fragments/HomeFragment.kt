package com.praneeth.assignment.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import androidx.fragment.app.Fragment
import android.view.View
import android.view.ViewGroup
import androidx.camera.core.Preview
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.praneeth.assignment.PreviewActivity
import com.praneeth.assignment.R
import com.praneeth.assignment.adapters.albumsAdapter
import com.praneeth.assignment.data.AlbumEntity
import com.praneeth.assignment.data.ImagesDao
import com.praneeth.assignment.data.ImagesRoomDataBase
import com.praneeth.assignment.repository.Repository
import com.praneeth.assignment.utils.onItemClicked
import com.praneeth.assignment.viewmodels.MyViewModel
import com.praneeth.assignment.viewmodels.MyViewModelFactory
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class HomeFragment : Fragment(R.layout.fragment_home),onItemClicked {

    lateinit var viewModel: MyViewModel
    lateinit var imagesDb: ImagesRoomDataBase
    lateinit var imagesDao: ImagesDao
    var dataList = mutableListOf<AlbumEntity>()
    lateinit var albumsAdapter: albumsAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        imagesDb = ImagesRoomDataBase.getImageDataBase(this.requireContext())
        imagesDao = imagesDb.getImagesDao()

        val repository = Repository(imagesDao)
        val viewModelFactory = MyViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MyViewModel::class.java)


        val llManager = GridLayoutManager(requireContext(), 2)
        albumsAdapter = albumsAdapter(dataList,this)
        rvFoldersRecyclerView.layoutManager = llManager
        rvFoldersRecyclerView.adapter = albumsAdapter




        viewModel.displayAlbumsList().observe(requireActivity(), Observer {

            dataList.clear()
            dataList.addAll(it)
            albumsAdapter.notifyDataSetChanged()


            if (albumsAdapter.dataList.size == 0) {
                rvFoldersRecyclerView?.visibility = View.GONE
                tvNoFilesMessage?.visibility = View.VISIBLE
                lottieAnimation.visibility = View.VISIBLE

            } else {
                rvFoldersRecyclerView?.visibility = View.VISIBLE
                tvNoFilesMessage?.visibility = View.GONE
                lottieAnimation?.visibility = View.GONE
            }

        })



    }

    override fun selectQuery(name: String) {

        val intent = Intent(requireActivity(),PreviewActivity::class.java)
        intent.putExtra("name",name)
        startActivity(intent)
    }


}