package com.example.r_android_template.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import com.example.r_android_template.R
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
        controller = ActivityController(this)
        controller.setInitialLocale(sharedApplicationViewModel.getLocale())

        binding = ActivityMainBinding.inflate(layoutInflater)

        controller.parseData()
        controller.initRecycler(binding.recyclerAm)

        // Observe lastId and update bar title
        sharedApplicationViewModel.lastId.observe(this) {
            supportActionBar?.title = "${resources.getString(R.string.app_name)}\t\tLast id: $it"
        }

        binding.showEstateAm.setOnClickListener {
            val estateNumber = controller.findHouseById(sharedApplicationViewModel.getLastId())
            Snackbar.make(this, it, "${resources.getString(R.string.estate_number)}: $estateNumber", Snackbar.LENGTH_LONG).show()
        }

        binding.changeLocaleAm.setOnClickListener {
            controller.toggleLocale()
        }
        setContentView(binding.root)
    }
}