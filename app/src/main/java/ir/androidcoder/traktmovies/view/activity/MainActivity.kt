package ir.androidcoder.traktmovies.view.activity

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.LoadState
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import dagger.hilt.android.AndroidEntryPoint
import ir.androidcoder.traktmovies.R
import ir.androidcoder.traktmovies.databinding.ActivityMainBinding
import ir.androidcoder.traktmovies.util.BlurTransformation
import ir.androidcoder.traktmovies.view.adapter.NowPlayingAdapter
import ir.androidcoder.traktmovies.view.adapter.PopularAdapter
import ir.androidcoder.traktmovies.view.adapter.TopRateAdapter
import ir.androidcoder.traktmovies.view.adapter.UpcomingAdapter
import ir.androidcoder.traktmovies.viewModel.MoviesViewModel
import koleton.api.hideSkeleton
import koleton.api.loadSkeleton
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainActivity : BaseActivity() {

    private val viewModel :  MoviesViewModel by viewModels()
//    private lateinit var binding: ActivityMainBinding

    companion object{
        fun showHome(context: Context) {
            context.startActivity(Intent(context, MainActivity::class.java))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding = ActivityMainBinding.inflate(layoutInflater, findViewById(R.id.container), true)

        initData(binding)
        observeData(binding)


    }

    private fun showSkeleton(binding: ActivityMainBinding) {
        binding.rvNowPlaying.loadSkeleton(R.layout.now_playing_item)
    }

    override fun getLayoutResourceId(): Int = R.layout.activity_base

    private fun initData(binding: ActivityMainBinding) {

        binding.apply {

            val overlayDrawable = ColorDrawable(Color.parseColor("#91000000"))
            imgBackground.setForeground(overlayDrawable)

            //layout manager
            rvNowPlaying.layoutManager = LinearLayoutManager(this@MainActivity , LinearLayoutManager.HORIZONTAL , false)
            rvPopular.layoutManager = LinearLayoutManager(this@MainActivity , LinearLayoutManager.HORIZONTAL , false)
            rvTopRate.layoutManager = LinearLayoutManager(this@MainActivity , LinearLayoutManager.HORIZONTAL , false)
            rvUpcoming.layoutManager = LinearLayoutManager(this@MainActivity , LinearLayoutManager.HORIZONTAL , false)

            //setup adapter
            setupNowPlayingAdapter(binding)
            setupPopularAdapter(binding)
            setupTopRateAdapter(binding)
            setupUpcomingAdapter(binding)
        }
    }

    private fun setupNowPlayingAdapter(binding: ActivityMainBinding){
        val transformation = MultiTransformation(CenterCrop(), RoundedCorners(55))
        binding.apply {
            val adapter = NowPlayingAdapter(
                onCover = { cover, title ->
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
                    DetailActivity.showDetail(this@MainActivity, id)
                }
            )

            rvNowPlaying.adapter = adapter

            adapter.addLoadStateListener { loadState ->
                when (loadState.refresh) {
                    is LoadState.Loading -> {
                        Log.v("testAdapter", "1")
                        rvNowPlaying.loadSkeleton(R.layout.now_playing_item)
                        imgMainCover.loadSkeleton()
                        imgBackground.loadSkeleton()
                    }

                    is LoadState.Error -> {
                        Log.v("testAdapter", "2")
                        rvNowPlaying.loadSkeleton(R.layout.now_playing_item)
                        imgMainCover.loadSkeleton()
                        imgBackground.loadSkeleton()
                    }

                    is LoadState.NotLoading -> {
                        Log.v("testAdapter", "3")
                        rvNowPlaying.hideSkeleton()
                        imgMainCover.hideSkeleton()
                        imgBackground.hideSkeleton()
                    }
                }
            }
        }

    }

    private fun setupPopularAdapter(binding: ActivityMainBinding){
        binding.apply {

            val adapter = PopularAdapter{ id -> DetailActivity.showDetail(this@MainActivity , id) }

            rvPopular.adapter = adapter

            adapter.addLoadStateListener { loadState ->
                when (loadState.refresh) {
                    is LoadState.Loading -> {
                        Log.v("testAdapter", "1")
                        rvPopular.loadSkeleton(R.layout.now_playing_item)
                    }

                    is LoadState.Error -> {
                        Log.v("testAdapter", "2")
                        rvPopular.loadSkeleton(R.layout.now_playing_item)
                    }

                    is LoadState.NotLoading -> {
                        Log.v("testAdapter", "3")
                        rvPopular.hideSkeleton()
                    }
                }
            }

        }
    }

    private fun setupTopRateAdapter(binding: ActivityMainBinding){
        binding.apply {

            val adapter = TopRateAdapter{ id -> DetailActivity.showDetail(this@MainActivity , id) }

            rvTopRate.adapter = adapter

            adapter.addLoadStateListener { loadState ->
                when (loadState.refresh) {
                    is LoadState.Loading -> {
                        Log.v("testAdapter", "1")
                        rvTopRate.loadSkeleton(R.layout.now_playing_item)
                    }

                    is LoadState.Error -> {
                        Log.v("testAdapter", "2")
                        rvTopRate.loadSkeleton(R.layout.now_playing_item)
                    }

                    is LoadState.NotLoading -> {
                        Log.v("testAdapter", "3")
                        rvTopRate.hideSkeleton()
                    }
                }
            }

        }
    }

    private fun setupUpcomingAdapter(binding: ActivityMainBinding){
        binding.apply {

            val adapter = UpcomingAdapter{ id -> DetailActivity.showDetail(this@MainActivity , id) }

            rvUpcoming.adapter = adapter

            adapter.addLoadStateListener { loadState ->
                when (loadState.refresh) {
                    is LoadState.Loading -> {
                        Log.v("testAdapter", "1")
                        rvUpcoming.loadSkeleton(R.layout.now_playing_item)
                    }

                    is LoadState.Error -> {
                        Log.v("testAdapter", "2")
                        rvUpcoming.loadSkeleton(R.layout.now_playing_item)
                    }

                    is LoadState.NotLoading -> {
                        Log.v("testAdapter", "3")
                        rvUpcoming.hideSkeleton()
                    }
                }
            }

        }
    }

    private fun observeData(binding: ActivityMainBinding) {

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