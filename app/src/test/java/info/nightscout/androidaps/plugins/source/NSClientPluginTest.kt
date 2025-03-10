package info.nightscout.androidaps.plugins.source

import dagger.android.AndroidInjector
import info.nightscout.androidaps.TestBase
import info.nightscout.androidaps.utils.buildHelper.ConfigImpl
import info.nightscout.androidaps.utils.resources.ResourceHelper
import info.nightscout.androidaps.utils.sharedPreferences.SP
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.mockito.Mock

class NSClientPluginTest : TestBase() {

    private lateinit var nsClientSourcePlugin: NSClientSourcePlugin

    @Mock lateinit var rh: ResourceHelper
    @Mock lateinit var sp: SP

    @Before
    fun setup() {
        nsClientSourcePlugin = NSClientSourcePlugin({ AndroidInjector { } }, rh, aapsLogger, ConfigImpl())
    }

    @Test fun advancedFilteringSupported() {
        Assert.assertEquals(false, nsClientSourcePlugin.advancedFilteringSupported())
    }
}