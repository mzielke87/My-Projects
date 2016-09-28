package problem3;

public class AutoGDWarnClass {

	int timer;
	boolean autoGDWarn;
	
	public void autoGearDownfunction (boolean gearUp, int airspeed, int altfeet) {
		timer=0; autoGDWarn=false;
		autoGDWarn = (gearUp && (airspeed<100 || altfeet<50));
		if (autoGDWarn)
			if (altfeet<50)
				if (airspeed<100)
					timer=5;
				else
					timer=10;
			else
				timer=15;
	}

	public int getTimer() {
		return timer;
	}

	public boolean isAutoGDWarn() {
		return autoGDWarn;
	}
	// sets were not used so I commented them out
	/*public void setTimer(int timer) {
		this.timer = timer;
	}

	public void setAutoGDWarn(boolean autoGDWarn) {
		this.autoGDWarn = autoGDWarn;
	}*/
}
