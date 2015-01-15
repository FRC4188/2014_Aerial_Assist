/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team4188_2014.subsystems;

import edu.wpi.first.wpilibj.Relay;
import edu.wpi.first.wpilibj.command.Subsystem;
import team4188_2014.RobotMap;

/**
 *
 * @author Owner
 */

//This is a separate class because putting it inside another subsystem and then using the requires() method makes it 
//withold the drivetrain or whatever other class for itself, causing them not to work. So just make a separate subsystem.
public class Lights extends Subsystem {
    Relay LED = RobotMap.LEDRelay;
    boolean LEDsOn = false;
    // Put methods for controlling this subsystem
    // here. Call these from Commands.

    public void initDefaultCommand() {
        // Set the default command for a subsystem here.
        //setDefaultCommand(new MySpecialCommand());
    }
    
    public void init(){
        LED.set(Relay.Value.kOff);
    }
         
    public void turnOnLEDs(){
        LED.set(Relay.Value.kForward);
        LEDsOn = true;
    }
    
    public void turnOffLEDs(){
        LED.set(Relay.Value.kOff);
        LEDsOn = false;
    }
    
    public boolean areLEDsOn(){
        return LEDsOn;
    }
   
}
