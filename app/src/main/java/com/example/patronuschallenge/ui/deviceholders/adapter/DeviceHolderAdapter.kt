package com.example.patronuschallenge.ui.deviceholders.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import coil.load
import com.example.patronuschallenge.databinding.ItemDeviceHolderBinding
import com.example.patronuschallenge.ui.base.BaseViewHolder
import com.example.patronuschallenge.ui.model.DeviceHolderPm

internal class DeviceHolderAdapter(
    private val onItemClicked: (DeviceHolderPm) -> Unit
)
 : ListAdapter<DeviceHolderPm, DeviceHolderAdapter.DeviceHolderViewHolder>(
    DiffCallback
) {

    companion object {
        private object DiffCallback : DiffUtil.ItemCallback<DeviceHolderPm>() {
            override fun areItemsTheSame(oldItem: DeviceHolderPm, newItem: DeviceHolderPm) =
                oldItem.id == newItem.id

            override fun areContentsTheSame(oldItem: DeviceHolderPm, newItem: DeviceHolderPm) =
                oldItem == newItem
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): DeviceHolderViewHolder =
        LayoutInflater.from(parent.context)
            .let { ItemDeviceHolderBinding.inflate(it, parent, false) }
            .let {
                DeviceHolderViewHolder(it)
            }

    override fun onBindViewHolder(holder: DeviceHolderViewHolder, position: Int) {
        holder.bind(currentList[position], position)
    }

    internal class DeviceHolderViewHolder(
        binding: ItemDeviceHolderBinding
    ) : BaseViewHolder<DeviceHolderPm, ItemDeviceHolderBinding>(binding) {

        override fun bind(item: DeviceHolderPm, position: Int) {
            with(binding) {
                userName.text = item.name
                userSex.text = item.gender
                userPhone.text = item.phoneNumber
                if (item.imageUrl.isEmpty()) {
                    placeholderTextView.text = item.imagePlaceholder
                } else {
                    userImage.load(item.imageUrl)
                }
                famLabel.isVisible = item.showFam
                banLabel.isVisible = item.showBan
            }
        }
    }
}
