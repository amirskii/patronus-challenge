package com.example.patronuschallenge.utils

import android.content.Context
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.DividerItemDecoration
import com.example.patronuschallenge.R

object UiUtils {

    fun getDividerIconDecoration(context: Context): DividerItemDecoration =
        DividerItemDecoration(context, LinearLayout.VERTICAL)
            .apply {
                ContextCompat.getDrawable(
                    context,
                    R.drawable.divider_item_decoration
                )
                    ?.let {
                        setDrawable(it)
                    }
            }
}
