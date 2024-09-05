package com.online.zcg.ui

import android.os.Bundle
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.Button
import android.widget.ProgressBar
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.coordinatorlayout.widget.CoordinatorLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.snackbar.Snackbar
import com.online.zcg.R
import com.online.zcg.model.Block
import com.online.zcg.ui.adapter.BlockAdapter
import com.online.zcg.ui.viewmodel.BlockViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: BlockViewModel
    private lateinit var blockAdapter: BlockAdapter

    private lateinit var progressBar: ProgressBar
    private lateinit var recyclerView: RecyclerView
    private lateinit var errorTextView: TextView
    private lateinit var retryButton: Button
    private lateinit var layout: CoordinatorLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Initialize views
        progressBar = findViewById(R.id.progressBar)
        recyclerView = findViewById(R.id.recyclerView)
        errorTextView = findViewById(R.id.errorTextView)
        retryButton = findViewById(R.id.retryButton)

        recyclerView.layoutManager = LinearLayoutManager(this)

        viewModel = ViewModelProvider(this).get(BlockViewModel::class.java)

        // Observe the UI state from ViewModel
        viewModel.uiState.observe(this) { state ->
            when (state) {
                is UIState.Loading -> {
                    showLoadingState()
                }

                is UIState.Success -> {
                    showSuccessState(state.blocks)
                }
                is UIState.Error -> {
                    showErrorState(state.message)
                    if (state.message.contains("No internet connection")) {
                        // Show a "No Internet" message
                        Snackbar.make(findViewById(R.id.retryButton), "No internet connection", Snackbar.LENGTH_LONG).show()
                    } else {
                        // Show other error messages
                        Snackbar.make(findViewById(R.id.retryButton), state.message, Snackbar.LENGTH_LONG).show()
                    }
                }
            }
        }

        // Retry button click listener
        retryButton.setOnClickListener {
            viewModel.fetchBlocks()
        }

        // Initial data fetch
        viewModel.fetchBlocks()
    }

    // Show loading state (ProgressBar)
    private fun showLoadingState() {
        progressBar.visibility = VISIBLE
        recyclerView.visibility = GONE
        errorTextView.visibility = GONE
        retryButton.visibility = GONE
    }

    // Show success state (Data loaded)
    private fun showSuccessState(blocks: List<Block>) {
        progressBar.visibility = GONE
        recyclerView.visibility = VISIBLE
        errorTextView.visibility = GONE
        retryButton.visibility = GONE

        blockAdapter = BlockAdapter(blocks)
        recyclerView.adapter = blockAdapter
    }

    // Show error state (Error message and retry button)
    private fun showErrorState(message: String) {
        progressBar.visibility = GONE
        recyclerView.visibility = GONE
        errorTextView.visibility = VISIBLE
        retryButton.visibility = VISIBLE

        errorTextView.text = message
    }
}
