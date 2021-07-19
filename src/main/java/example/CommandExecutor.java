package example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import example.josh.subsystems.OdomDrivetrain;
import lejos.utility.Delay;

public class CommandExecutor
{
    Object[][] auton;
    int step = 0;
    OdomDrivetrain dt;

    Logger LOGGER = LoggerFactory.getLogger(CommandExecutor.class);
    
    Action drive;
    Action turn;
    Action wait = (period) -> { Delay.msDelay((long) period); return true; };

    public CommandExecutor(OdomDrivetrain dt)
    {
        this.dt = dt;

        dt.resetPosition();
    
        drive = dt::driveToDistance;
        turn = dt::turnToAngle;
    }

    @FunctionalInterface
    interface Action
    {
        boolean execute(double param);
    }

    public void init(Runnable pathSelection)
    {
        pathSelection.run();
        dt.resetPosition();
        step = 0;
    }

    public void periodic()
    {
        Action currentAction = (Action) auton[step][0];
        double param = (double) auton[step][1];
        
        boolean currentActionCompleted = currentAction.execute(param);
        
        if (currentActionCompleted && step < auton.length-1)
        {
            step++;
            dt.resetPosition();
        }
        
        String nameCurAction = (currentAction == drive) ? "drive" : "turn";
        
        LOGGER.info("Current Action: " + nameCurAction);
        LOGGER.info("Complete? " + currentActionCompleted);
    }

    public void selectSquarePath()
	{
        auton = new Object[][]
        {
            //SQUARE
            {drive, 0.25},
            {turn, 90.0},
            {drive, 0.25},
            {turn, 90.0}, 
            {drive, 0.25},
            {turn, 90.0},
            {drive, 0.25},
            {turn, 90.0}
        };
    }
}
