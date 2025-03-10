package info.nightscout.androidaps.interfaces

import android.os.SystemClock
import dagger.android.HasAndroidInjector
import info.nightscout.androidaps.logging.AAPSLogger
import info.nightscout.androidaps.utils.resources.ResourceHelper

abstract class PumpPluginBase(
    pluginDescription: PluginDescription,
    injector: HasAndroidInjector,
    aapsLogger: AAPSLogger,
    rh: ResourceHelper,
    val commandQueue: CommandQueue
) : PluginBase(pluginDescription, aapsLogger, rh, injector) {

    override fun onStart() {
        super.onStart()
        if (getType() == PluginType.PUMP) {
            Thread {
                SystemClock.sleep(3000)
                commandQueue.readStatus("Pump driver changed.", null)
            }.start()
        }
    }
}