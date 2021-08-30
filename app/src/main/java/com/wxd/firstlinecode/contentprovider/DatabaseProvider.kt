package com.wxd.firstlinecode.contentprovider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.wxd.firstlinecode.datastorage.MyDatabaseHelper

class DatabaseProvider : ContentProvider() {

    private val bookDir = 0
    private val bookItem = 1
    private val categoryDir = 2
    private val categoryItem = 3
    private val book_dir = "book"
    private val book_item = "book/#"
    private val category_dir = "category"
    private val category_item = "category/#"
    private val authority = "com.wxd.database.provider"
    private var dbHelper: MyDatabaseHelper? = null

    private val uriMatcher by lazy {
        val matcher = UriMatcher(UriMatcher.NO_MATCH)
        matcher.addURI(authority, book_dir, bookDir)
        matcher.addURI(authority, book_item, bookItem)
        matcher.addURI(authority, category_dir, categoryDir)
        matcher.addURI(authority, category_item, categoryItem)
        matcher
    }


    override fun delete(uri: Uri, selection: String?, selectionArgs: Array<String>?) =
        dbHelper?.let {
            val db = it.writableDatabase
            val deletedRows = when (uriMatcher.match(uri)) {
                bookDir -> db.delete("Book", selection, selectionArgs)
                bookItem -> {
                    val bookId = uri.pathSegments[1]
                    db.delete("Book", "id = ?", arrayOf(bookId))
                }
                categoryDir -> db.delete("Category", selection, selectionArgs)
                categoryItem -> {
                    val categoryId = uri.pathSegments[1]
                    db.delete("Category", "id = ?", arrayOf(categoryId))
                }
                else -> 0
            }
            deletedRows
        } ?: 0

    override fun getType(uri: Uri) = when(uriMatcher.match(uri)){
        bookDir -> "vnd.android.cursor.dir/vnd.$authority.$book_dir"
        bookItem -> "vnd.android.cursor.item/vnd.$authority.$book_dir"
        categoryDir -> "vnd.android.cursor.dir/vnd.$authority.$category_dir"
        categoryItem -> "vnd.android.cursor.item/vnd.$authority.$category_item"
        else -> null

    }

    override fun insert(uri: Uri, values: ContentValues?) = dbHelper?.let {
        val db = it.writableDatabase
        val uriReturn = when (uriMatcher.match(uri)) {
            bookDir, bookItem -> {
                val newBookId = db.insert("Book", null, values)
                Uri.parse("content://$authority/$book_dir/$newBookId")
            }
            categoryDir, categoryItem -> {
                val newCategoryId = db.insert("Category", null, values)
                Uri.parse("content://$authority/$category_dir/$newCategoryId")
            }
            else -> null
        }
        uriReturn
    }

    override fun onCreate() = context?.let {
        dbHelper = MyDatabaseHelper(it, "BookStore.db", 2)
        true
    } ?: false

    override fun query(
        uri: Uri, projection: Array<String>?, selection: String?,
        selectionArgs: Array<String>?, sortOrder: String?
    ) = dbHelper?.let {
        val db = it.readableDatabase
        val cursor = when (uriMatcher.match(uri)) {
            bookDir -> db.query(
                "Book", projection, selection, selectionArgs,
                null, null, sortOrder
            )
            bookItem -> {
                val bookId = uri.pathSegments[1]
                db.query("Book", projection, "id=?", arrayOf(bookId), null, null, sortOrder)
            }
            categoryDir -> db.query(
                "Category", projection, selection, selectionArgs,
                null, null, sortOrder
            )
            categoryItem -> {
                val categoryId = uri.pathSegments[1]
                db.query("Category", projection, "id=?", arrayOf(categoryId), null, null, sortOrder)
            }
            else -> null
        }
        cursor
    }

    override fun update(
        uri: Uri, values: ContentValues?, selection: String?,
        selectionArgs: Array<String>?
    ) = dbHelper?.let {
        val db = it.writableDatabase
        val updateRows = when (uriMatcher.match(uri)) {
            bookDir -> db.update("Book", values, selection, selectionArgs)
            bookItem -> {
                val bookId = uri.pathSegments[1]
                db.update("Book", values, "id = ?", arrayOf(bookId))
            }
            categoryDir -> db.update("Category", values, selection, selectionArgs)
            categoryItem -> {
                val categoryId = uri.pathSegments[1]
                db.update("Category", values, "id = ?", arrayOf(categoryId))
            }
            else -> 0
        }
        updateRows
    } ?: 0
}