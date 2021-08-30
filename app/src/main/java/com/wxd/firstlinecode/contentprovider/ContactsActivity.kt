package com.wxd.firstlinecode.contentprovider

import android.Manifest
import android.content.pm.PackageManager
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.ContactsContract
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wxd.firstlinecode.R
import com.wxd.firstlinecode.databinding.ActivityContactsBinding
import com.wxd.firstlinecode.databinding.NewsItemBinding

class ContactsActivity : AppCompatActivity() {

    private lateinit var binding: ActivityContactsBinding
    private val contactsList = ArrayList<String>()
    private lateinit var adapter: ContactsAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityContactsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val layoutManager = LinearLayoutManager(this)
        binding.recyclerView.layoutManager = layoutManager
        adapter = ContactsAdapter(contactsList)
        binding.recyclerView.adapter = adapter
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.READ_CONTACTS) !=
            PackageManager.PERMISSION_GRANTED
        ) {
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_CONTACTS), 1)
        } else {
            readContacts()
        }
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when (requestCode) {
            1 -> {
                if (grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    readContacts()
                } else {
                    Toast.makeText(
                        this, "You denied the permission",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    private fun readContacts() {
        contentResolver.query(
            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
            null,
            null,
            null,
            null
        )?.apply {
            while (moveToNext()) {
                val displayName =
                    getString(getColumnIndex(ContactsContract.CommonDataKinds.Phone.DISPLAY_NAME))
                val number =
                    getString(getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                contactsList.add("$displayName\n$number")
            }
            adapter.notifyDataSetChanged()
            close()
        }
    }

    inner class ContactsAdapter(val contactsList: List<String>) :
        RecyclerView.Adapter<ContactsAdapter.ViewHolder>() {
        inner class ViewHolder(itemBinding: NewsItemBinding) :
            RecyclerView.ViewHolder(itemBinding.root) {
            val contact = itemBinding.newsTitle
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val binding =
                NewsItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
            return ViewHolder(binding)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            holder.contact.text = contactsList[position]
        }

        override fun getItemCount(): Int {
            return contactsList.size
        }
    }
}