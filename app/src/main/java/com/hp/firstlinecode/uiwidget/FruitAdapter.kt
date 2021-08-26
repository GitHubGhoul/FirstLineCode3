package com.hp.firstlinecode.uiwidget

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.hp.firstlinecode.R

class FruitAdapter(private val fruitList: List<Fruit>) :
    RecyclerView.Adapter<FruitAdapter.ViewHolder>() {

    var mContext: Context? = null

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val fruitImage: ImageView = view.findViewById(R.id.fruit_image)
        val fruitName: TextView = view.findViewById(R.id.fruit_name)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.fruit_item, parent, false)
        mContext = parent.context
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val fruit = fruitList[position]
        holder.itemView.setOnClickListener {
            Toast.makeText(
                mContext, "you clicked itemView ${fruit.name}",
                Toast.LENGTH_SHORT
            ).show()
        }
        holder.fruitImage.setImageResource(fruit.image)
        holder.fruitImage.setOnClickListener {
            Toast.makeText(
                mContext, "you clicked image ${fruit.name}",
                Toast.LENGTH_SHORT
            ).show()
        }
        holder.fruitName.text = fruit.name
    }

    override fun getItemCount(): Int {
        return fruitList.size
    }

}