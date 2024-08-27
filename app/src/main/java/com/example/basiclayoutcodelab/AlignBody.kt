package com.example.basiclayoutcodelab

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes

data class AlignBody(
    @DrawableRes val drawableRes: Int,
    @StringRes val text: Int
)
