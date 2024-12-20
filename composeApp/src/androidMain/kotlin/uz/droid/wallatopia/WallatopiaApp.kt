package uz.droid.wallatopia

import android.app.Application
import android.graphics.Bitmap
import io.github.aakira.napier.DebugAntilog
import io.github.aakira.napier.Napier
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import uz.droid.wallatopia.di.initKoin

class WallatopiaApp : Application() {
    override fun onCreate() {
        super.onCreate()

        Napier.base(DebugAntilog())

        initKoin {
            androidLogger()
            androidContext(this@WallatopiaApp)
        }
    }
}