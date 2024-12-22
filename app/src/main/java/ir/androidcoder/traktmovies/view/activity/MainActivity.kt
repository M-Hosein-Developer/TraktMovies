package ir.androidcoder.traktmovies.view.activity

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import dagger.hilt.android.AndroidEntryPoint
import ir.androidcoder.traktmovies.databinding.ActivityMainBinding
import ir.androidcoder.traktmovies.util.BlurTransformation
import ir.androidcoder.traktmovies.view.adapter.NowPlayingAdapter
import ir.androidcoder.traktmovies.view.adapter.PopularAdapter
import ir.androidcoder.traktmovies.view.adapter.TopRateAdapter
import ir.androidcoder.traktmovies.view.adapter.UpcomingAdapter
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
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }


        initData()
        observeData()


    }

    private fun initData() {

        binding.apply {

            val transformation = MultiTransformation(CenterCrop(), RoundedCorners(55))
            val overlayDrawable = ColorDrawable(Color.parseColor("#91000000"))
            imgBackground.setForeground(overlayDrawable)

            //layout manager
            rvNowPlaying.layoutManager = LinearLayoutManager(this@MainActivity , LinearLayoutManager.HORIZONTAL , false)
            rvPopular.layoutManager = LinearLayoutManager(this@MainActivity , LinearLayoutManager.HORIZONTAL , false)
            rvTopRate.layoutManager = LinearLayoutManager(this@MainActivity , LinearLayoutManager.HORIZONTAL , false)
            rvUpcoming.layoutManager = LinearLayoutManager(this@MainActivity , LinearLayoutManager.HORIZONTAL , false)

            //setup adapter
            rvNowPlaying.adapter = NowPlayingAdapter(
                onCover = { cover  , title->
                    txtTitle.text = title
                    Glide.with(this@MainActivity)
                        .load(cover)
                        .transform(BlurTransformation(this@MainActivity, 15))
                        .centerCrop()
                        .into(imgBackground)
                    Glide.with(this@MainActivity)
                        .load(cover)
                        .apply(RequestOptions.bitmapTransform(transformation))
                        .into(imgMainCover)
                },
                onClick = { id ->
                    DetailActivity.showDetail(this@MainActivity , id)
                }
            )

            rvPopular.adapter = PopularAdapter{ id ->
                DetailActivity.showDetail(this@MainActivity , id)
            }
            rvTopRate.adapter = TopRateAdapter{ id ->
                DetailActivity.showDetail(this@MainActivity , id)
            }
            rvUpcoming.adapter = UpcomingAdapter{ id ->
                DetailActivity.showDetail(this@MainActivity , id)
            }

        }

    }

    private fun observeData(){
        lifecycleScope.launch {
            viewModel.mowPlaying.collectLatest { data ->
                (binding.rvNowPlaying.adapter as NowPlayingAdapter).submitData(data)
            }
        }

        lifecycleScope.launch {
            viewModel.popular.collectLatest { data ->
                (binding.rvPopular.adapter as PopularAdapter).submitData(data)
            }
        }

        lifecycleScope.launch {
            viewModel.topRate.collectLatest { data ->
                (binding.rvTopRate.adapter as TopRateAdapter).submitData(data)
            }
        }

        lifecycleScope.launch {
            viewModel.upcoming.collectLatest { data ->
                (binding.rvUpcoming.adapter as UpcomingAdapter).submitData(data)
            }
        }
    }
}