package com.wxd.firstlinecode.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.wxd.firstlinecode.R
import com.wxd.firstlinecode.databinding.ActivityFragmentBinding
import com.wxd.firstlinecode.databinding.NewsContentFragBinding

class NewsContentFragment : Fragment() {

    private var binding: NewsContentFragBinding? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = NewsContentFragBinding.inflate(inflater, container, false)
        return binding?.root
    }

    fun refresh(title: String, content: String) {
        binding?.visibilityLayout?.visibility = View.VISIBLE
        binding?.newsTitle?.text = title
        binding?.newsContent?.text = content
    }

    override fun onDestroyView() {
        super.onDestroyView()
        binding = null
    }
}