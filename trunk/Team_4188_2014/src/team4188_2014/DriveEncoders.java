/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package team4188_2014;

import edu.wpi.first.wpilibj.Encoder;
import edu.wpi.first.wpilibj.PIDSource;

/**
 *
 * @author Erin
 */
public class DriveEncoders implements PIDSource {
    Encoder left, right;
    
    public DriveEncoders(Encoder left, Encoder right) {
        this.left = left;
        this.right = right;
        this.left.setPIDSourceParameter(Encoder.PIDSourceParameter.kDistance);
        this.right.setPIDSourceParameter(Encoder.PIDSourceParameter.kDistance);
    }
    
    public DriveEncoders(final int aSlotL, final int aChannelL,
            final int bSlotL, final int bChannelL,
            boolean reverseDirectionL, final int aSlotR, final int aChannelR,
            final int bSlotR, final int bChannelR,
            boolean reverseDirectionR) {
        this.left = new Encoder(aSlotL,aChannelL,bSlotL,bChannelL,reverseDirectionL);
        this.right = new Encoder(aSlotR,aChannelR,bSlotR,bChannelR,reverseDirectionR);
        left.setPIDSourceParameter(Encoder.PIDSourceParameter.kDistance);
        right.setPIDSourceParameter(Encoder.PIDSourceParameter.kDistance);
    }
    
    public DriveEncoders(final int aSlotL, final int aChannelL,
            final int bSlotL, final int bChannelL, final int aSlotR,
            final int aChannelR, final int bSlotR, final int bChannelR) {
        this.left = new Encoder(aSlotL,aChannelL,bSlotL,bChannelL);
        this.right = new Encoder(aSlotR,aChannelR,bSlotR,bChannelR);
        left.setPIDSourceParameter(Encoder.PIDSourceParameter.kDistance);
        right.setPIDSourceParameter(Encoder.PIDSourceParameter.kDistance);
    }
    
    public DriveEncoders(final int aChannelL, final int bChannelL,
            final int aChannelR, final int bChannelR) {
        this.left = new Encoder(aChannelL,bChannelL);
        this.right = new Encoder(aChannelR,bChannelR);
        left.setPIDSourceParameter(Encoder.PIDSourceParameter.kDistance);
        right.setPIDSourceParameter(Encoder.PIDSourceParameter.kDistance);
    }
    // finish the rest of the constructors later...
    
    public void reset() {
        left.reset();
        right.reset();
    }
    
    /**
     * @param wheelSide 0 for left, 1 for right. Incorrect side input will return a position value of 0.0.
     * @return Count from the encoder (since the last reset?).
     */
    public double getEncoderCount(int wheelSide) {
        if (wheelSide == 0) return left.get();
        else if (wheelSide == 1) return right.get();
        else return 0.0;
    }
    
    /**
     * @param wheelSide 0 for left, 1 for right. Incorrect side input will return a position value of 0.0.
     * @return Distance the encoder has recorded since the last reset, adjusted for the gear ratio.
     */
    public double getEncoderDistance(int wheelSide) {
        if (wheelSide == 0) return left.getDistance();
        else if (wheelSide == 1) return right.getDistance();
        else return 0.0;
    }
    
    public double getAvgEncoderDistance() {
        return (left.getDistance()+right.getDistance())/2.0;
    }
    
    // returns average of the two encoder readings...?
    public double pidGet() {
        //return (left.pidGet()+right.pidGet())/2.0;
        return getAvgEncoderDistance();
    }
    
}
