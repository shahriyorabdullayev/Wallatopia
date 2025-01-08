package uz.droid.wallatopia.presentation.components

import androidx.compose.ui.graphics.BlendMode
import androidx.compose.ui.graphics.NativePaint

internal expect fun NativePaint.setMaskFilter(blurRadius: Float)
internal expect fun NativePaint.setBlendMode(blendMode: BlendMode)
