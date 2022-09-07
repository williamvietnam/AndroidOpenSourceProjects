package com.android.vncalling.utilities

import android.app.KeyguardManager
import android.app.KeyguardManager.KeyguardLock
import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.os.Build.VERSION
import android.os.Build.VERSION_CODES
import android.os.PowerManager
import android.os.PowerManager.WakeLock

class Utilities {

}

class SensorManagerUtils(private val context: Context) : SensorEventListener {
    private var mSensorManager: SensorManager? = null
    private var mProximity: Sensor? = null
    private var powerManager: PowerManager? = null
    private var wakeLock: WakeLock? = null
    private var lock: KeyguardLock? = null

    companion object {
        private var instance: SensorManagerUtils? = null
        fun getInstance(context: Context): SensorManagerUtils? {
            if (instance == null) {
                instance = SensorManagerUtils(context)
            }
            return instance
        }
    }

    fun acquireProximitySensor(tag: String?) {
        mSensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
        mProximity = mSensorManager!!.getDefaultSensor(Sensor.TYPE_PROXIMITY)
        mSensorManager!!.registerListener(this, mProximity, SensorManager.SENSOR_DELAY_NORMAL)
        powerManager = context.getSystemService(Context.POWER_SERVICE) as PowerManager
        val screenLockValue: Int = if (VERSION.SDK_INT >= VERSION_CODES.LOLLIPOP) {
            PowerManager.PROXIMITY_SCREEN_OFF_WAKE_LOCK
        } else {
            try {
                PowerManager::class.java.getField("PROXIMITY_SCREEN_OFF_WAKE_LOCK").getInt(null)
            } catch (exc: Exception) {
                32 // default integer value of PROXIMITY_SCREEN_OFF_WAKE_LOCK
            }
        }
        if (wakeLock == null) {
            wakeLock = powerManager!!.newWakeLock(screenLockValue, tag)
        }
        wakeLock?.acquire()
    }

    fun disableKeyguard() {
        if (lock == null) {
            lock =
                (context.getSystemService(Context.KEYGUARD_SERVICE) as KeyguardManager).newKeyguardLock(
                    Context.KEYGUARD_SERVICE
                )
        }
        lock?.disableKeyguard()
    }

    fun releaseSensor() {
        if (mSensorManager != null) {
            mSensorManager!!.unregisterListener(this)
            mSensorManager = null
        }
        if (wakeLock != null && wakeLock!!.isHeld) {
            wakeLock!!.release()
            wakeLock = null
        }
        if (lock != null) {
            lock!!.reenableKeyguard()
            lock = null
        }
        if (instance != null) {
            instance = null
        }
    }

    override fun onSensorChanged(sensorEvent: SensorEvent) {
        val value = sensorEvent.values[0]
        if (value == 0f) {
            if (wakeLock != null && !wakeLock!!.isHeld) {
                wakeLock!!.acquire()
            }
        } else {
            if (wakeLock != null && wakeLock!!.isHeld) {
                wakeLock!!.release()
            }
        }
    }

    override fun onAccuracyChanged(sensor: Sensor, i: Int) {

    }
}