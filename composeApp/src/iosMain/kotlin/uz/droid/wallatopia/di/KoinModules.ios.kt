package uz.droid.wallatopia.di

import androidx.room.RoomDatabase
import com.russhwolf.settings.NSUserDefaultsSettings
import com.russhwolf.settings.Settings
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.module
import platform.Foundation.NSUserDefaults
import uz.droid.wallatopia.data.local.WallatopiaDatabase
import uz.droid.wallatopia.getMyDatabase

actual val platformModule = module {
    singleOf<RoomDatabase.Builder<WallatopiaDatabase>>(::getMyDatabase)
    single<Settings> { NSUserDefaultsSettings(NSUserDefaults.standardUserDefaults) }
}