package com.example.r_android_template.controllers

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.r_android_template.adapters.HousesRecyclerAdapter
import com.example.r_android_template.decorators.HouseItemDecorator
import com.example.r_android_template.models.House
import com.example.r_android_template.service.Service
import com.example.r_android_template.view_models.SharedApplicationViewModel
import com.google.gson.Gson

class ActivityController(val activity: AppCompatActivity)
{
	private val sharedViewModel = ViewModelProvider(activity).get(SharedApplicationViewModel::class.java)

	fun parseData()
	{
		val data = Gson().fromJson(Service.estateJsonString, Array<House>::class.java).toList()
		sharedViewModel.setHouses(data)
	}

	fun initRecycler(recycler: RecyclerView)
	{
		recycler.layoutManager = LinearLayoutManager(activity)
		recycler.adapter = HousesRecyclerAdapter(sharedViewModel.getHouses(), activity)
		recycler.addItemDecoration(HouseItemDecorator())
	}

	fun findHouseById(id: String): String
	{
		sharedViewModel.getHouses().forEach {
			if (it.id == id)
			{
				return it.estateNo
			}
		}
		return "Item not found"
	}
}