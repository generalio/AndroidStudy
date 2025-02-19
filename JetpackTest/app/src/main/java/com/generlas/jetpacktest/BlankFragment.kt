package com.generlas.jetpacktest

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.generlas.jetpacktest.databinding.FragmentBlankBinding

class BlankFragment : BaseFragment<FragmentBlankBinding>() {
    override fun inflateBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentBlankBinding {
        return FragmentBlankBinding.inflate(inflater,container,false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        // 在这里编写你的代码
        binding.textView.text = "Hello, ViewBinding in Fragment!"
    }
}