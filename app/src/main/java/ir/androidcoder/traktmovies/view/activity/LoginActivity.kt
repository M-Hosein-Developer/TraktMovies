package ir.androidcoder.traktmovies.view.activity

import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import ir.androidcoder.traktmovies.R
import ir.androidcoder.traktmovies.databinding.ActivityLoginBinding
import ir.androidcoder.traktmovies.view.adapter.AuthPagerAdapter
import ir.androidcoder.traktmovies.viewModel.AuthViewModel
import kotlinx.coroutines.launch

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val viewModel : AuthViewModel by viewModels()
    private lateinit var binding: ActivityLoginBinding

    companion object{
        fun showLogin(context: Context){
            context.let {
                context.startActivity(Intent(context , LoginActivity::class.java))
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)

        observeData()
        initView()
        handleIntent()
    }


    private fun observeData() {
        viewModel.getAccessToken("")
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
                viewModel.getAccessToken(code)
                Toast.makeText(this, getString(R.string.success_message), Toast.LENGTH_SHORT).show()
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
            val authUrl = viewModel.authorization()
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