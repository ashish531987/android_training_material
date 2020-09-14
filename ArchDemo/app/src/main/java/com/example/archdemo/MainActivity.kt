package com.example.archdemo

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.archdemo.view.RecyclerViewAdapter
import com.example.archdemo.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var context : Context
    private lateinit var viewModel : MainViewModel
    private lateinit var recyclerView: RecyclerView
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        context = this

        // init
        recyclerView = findViewById(R.id.rv_main)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.userLiveData.observe(this){
            recyclerViewAdapter = RecyclerViewAdapter(it)
            recyclerView.layoutManager = LinearLayoutManager(context)
            recyclerView.adapter = recyclerViewAdapter
        }
        viewModel.load()
    }
    fun onClicked(v: View){
        viewModel.addButtonClicked()
    }
}