package uz.droid.wallatopia.common.preference

import com.russhwolf.settings.Settings
import kotlinx.coroutines.flow.Flow

sealed class SettingConfig<T>(
    val settings: Settings,
    val key: String,
    val defaultValue: T,
) {
    protected abstract fun getStringValue(settings: Settings, key: String, defaultValue: T): String
    protected abstract fun setStringValue(settings: Settings, key: String, value: String)

    fun remove() = settings.remove(key)
    fun exists(): Boolean = settings.hasKey(key)

    fun get(): String = getStringValue(settings, key, defaultValue)
    fun set(value: String): Boolean {
        return try {
            setStringValue(settings, key, value)
            true
        } catch (exception: Exception) {
            false
        }
    }

    override fun toString() = key

    abstract val value: Flow<String>
}