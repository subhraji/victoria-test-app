package com.example.testapp.ui.fragment

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.fragment.findNavController
import com.example.testapp.R
import com.example.testapp.databinding.FragmentDashBoardBinding
import com.example.testapp.databinding.FragmentMovieDetailsBinding

class MovieDetailsFragment : Fragment() {
    private lateinit var binding: FragmentMovieDetailsBinding
    private var param1: String? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString("")
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater, R.layout.fragment_movie_details, container, false
        )
        val view: View = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.backBtn.setOnClickListener {
            findNavController().navigateUp()
        }
    }

}