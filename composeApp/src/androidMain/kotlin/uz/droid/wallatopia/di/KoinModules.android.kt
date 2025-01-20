package uz.droid.wallatopia.di

import android.preference.PreferenceManager
import androidx.compose.ui.platform.LocalContext
import androidx.room.RoomDatabase
import com.russhwolf.settings.Settings
import com.russhwolf.settings.SharedPreferencesSettings
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import uz.droid.wallatopia.data.local.WallatopiaDatabase
import uz.droid.wallatopia.getMyDatabase

actual val platformModule = module {
    single<RoomDatabase.Builder<WallatopiaDatabase>> { getMyDatabase(get()) }
    single<Settings> { SharedPreferencesSettings(PreferenceManager.getDefaultSharedPreferences(androidContext())) }
}