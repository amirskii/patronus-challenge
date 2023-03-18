package com.example.patronuschallenge.ui

import android.os.Bundle
import android.view.View
import androidx.navigation.fragment.findNavController
import com.example.patronuschallenge.R
import com.example.patronuschallenge.databinding.FragmentDeviceHoldersBinding
import com.example.patronuschallenge.ui.base.BaseFragment

class DeviceHoldersFragment : BaseFragment<FragmentDeviceHoldersBinding>(
    FragmentDeviceHoldersBinding::inflate
) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(R.id.to_DetailsFragment)
        }
    }
}