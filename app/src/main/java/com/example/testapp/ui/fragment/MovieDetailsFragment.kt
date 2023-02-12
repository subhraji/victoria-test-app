package com.example.testapp.ui.fragment

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.testapp.R
import com.example.testapp.databinding.FragmentDashBoardBinding
import com.example.testapp.databinding.FragmentMovieDetailsBinding
import com.example.testapp.model.repository.Outcome
import com.example.testapp.utils.Constants
import com.example.testapp.viewmodel.GetMovieDetailsViewModel
import com.example.testapp.viewmodel.GetMoviesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailsFragment : Fragment() {
    private lateinit var binding: FragmentMovieDetailsBinding
    private val args: MovieDetailsFragmentArgs by navArgs()
    private val mGetMovieDetailsViewModel: GetMovieDetailsViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
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
        val id = args.id
        binding.backBtn.setOnClickListener {
            findNavController().navigateUp()
        }

        //observe
        getMovieDetailsObserve()

        binding.progressBar.visibility = View.VISIBLE
        mGetMovieDetailsViewModel.getMovieDetails("Bearer ${Constants.TOKEN}", id)
    }

    private fun getMovieDetailsObserve(){
        mGetMovieDetailsViewModel.response.observe(viewLifecycleOwner, Observer { outcome ->
            when(outcome){
                is Outcome.Success ->{
                    binding.progressBar.visibility = View.GONE
                    if(outcome.data != null){
                        binding.details = outcome.data
                        mGetMovieDetailsViewModel.navigationComplete()
                    }else{
                        Toast.makeText(activity,"no data found", Toast.LENGTH_SHORT).show()
                    }
                }
                is Outcome.Failure<*> -> {
                    Toast.makeText(activity,outcome.e.message, Toast.LENGTH_SHORT).show()
                    binding.progressBar.visibility = View.GONE

                    outcome.e.printStackTrace()
                    Log.i("status",outcome.e.cause.toString())
                }
                is Outcome.Progress ->{

                }
                null ->{

                }
            }
        })
    }

}