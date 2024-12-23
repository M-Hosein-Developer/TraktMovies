package ir.androidcoder.traktmovies.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import ir.androidcoder.traktmovies.R
import ir.androidcoder.traktmovies.databinding.ActivitySearchBinding
import ir.androidcoder.traktmovies.viewModel.SearchViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

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

    private fun initData(binding: ActivitySearchBinding) {


    }

    private fun observeData(binding: ActivitySearchBinding) {
        binding.apply {

            lifecycleScope.launch {
                viewModel.searchMovies("god").collectLatest { data ->
                    Log.v("testSearch1" , data.toString())
                }
            }

        }
    }

    override fun getLayoutResourceId(): Int  = R.layout.activity_base


}