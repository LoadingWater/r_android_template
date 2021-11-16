package com.example.r_android_template.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.r_android_template.controllers.ActivityController
import com.example.r_android_template.databinding.ActivityMainBinding
import com.example.r_android_template.view_models.SharedApplicationViewModel
import com.google.android.material.snackbar.Snackbar


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var controller: ActivityController
    private val sharedApplicationViewModel: SharedApplicationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        controller = ActivityController(this)
        controller.parseData()
        controller.initRecycler(binding.recyclerAm)

        binding.showEstateAm.setOnClickListener {
            val estateNumber = controller.findHouseById(sharedApplicationViewModel.getLastId())
            Snackbar.make(this, it, "Estate number is: $estateNumber", Snackbar.LENGTH_LONG).show()
        }

        Snackbar.make(this, binding.root, "Last id is: ${sharedApplicationViewModel.getLastId()}", Snackbar.LENGTH_LONG).show()
    }
}