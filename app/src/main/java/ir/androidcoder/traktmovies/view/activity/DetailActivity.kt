package ir.androidcoder.traktmovies.view.activity

import android.content.Context
import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.webkit.WebSettings
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import com.bumptech.glide.Glide
import com.bumptech.glide.load.MultiTransformation
import com.bumptech.glide.load.resource.bitmap.CenterCrop
import com.bumptech.glide.load.resource.bitmap.RoundedCorners
import com.bumptech.glide.request.RequestOptions
import dagger.hilt.android.AndroidEntryPoint
import ir.androidcoder.traktmovies.databinding.ActivityDetailBinding
import ir.androidcoder.traktmovies.util.BlurTransformation
import ir.androidcoder.traktmovies.util.Constant.DETAIL_ID
import ir.androidcoder.traktmovies.viewModel.MoviesViewModel
import kotlinx.coroutines.launch


@AndroidEntryPoint
class DetailActivity : AppCompatActivity() {

    companion object {
        fun showDetail(context: Context, id: Int) {
            val intent = Intent(context, DetailActivity::class.java)
            intent.putExtra(DETAIL_ID, id)
            context.startActivity(intent)
        }
    }

    private val viewModel: MoviesViewModel by viewModels()

    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        handleIntent()
        initView()
        backPressed()
        youtubePlayer()
    }

    private fun backPressed() {
        binding.btnBack.setOnClickListener {
            onBackPressedDispatcher.onBackPressed()
            finish()
        }
    }

    private fun initView() {

        val transformation = MultiTransformation(CenterCrop(), RoundedCorners(55))
        val overlayDrawable = ColorDrawable(Color.parseColor("#91000000"))
        binding.apply {
            lifecycleScope.launch {
                viewModel.movieDetail.collect { data ->
                    if (data != null) {
                        imgDetailBackground.setForeground(overlayDrawable)

                        txtDetailTitle.text = data.title

                        Glide.with(this@DetailActivity)
                            .load("https://image.tmdb.org/t/p/w500" + data.backdrop_path)
                            .transform(BlurTransformation(this@DetailActivity, 15))
                            .centerCrop()
                            .into(imgDetailBackground)

                        Glide.with(this@DetailActivity)
                            .load("https://image.tmdb.org/t/p/w500" + data.backdrop_path)
                            .apply(RequestOptions.bitmapTransform(transformation))
                            .into(imgDetailCover)

                        data.genres.forEach { genre ->
                            txtGenre.append(genre.name + "  -  ")
                        }

                        txtDetailDate.text = data.release_date
                        txtDetailHourse.text = data.runtime.toString() + " min"

                        txtDetailOverview.text = data.overview

                        txtRatting.text = data.vote_average.toString()
                        txtMyRatting.text = data.vote_count.toString()


                    }
                }
            }
        }
    }

    private fun youtubePlayer() {
        binding.apply {
            lifecycleScope.launch {
                viewModel.youtubeUrl.collect { data ->
                    if (data != null) {
                        webview.settings.javaScriptEnabled = true
                        webview.settings.pluginState = WebSettings.PluginState.ON
                        webview.loadUrl("https://www.youtube.com/embed/${data[0].key}")
                    }
                }
            }
        }
    }

    private fun handleIntent() {
        val id = intent.extras?.getInt(DETAIL_ID, 0)
        if (id != null) viewModel.getMovieDetail(id)
    }
}