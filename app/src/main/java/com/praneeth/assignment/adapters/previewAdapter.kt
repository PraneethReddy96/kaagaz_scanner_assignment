package com.praneeth.assignment.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.praneeth.assignment.R
import com.praneeth.assignment.data.ImagesEntity

class previewAdapter(val dataList: MutableList<ImagesEntity>) : RecyclerView.Adapter<previewViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): previewViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.preview_item_layout,parent,false)
        return previewViewHolder(view)
    }

    override fun onBindViewHolder(holder: previewViewHolder, position: Int) {


        holder.image.load(Uri.parse(dataList[position].image))


    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}

class previewViewHolder(val view: View) : RecyclerView.ViewHolder(view) {


    var image = view.findViewById<ImageView>(R.id.ivPreviewImage)
    var name = view.findViewById<TextView>(R.id.tvPreviewName)



}
