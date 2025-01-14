package info.nightscout.androidaps.danar.comm

import dagger.android.HasAndroidInjector
import info.nightscout.androidaps.danar.R
import info.nightscout.androidaps.logging.LTag
import info.nightscout.androidaps.plugins.general.overview.events.EventNewNotification
import info.nightscout.androidaps.plugins.general.overview.notifications.Notification

class MsgSetSingleBasalProfile(
    injector: HasAndroidInjector,
    values: Array<Double>
) : MessageBase(injector) {

    // index 0-3
    init {
        SetCommand(0x3302)
        for (i in 0..23) {
            AddParamInt((values[i] * 100).toInt())
        }
        aapsLogger.debug(LTag.PUMPCOMM, "New message")
    }

    override fun handleMessage(bytes: ByteArray) {
        val result = intFromBuff(bytes, 0, 1)
        if (result != 1) {
            failed = true
            aapsLogger.debug(LTag.PUMPCOMM, "Set basal profile result: $result FAILED!!!")
            val reportFail = Notification(Notification.PROFILE_SET_FAILED, rh.gs(R.string.profile_set_failed), Notification.URGENT)
            rxBus.send(EventNewNotification(reportFail))
        } else {
            failed = false
            aapsLogger.debug(LTag.PUMPCOMM, "Set basal profile result: $result")
            val reportOK = Notification(Notification.PROFILE_SET_OK, rh.gs(R.string.profile_set_ok), Notification.INFO, 60)
            rxBus.send(EventNewNotification(reportOK))
        }
    }
}