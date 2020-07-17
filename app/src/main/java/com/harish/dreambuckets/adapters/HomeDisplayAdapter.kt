package com.harish.dreambuckets.adapters

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.cardview.widget.CardView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.harish.dreambuckets.R
import com.harish.dreambuckets.database.BucketList
import com.harish.dreambuckets.viewmodels.BucketListViewModel
import java.io.FileInputStream

private const val TAG = "SWIPED"

class HomeDisplayAdapter(var context: Context,var viewModel: BucketListViewModel,
                        var onItemSelectedListener: OnItemSelectedListener)
    : RecyclerView.Adapter<HomeDisplayAdapter.HomeDisplayViewHolder>() {

    var arr = mutableListOf<BucketList>()


    class HomeDisplayViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val bucketName = itemView.findViewById<TextView>(R.id.bucketNameTextView)
        val bucketImage = itemView.findViewById<ImageView>(R.id.cardColorImageView)
        val masterCardView = itemView.findViewById<CardView>(R.id.masterCardView)
        val cardColorView = itemView.findViewById<CardView>(R.id.cardColorView)
        val cardImageView = itemView.findViewById<ImageView>(R.id.cardImageView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HomeDisplayViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.container_home_display, parent, false)
        return HomeDisplayViewHolder(view)
    }

    fun setWords(bucketLists: List<BucketList>){
        arr = bucketLists as MutableList<BucketList>
        notifyDataSetChanged()
    }

    fun deleteItem(position: Int){
        viewModel.deleteBucket(arr[position].id)
        arr.removeAt(position)
        Toast.makeText(context,"Dream Gone!!",Toast.LENGTH_LONG).show()
        notifyItemRemoved(position)
    }

    override fun getItemCount(): Int {
        return arr.size
    }

    override fun onBindViewHolder(holder: HomeDisplayViewHolder, position: Int) {

            holder.bucketName.text = arr[position].bucketName
            when(arr[position].dreamLevel){
            "Level 1"-> holder.cardImageView.setImageResource(R.color.levelcolor1)
            "Level 2"-> holder.cardImageView.setImageResource(R.color.levelcolor2)
            "Level 3"-> holder.cardImageView.setImageResource(R.color.levelcolor3)
            }


        Glide
                .with(context)
                .load(arr[position].bucketImageUri)
                .centerCrop()
                .into(holder.bucketImage)
        holder.masterCardView.setOnClickListener {
            onItemSelectedListener.onItemSelected(position, arr[position].id,holder.cardColorView)
        }




    }

    interface OnItemSelectedListener{
        fun onItemSelected(position: Int, id: Int, view:View)
    }


}




