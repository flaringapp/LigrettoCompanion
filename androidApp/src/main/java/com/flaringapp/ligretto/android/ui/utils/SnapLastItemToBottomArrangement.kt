package com.flaringapp.ligretto.android.ui.utils

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.ui.unit.Density

class SnapLastItemToBottomArrangement : Arrangement.Vertical {

    override fun Density.arrange(totalSize: Int, sizes: IntArray, outPositions: IntArray) {
        val lastIndex = sizes.lastIndex
        var currentOffset = 0
        sizes.forEachIndexed { index, size ->
            if (index == lastIndex) {
                outPositions[index] = totalSize - size
            } else {
                outPositions[index] = currentOffset
                currentOffset += size
            }
        }
    }
}
