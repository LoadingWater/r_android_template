package com.example.r_android_template.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.r_android_template.adapters.HousesRecyclerAdapter
import com.example.r_android_template.databinding.ActivityMainBinding
import com.example.r_android_template.decorators.HouseItemDecorator
import com.example.r_android_template.models.House
import com.example.r_android_template.service.Service
import com.example.r_android_template.view.controllers.ActivityController
import com.example.r_android_template.view_models.SharedApplicationViewModel
import com.google.gson.Gson


class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var controller: ActivityController
    private val sharedApplicationViewModel: SharedApplicationViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        controller = ActivityController(this)

        val data = Gson().fromJson(Service.estateJsonString, Array<House>::class.java).toList()
        sharedApplicationViewModel.setHouses(data)

        binding.recyclerAm.layoutManager = LinearLayoutManager(this)
        binding.recyclerAm.adapter = HousesRecyclerAdapter(data, this)
        binding.recyclerAm.addItemDecoration(HouseItemDecorator())

        Toast.makeText(this, "Last id: ${sharedApplicationViewModel.lastId.value}", Toast.LENGTH_SHORT).show()


    }
}