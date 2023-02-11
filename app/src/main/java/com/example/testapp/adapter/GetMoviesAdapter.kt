package com.example.testapp.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.testapp.R
import com.example.testapp.databinding.GetMoviesListItemLayoutBinding
import com.example.testapp.model.pojo.get_movies.Result
import com.example.testapp.utils.ItemClickListener

class GetMoviesAdapter(
    private var itemList: MutableList<Result>,
    private var context: Context,
    private var itemClickListener: ItemClickListener
) : RecyclerView.Adapter<GetMoviesAdapter.GetMoviesViewHolder>(){

    inner class GetMoviesViewHolder(
        val getMoviesListItemLayoutBinding: GetMoviesListItemLayoutBinding
    ): RecyclerView.ViewHolder(getMoviesListItemLayoutBinding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = GetMoviesViewHolder(
        DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.get_movies_list_item_layout,
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: GetMoviesViewHolder, position: Int) {
        holder.getMoviesListItemLayoutBinding.movies = itemList[position]
        holder.getMoviesListItemLayoutBinding.root.setOnClickListener {
            itemClickListener.onClick(it.rootView, itemList[position].id)
        }
    }

    override fun getItemCount() = itemList.size
}