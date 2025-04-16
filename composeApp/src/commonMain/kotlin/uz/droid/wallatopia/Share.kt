package uz.droid.wallatopia

import androidx.compose.runtime.Composable
import uz.droid.wallatopia.domain.model.ShareImageModel

@Suppress("EXPECT_ACTUAL_CLASSIFIERS_ARE_IN_BETA_WARNING")
expect class ShareManager {
    suspend fun shareImage(image: ShareImageModel): Result<Unit>
    fun openRateUs()
}

@Composable
expect fun rememberShareManager(): ShareManager