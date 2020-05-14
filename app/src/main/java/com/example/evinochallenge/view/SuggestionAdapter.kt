package com.example.evinochallenge.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.recyclerview.widget.RecyclerView
import com.example.evinochallenge.R
import kotlinx.android.synthetic.main.item_suggestion.view.*


class SuggestionAdapter(
    val items: ArrayList<String>,
    val context: Context,
    val clickListener: (String) -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        (p0 as SuggestionViewHolder).bind(items[p1], clickListener)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): SuggestionViewHolder {
        return SuggestionViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_suggestion,
                p0,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class SuggestionViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemVieww = view
        fun bind(part: String, clickListener: (String) -> Unit) {

            val button: Button = itemVieww.bt_item_suggestion
            button.text = part


            button.setOnClickListener { clickListener(part) }
        }

    }


}