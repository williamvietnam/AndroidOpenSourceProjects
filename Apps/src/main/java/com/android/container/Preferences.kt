package com.android.container
import android.content.Context
import android.content.SharedPreferences
import java.util.concurrent.*

internal class SharedPreferencesLoader {

    internal interface OnPrefsLoadedListener {
        fun onPrefsLoaded(prefs: SharedPreferences?)
    }

    fun loadPreferences(
        context: Context,
        name: String?,
        listener: OnPrefsLoadedListener?
    ): Future<SharedPreferences> {
        val loadSharedPrefs = LoadSharedPreferences(context, name, listener)
        val task = FutureTask(loadSharedPrefs)
        mExecutor.execute(task)
        return task
    }

    private class LoadSharedPreferences(
        private val mContext: Context,
        private val mPrefsName: String?,
        private val mListener: OnPrefsLoadedListener?
    ) :
        Callable<SharedPreferences> {
        override fun call(): SharedPreferences {
            val ret = mContext.getSharedPreferences(mPrefsName, Context.MODE_PRIVATE)
            mListener?.onPrefsLoaded(ret)
            return ret
        }
    }

    private val mExecutor: Executor

    init {
        mExecutor = Executors.newSingleThreadExecutor()
    }
}

class Preferences {
    private val sPrefsLoader = SharedPreferencesLoader()
    private var sReferrerPrefs: Future<SharedPreferences>? = null

    companion object {
        private const val REFERRER_PREFS_NAME = "com.android"
        val instance = Preferences()
    }

    fun load(context: Context) {
        if (null == sReferrerPrefs) {
            sReferrerPrefs = sPrefsLoader.loadPreferences(context, REFERRER_PREFS_NAME, null);
        }
    }

    fun get(keyPref: String?, defaultValue: Any?): Any? {
        val pref: SharedPreferences
        try {
            pref = sReferrerPrefs?.get() ?: return null
            when (defaultValue) {
                is String -> {
                    return pref.getString(keyPref, defaultValue as String?)
                }
                is Int -> {
                    return pref.getInt(keyPref, (defaultValue as Int?)!!)
                }
                is Float -> {
                    return pref.getFloat(keyPref, (defaultValue as Float?)!!)
                }
                is Boolean -> {
                    return pref.getBoolean(keyPref, (defaultValue as Boolean?)!!)
                }
                is Long -> {
                    return pref.getLong(keyPref, (defaultValue as Long?)!!)
                }
            }
        } catch (ignored: InterruptedException) {
        } catch (ignored: ExecutionException) {
        }
        return null
    }

    fun set(keyPref: String?, valuePref: Any?) {
        try {
            val pref: SharedPreferences = sReferrerPrefs?.get() ?: return
            val editor = pref.edit()
            if (valuePref == null) return
            when (valuePref) {
                is String -> {
                    editor.putString(keyPref, valuePref as String?)
                }
                is Int -> {
                    editor.putInt(keyPref, (valuePref as Int?)!!)
                }
                is Float -> {
                    editor.putFloat(keyPref, (valuePref as Float?)!!)
                }
                is Boolean -> {
                    editor.putBoolean(keyPref, (valuePref as Boolean?)!!)
                }
                is Long -> {
                    editor.putLong(keyPref, (valuePref as Long?)!!)
                }
            }
            editor.apply()
        } catch (ignored: InterruptedException) {
        } catch (ignored: ExecutionException) {
        }
    }
}