package com.example.patronuschallenge.ui.details

import android.os.Bundle
import android.view.View
import androidx.core.view.isVisible
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.example.patronuschallenge.R
import com.example.patronuschallenge.databinding.FragmentDetailsBinding
import com.example.patronuschallenge.ui.base.BaseFragment
import com.example.patronuschallenge.utils.extensions.loadRoundImage
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailsFragment : BaseFragment<FragmentDetailsBinding>(FragmentDetailsBinding::inflate) {

    private val viewModel by viewModel<DetailsViewModelImpl>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.detailsToolbar.setNavigationOnClickListener {
            findNavController().navigateUp()
        }

        observe()
    }

    private fun observe() {
        lifecycleScope.launchWhenStarted {
            viewModel.uiState.collect { state ->
                state.deviceHolder?.let {
                    with(binding) {
                        userName.text = it.name
                        userGender.text = it.gender
                        userPhone.text = it.phoneNumber
                        addressValue.text = it.address
                        famLabel.isVisible = it.showFam
                        banLabel.isVisible = it.showBan
                        setupMap(it.lat, it.lon)

                        placeholderTextView.isVisible = it.imageUrl.isEmpty()
                        placeholderTextView.text = it.imagePlaceholder
                        userImage.loadRoundImage(it.imageUrl, onError = {
                            placeholderTextView.isVisible = true
                            placeholderTextView.text = it.imagePlaceholder
                        })
                    }
                }
                if (!state.error.isNullOrBlank()) {
                    showError(state.error)
                }
            }
        }
    }

    private fun setupMap(lat: Double, lon: Double) {
        (childFragmentManager.findFragmentById(R.id.fcv_map) as? SupportMapFragment)?.getMapAsync { googleMap ->
            googleMap.setMinZoomPreference(15.0f)
            googleMap.setMaxZoomPreference(20.0f)

            val userLocation = LatLng(lat, lon)
            googleMap.addMarker(
                MarkerOptions().position(userLocation)
                    .icon(BitmapDescriptorFactory.fromResource(R.drawable.location_marker))
            )
            googleMap.moveCamera(CameraUpdateFactory.newLatLng(userLocation))
        }
    }
}