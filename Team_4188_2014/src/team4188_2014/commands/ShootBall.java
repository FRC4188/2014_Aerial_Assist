// RobotBuilder Version: 0.0.2
//
// This file was generated by RobotBuilder. It contains sections of
// code that are automatically generated and assigned by robotbuilder.
// These sections will be updated in the future when you export to
// Java from RobotBuilder. Do not put any code or make any change in
// the blocks indicating autogenerated code or it will be lost on an
// update. Deleting the comments indicating the section will prevent
// it from being updated in th future.
package team4188_2014.commands;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.CommandGroup;
import team4188_2014.Robot;
import team4188_2014.RobotMap;
/**
 *
 */
public class  ShootBall extends CommandGroup {
    public ShootBall() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
	
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
//        requires(Robot.shooter);
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }
    private double time;
    private boolean isDone = false;
    private Timer timer;

    // Called just before this Command runs the first time
    protected void initialize() {}
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
//        if(Robot.retriever.isRetracted()) Robot.retriever.deployRetriever();
        if(Robot.shooter.getGateLatch()) {
            Robot.shooter.retractShooter();
            Timer.delay(3.0);
        }
        
        //if statement makes difficult for code to work unless perfect timing. while ensures no action until limit switch hit
        //while may block other code...? test to see if escape is possible by hitting copilot buttons for shooter
        if(!Robot.shooter.getGateLatch()) {
            Robot.shooter.gateLatchReady();
            Timer.delay(1.0);
            Robot.shooter.releaseTension();
            Timer.delay(1.0);
            Robot.shooter.deployShooter();
        }
        
//        while(Robot.shooter.getGateLatch()) Timer.delay(1.0);
//            Robot.shooter.gateLatchReady();
//            Timer.delay(1.0);
//            Robot.shooter.releaseTension();
//            Timer.delay(1.0);
//            Robot.shooter.deployShooter();
    }
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return true;
    }
    // Called once after isFinished returns true
    protected void end() {}
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {}

    private boolean Wait(double time) {
        timer.start();
        while(timer.get() < time) isDone = false;
        isDone = true;
        return isDone;
    }
}
