/**
 * Jamie Lynn Lufrano - ASL Fingerspelling Bee - Project Iteration 3
 * Interface for the use of the accelerometer for use with features that are dependent on shake
 * detection..
 */
package aslfingerspellingbee;

public interface AccelerometerListener {

    public void onAccelerationChanged(float x, float y, float z);

    public void onShake(float force);
}
