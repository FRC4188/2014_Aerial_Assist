/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team4188_2014.commands;

import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.camera.AxisCameraException;
import edu.wpi.first.wpilibj.command.Command;
import edu.wpi.first.wpilibj.image.NIVisionException;
import edu.wpi.first.wpilibj.smartdashboard.SmartDashboard;
import team4188_2014.CorpsLog;
import team4188_2014.Robot;
import team4188_2014.RobotMap;

/**
 *
 * @author Owner
 */
public class RecognizeTarget extends Command {
    boolean cameradoneYet = false;
    int i = 1;
    
    public RecognizeTarget() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.vision);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.vision.turnLightsOn();
        
        if(!Robot.vision.targetRecognition() && i <= 10){
            i++;
            SmartDashboard.putNumber("NumImages", i);
            cameradoneYet = false;
        }
        
        else{
//                Robot.drivetrain.driveAuto(0, 0.2, 0, 0);
//                Timer.delay(1.0);
//                Robot.drivetrain.driveAuto(0, 0, 0, 0);
//                
                SmartDashboard.putBoolean("Gate Latch Autonomous", Robot.shooter.getGateLatch());
                
                CorpsLog.log("Autonomous", "Shooting...", false, true);
                Robot.shooter.deployShooter();
                    
                if(Robot.shooter.getGateLatch()) {
                    CorpsLog.log("Autonomous", "Retracting Shooter...", false, true);
                    Robot.shooter.retractShooter();
                    Timer.delay(3.0);
                }

                //if statement makes difficult for code to work unless perfect timing. while ensures no action until limit switch hit
                //while may block other code...? test to see if escape is possible by hitting copilot buttons for shooter
                if(!Robot.shooter.getGateLatch()) {
                    CorpsLog.log("Autonomous", "Setting Gate Latch...", false, true);
                    Robot.shooter.gateLatchReady();
                    Timer.delay(1.0);
                    CorpsLog.log("Autonomous", "Releasing Shooter Tension...", false, true);
                    Robot.shooter.releaseTension();
                    Timer.delay(1.0);
                }
                    
               
                CorpsLog.log("Target ?= Hot", Robot.vision.targetRecognition(), false, true);
                    
        Robot.vision.turnLightsOff();
        cameradoneYet = true;
        }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return cameradoneYet;
    }

    // Called once after isFinished returns true
    protected void end() {
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
