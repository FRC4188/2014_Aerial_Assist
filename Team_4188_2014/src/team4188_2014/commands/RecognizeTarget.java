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
    private boolean deployed = false;
    private boolean doneYet1 = false;
    private boolean doneYet2 = false;
    private boolean doneYet3 = false;
    private Timer timer;
    private final double TIME_1 = 1.0;
    private final double TIME_2 = 6.0;
    private final double TIME_3 = 7.1;
    private final double TIME_4 = 8.2;
    
    boolean cameradoneYet = false;
    int i = 1;
    
    public RecognizeTarget() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.vision);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        timer = new Timer();
        timer.start(); 
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        Robot.vision.turnLightsOn();
        
        if(!Robot.vision.targetRecognition() && i <= 7){
            i++;
            CorpsLog.log("Autonomous", "NumImages" + i, false, true);
            cameradoneYet = false;
        }
        
        else{
            cameradoneYet = true;
            Robot.shooter.deployShooter();
            doneYet1 = true;
            doneYet2 = true;
            doneYet3 = true;
            }
                
        CorpsLog.log("Target ?= Hot", Robot.vision.targetRecognition(), false, true);         
        Robot.vision.turnLightsOff();
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return cameradoneYet && doneYet1 && doneYet2 && doneYet3;
    }

    // Called once after isFinished returns true
    protected void end() {
        i = 1;
        cameradoneYet = false;
        doneYet1 = false;
        doneYet2 = false;
        doneYet3 = false;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }
}
