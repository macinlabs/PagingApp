package com.app.assestmentloadingpager

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity(),OnItemClickListener {
    private lateinit var viewModel: ItemViewModel
    private val adapter = ItemAdapter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val apiService = RetrofitClient.create()
        val viewModelFactory = ItemViewModelFactory(apiService)
        viewModel = ViewModelProvider(this, viewModelFactory).get(ItemViewModel::class.java)

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerView)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = adapter

        lifecycleScope.launch {
            viewModel.getItems().collect { pagingData ->
                adapter.submitData(pagingData)
            }
        }
    }

    override fun onItemClick(item: Item) {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("itemId", item.id)
        intent.putExtra("itemTitle", item.title)
        intent.putExtra("itemBody", item.body)
        startActivity(intent)
    }
}