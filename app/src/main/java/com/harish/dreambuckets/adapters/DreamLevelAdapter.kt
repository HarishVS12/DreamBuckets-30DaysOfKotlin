package com.harish.dreambuckets.adapters

import android.content.Context
import android.opengl.Visibility
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.harish.dreambuckets.R

data class DreamLevel(var levelName: String, var levelColor: Int, var isChecked: Boolean)

class DreamLevelAdapter(var arrayList: ArrayList<DreamLevel>, var context: Context,
                        var onLevelSelectListener: OnLevelSelectListener)
    : RecyclerView.Adapter<DreamLevelAdapter.DreamLevelViewHolder>(){


    var tempBool: Boolean = false
    var tempPos: Int = -1

    class DreamLevelViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
        var levelTextView: TextView = itemView.findViewById(R.id.levelTextView)
        var levelColorImageView: ImageView = itemView.findViewById(R.id.cardColorImageView)
        var rootCardView: CardView = itemView.findViewById(R.id.rootCardViewLevel)
        var tickImageView: ImageView = itemView.findViewById(R.id.tickImageView)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DreamLevelViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.container_dream_level, parent, false)
        return DreamLevelViewHolder(view)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: DreamLevelViewHolder, position: Int) {
        holder.levelTextView.text = arrayList[position].levelName
        holder.levelColorImageView.setImageResource(arrayList[position].levelColor)

        holder.rootCardView.setOnClickListener {

            if(tempBool && (tempPos!=position)){
                Toast.makeText(context,"Select just one level",Toast.LENGTH_LONG).show()
                return@setOnClickListener
            }

            if(holder.tickImageView.visibility == View.VISIBLE) {
                tempBool = false
                arrayList[position].isChecked = false
                holder.tickImageView.visibility = View.INVISIBLE
                onLevelSelectListener.onLevelSelected(position,false)
            }
            else {
                tempPos = position
                tempBool = true
                arrayList[position].isChecked = true
                holder.tickImageView.visibility = View.VISIBLE
                onLevelSelectListener.onLevelSelected(position, true)
            }
        }
    }

    interface OnLevelSelectListener{
        fun onLevelSelected(position: Int, isChecked: Boolean)
    }

   /* fun checkIfAlreadyPressed(holder:DreamLevelViewHolder,pos:Int){
        for(i in 0..arrayList.size-1){
            if(holder.adapterPosition != tempPosition)
                holder.tickImageView.visibility = View.INVISIBLE
        }
    }*/


}