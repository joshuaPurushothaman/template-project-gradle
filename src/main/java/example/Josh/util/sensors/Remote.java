package example.josh.util.sensors;

import ev3dev.sensors.ev3.EV3IRSensor;
import example.josh.util.IPeriodicSubsystem;
import lejos.robotics.SampleProvider;

public class Remote implements IPeriodicSubsystem
{
    EV3IRSensor ir;
    SampleProvider sp;

    public Remote(EV3IRSensor ir)
    {
        this.ir = ir;
		sp = ir.getRemoteMode();
    }
    
    int beaconInfo1 = 0;
    int beaconInfo2 = 0;
    int beaconInfo3 = 0;
    int beaconInfo4 = 0;

    @Override
    public void updatePeriodic()
    {
        float [] sample = new float[sp.sampleSize()];
        sp.fetchSample(sample, 0);

        beaconInfo1 = (int)sample[0];
        beaconInfo2 = (int)sample[1];
        beaconInfo3 = (int)sample[2];
        beaconInfo4 = (int)sample[3];	
    }
        // if(beaconInfo1 == 0){//nothign is presswed
        // }else if(beaconInfo1 == 1){//lf
        // }else if(beaconInfo1 == 2){//lb
        // }else if(beaconInfo1 == 3){//rf
        // }else if(beaconInfo1 == 4){//rb
        // }else if(beaconInfo1 == 5){//lf rf
        // }else if(beaconInfo1 == 6){// lf rb
        // }else if(beaconInfo1 == 7){//lb rf
        // }else if(beaconInfo1 == 8){// lb rb
        // }
    
    public int getLeft()
    {
        if (beaconInfo1 == 1 || beaconInfo1 == 5 || beaconInfo1 == 6)   //  forward technically takes priority with this
            return 1;
        else if (beaconInfo1 == 2 || beaconInfo1 == 7 || beaconInfo1 == 8)
            return -1;
        else
            return 0;
    }

    public int getRight()
    {
        if (beaconInfo1 == 3 || beaconInfo1 == 5 || beaconInfo1 == 7)
            return 1;
        else if (beaconInfo1 == 4 || beaconInfo1 == 6 || beaconInfo1 == 8)
            return -1;
        else
            return 0;
    }
}
