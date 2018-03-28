package com.digigirls.habitapp

import android.content.Context
import android.support.v4.content.ContextCompat
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import com.digigirls.habitapp.andmed.Harjumus

class HarjumusAdapter(private var context: Context, private var harjumused: ArrayList<Harjumus>, private val adapterCallback: HarjumusAdapterCallback) : RecyclerView.Adapter<HarjumusAdapter.ViewHolder>() {

    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val skoor = view.findViewById(R.id.skoor) as TextView
        val ikoon = view.findViewById(R.id.ikoon) as ImageView
        val plussNupp = view.findViewById(R.id.plussNupp) as TextView
        val miinusNupp = view.findViewById(R.id.miinusNupp) as TextView
        val kustuta = view.findViewById(R.id.kustuta) as ImageView
        val item = view.findViewById(R.id.item) as LinearLayout
        val progress = view.findViewById(R.id.progress) as LinearLayout
        val valikud = view.findViewById(R.id.valikud) as FrameLayout

        // lisa loodud TextView üleval olevate näidete põhjal
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): HarjumusAdapter.ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.view_harjumus, parent, false) as View
        return ViewHolder(view)
    }

    override fun getItemCount() = harjumused.size

    fun setUuedHarjumused(harjumused: ArrayList<Harjumus>) {
        this.harjumused = harjumused
        notifyDataSetChanged()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val harjumus = harjumused[position]

        val skoor = harjumus.heaTulemus - harjumus.halbTulemus
        holder.skoor.text = "Sinu skoor hetkel on $skoor."
        holder.ikoon.background = ContextCompat.getDrawable(context, getImage(skoor))

        // lisa üleval loodud väljale harjumuse nimi

        holder.plussNupp.setOnClickListener { plussNupuKlikk(harjumus) }
        holder.miinusNupp.setOnClickListener { miinusNupuKlikk(harjumus) }
        holder.kustuta.setOnClickListener { kustutaNupuKlikk(harjumus) }

        holder.item.setOnLongClickListener { itemKlikk(holder) }
    }

    private fun plussNupuKlikk(harjumus: Harjumus) {
        val gScore = harjumus.heaTulemus
        harjumus.heaTulemus = gScore + 1
        notifyDataSetChanged()
        adapterCallback.heaTulemuseKlikk(harjumus.id)
    }

    private fun miinusNupuKlikk(harjumus: Harjumus) {
        val bScore = harjumus.halbTulemus
        harjumus.halbTulemus = bScore + 1
        notifyDataSetChanged()
        adapterCallback.halvaTulemuseKlikk(harjumus.id)
    }

    private fun kustutaNupuKlikk(harjumus: Harjumus) {
        harjumused.remove(harjumus)
        notifyDataSetChanged()
        adapterCallback.kustutaHarjumus(harjumus.id)
    }

    private fun itemKlikk(holder: ViewHolder) : Boolean {
        if (holder.valikud.visibility == View.INVISIBLE) {
            holder.valikud.visibility = View.VISIBLE
            holder.progress.visibility = View.INVISIBLE
        } else {
            holder.valikud.visibility = View.INVISIBLE
            holder.progress.visibility = View.VISIBLE
        }
        return true
    }

    private fun getImage(difference: Int): Int {
        return when {
            difference > 4 -> R.drawable.castle
            difference == 4 -> R.drawable.village
            difference == 3 -> R.drawable.home2
            difference == 2 -> R.drawable.home
            difference == 1 -> R.drawable.mill
            difference == 0 -> R.drawable.forest
            difference == -1 -> R.drawable.fields
            difference == -2 -> R.drawable.desert
            else -> R.drawable.desert2
        }
    }

}
