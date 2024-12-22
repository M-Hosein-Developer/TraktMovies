package ir.androidcoder.traktmovies.view.activity

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.google.android.material.bottomnavigation.BottomNavigationView
import ir.androidcoder.traktmovies.R

abstract class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(getLayoutResourceId())

        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottom_nav)

        when (this::class.java) {
            MainActivity::class.java -> bottomNavigationView.selectedItemId = R.id.homeP
            SearchActivity::class.java -> bottomNavigationView.selectedItemId = R.id.search
            WatchListActivity::class.java -> bottomNavigationView.selectedItemId = R.id.hamburger
            SettingActivity::class.java -> bottomNavigationView.selectedItemId = R.id.setting
        }

        bottomNavigationView.setOnItemSelectedListener { item ->
            when (item.itemId) {
                R.id.homeP -> {
                    if (this !is MainActivity) {
                        startActivity(Intent(this, MainActivity::class.java))
                    }
                    true
                }
                R.id.search -> {
                    if (this !is SearchActivity) {
                        startActivity(Intent(this, SearchActivity::class.java))
                    }
                    true
                }
                R.id.hamburger -> {
                    if (this !is WatchListActivity) {
                        startActivity(Intent(this, WatchListActivity::class.java))
                    }
                    true
                }
                R.id.setting -> {
                    if (this !is SettingActivity) {
                        startActivity(Intent(this, SettingActivity::class.java))
                    }
                    true
                }
                else -> false
            }
        }
    }

    protected abstract fun getLayoutResourceId(): Int
}