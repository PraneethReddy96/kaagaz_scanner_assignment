package com.praneeth.assignment.ui.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.View
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.praneeth.assignment.ui.PreviewActivity
import com.praneeth.assignment.R
import com.praneeth.assignment.adapters.albumsAdapter
import com.praneeth.assignment.data.AlbumEntity
import com.praneeth.assignment.data.ImagesDao
import com.praneeth.assignment.data.ImagesRoomDataBase
import com.praneeth.assignment.databinding.FragmentHomeBinding
import com.praneeth.assignment.repository.Repository
import com.praneeth.assignment.utils.onItemClicked
import com.praneeth.assignment.viewmodels.MyViewModel
import com.praneeth.assignment.viewmodels.MyViewModelFactory
import kotlinx.android.synthetic.main.fragment_home.*


class HomeFragment : Fragment(R.layout.fragment_home),onItemClicked {

    lateinit var viewModel: MyViewModel
    lateinit var imagesDb: ImagesRoomDataBase
    lateinit var imagesDao: ImagesDao
    var dataList = mutableListOf<AlbumEntity>()
    lateinit var albumsAdapter: albumsAdapter
    lateinit var binding: FragmentHomeBinding


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding = FragmentHomeBinding.bind(view)

        imagesDb = ImagesRoomDataBase.getImageDataBase(this.requireContext())
        imagesDao = imagesDb.getImagesDao()

        val repository = Repository(imagesDao)
        val viewModelFactory = MyViewModelFactory(repository)
        viewModel = ViewModelProvider(this, viewModelFactory).get(MyViewModel::class.java)



        val llManager = GridLayoutManager(requireContext(), 2)
        albumsAdapter = albumsAdapter(dataList,this)
        binding.rvFoldersRecyclerView.layoutManager = llManager
        binding.rvFoldersRecyclerView.adapter = albumsAdapter


        viewModel.displayAlbumsList().observe(requireActivity(), Observer {

            dataList.clear()
            dataList.addAll(it)
            albumsAdapter.notifyDataSetChanged()


            if (albumsAdapter.dataList.size == 0) {
                binding.rvFoldersRecyclerView.visibility = View.GONE
                binding.tvNoFilesMessage.visibility = View.VISIBLE
                binding.lottieAnimation.visibility = View.VISIBLE

            } else {
                binding.rvFoldersRecyclerView.visibility = View.VISIBLE
                binding.tvNoFilesMessage.visibility = View.GONE
                binding.lottieAnimation.visibility = View.GONE
            }

        })



    }

    override fun selectQuery(name: String) {

        val intent = Intent(requireActivity(), PreviewActivity::class.java)
        intent.putExtra("name",name)
        startActivity(intent)
    }


}