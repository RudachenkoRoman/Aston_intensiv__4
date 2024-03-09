package com.rudachenkoroman.aston_intensiv__4

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.rudachenkoroman.aston_intensiv__4.databinding.ActivityMainBinding
import com.rudachenkoroman.aston_intensiv__4.fragment.StartFragment

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.containerFragment, StartFragment(), null).commit()
        }
    }
}