package com.example.testapp.adapter

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.example.testapp.R
import com.example.testapp.utils.Constants

@BindingAdapter("image")
fun loadImage(view: ImageView, url: String?){
    Glide.with(view)
        .load(Constants.IMAGE_URL+url)
        .placeholder(R.color.color_grey)
        .into(view)
}