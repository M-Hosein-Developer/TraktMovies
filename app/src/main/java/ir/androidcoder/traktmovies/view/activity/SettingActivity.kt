package ir.androidcoder.traktmovies.view.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import dagger.hilt.android.AndroidEntryPoint
import ir.androidcoder.traktmovies.R
import ir.androidcoder.traktmovies.databinding.ActivitySettingBinding
import ir.androidcoder.traktmovies.viewModel.AuthViewModel

@AndroidEntryPoint
class SettingActivity : BaseActivity() {

    private val viewModel : AuthViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding = ActivitySettingBinding.inflate(layoutInflater, findViewById(R.id.container), true)


        binding.btnLogout.setOnClickListener {
            viewModel.logout()
            LoginActivity.showLogin(this@SettingActivity)
            finish()
        }


    }

    override fun getLayoutResourceId(): Int = R.layout.activity_base
}