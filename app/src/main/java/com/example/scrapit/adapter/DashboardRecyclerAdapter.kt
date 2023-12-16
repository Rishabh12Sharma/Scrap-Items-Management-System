package com.example.scrapit.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.scrapit.NextActivity
import com.example.scrapit.R
import com.example.scrapit.model.Scrap


class DashboardRecyclerAdapter (val context:Context, val itemList: ArrayList<Scrap>): RecyclerView.Adapter<DashboardRecyclerAdapter.DashboardViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DashboardViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.recycler_dashboard_single_row,parent,false)
        return DashboardViewHolder(view)
    }

    override fun onBindViewHolder(holder: DashboardViewHolder, position: Int) {
        val scrap=itemList[position]
        holder.txtScrapName.text=scrap.scrapName
        holder.txtScrapType.text=scrap.scrapType
        holder.txtScrapPrice.text=scrap.scrapPrice
        holder.txtScrapRating.text=scrap.scrapRating
        scrap.scrapImage?.let { holder.imgScrapImage.setImageResource(it.toInt()) }


        holder.llContent.setOnClickListener{
            /*Toast.makeText(context, "Clicked on ${holder.txtScrapName.text}",Toast.LENGTH_SHORT).show()*/
            val intent=Intent(context, NextActivity::class.java)
            intent.putExtra("ScrapName",holder.txtScrapName.text)
            intent.putExtra("ScrapType",holder.txtScrapType.text)
            intent.putExtra("ScrapPrice",holder.txtScrapPrice.text)
            intent.putExtra("ScrapRating",holder.txtScrapRating.text)
            intent.putExtra("ScrapImage",holder.imgScrapImage.id)
            context.startActivity(intent)
        }
    }

    override fun getItemCount(): Int {
        return itemList.size
    }
    class  DashboardViewHolder(view: View) : RecyclerView.ViewHolder(view){
        val txtScrapName:TextView=view.findViewById(R.id.txtScrapName)
        val txtScrapType:TextView=view.findViewById(R.id.txtScrapType)
        val txtScrapPrice:TextView=view.findViewById(R.id.txtScrapPrice)
        val txtScrapRating:TextView=view.findViewById(R.id.txtScrapRating)
        val imgScrapImage:ImageView=view.findViewById(R.id.imgScrapImage)
        val llContent:LinearLayout=view.findViewById(R.id.llContent)
    }
}