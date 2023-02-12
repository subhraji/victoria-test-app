package com.example.testapp.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.testapp.R
import com.example.testapp.adapter.GetMoviesAdapter
import com.example.testapp.databinding.FragmentDashBoardBinding
import com.example.testapp.model.pojo.get_movies.Result
import com.example.testapp.model.repository.Outcome
import com.example.testapp.utils.Constants
import com.example.testapp.utils.ItemClickListener
import com.example.testapp.viewmodel.GetMoviesViewModel
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class DashBoardFragment : Fragment(), ItemClickListener {
    private lateinit var binding: FragmentDashBoardBinding
    private val mGetMoviesViewModel: GetMoviesViewModel by viewModels()

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
            inflater, R.layout.fragment_dash_board, container, false
        )
        val view: View = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //observe
        getMoviesObserve()

        binding.progressBar.visibility = View.VISIBLE
        mGetMoviesViewModel.getMovies("Bearer ${Constants.TOKEN}")
    }

    private fun getMoviesObserve(){
        mGetMoviesViewModel.response.observe(viewLifecycleOwner, Observer { outcome ->
            when(outcome){
                is Outcome.Success ->{
                    binding.progressBar.visibility = View.GONE
                    if(outcome.data != null){
                        fillTestRecycler(outcome.data?.results!!.toMutableList())
                        mGetMoviesViewModel.navigationComplete()
                    }else{
                        Toast.makeText(activity,"no data", Toast.LENGTH_SHORT).show()
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

    private fun fillTestRecycler(list: MutableList<Result>) {
        val gridLayoutManager = GridLayoutManager(activity, 2,LinearLayoutManager.VERTICAL, false)
        binding.moviesRecycler.apply {
            layoutManager = gridLayoutManager
            adapter = GetMoviesAdapter(list,requireActivity(),this@DashBoardFragment)
        }
    }

    override fun onClick(view: View, id: Int) {
        val directions = DashBoardFragmentDirections.actionDashBoardFragmentToMovieDetailsFragment(id)
        findNavController().navigate(directions)
    }


}