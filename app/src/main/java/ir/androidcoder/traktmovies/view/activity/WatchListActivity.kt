package ir.androidcoder.traktmovies.view.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ir.androidcoder.traktmovies.R
import ir.androidcoder.traktmovies.databinding.ActivityWatchListBinding
import ir.androidcoder.traktmovies.view.adapter.WatchListAdapter
import ir.androidcoder.traktmovies.viewModel.WatchListViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class WatchListActivity : BaseActivity() {

    private val viewModel : WatchListViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding = ActivityWatchListBinding.inflate(layoutInflater, findViewById(R.id.container), true)

        initView(binding)
        observeData(binding)

    }

    private fun initView(binding: ActivityWatchListBinding) {

        binding.apply {

            rvWatchList.layoutManager = GridLayoutManager(this@WatchListActivity , 2)

            rvWatchList.adapter = WatchListAdapter()

        }

    }

    private fun observeData(binding: ActivityWatchListBinding) {

        lifecycleScope.launch {
            viewModel.getAllWatchList.collectLatest { data ->
                (binding.rvWatchList.adapter as WatchListAdapter).submitData(data)
            }
        }

        lifecycleScope.launch {
            viewModel.searchQuery.collectLatest { searchQuery ->
                viewModel.getMoviesBySearch(searchQuery).collectLatest { data ->
                    (binding.rvWatchList.adapter as WatchListAdapter).submitData(data)
                }
            }
        }

    }

    override fun getLayoutResourceId(): Int = R.layout.activity_base
}