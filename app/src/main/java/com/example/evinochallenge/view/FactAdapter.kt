package com.example.evinochallenge.view

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.evinochallenge.R
import com.example.evinochallenge.entity.Top
import kotlinx.android.synthetic.main.item_game.view.*


class FactAdapter(
    val items: ArrayList<Top?>,
    val context: Context,
    val clickListener: (Top?) -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        (p0 as FactViewHolder).bind(items[p1], clickListener)
    }

    override fun onCreateViewHolder(p0: ViewGroup, p1: Int): FactViewHolder {
        return FactViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.item_game,
                p0,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class FactViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val itemVieww = view
        fun bind(part: Top?, clickListener: (Top?) -> Unit) {

            itemVieww.tv_frase_item_chuck_fact.text = part?.game?.localized_name

            val layout: CardView = itemVieww.cv_card

            val animZoomin = AnimationUtils.loadAnimation(
                itemVieww.context,
                R.anim.zoom_from_center
            )
            val x = 200 * adapterPosition
            animZoomin.startOffset = x.toLong()
            layout.setAnimation(animZoomin)

            itemVieww.ib_item_game_fav.setOnClickListener { clickListener(part) }
        }

    }


}