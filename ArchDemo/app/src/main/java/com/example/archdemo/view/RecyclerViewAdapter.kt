package com.example.archdemo.view

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.archdemo.R
import com.example.archdemo.model.User
import kotlinx.android.synthetic.main.item.view.*

class RecyclerViewAdapter(private val dataSet : List<User>) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val icIV = itemView.findViewById<ImageView>(R.id.imgView_icon)
        val titleTV = itemView.findViewById<TextView>(R.id.txtView_title)
        val descTV = itemView.findViewById<TextView>(R.id.txtView_description)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return ViewHolder(LayoutInflater.from(parent.context).
        inflate(R.layout.item, parent, false))
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val user = dataSet[position]
        val viewHolder = holder as ViewHolder
        viewHolder.titleTV.text = user.title
        viewHolder.descTV.text = user.description
    }

    override fun getItemCount() = dataSet.size
}