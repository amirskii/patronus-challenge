package com.example.patronuschallenge.utils.extensions

import android.widget.ImageView
import coil.load
import coil.transform.CircleCropTransformation
import com.example.patronuschallenge.R


fun ImageView.loadRoundImage(
    url: String, onError: () -> Unit
) {
    load(url) {
        placeholder(R.drawable.sh_round)
        error(R.drawable.sh_round)
        fallback(R.drawable.sh_round)
        transformations(CircleCropTransformation())
        listener(
            onSuccess = { _, _ ->
            },
            onError = { _, _ ->
                onError()
            })
    }
}