package com.example.ktorfetchingdata.ui

import android.os.Bundle
import android.view.View
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.ktorfetchingdata.adapter.PostAdapter
import com.example.ktorfetchingdata.databinding.ActivityMainBinding
import com.example.ktorfetchingdata.ui.viewModel.MainViewModel
import com.example.ktorfetchingdata.utils.Resource
import com.google.android.material.snackbar.Snackbar
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collect
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val viewModel: MainViewModel by viewModels()

    @Inject
    lateinit var postAdapter: PostAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setUpRecyclerView()

        viewModel.getPost()
        lifecycleScope.launchWhenCreated {
            viewModel.state.collect {
                binding.apply {
                    when (it) {
                        is Resource.Success -> {
                            progressBar.visibility = View.GONE
                            rvPost.visibility = View.VISIBLE
                            postAdapter.differ.submitList(it.data)
                        }
                        is Resource.Error -> {
                            progressBar.visibility = View.GONE
                            rvPost.visibility = View.GONE
                            Snackbar.make(
                                View(this@MainActivity), "An error occurred: ${it.message} ",
                                Snackbar.LENGTH_LONG
                            ).show()
                        }
                        is Resource.Loading -> {
                            progressBar.visibility = View.VISIBLE
                            rvPost.visibility = View.GONE
                        }

                        is Resource.Empty -> {

                        }
                    }

                }
            }
        }
    }


    private fun setUpRecyclerView() {
        binding.apply {
            rvPost.apply {
                setHasFixedSize(true)
                rvPost.adapter = postAdapter
                rvPost.layoutManager = LinearLayoutManager(this@MainActivity)
            }

        }
    }


}