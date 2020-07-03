/**
 *  Jamie Lynn Lufrano - ASL Fingerspelling Bee - Project Iteration 3
 *  Class that sets up the Observable for the accelerometer.
 *  Project Iteration 5
 *  Updated sensitivity settings to make the shake detection less sensitive.
 */

package aslfingerspellingbee;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import java.util.List;

public class AccelerometerManager {
    private static Context context = null;

    // Settings for sensor sensitivity.
    private static float FORCE_THRESHOLD = 20.5f;
    private static int TIME_INTERVAL = 1000;

    // Variables for listener.
    private static Sensor sensor;
    private static SensorManager sensorManager;
    private static AccelerometerListener listener;

    // Indicates whether accelerometer sensor is supported.
    private static Boolean supported;

    // Indicates if accelerometer sensor is running.
    private static boolean running = false;

    /**
     * Checks if at least one accelerometer sensor is available and returns true, if so.
     */
    public static boolean isSupported(Context contxt) {
        context = contxt;
        if (supported == null) {
            if (context != null) {
                sensorManager = (SensorManager) context.getSystemService((Context.SENSOR_SERVICE));

                // Get all sensors.
                List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);
                supported = new Boolean(sensors.size() > 0);
            } else {
                supported = Boolean.FALSE;
            }
        } return supported;
    }

    /**
     * Checks whether the manager is listening for accelerometer events and returns true if it is
     * listening.
     */
    public static boolean isListening() {
        return running;
    }

    /**
     * Registers a listener and start listening.
     */
    public static void startListening(AccelerometerListener accelerometerListener) {
        sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);

        List<Sensor> sensors = sensorManager.getSensorList(Sensor.TYPE_ACCELEROMETER);

        if (sensors.size() > 0) {
            sensor = sensors.get(0);

            // Register listener
            running = sensorManager.registerListener(sensorEventListener, sensor,
                    SensorManager.SENSOR_DELAY_GAME);
            listener = accelerometerListener;
        }
    }

    /**
     * Configures threshold, registers listener, and starts listening.
     */
    public static void startListening(AccelerometerListener accelerometerListener, int threshold,
                                      int interval) {
        configure(threshold, interval);
        startListening(accelerometerListener);
    }

    /**
     * Unregisters listeners.
     */
    public static void stopListening() {
        running = false;
        try {
            if (sensorManager != null && sensorEventListener != null) {
                sensorManager.unregisterListener(sensorEventListener);
            }
        } catch (Exception e) {
        }
    }

    /**
     * Configure the listener for shaking.
     */
    public static void configure(int threshold, int interval) {
        AccelerometerManager.FORCE_THRESHOLD = threshold;
        AccelerometerManager.TIME_INTERVAL = interval;
    }

    /**
     * Creates listener for Accelerometer.
     */
    private static SensorEventListener sensorEventListener = new SensorEventListener() {

        private long now = 0;
        private long timeDiff = 0;
        private long lastUpdate = 0;
        private long lastShake = 0;

        private float x = 0;
        private float y = 0;
        private float z = 0;
        private float lastX = 0;
        private float lastY = 0;
        private float lastZ = 0;
        private float force = 0;

        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            now = sensorEvent.timestamp;
            x = sensorEvent.values[0];
            y = sensorEvent.values[1];
            z = sensorEvent.values[2];

            if(lastUpdate == 0) {
                lastUpdate = now;
                lastShake = now;
                lastX = x;
                lastY = y;
                lastZ = z;
            } else {
                timeDiff = now - lastUpdate;
                if (timeDiff > 0) {
                    force = Math.abs(x + y + z - lastX - lastY - lastZ);
                    if (Float.compare(force, FORCE_THRESHOLD) > 0) {
                        if (now - lastShake >= TIME_INTERVAL) {
                            // Shake event triggered
                            listener.onShake(force);
                        }
                    }
                    lastShake = now;
                }
            }

            listener.onAccelerationChanged(x,y,z);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int accuracy) {}
    };
}
