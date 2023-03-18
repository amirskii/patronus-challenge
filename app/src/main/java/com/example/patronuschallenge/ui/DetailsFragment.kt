package com.example.patronuschallenge.ui

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import com.example.patronuschallenge.R
import com.example.patronuschallenge.databinding.FragmentDetailsBinding
import com.example.patronuschallenge.ui.base.BaseFragment

class DetailsFragment : BaseFragment<FragmentDetailsBinding>(FragmentDetailsBinding::inflate) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSecond.setOnClickListener {
            findNavController().navigate(R.id.to_DeviceHoldersFragment)
        }
    }
}