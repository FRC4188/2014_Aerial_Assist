/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package team4188_2014.commands;
import edu.wpi.first.wpilibj.Timer;
import edu.wpi.first.wpilibj.command.Command;
import team4188_2014.Robot;
import team4188_2014.RobotMap;

/**
 *
 * @author Owner
 */
public class RetrieverIn extends Command{
    private boolean in = false;
    private boolean doneYet = false;
    private Timer timer;
    private double TIME_FINAL = 2.0;

    public RetrieverIn() {
        // Use requires() here to declare subsystem dependencies
        // eg. requires(chassis);
        requires(Robot.retriever);
    }

    // Called just before this Command runs the first time
    protected void initialize() {
        timer = new Timer();
        timer.start();
    }

    // Called repeatedly when this Command is scheduled to run
    protected void execute() {
    
            Robot.retriever.retractRetriever();
                    
            
            if(timer.get() < TIME_FINAL)
                Robot.retriever.retrieve();      
            else {
                timer.stop();
                Robot.retriever.doNothing();
                doneYet = true;
            }
    }

    // Make this return true when this Command no longer needs to run execute()
    protected boolean isFinished() {
        return doneYet;
    }

    // Called once after isFinished returns true
    protected void end() {
        doneYet = false;
        in = false;
    }

    // Called when another command which requires one or more of the same
    // subsystems is scheduled to run
    protected void interrupted() {
    }

}
