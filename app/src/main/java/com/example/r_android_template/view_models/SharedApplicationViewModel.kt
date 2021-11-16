package com.example.r_android_template.view_models

import android.app.Application
import androidx.core.content.edit
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.preference.PreferenceManager
import com.example.r_android_template.constants.enums.AppLocales
import com.example.r_android_template.constants.enums.Preferences
import com.example.r_android_template.models.House

class SharedApplicationViewModel(application: Application): AndroidViewModel(application)
{
	private val _lastId = MutableLiveData<String>("N/A")
	val lastId: LiveData<String> = _lastId
	fun getLastId() = _lastId.value!!
	fun setLastId(newId: String)
	{
		_lastId.value = newId
		PreferenceManager.getDefaultSharedPreferences(getApplication<Application>().applicationContext).edit {
			putString(Preferences.lastId.name, newId)
		}
	}

	private val _houses = MutableLiveData<List<House>>(mutableListOf(House("N/A", "N/A", 0.0, "N/A", "N/A")))
	val houses: LiveData<List<House>> = _houses
	fun getHouses() = _houses.value!!
	fun setHouses(newValue: List<House>)
	{
		_houses.value = newValue
	}

	private val _locale = MutableLiveData<String>(AppLocales.en_US)
	val locale: LiveData<String> = _locale
	fun getLocale() = _locale.value!!
	fun setLocale(newValue: String)
	{
		_locale.value = newValue
		PreferenceManager.getDefaultSharedPreferences(getApplication<Application>().applicationContext).edit {
			putString(Preferences.locale.name, newValue)
		}
	}

	// No point of adding getter/setter to this. It's used only one time at the app startup
	val isFirstTime = MutableLiveData<Boolean>(true)

	init
	{
		_lastId.value = PreferenceManager.getDefaultSharedPreferences(getApplication<Application>().applicationContext).getString(Preferences.lastId.name, "N/A")
		_locale.value = PreferenceManager.getDefaultSharedPreferences(getApplication<Application>().applicationContext).getString(Preferences.locale.name, AppLocales.en_US)
	}
}