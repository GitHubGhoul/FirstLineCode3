package com.hp.firstlinecode.uiwidget

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.hp.firstlinecode.R
import com.hp.firstlinecode.databinding.ActivityFirstBinding
import com.hp.firstlinecode.databinding.ActivityFruitBinding

class FruitActivity : AppCompatActivity() {

    private lateinit var binding: ActivityFruitBinding
    private var fruitList = ArrayList<Fruit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFruitBinding.inflate(layoutInflater)
        setContentView(binding.root)
        initFruits() // 初始化水果数据
        val layoutManager = StaggeredGridLayoutManager(3,
            StaggeredGridLayoutManager.VERTICAL)
        binding.recyclerView.layoutManager = layoutManager
        val adapter = FruitAdapter(fruitList)
        binding.recyclerView.adapter = adapter
    }

    private fun initFruits() {
        repeat(2) {
            fruitList.add(Fruit(getRandomLengthString("Apple"), R.drawable.apple))
            fruitList.add(Fruit(getRandomLengthString("Banana"), R.drawable.banana))
            fruitList.add(Fruit(getRandomLengthString("Orange"), R.drawable.orange))
            fruitList.add(Fruit(getRandomLengthString("Watermelon"), R.drawable.watermelon))
            fruitList.add(Fruit(getRandomLengthString("Pear"), R.drawable.pear))
            fruitList.add(Fruit(getRandomLengthString("Grape"), R.drawable.grape))
            fruitList.add(Fruit(getRandomLengthString("Pineapple"), R.drawable.pineapple))
            fruitList.add(Fruit(getRandomLengthString("Strawberry"), R.drawable.strawberry))
            fruitList.add(Fruit(getRandomLengthString("Cherry"), R.drawable.cherry))
            fruitList.add(Fruit(getRandomLengthString("Mango"), R.drawable.mango))
        }
    }

    private fun getRandomLengthString(str: String): String {
        val n = (1..20).random()
        val builder = StringBuilder()
        repeat(n) {
            builder.append(str)
        }
        return builder.toString()
    }

}