package example.josh.util.alg;

import example.josh.util.EzMath;

public class PIDFController
{
	double iErr = 0, lastError = 0;

	double kP, kI, kD, kFF, minOutput, maxOutput, iZone, allowableError;

	double error;

	public PIDFController(double kP, double kI, double kD, double kFF)
	{
		this(kP, kI, kD, kFF, -Double.MAX_VALUE, Double.MAX_VALUE, 0, 0);
	}

	public PIDFController(double kP, double kI, double kD, double kFF, double minOutput, double maxOutput, double iZone, double allowableError)
	{
		this.kP = kP;
		this.kI = kI;
		this.kD = kD;
		this.kFF = kFF;
		this.minOutput = minOutput;
		this.maxOutput = maxOutput;
		this.iZone = iZone;
		this.allowableError = allowableError;
	}

	public double calculate(double setpoint, double processVariable)
	{
		error = setpoint - processVariable;
		
		// Integrate error.
		iErr += error;
		// Apply iZone.
		double iTerm = Math.abs(error) < iZone ? kI * iErr : 0;

		// Calculate dErr.
		double dErr = error - lastError;
		lastError = error;

		// Output.
		double output = kP * error + 
						iTerm + 
						kD * dErr +
						kFF * setpoint;

		output = EzMath.clamp(output, minOutput, maxOutput);

		return output;
	}

	public boolean atSetpoint()
	{
		return Math.abs(error) < allowableError;
	}
}