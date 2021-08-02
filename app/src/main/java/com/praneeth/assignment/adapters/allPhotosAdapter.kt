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
import com.praneeth.assignment.utils.onImageClicked

class allPhotosAdapter(val dataList: MutableList<ImagesEntity>, val onImageClicked: onImageClicked) :
    RecyclerView.Adapter<allPhotosViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): allPhotosViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.all_photos_item_layout, parent, false)
        return allPhotosViewHolder(view)
    }

    override fun onBindViewHolder(holder: allPhotosViewHolder, position: Int) {


        holder.image.load(Uri.parse(dataList[position].image))

        holder.image.setOnClickListener(View.OnClickListener {

            onImageClicked.getUri(dataList[position])

        })

    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}

class allPhotosViewHolder(val view: View) : RecyclerView.ViewHolder(view) {


    var image = view.findViewById<ImageView>(R.id.ivAllImages)


}
