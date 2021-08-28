package com.wxd.firstlinecode.datastorage

import android.content.ContentValues
import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.core.content.contentValuesOf
import com.wxd.firstlinecode.R
import com.wxd.firstlinecode.databinding.ActivityStorageBinding
import com.wxd.firstlinecode.kt.cvOf
import com.wxd.firstlinecode.kt.open
import java.io.BufferedWriter
import java.io.OutputStreamWriter
import java.lang.NullPointerException

class StorageActivity : AppCompatActivity() {

    companion object {
        private const val TAG = "StorageActivity"
    }

    private lateinit var binding: ActivityStorageBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityStorageBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.putSP.setOnClickListener {
            getSharedPreferences("data",Context.MODE_PRIVATE).open {
                putString("name", "Tom")
                putInt("age", 28)
                putBoolean("married", false)
            }
        }
        binding.getSP.setOnClickListener {
            val prefs = getSharedPreferences("data", Context.MODE_PRIVATE)
            val name = prefs.getString("name", "")
            val age = prefs.getInt("age", 0)
            val married = prefs.getBoolean("married", false)
            Log.d(TAG, "name is $name")
            Log.d(TAG, "age is $age")
            Log.d(TAG, "married is $married")
        }
        val dbHelper = MyDatabaseHelper(this, "BookStore.db", 2)
        binding.createDB.setOnClickListener {
            dbHelper.writableDatabase
        }
        binding.insertDB.setOnClickListener {
            val db = dbHelper.writableDatabase
            val values1 = ContentValues().apply {
                // 开始组装第一条数据
                put("name", "The Da Vinci Code")
                put("author", "Dan Brown")
                put("pages", 454)
                put("price", 16.96)
            }
            db.insert("Book", null, values1)
            val values2 = ContentValues().apply {
                // 开始组装第二条数据
                put("name", "The Lost Symbol")
                put("author", "Dan Brown")
                put("pages", 510)
                put("price", 19.95)
            }
            db.insert("Book", null, values2)
            /*db.execSQL("insert into Book (name, author, pages, price) values(?, ?, ?, ?)",
                arrayOf("The Da Vinci Code", "Dan Brown", "454", "16.96")
            )
            db.execSQL("insert into Book (name, author, pages, price) values(?, ?, ?, ?)",
                arrayOf("The Lost Symbol", "Dan Brown", "510", "19.95")
            )*/
        }
        binding.updateDB.setOnClickListener {
            val db = dbHelper.writableDatabase
            val values = ContentValues()
            values.put("price", 10.99)
            db.update("Book", values, "name = ?", arrayOf("The Da Vinci Code"))
            //db.execSQL("update Book set price = ? where name = ?", arrayOf("10.99", "The Da Vinci Code"))
        }
        binding.delDB.setOnClickListener {
            val db = dbHelper.writableDatabase
            db.delete("Book", "pages > ?", arrayOf("500"))
            //db.execSQL("delete from Book where pages > ?", arrayOf("500"))
        }
        binding.queryDB.setOnClickListener {
            val db = dbHelper.writableDatabase
            val cursor = db.query("Book", null, null, null, null, null, null)
            //val cursor = db.rawQuery("select * from Book", null)
            if (cursor.moveToFirst()) {
                do {
                    val name = cursor.getString(cursor.getColumnIndex("name"))
                    val author = cursor.getString(cursor.getColumnIndex("author"))
                    val pages = cursor.getInt(cursor.getColumnIndex("pages"))
                    val price = cursor.getDouble(cursor.getColumnIndex("price"))
                    Log.d(TAG, "book name is $name")
                    Log.d(TAG, "book author is $author")
                    Log.d(TAG, "book pages is $pages")
                    Log.d(TAG, "book price is $price")
                } while (cursor.moveToNext())
            }
            cursor.close()
        }
        binding.replaceDB.setOnClickListener {
            val db = dbHelper.writableDatabase
            db.beginTransaction()
            try {
                db.delete("Book",null,null)
                if (true){
                    throw NullPointerException()
                }
                //高阶函数应用
                val values = cvOf("name" to "Game of Thrones", "author" to "George Martin",
                    "pages" to 720, "price" to 20.85)
                //官方写法
                val values1 = contentValuesOf("name" to "Game of Thrones", "author" to "George Martin",
                    "pages" to 720, "price" to 20.85)
                //默认实现
                val values2 = ContentValues().apply {
                    put("name", "Game of Thrones")
                    put("author", "George Martin")
                    put("pages", 720)
                    put("price", 20.85)
                }
                db.insert("Book", null, values)
                db.setTransactionSuccessful()
            } catch (e: Exception) {
                e.printStackTrace()
            } finally {
                db.endTransaction()
            }
        }
    }

}