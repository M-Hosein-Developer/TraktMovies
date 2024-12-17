package ir.androidcoder.traktmovies.view.activity

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.util.Log
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import dagger.hilt.android.AndroidEntryPoint
import ir.androidcoder.data.util.TokenManager
import ir.androidcoder.traktmovies.BuildConfig
import ir.androidcoder.traktmovies.R
import ir.androidcoder.traktmovies.databinding.ActivityLoginBinding
import ir.androidcoder.traktmovies.viewModel.AuthViewModel

@AndroidEntryPoint
class LoginActivity : AppCompatActivity() {

    private val viewModel : AuthViewModel by viewModels()
    private lateinit var binding: ActivityLoginBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        enableEdgeToEdge()
        binding = ActivityLoginBinding.inflate(layoutInflater)
        setContentView(binding.root)
//        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
//            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
//            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
//            insets
//        }

        val clientId = BuildConfig.CLIENT_ID
        val redirectUri = BuildConfig.REDIRECT_URL

        binding.btnLogin.setOnClickListener {
            val authUrl = "https://api.trakt.tv/oauth/authorize?response_type=code&client_id=$clientId&redirect_uri=$redirectUri"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(authUrl))
            startActivity(intent)
        }

        intent?.data?.let { uri ->
            val code = uri.getQueryParameter("code")
            val error = uri.getQueryParameter("error")

            if (code != null) {
                Log.v("testPre1" , code)
               viewModel.getAccessToken(code , clientId , BuildConfig.CLIENT_SECRET)
            } else if (error != null) {
                Log.e("RedirectError", "Error: $error")
            }else{

            }
        }

    }
}