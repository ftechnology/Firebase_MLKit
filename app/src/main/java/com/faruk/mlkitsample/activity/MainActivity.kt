package com.faruk.mlkitsample.activity

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.LinearLayoutManager
import com.faruk.mlkitsample.Adapter.HomeListAdapter
import com.faruk.mlkitsample.R

import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.content_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        setSupportActionBar(toolbar)
        supportActionBar?.setTitle(R.string.firebase_ml_kit)

        initRecyclerView()
    }

    private fun initRecyclerView() {
        main_activity_recycler_view.layoutManager = LinearLayoutManager(this)
        var adapter = HomeListAdapter(this)
        main_activity_recycler_view.adapter = adapter;
        adapter.loadData()
    }
}
