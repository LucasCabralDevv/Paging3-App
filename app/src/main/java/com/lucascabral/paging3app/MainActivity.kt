package com.lucascabral.paging3app

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.lucascabral.paging3app.databinding.ActivityMainBinding
import kotlinx.coroutines.flow.collectLatest

class MainActivity : AppCompatActivity() {

    private lateinit var viewBinding: ActivityMainBinding
    private lateinit var rickMortyAdapter: RickMortyAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)

        initRecyclerView()
        initViewModel()
    }

    private fun initRecyclerView() {
        viewBinding.mainRecyclerView.apply {
            layoutManager = LinearLayoutManager(this@MainActivity)
            val itemDecoration = DividerItemDecoration(applicationContext, DividerItemDecoration.VERTICAL)
            addItemDecoration(itemDecoration)
            rickMortyAdapter = RickMortyAdapter()
            adapter = rickMortyAdapter
        }
    }

    private fun initViewModel() {
        val viewModel = ViewModelProvider(this).get(MainActivityViewModel::class.java)
        lifecycleScope.launchWhenCreated {
            viewModel.getListData().collectLatest {
                rickMortyAdapter.submitData(it)
            }
        }
    }
}