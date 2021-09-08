package com.wxd.firstlinecode.uiwidget

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.wxd.firstlinecode.R
import com.wxd.firstlinecode.materialdesign.DetailsActivity

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
            val position = holder.adapterPosition
            val fruit = fruitList[position]
            val intent = Intent(mContext, DetailsActivity::class.java).apply {
                putExtra(DetailsActivity.FRUIT_NAME, fruit.name)
                putExtra(DetailsActivity.FRUIT_IMAGE_ID, fruit.image)
            }
            mContext?.startActivity(intent)
        }
        Glide.with(mContext!!).load(fruit.image).into(holder.fruitImage)
        //holder.fruitImage.setImageResource(fruit.image)
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