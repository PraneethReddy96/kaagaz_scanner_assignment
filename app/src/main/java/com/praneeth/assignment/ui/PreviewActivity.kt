package com.praneeth.assignment.ui

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.praneeth.assignment.R
import com.praneeth.assignment.adapters.previewAdapter
import com.praneeth.assignment.data.ImagesDao
import com.praneeth.assignment.data.ImagesEntity
import com.praneeth.assignment.data.ImagesRoomDataBase
import com.praneeth.assignment.databinding.ActivityPreviewBinding
import com.praneeth.assignment.repository.Repository
import com.praneeth.assignment.utils.onImageClicked
import com.praneeth.assignment.viewmodels.MyViewModel
import com.praneeth.assignment.viewmodels.MyViewModelFactory
import kotlinx.android.synthetic.main.activity_preview.*
import kotlinx.android.synthetic.main.activity_preview.rvAlbumsRecyclerView

class PreviewActivity : AppCompatActivity(),onImageClicked {

    lateinit var viewModel: MyViewModel
    lateinit var imagesDb: ImagesRoomDataBase
    lateinit var imagesDao: ImagesDao
    var dataList = mutableListOf<ImagesEntity>()
    lateinit var previewAdapter: previewAdapter
    lateinit var binding: ActivityPreviewBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPreviewBinding.inflate(layoutInflater)
        setContentView(binding.root)


        var searchText = intent.getStringExtra("name")
        binding.tvMyFolderName.text =searchText


        imagesDb = ImagesRoomDataBase.getImageDataBase(this)
        imagesDao = imagesDb.getImagesDao()

        val repository = Repository(imagesDao)
        val viewModelFactory = MyViewModelFactory(repository)

        viewModel = ViewModelProvider(this, viewModelFactory).get(MyViewModel::class.java)


        val llManager = GridLayoutManager(this, 2)
        previewAdapter = previewAdapter(dataList,this)
        binding.rvAlbumsRecyclerView.layoutManager = llManager
        binding.rvAlbumsRecyclerView.adapter = previewAdapter



        viewModel.displaySelectedItems(searchText!!).observe(this, Observer {

            dataList.clear()
            dataList.addAll(it)
            previewAdapter.notifyDataSetChanged()

        })



    }

    override fun getUri(imagesEntity: ImagesEntity) {

        var uri = imagesEntity.image
        val intent = Intent(this, FullViewActivity::class.java)
        intent.putExtra("uri",uri)
        startActivity(intent)

    }
}