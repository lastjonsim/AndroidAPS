package info.nightscout.androidaps.di

import android.content.Context
import android.telephony.SmsManager
import androidx.preference.PreferenceManager
import dagger.Module
import dagger.Provides
import info.nightscout.androidaps.logging.AAPSLogger
import info.nightscout.androidaps.logging.AAPSLoggerProduction
import info.nightscout.androidaps.logging.L
import info.nightscout.androidaps.utils.resources.ResourceHelper
import info.nightscout.androidaps.utils.resources.ResourceHelperImplementation
import info.nightscout.androidaps.utils.sharedPreferences.SP
import info.nightscout.androidaps.utils.sharedPreferences.SPImplementation
import javax.inject.Singleton

@Module(includes = [
    CoreReceiversModule::class,
    CoreFragmentsModule::class,
    CoreDataClassesModule::class
])
open class CoreModule {

    @Provides
    @Singleton
    fun provideResources(context: Context): ResourceHelper = ResourceHelperImplementation(context)

    @Provides
    @Singleton
    fun provideSharedPreferences(context: Context, rh: ResourceHelper): SP = SPImplementation(PreferenceManager.getDefaultSharedPreferences(context), rh)

    @Provides
    @Singleton
    fun provideAAPSLogger(l: L): AAPSLogger = AAPSLoggerProduction(l)

    @Provides
    fun smsManager() : SmsManager = SmsManager.getDefault()

}