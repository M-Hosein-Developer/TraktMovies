package ir.androidcoder.traktmovies.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.paging.map
import dagger.hilt.android.AndroidEntryPoint
import ir.androidcoder.traktmovies.R
import ir.androidcoder.traktmovies.databinding.ActivityMainBinding
import ir.androidcoder.traktmovies.viewModel.MoviesViewModel
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel :  MoviesViewModel by viewModels()
    private lateinit var binding: ActivityMainBinding

    companion object{
        fun showHome(context: Context) {
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        lifecycleScope.launch {
            viewModel.mowPlaying.collectLatest {
                Log.v("testData" , it.toString())
            }
        }


    }
}