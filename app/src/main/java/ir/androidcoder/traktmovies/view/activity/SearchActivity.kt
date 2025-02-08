package ir.androidcoder.traktmovies.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.GridLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import ir.androidcoder.traktmovies.R
import ir.androidcoder.traktmovies.databinding.ActivitySearchBinding
import ir.androidcoder.traktmovies.view.adapter.SearchAdapter
import ir.androidcoder.traktmovies.viewModel.SearchViewModel
import koleton.api.hideSkeleton
import koleton.api.loadSkeleton
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import ir.androidcoder.domain.base.Error

@AndroidEntryPoint
class SearchActivity : BaseActivity() {

    companion object{

        fun showSearch(context: Context){
            context.startActivity(Intent(context , SearchActivity::class.java))
        }

    }

    private val viewModel : SearchViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding = ActivitySearchBinding.inflate(layoutInflater, findViewById(R.id.container), true)

        initData(binding)
        observeData(binding)

    }

    override fun getLayoutResourceId(): Int = R.layout.activity_base

    private fun initData(binding: ActivitySearchBinding) {

        binding.apply {

            val adapter = SearchAdapter()

            rvSearch.adapter = adapter
            rvSearch.layoutManager = GridLayoutManager(this@SearchActivity , 2)

            adapter.addLoadStateListener {
                when(val refreshState = it.refresh){
                    is LoadState.Loading -> {
                        lifecycleScope.launch {
                            delay(1000)
                            rvSearch.loadSkeleton()
                        }
                    }

                    is LoadState.NotLoading -> {
                        rvSearch.hideSkeleton()
                    }

                    is LoadState.Error -> {
                        when(refreshState.error){
                            is Error.Internet -> {
                                Toast.makeText(this@SearchActivity, "اینترنت", Toast.LENGTH_SHORT).show()
                            }
                            is Error.Unknown -> {
                                Toast.makeText(this@SearchActivity, "نامشخص", Toast.LENGTH_SHORT).show()
                            }
                            is Error.ServerError -> {
                                Toast.makeText(this@SearchActivity, "سرور", Toast.LENGTH_SHORT).show()
                            }
                            else -> {
//                                ErrorActivity.showError(this@SearchActivity)
                            }
                        }
                    }
                }
            }

            edtSearch.addTextChangedListener(object : TextWatcher {
                override fun beforeTextChanged(
                    s: CharSequence?,
                    start: Int,
                    count: Int,
                    after: Int
                ) {

                }

                override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                    viewModel._searchQuery.value = s.toString()
                }

                override fun afterTextChanged(s: Editable?) {
                }

            })
        }

    }

    private fun observeData(binding: ActivitySearchBinding) {
        lifecycleScope.launch {
            viewModel.searchQuery.collectLatest{ searchQuery ->
                viewModel.searchMovies(searchQuery).collectLatest { data ->
                    (binding.rvSearch.adapter as SearchAdapter).submitData(data)
                }
            }
        }
    }

}