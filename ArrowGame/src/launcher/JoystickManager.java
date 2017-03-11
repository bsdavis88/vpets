package launcher;

import net.java.games.input.Controller;

public class JoystickManager {

	private JInputJoystick joystick;
	private float hat;

	public JoystickManager(){
		joystick = new JInputJoystick(Controller.Type.GAMEPAD);
		hat = 0;
		
		System.out.println(joystick.isControllerConnected());
		System.out.println(joystick.getControllerName());
		System.out.println(joystick.pollController());
		System.out.println(joystick.getNumberOfButtons());
		
	}
	
	public boolean checkHat(float position){
		joystick.pollController();
		hat = joystick.getHatSwitchPosition() * 8;
		if (hat == position){
			return true;
		}else{
			return false;
		}
	}
}
