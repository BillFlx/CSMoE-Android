package in.celest.xash3d;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;

public class Gyroscope implements SensorEventListener {
    private SensorManager sensorManager;
    private Sensor gyroSensor;

    public Gyroscope(Context context) {
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
        gyroSensor = sensorManager.getDefaultSensor(Sensor.TYPE_GYROSCOPE);
    }

    // Start listening to gyroscope data
    public void start() {
        sensorManager.registerListener(this, gyroSensor, SensorManager.SENSOR_DELAY_GAME);
    }

    // Stop listening to gyroscope data
    public void stop() {
        sensorManager.unregisterListener(this);
    }

    @Override
    public void onSensorChanged(SensorEvent event) {
        float x = event.values[0];
        float y = event.values[1];

        // Optional: enable if roll (z-axis) data is needed
        // float z = event.values[2];

        nativeOnGyroscopeChanged(x, y);
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int accuracy) { }

    // Send gyroscope data to the engine via JNI (C++)
    private native void nativeOnGyroscopeChanged(float x, float y);
}