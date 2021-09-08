package com.wxd.firstlinecode.materialdesign

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.core.view.GravityCompat
import androidx.recyclerview.widget.GridLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.wxd.firstlinecode.R
import com.wxd.firstlinecode.databinding.ActivityToolbarBinding
import com.wxd.firstlinecode.kt.showSnackbar
import com.wxd.firstlinecode.kt.showToast
import com.wxd.firstlinecode.uiwidget.Fruit
import com.wxd.firstlinecode.uiwidget.FruitAdapter
import kotlin.concurrent.thread

class ToolbarActivity : AppCompatActivity() {

    private lateinit var binding:ActivityToolbarBinding
    val fruits = mutableListOf(
        Fruit("Apple", R.drawable.apple), Fruit("Banana",
        R.drawable.banana), Fruit("Orange", R.drawable.orange), Fruit("Watermelon",
        R.drawable.watermelon), Fruit("Pear", R.drawable.pear), Fruit("Grape",
        R.drawable.grape), Fruit("Pineapple", R.drawable.pineapple), Fruit("Strawberry",
        R.drawable.strawberry), Fruit("Cherry", R.drawable.cherry), Fruit("Mango",
        R.drawable.mango))
    val fruitList = ArrayList<Fruit>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityToolbarBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.toolbar)
        supportActionBar?.let {
            it.setDisplayHomeAsUpEnabled(true)
            it.setHomeAsUpIndicator(R.drawable.ic_menu)
        }
        binding.navView.setCheckedItem(R.id.navCall)
        binding.navView.setNavigationItemSelectedListener {
            binding.drawerLayout.closeDrawers()
            true
        }
        binding.fab.setOnClickListener {
            it.showSnackbar("Data deleted","Undo"){
                "Data restored".showToast(this)
            }
        }
        initFruits()
        val layoutManager = GridLayoutManager(this, 2)
        binding.recyclerView.layoutManager = layoutManager
        val adapter = FruitAdapter(fruitList)
        binding.recyclerView.adapter = adapter
        binding.swipeRefresh.setColorSchemeResources(R.color.purple_500)
        binding.swipeRefresh.setOnRefreshListener {
            thread {
                Thread.sleep(1000)
                runOnUiThread {
                    initFruits()
                    adapter.notifyDataSetChanged()
                    binding.swipeRefresh.isRefreshing = false
                }
            }
        }
    }

    private fun initFruits() {
        fruitList.clear()
        repeat(50) {
            val index = (0 until fruits.size).random()
            fruitList.add(fruits[index])
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.toolbar, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            android.R.id.home -> binding.drawerLayout.openDrawer(GravityCompat.START)
            R.id.backup -> Toast.makeText(this, "You clicked Backup",
                Toast.LENGTH_SHORT).show()
            R.id.delete -> Toast.makeText(this, "You clicked Delete",
                Toast.LENGTH_SHORT).show()
            R.id.settings -> Toast.makeText(this, "You clicked Settings",
                Toast.LENGTH_SHORT).show()
        }
        return true
    }

}