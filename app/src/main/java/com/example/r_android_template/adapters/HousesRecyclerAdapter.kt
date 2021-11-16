package com.example.r_android_template.adapters

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.r_android_template.R
import com.example.r_android_template.models.House
import com.example.r_android_template.view_models.SharedApplicationViewModel


// I know it's possible to subclass Application class and get context from everywhere, but
// i decided to go with this solution
class HousesRecyclerAdapter(private val data: List<House>, private val context: Context): RecyclerView.Adapter<HousesRecyclerAdapter.ViewHolder>()
{
	private val sharedAppViewModel = ViewModelProvider(context as AppCompatActivity).get(SharedApplicationViewModel::class.java)

	private val estateNumber = context.resources.getString(R.string.estate_number)
	private val area = context.resources.getString(R.string.area)
	private val land = context.resources.getString(R.string.land)
	private val district = context.resources.getString(R.string.district)

	inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
		val root = view.findViewById<ConstraintLayout>(R.id.root_hi)
		var id = ""
		val estateNumber = view.findViewById<TextView>(R.id.estateNumber_hi)
		val area = view.findViewById<TextView>(R.id.area_hi)
		val land = view.findViewById<TextView>(R.id.land_hi)
		val district = view.findViewById<TextView>(R.id.district_hi)

		init
		{
			root.setOnClickListener {
				sharedAppViewModel.setLastId(id)
				val anim = AnimationUtils.loadAnimation(it.context, R.anim.item_click)
				it.startAnimation(anim)
			}
		}
	}

	override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder
	{
		val view = LayoutInflater.from(parent.context).inflate(R.layout.house_item, parent, false)
		return ViewHolder(view)
	}

	@SuppressLint("SetTextI18n")
	override fun onBindViewHolder(holder: ViewHolder, position: Int)
	{
		holder.id = data[position].id
		holder.estateNumber.text = "$estateNumber ${data[position].estateNo}"
		holder.area.text = "$area ${String.format("%.2f", data[position].area)} ha"
		holder.land.text = "$land ${data[position].land}"
		holder.district.text = "$district ${data[position].district}"

		val anim = AnimationUtils.loadAnimation(context, R.anim.fade_in)
		holder.root.startAnimation(anim)
	}

	override fun getItemCount() = data.size
}