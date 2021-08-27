package com.wxd.firstlinecode.fragment

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.wxd.firstlinecode.R
import com.wxd.firstlinecode.databinding.NewsContentFragBinding
import com.wxd.firstlinecode.databinding.NewsItemBinding
import com.wxd.firstlinecode.databinding.NewsTitleFragBinding
import com.wxd.firstlinecode.kt.times

class NewsTitleFragment : Fragment() {

    private var binding: NewsTitleFragBinding? = null
    private var isTwoPane = false

    inner class NewsAdapter(val newsList: List<News>) :
        RecyclerView.Adapter<NewsAdapter.ViewHolder>() {
        inner class ViewHolder(binding: NewsItemBinding) : RecyclerView.ViewHolder(binding.root) {
            val newsTitle: TextView = binding.newsTitle
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val binding = NewsItemBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
            return ViewHolder(binding)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val news = newsList[position]
            holder.newsTitle.text = news.title
            holder.itemView.setOnClickListener {
                if (isTwoPane) {
                    // 如果是双页模式，则刷新NewsContentFragment中的内容
                    val fragment =
                        activity?.supportFragmentManager?.findFragmentById(R.id.news_content_fragment) as NewsContentFragment
                    fragment.refresh(news.title, news.content)
                } else {
                    // 如果是单页模式，则直接启动NewsContentActivity
                    NewsContentActivity.actionStart(
                        activity!!, news.title,
                        news.content
                    )
                }
            }
        }

        override fun getItemCount(): Int {
            return newsList.size
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = NewsTitleFragBinding.inflate(inflater,container,false)
        return binding?.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        isTwoPane = activity?.findViewById<View>(R.id.news_content_layout) != null
        val layoutManager = LinearLayoutManager(activity)
        binding?.newsTitleRecyclerView?.layoutManager = layoutManager
        val adapter = NewsAdapter(getNews())
        binding?.newsTitleRecyclerView?.adapter = adapter
    }

    private fun getNews(): List<News> {
        val newsList = ArrayList<News>()
        for (i in 1..50) {
            val news =
                News("This is news title $i", getRandomLengthString("This is news content $i. "))
            newsList.add(news)
        }
        return newsList
    }

    private fun getRandomLengthString(str: String): String {
        val n = (1..20).random()
        return str*n
    }

}