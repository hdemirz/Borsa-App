package com.hakandemir.borsa.view

import android.graphics.Color
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.hakandemir.borsa.R
import com.hakandemir.borsa.databinding.ActivityMainBinding
import com.hakandemir.borsa.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var viewModel: MainViewModel
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
             v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        binding = DataBindingUtil.setContentView(this,R.layout.activity_main)
        viewModel = ViewModelProvider(this)[MainViewModel::class.java]
        setObservers()

    }

    private fun setObservers(){
        viewModel.borsaDate.observe(this, Observer {data ->
            data.borsaUrl // fake url
            data.name
            binding.imageView.setImageDrawable(resources.getDrawable(R.drawable.images))
            binding.textView.text = data.name
        })

        viewModel.borsaLoad.observe(this, Observer {
            if ( it == true){
                binding.textView.setTextColor(Color.GREEN)
            }
            if (it == false){
                binding.textView.setTextColor(Color.DKGRAY)
            }
        })
    }
}