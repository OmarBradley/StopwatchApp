package omarbradley.com.stopwatchapp

import android.app.Application
import omarbradley.com.stopwatchapp.module.appModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class StopwatchApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@StopwatchApp)
            modules(appModule)
        }
    }

}
