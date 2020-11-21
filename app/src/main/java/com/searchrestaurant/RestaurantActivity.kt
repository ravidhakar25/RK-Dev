package com.searchrestaurant

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doOnTextChanged
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_restaurant.*

class RestaurantActivity : AppCompatActivity() {
    private val mSRViewModel: SRViewModel by lazy {
        ViewModelProvider(this).get(SRViewModel::class.java)
    }
    private val mRestaurantAdapter = RestaurantAdapter()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_restaurant)
        bindAdapter()
        listeners()
        mSRViewModel.nearBySearch()
    }

    private fun bindAdapter() {
        mRecyclerView.layoutManager = LinearLayoutManager(this)
        mRecyclerView.adapter = mRestaurantAdapter
    }

    private fun listeners() {
        mSRViewModel.mSearchResult.observe(this, {
            mRestaurantAdapter.updateValues(if (it != null) it.results else ArrayList())
        })
        edit_search.doOnTextChanged { text, start, before, count ->
            if (text.toString().isNotBlank())
                mSRViewModel.nearBySearch(text.toString())
            else
                mSRViewModel.nearBySearch()
        }
    }
}