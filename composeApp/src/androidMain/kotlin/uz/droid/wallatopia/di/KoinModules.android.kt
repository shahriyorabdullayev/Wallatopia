package uz.droid.wallatopia.di

import android.app.WallpaperManager
import androidx.preference.PreferenceManager
import androidx.room.RoomDatabase
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import uz.droid.wallatopia.data.local.WallatopiaDatabase
import uz.droid.wallatopia.getMyDatabase
import uz.droid.wallatopia.wallpaper_utils.WallatopiaWallpaperManager

actual val platformModule = module {
    single<RoomDatabase.Builder<WallatopiaDatabase>> { getMyDatabase(get()) }
    single<Settings> {
        SharedPreferencesSettings(
            PreferenceManager.getDefaultSharedPreferences(
                androidContext()
            )
        )
    }
    single { WallpaperManager.getInstance(androidContext()) }
    single { WallatopiaWallpaperManager(get()) }
}