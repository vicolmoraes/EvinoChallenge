package com.example.evinochallenge.view

import android.content.Context
import android.os.Build
import android.view.LayoutInflater
import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.view.ViewGroup
import android.view.animation.AnimationUtils
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.example.evinochallenge.R
import com.example.evinochallenge.entity.Top
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_game.view.*


class GameAdapter(
    val items: ArrayList<Top?>,
    val context: Context,
    val favorites: Boolean,
    val clickListener: (Top?) -> Unit
) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onBindViewHolder(p0: RecyclerView.ViewHolder, p1: Int) {
        (p0 as FactViewHolder).bind(items[p1], clickListener, favorites)
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
        fun bind(part: Top?, clickListener: (Top?) -> Unit, favorites: Boolean) {

            itemVieww.tv_item_game_name.text = part?.game?.localized_name
            itemVieww.tv_item_game_canais_numero.text = part?.channels.toString()
            itemVieww.tv_item_game_visualizacoes_numero.text = part?.viewers.toString()

            Picasso.get().load(part?.game?.box?.small).into(itemVieww.iv_item_game_box)
            Picasso.get().load(part?.game?.logo?.small).into(itemVieww.iv_item_game_preview)

            val layout: CardView = itemVieww.cv_card

            val animZoomin = AnimationUtils.loadAnimation(
                itemVieww.context,
                R.anim.zoom_from_center
            )
            val x = 200 * adapterPosition
            animZoomin.startOffset = x.toLong()
            layout.setAnimation(animZoomin)

            if (favorites) {
                itemVieww.ib_item_game_fav.setBackgroundResource(R.drawable.ic_fav_broken)
            }

            itemVieww.ib_item_game_fav.setOnClickListener {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                    itemVieww.ib_item_game_fav.setBackgroundResource(R.drawable.ic_fav_red)
                }
                clickListener(part)
            }

            itemVieww.bt_top_games_logout.setOnClickListener {
                if (itemVieww.cl_item_game_detalhes.visibility == GONE) {
                    itemVieww.cl_item_game_detalhes.visibility = VISIBLE
                } else {
                    itemVieww.cl_item_game_detalhes.visibility = GONE
                }
            }
        }

    }


}