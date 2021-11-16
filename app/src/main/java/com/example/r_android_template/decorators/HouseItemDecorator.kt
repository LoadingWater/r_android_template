package com.example.r_android_template.decorators

import android.graphics.Rect
import android.view.View
import androidx.recyclerview.widget.RecyclerView

class HouseItemDecorator: RecyclerView.ItemDecoration()
{
	private val DIVIDER_SIZE = 15

	override fun getItemOffsets(
		outRect: Rect,
		view: View,
		parent: RecyclerView,
		state: RecyclerView.State
	)
	{
		super.getItemOffsets(outRect, view, parent, state)

		when (parent.getChildAdapterPosition(view))
		{
			0 -> outRect.set(DIVIDER_SIZE, DIVIDER_SIZE, DIVIDER_SIZE, DIVIDER_SIZE)
			else -> outRect.set(DIVIDER_SIZE, 0, DIVIDER_SIZE, DIVIDER_SIZE)
		}
	}
}