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
        
        if(!Robot.vision.targetRecognition() && i <= 10){
            i++;
            CorpsLog.log("Autonomous", "NumImages" + i, false, true);
            cameradoneYet = false;
        }
        
        else{
            cameradoneYet = true;
            //Step 1: Deploy the Retriever to get it out of the way if it is not already deployed.
            if(Robot.retriever.isRetracted()) Robot.retriever.deployRetriever();
            else Robot.retriever.doNothing();

            //Step 2A: Deploy the Shooter and reset if the limit switch is clicked and the tension has been released.
            if((!Robot.shooter.getGateLatch() && Robot.shooter.isExtended()) || deployed){
                //Step 3A: Deploy shooter if not already deployed.
                if(!deployed) {
                     CorpsLog.log("Autonomous", "Shooting...", false, true);
                     Robot.shooter.deployShooter();
                     deployed = true;
                }

                //Step 3B: If shooter is already deployed, begin reset.
                else{
                    CorpsLog.log("Autonomous", "Resetting Shooter...", false, true);

                    //Step 4: If 1 second has passed and shooter has not been retracted, retract shooter.
                    if(timer.get() >= TIME_1 && timer.get() < TIME_2 && !doneYet1){
                        CorpsLog.log("Autonomous", "Retracting Shooter...", false, true);
                        Robot.shooter.retractShooter();
                        doneYet1 = true;
                    }          

                    //Step 5: If 6 seconds have passed and the gate latch has not been locked, lock gate latch.
                    else if(timer.get() >= TIME_2 && timer.get() < TIME_3 && !doneYet2){ 
                        //Only set the gate latch if the limit switch is pressed.
                        if(!Robot.shooter.getGateLatch()){
                            CorpsLog.log("Autonomous", "Setting Gate Latch...", false, true);
                            Robot.shooter.gateLatchReady();
                            doneYet2 = true;
                        }
                        else {
                            CorpsLog.log("Autonomous", "Limit switch not clicked... Cannot lock gate latch...", false, true);
                            Robot.shooter.doNothing();
                            //doneYet2 is still false.
                        }
                    }

                    //Step 6: If 7 seconds has passed and tension has not been released, release shooter tension.
                    else if(timer.get() >= TIME_3 && timer.get() <TIME_4 && !doneYet3){
                        if(doneYet2){
                            CorpsLog.log("Autonomous", "Releasing Shooter Tension... Sequence finished... Exiting command 'ShootBall()'", false, true);
                            Robot.shooter.releaseTension();
                            doneYet3 = true;
                        }
                        else{
                            Robot.shooter.doNothing();
                            CorpsLog.log("Autonomous", "Gate Latch unlocked... Cannot release tension... Exiting command 'ShootBall()'", false, true);
                            doneYet1 = true;
                            doneYet2 = true;
                            doneYet3 = true;
                        }
                    }

                    //In the event that none of these above 3 cases is true, wait.
                    else Robot.shooter.doNothing();
                }
            }
            
            else{
                CorpsLog.log("Autonomous", "Shooter Not Ready...'", false, true);
                doneYet1 = true;
                doneYet2 = true;
                doneYet3 = true;
            }
                
        CorpsLog.log("Target ?= Hot", Robot.vision.targetRecognition(), false, true);         
        Robot.vision.turnLightsOff();
        }
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
