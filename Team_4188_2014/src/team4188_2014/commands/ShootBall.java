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
import edu.wpi.first.wpilibj.command.Command;
import team4188_2014.Robot;
/**
 *
 */
public class  ShootBall extends Command {
    public ShootBall() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
	
        // BEGIN AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
        requires(Robot.shooter);
        requires(Robot.retriever);
    // END AUTOGENERATED CODE, SOURCE=ROBOTBUILDER ID=REQUIRES
    }
    boolean retracted = false;
    // Called just before this Command runs the first time
    protected void initialize() {}
    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
        if(!retracted) {
            Robot.retriever.retractRetriever();
            Robot.shooter.retractShooter();
            retracted = true;
        }
        else if(retracted) {
            Robot.retriever.deployRetriever();
            Robot.shooter.deployShooter();
            retracted = false;
        }
    }
    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return false;
    }
    // Called once after isFinished returns true
    protected void end() {}
    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {}
}
