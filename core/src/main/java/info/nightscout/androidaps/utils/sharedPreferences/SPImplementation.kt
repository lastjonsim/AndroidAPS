package info.nightscout.androidaps.utils.sharedPreferences

import android.content.SharedPreferences
import info.nightscout.androidaps.utils.SafeParse
import info.nightscout.androidaps.utils.resources.ResourceHelper
import javax.inject.Inject
import javax.inject.Singleton

/**
 * Created by mike on 17.02.2017.
 */
@Singleton
class SPImplementation @Inject constructor(
    private val sharedPreferences: SharedPreferences,
    private val rh: ResourceHelper) : SP {

    override fun getAll(): Map<String, *> = sharedPreferences.all

    override fun clear() = sharedPreferences.edit().clear().apply()

    override fun contains(key: String): Boolean = sharedPreferences.contains(key)

    override fun contains(resourceId: Int): Boolean = sharedPreferences.contains(rh.gs(resourceId))

    override fun remove(resourceID: Int) =
        sharedPreferences.edit().remove(rh.gs(resourceID)).apply()

    override fun remove(key: String) =
        sharedPreferences.edit().remove(key).apply()

    override fun getString(resourceID: Int, defaultValue: String): String =
        sharedPreferences.getString(rh.gs(resourceID), defaultValue) ?: defaultValue

    override fun getStringOrNull(resourceID: Int, defaultValue: String?): String? =
        sharedPreferences.getString(rh.gs(resourceID), defaultValue) ?: defaultValue

    override fun getStringOrNull(key: String, defaultValue: String?): String? =
        sharedPreferences.getString(key, defaultValue)

    override fun getString(key: String, defaultValue: String): String =
        sharedPreferences.getString(key, defaultValue) ?: defaultValue

    override fun getBoolean(resourceID: Int, defaultValue: Boolean): Boolean {
        return try {
            sharedPreferences.getBoolean(rh.gs(resourceID), defaultValue)
        } catch (e: Exception) {
            defaultValue
        }
    }

    override fun getBoolean(key: String, defaultValue: Boolean): Boolean {
        return try {
            sharedPreferences.getBoolean(key, defaultValue)
        } catch (e: Exception) {
            defaultValue
        }
    }

    override fun getDouble(resourceID: Int, defaultValue: Double): Double =
        SafeParse.stringToDouble(sharedPreferences.getString(rh.gs(resourceID), defaultValue.toString()))

    override fun getDouble(key: String, defaultValue: Double): Double =
        SafeParse.stringToDouble(sharedPreferences.getString(key, defaultValue.toString()))

    override fun getInt(resourceID: Int, defaultValue: Int): Int {
        return try {
            sharedPreferences.getInt(rh.gs(resourceID), defaultValue)
        } catch (e: Exception) {
            SafeParse.stringToInt(sharedPreferences.getString(rh.gs(resourceID), defaultValue.toString()))
        }
    }

    override fun getInt(key: String, defaultValue: Int): Int {
        return try {
            sharedPreferences.getInt(key, defaultValue)
        } catch (e: Exception) {
            SafeParse.stringToInt(sharedPreferences.getString(key, defaultValue.toString()))
        }
    }

    override fun getLong(resourceID: Int, defaultValue: Long): Long {
        return try {
            sharedPreferences.getLong(rh.gs(resourceID), defaultValue)
        } catch (e: Exception) {
            try {
                SafeParse.stringToLong(sharedPreferences.getString(rh.gs(resourceID), defaultValue.toString()))
            } catch (e: Exception) {
                defaultValue
            }
        }
    }

    override fun getLong(key: String, defaultValue: Long): Long {
        return try {
            sharedPreferences.getLong(key, defaultValue)
        } catch (e: Exception) {
            try {
                SafeParse.stringToLong(sharedPreferences.getString(key, defaultValue.toString()))
            } catch (e: Exception) {
                defaultValue
            }
        }
    }

    override fun incLong(resourceID: Int) {
        val value = getLong(resourceID, 0) + 1L
        sharedPreferences.edit().putLong(rh.gs(resourceID), value).apply()
    }

    override fun putBoolean(key: String, value: Boolean) = sharedPreferences.edit().putBoolean(key, value).apply()

    override fun putBoolean(resourceID: Int, value: Boolean) =
        sharedPreferences.edit().putBoolean(rh.gs(resourceID), value).apply()

    override fun putDouble(key: String, value: Double) =
        sharedPreferences.edit().putString(key, value.toString()).apply()

    override fun putDouble(resourceID: Int, value: Double) {
        sharedPreferences.edit().putString(rh.gs(resourceID), value.toString()).apply()
    }

    override fun putLong(key: String, value: Long) =
        sharedPreferences.edit().putLong(key, value).apply()

    override fun putLong(resourceID: Int, value: Long) =
        sharedPreferences.edit().putLong(rh.gs(resourceID), value).apply()

    override fun putInt(key: String, value: Int) =
        sharedPreferences.edit().putInt(key, value).apply()

    override fun putInt(resourceID: Int, value: Int) =
        sharedPreferences.edit().putInt(rh.gs(resourceID), value).apply()

    override fun incInt(resourceID: Int) {
        val value = getInt(resourceID, 0) + 1
        sharedPreferences.edit().putInt(rh.gs(resourceID), value).apply()
    }

    override fun putString(resourceID: Int, value: String) =
        sharedPreferences.edit().putString(rh.gs(resourceID), value).apply()

    override fun putString(key: String, value: String) =
        sharedPreferences.edit().putString(key, value).apply()

}