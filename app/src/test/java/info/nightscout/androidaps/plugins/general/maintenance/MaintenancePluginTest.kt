package info.nightscout.androidaps.plugins.general.maintenance

import android.content.Context
import dagger.android.HasAndroidInjector
import info.nightscout.androidaps.TestBase
import info.nightscout.androidaps.plugins.general.nsclient.data.NSSettingsStatus
import info.nightscout.androidaps.utils.buildHelper.BuildHelper
import info.nightscout.androidaps.utils.buildHelper.ConfigImpl
import info.nightscout.androidaps.utils.resources.ResourceHelper
import info.nightscout.androidaps.utils.sharedPreferences.SP
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock
import org.mockito.Mockito.`when`
import java.io.File

class MaintenancePluginTest : TestBase() {

    @Mock lateinit var injector: HasAndroidInjector
    @Mock lateinit var context: Context
    @Mock lateinit var rh: ResourceHelper
    @Mock lateinit var sp: SP
    @Mock lateinit var nsSettingsStatus: NSSettingsStatus
    @Mock lateinit var buildHelper: BuildHelper
    @Mock lateinit var loggerUtils: LoggerUtils
    @Mock lateinit var fileListProvider: PrefFileListProvider

    lateinit var sut: MaintenancePlugin

    @Before
    fun mock() {
        sut = MaintenancePlugin(injector, context, rh, sp, nsSettingsStatus, aapsLogger, buildHelper, ConfigImpl(), fileListProvider, loggerUtils)
        `when`(loggerUtils.suffix).thenReturn(".log.zip")
        `when`(loggerUtils.logDirectory).thenReturn("src/test/res/logger")
        `when`(fileListProvider.ensureTempDirExists()).thenReturn(File("src/test/res/logger"))
    }

    @Test fun logFilesTest() {
        var logs = sut.getLogFiles(2)
        Assert.assertEquals(2, logs.size)
        Assert.assertEquals("AndroidAPS.log", logs[0].name)
        Assert.assertEquals("AndroidAPS.2018-01-03_01-01-00.1.zip", logs[1].name)
        logs = sut.getLogFiles(10)
        Assert.assertEquals(4, logs.size)
    }

    @Test
    fun zipLogsTest() {
        val logs = sut.getLogFiles(2)
        val name = "AndroidAPS.log.zip"
        var zipFile = File("build/$name")
        zipFile = sut.zipLogs(zipFile, logs)
        Assert.assertTrue(zipFile.exists())
        Assert.assertTrue(zipFile.isFile)
    }
}