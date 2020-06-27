package com.harish.dreambuckets.adapters

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.harish.dreambuckets.R
import com.harish.dreambuckets.database.BucketList
import com.harish.dreambuckets.viewmodels.BucketListViewModel

private const val TAG = "SWIPED"

class HomeDisplayAdapter(var context: Context,var viewModel: BucketListViewModel) : RecyclerView.Adapter<HomeDisplayAdapter.HomeDisplayViewHolder>() {

    var arr = mutableListOf<BucketList>()


    class HomeDisplayViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView){
        val bucketName = itemView.findViewById<TextView>(R.id.bucketNameTextView)
        val bucketThoughts = itemView.findViewById<TextView>(R.id.thoughtsTextView)
        val category = itemView.findViewById<TextView>(R.id.categoryTextView)
        val date = itemView.findViewById<TextView>(R.id.dateTextView)
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
            holder.bucketThoughts.text = arr[position].bucketThoughts
            holder.category.text = "CATEGORY: ${arr[position].category}"
            holder.date.text = "TARGET DATE: ${arr[position].bucketTargetDate}"

    }

 /*   fun toast(message:String){
        val v = View(context.applicationContext)
        val inflater = LayoutInflater.from(context)
        val container: ViewGroup  = v.findViewById(R.id.custom_toast_container)
        val layout: ViewGroup = inflater.inflate(R.layout.custom_toast, container) as ViewGroup
        val text:TextView = layout.findViewById(R.id.toastMessageTextView)
        text.text = message
        with(Toast(context.applicationContext)){
            view = layout
            duration = Toast.LENGTH_LONG
            show()
        }
    }*/


}




/*
class HomeDisplayDiffUtil : DiffUtil.ItemCallback<BucketList>(){
    override fun areItemsTheSame(oldItem: BucketList, newItem: BucketList): Boolean {
        return oldItem.id==newItem.id
    }

    override fun areContentsTheSame(oldItem: BucketList, newItem: BucketList): Boolean {
        return oldItem==newItem
    }

}*/
