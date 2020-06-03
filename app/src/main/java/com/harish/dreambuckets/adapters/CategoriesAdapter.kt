package com.harish.dreambuckets.adapters

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.harish.dreambuckets.R
import com.harish.dreambuckets.databinding.FragmentCategoriesBinding
import com.harish.dreambuckets.models.CategoriesModel

class CategoriesAdapter(var arrayList: ArrayList<CategoriesModel>, var onCategoryClickListener:
                            OnCategoryClickListener) :
    RecyclerView.Adapter<CategoriesAdapter.CategoriesViewHolder>() {



    class CategoriesViewHolder(itemView: View): RecyclerView.ViewHolder(itemView){
            val textView = itemView.findViewById<TextView>(R.id.tv_categoryTitle)
            val imageView = itemView.findViewById<ImageView>(R.id.iv_categoryImage)
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
        holder.textView.text = arrayList[position].categoryName
        holder.imageView.setImageResource(arrayList[position].Image)
        holder.itemView.setOnClickListener {
            onCategoryClickListener.onClickCatListener(position)
        }
    }

    interface OnCategoryClickListener{
        fun onClickCatListener(position: Int)
    }
}