package com.praneeth.assignment.adapters

import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.praneeth.assignment.R
import com.praneeth.assignment.data.AlbumEntity
import com.praneeth.assignment.utils.onItemClicked

class albumsAdapter(val dataList: MutableList<AlbumEntity>,val itemClicked: onItemClicked) : RecyclerView.Adapter<AlbumsViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumsViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.folders_item_layout,parent,false)
        return AlbumsViewHolder(view)
    }

    override fun onBindViewHolder(holder: AlbumsViewHolder, position: Int) {

        holder.image.load(Uri.parse(dataList[position].image))

        holder.name.text = dataList[position].name

        holder.container.setOnClickListener(View.OnClickListener {

            itemClicked.selectQuery(dataList[position].name.toString())

        })


    }

    override fun getItemCount(): Int {
        return dataList.size
    }
}

class AlbumsViewHolder(val view: View) : RecyclerView.ViewHolder(view){

    var image = view.findViewById<ImageView>(R.id.ivAlbumImage)
    var name = view.findViewById<TextView>(R.id.tvFolderName)
    var container = view.findViewById<LinearLayout>(R.id.llFolderContainer)


}