package ir.androidcoder.traktmovies.view.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.viewpager.widget.PagerAdapter
import dagger.hilt.android.AndroidEntryPoint
import ir.androidcoder.data.util.TokenManager
import ir.androidcoder.traktmovies.BuildConfig
import ir.androidcoder.traktmovies.R
import ir.androidcoder.traktmovies.databinding.ActivityLoginBinding
import ir.androidcoder.traktmovies.view.adapter.AuthPagerAdapter
import ir.androidcoder.traktmovies.viewModel.AuthViewModel
import kotlinx.coroutines.launch
import kotlin.math.log

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val viewModel : AuthViewModel by viewModels()
    private lateinit var binding: ActivityLoginBinding

    private val clientId = BuildConfig.CLIENT_ID
    private val redirectUri = BuildConfig.REDIRECT_URL
    private val clientSecret = BuildConfig.CLIENT_SECRET

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        observeData()
        initView()
        handleIntent()
    }


    private fun observeData() {
        viewModel.getAccessToken("" , clientId , clientSecret)
        lifecycleScope.launch {
            viewModel.isAuth.collect { authState ->
                if (authState){
                    MainActivity.showHome(this@LoginActivity)
                    finish()
                }
            }
        }
    }

    private fun handleIntent() {

        intent?.data?.let { uri ->
            val code = uri.getQueryParameter("code")
            val error = uri.getQueryParameter("error")

            if (code != null) {
                viewModel.getAccessToken(code , clientId , clientSecret)
                Toast.makeText(this, "ثبت نام با موفقیت انجام شد", Toast.LENGTH_SHORT).show()
                MainActivity.showHome(this@LoginActivity)
                finish()
            } else if (error != null) {
                Log.e("RedirectError", "Error: $error")
            }else{

            }
        }

    }

    private fun initView() {

        imageSlider()

        binding.btnLogin.setOnClickListener {
            val authUrl = "https://api.trakt.tv/oauth/authorize?response_type=code&client_id=$clientId&redirect_uri=$redirectUri"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(authUrl))
            startActivity(intent)
        }

    }

    private fun imageSlider() {
        val data = listOf(
            R.drawable.back4,
            R.drawable.back3,
            R.drawable.back1,
            R.drawable.back2
        )
        binding.apply {
            loginPager.adapter = AuthPagerAdapter(data) { position ->
                if (position == data.size - 1) {
                    btnNext.visibility = View.GONE
                    btnLogin.visibility = View.VISIBLE
                } else {
                    btnNext.visibility = View.VISIBLE
                    btnLogin.visibility = View.GONE
                }
            }
            dotsIndicator.attachTo(binding.loginPager)
        }
    }
}