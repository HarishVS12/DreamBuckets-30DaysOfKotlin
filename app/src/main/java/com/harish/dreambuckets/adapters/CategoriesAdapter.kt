package com.harish.dreambuckets.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.harish.dreambuckets.R
import com.harish.dreambuckets.databinding.FragmentCategoriesBinding

class CategoriesAdapter(var arrayList: ArrayList<String>) : RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {


    class CategoriesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
            val textView = itemView.findViewById<TextView>(R.id.tv_categoryTitle)

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CategoriesViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.container_categories,
                                                        parent, false)

        return CategoriesViewHolder(v)
    }

    override fun getItemCount(): Int {
        return arrayList.size
    }

    override fun onBindViewHolder(holder: CategoriesViewHolder, position: Int) {
        holder.textView.text = arrayList[position]
    }
}