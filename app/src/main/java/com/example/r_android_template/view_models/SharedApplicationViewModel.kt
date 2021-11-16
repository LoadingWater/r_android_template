package com.example.r_android_template.view_models

import android.app.Application
import androidx.core.content.edit
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.preference.PreferenceManager
import com.example.r_android_template.enums.Preferences
import com.example.r_android_template.models.House

class SharedApplicationViewModel(application: Application): AndroidViewModel(application)
{
	private val _lastId = MutableLiveData<String>("")
	val lastId: LiveData<String> = _lastId
	fun setLastId(newId: String)
	{
		_lastId.value = newId
		PreferenceManager.getDefaultSharedPreferences(getApplication<Application>().applicationContext).edit {
			putString(Preferences.lastId.name, newId)
		}
	}

	init
	{
		_lastId.value = PreferenceManager.getDefaultSharedPreferences(getApplication<Application>().applicationContext).getString(Preferences.lastId.name, "N/A")
	}

	private val _houses = MutableLiveData<List<House>>(mutableListOf(House("N/A", "N/A", 0.0, "N/A", "N/A")))
	val houses: LiveData<List<House>> = _houses
	fun getHouses() = _houses.value
	fun setHouses(newValue: List<House>)
	{
		_houses.value = newValue
	}
}