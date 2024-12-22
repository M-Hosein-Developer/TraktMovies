package ir.androidcoder.traktmovies.view.activity

import android.content.Context
import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import dagger.hilt.android.AndroidEntryPoint
import ir.androidcoder.traktmovies.R
import ir.androidcoder.traktmovies.databinding.ActivitySearchBinding

@AndroidEntryPoint
class SearchActivity : BaseActivity() {

    companion object{

        fun showSearch(context: Context){
            context.startActivity(Intent(context , SearchActivity::class.java))
        }

    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val binding = ActivitySearchBinding.inflate(layoutInflater, findViewById(R.id.container), true)



    }

    override fun getLayoutResourceId(): Int  = R.layout.activity_base


}