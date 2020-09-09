package com.example.recyclerviewdemo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val dataSet = listOf("Everton", "Manchester United", "Brighton", "Leeds", "Liverpool", "Chelsea", "Arsenal")


        viewManager = LinearLayoutManager(this)
        viewAdapter = MyAdapter(dataSet = dataSet)
        recyclerView = findViewById<RecyclerView>(R.id.recyclerView).apply {
            setHasFixedSize(true)
            layoutManager = viewManager
            adapter = viewAdapter
        }

    }

    class MyAdapter(private val dataSet: List<String>):RecyclerView.Adapter<MyAdapter.MyViewHolder>(){

        class MyViewHolder(val textView: TextView) : RecyclerView.ViewHolder(textView)

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
            val textView = LayoutInflater.from(parent.context).inflate(R.layout.my_text_view, parent, false) as TextView
            return MyViewHolder(textView)
        }

        override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
            holder.textView.text = dataSet.get(position)
        }

        override fun getItemCount(): Int = dataSet.size
    }
}