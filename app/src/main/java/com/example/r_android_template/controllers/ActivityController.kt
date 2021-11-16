package com.example.r_android_template.controllers

import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.r_android_template.adapters.HousesRecyclerAdapter
import com.example.r_android_template.constants.enums.AppLocales
import com.example.r_android_template.decorators.HouseItemDecorator
import com.example.r_android_template.models.House
import com.example.r_android_template.service.Service
import com.example.r_android_template.view_models.SharedApplicationViewModel
import com.google.gson.Gson
import java.util.*
import java.util.Locale


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

	fun setLocale(languageCode: String)
	{
		val currentLocale = activity.resources.configuration.locales[0].toLanguageTag()
		activity.resources.configuration.setLocale(Locale.forLanguageTag(languageCode))
		activity.resources.updateConfiguration(activity.resources.configuration, activity.resources.displayMetrics)
		sharedViewModel.setLocale(languageCode)

		if (currentLocale != languageCode)
		{
			activity.recreate()
		}
	}

	fun toggleLocale()
	{
		val currentLocale = activity.resources.configuration.locales[0].toLanguageTag()
		val newLocale = if (currentLocale == AppLocales.en_US) AppLocales.pl_PL else AppLocales.en_US
		activity.resources.configuration.setLocale(Locale.forLanguageTag(newLocale))
		activity.resources.updateConfiguration(activity.resources.configuration, activity.resources.displayMetrics)
		sharedViewModel.setLocale(newLocale)

		if (currentLocale != newLocale)
		{
			activity.recreate()
		}
	}
}