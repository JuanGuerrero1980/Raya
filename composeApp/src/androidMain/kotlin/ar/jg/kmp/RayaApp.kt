package ar.jg.kmp

import android.app.Application
import ar.jg.kmp.di.initKoin
import io.ktor.http.ContentType
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.component.KoinComponent

class RayaApp: Application(), KoinComponent {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidLogger()
            androidContext(this@RayaApp)
        }
    }
}