package ir.androidcoder.traktmovies.view.activity

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import ir.androidcoder.traktmovies.R
import ir.androidcoder.traktmovies.databinding.ActivityWatchListBinding

class WatchListActivity : BaseActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding = ActivityWatchListBinding.inflate(layoutInflater, findViewById(R.id.container), true)





    }

    override fun getLayoutResourceId(): Int = R.layout.activity_base
}