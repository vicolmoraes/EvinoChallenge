package com.example.evinochallenge.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.evinochallenge.R
import kotlinx.android.synthetic.main.item_category.view.*


class CategoryAdapter(
    val items: ArrayList<String>,
    val context: Context,
    val clickListener: (String) -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        (p0 as CategoryViewHolder).bind(items[p1], clickListener)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): CategoryViewHolder {
        return CategoryViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_category,
                p0,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class CategoryViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemVieww = view
        fun bind(part: String, clickListener: (String) -> Unit) {

            val button: Button = itemVieww.bt_item_category
            button.text = part

            val animZoomin = AnimationUtils.loadAnimation(
                itemVieww.context,
                R.anim.zoom_from_center
            )
            val x = 200 * adapterPosition
            animZoomin.startOffset = x.toLong()
            button.setAnimation(animZoomin)

            button.setOnClickListener { clickListener(part) }
        }

    }


}