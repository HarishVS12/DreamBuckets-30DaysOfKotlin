package com.harish.dreambuckets.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.harish.dreambuckets.R
import com.harish.dreambuckets.database.BucketList
import com.harish.dreambuckets.models.HomeDisplayModel

class HomeDisplayAdapter() : RecyclerView.Adapter<HomeDisplayAdapter.HomeDisplayViewHolder>() {

    var arrayList = emptyList<BucketList>()


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
        arrayList = bucketLists
        notifyDataSetChanged()
    }

    fun deleteItem(postion: Int){
        val arr = arrayList as MutableList<BucketList>
        arr.removeAt(postion)
        notifyItemRemoved(postion)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: HomeDisplayViewHolder, position: Int) {
            holder.bucketName.text = arrayList[position].bucketName
            holder.bucketThoughts.text = arrayList[position].bucketThoughts
            holder.category.text = "CATEGORY: ${arrayList[position].category}"
            holder.date.text = "TARGET DATE: ${arrayList[position].bucketTargetDate}"
    }
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
