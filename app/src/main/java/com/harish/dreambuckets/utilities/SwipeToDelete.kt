package com.harish.dreambuckets.utilities

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.graphics.drawable.Drawable
import android.util.Log
import android.view.View
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.ContextCompat
import androidx.core.content.ContextCompat.getColor
import androidx.recyclerview.widget.ItemTouchHelper
import androidx.recyclerview.widget.RecyclerView
import com.harish.dreambuckets.R
import com.harish.dreambuckets.adapters.HomeDisplayAdapter
import com.harish.dreambuckets.ui.activities.DashboardActivity

private const val TAG = "SWIPED"

class SwipeToDelete(var adapter: HomeDisplayAdapter,var context:Context) : ItemTouchHelper.SimpleCallback(
    0,
    ItemTouchHelper.LEFT
) {


    private lateinit var background: ColorDrawable
    private var icon: Drawable?

    init {
        icon = ContextCompat.getDrawable(adapter.context, R.drawable.ic_delete)
        if(DashboardActivity.isNightMode) {
            background = ColorDrawable(getColor(context,R.color.errorColorNight))
        }else{
            background = ColorDrawable(Color.RED)
        }
    }

    override fun onChildDraw(
        c: Canvas,
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        dX: Float,
        dY: Float,
        actionState: Int,
        isCurrentlyActive: Boolean
    ) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive)
        val itemView: View = viewHolder.itemView
        val backGroundCornerOffset: Int = 20

        val iconMargin = (itemView.height - icon!!.intrinsicHeight) / 2
        val iconTop = itemView.top + (itemView.height - icon!!.intrinsicHeight) / 2
        val iconBottom = iconTop + icon!!.intrinsicHeight

        if (dX > 0) { // Swiping to the right
            val iconLeft = itemView.left + iconMargin + icon!!.intrinsicWidth
            val iconRight = itemView.left + iconMargin
            icon!!.setBounds(iconLeft, iconTop, iconRight, iconBottom)
            background.setBounds(
                itemView.left, itemView.top,
                itemView.left + dX.toInt() + backGroundCornerOffset,
                itemView.bottom
            )
        } else if (dX < 0) { // Swiping to the left
            val iconLeft = itemView.right - iconMargin - icon!!.intrinsicWidth
            val iconRight = itemView.right - iconMargin
            icon!!.setBounds(iconLeft, iconTop, iconRight, iconBottom)
            background.setBounds(
                itemView.right + dX.toInt() - backGroundCornerOffset,
                itemView.top, itemView.right, itemView.bottom
            )
        } else { // view is unSwiped
            background.setBounds(0, 0, 0, 0)
        }

        background.draw(c)
        icon!!.draw(c)
    }


    override fun onMove(
        recyclerView: RecyclerView,
        viewHolder: RecyclerView.ViewHolder,
        target: RecyclerView.ViewHolder
    ): Boolean {
        TODO("Not yet implemented")
        return false
    }


    override fun onSwiped(viewHolder: RecyclerView.ViewHolder, direction: Int) {
        var position = viewHolder.adapterPosition
        Log.i(TAG, "onSwiped: $position")
        displayDialog(position)
    }


    fun displayDialog(position: Int) {
        val alertDialogBuilder = AlertDialog.Builder(adapter.context, R.style.AlertDialogTheme)
        alertDialogBuilder
            .setTitle(adapter.context.getString(R.string.AlertDialogTitle))
            .setIcon(R.drawable.ic_icon)
            .setMessage(adapter.context.getString(R.string.AlertDialogMessage))
            .setNegativeButton("Cancel", DialogInterface.OnClickListener { dialogInterface, i ->
                adapter.notifyItemChanged(position)
            })
            .setPositiveButton("Ok", DialogInterface.OnClickListener({ dialogInterface, i ->
                adapter.deleteItem(position)
            }))

        val alertDialog = alertDialogBuilder.create()
        alertDialog.setCancelable(false)
        alertDialog.show()
    }


}