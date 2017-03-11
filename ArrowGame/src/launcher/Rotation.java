package launcher;

public class Rotation {

	public static int getTargetAngle(int targetX, int targetY, int x, int y){
		double targetAngle = 180;
		double deltaX, deltaY;
		deltaY = targetY - y;
		deltaX = targetX - x;
		targetAngle = Math.atan2(deltaY, deltaX);
		targetAngle *= 180;
		targetAngle /= Math.PI;
		
		if (targetAngle < 0){targetAngle *= -1;}else{targetAngle = 360 - targetAngle;}
		if (targetAngle == 360){targetAngle = 0;}
		
		return (int) targetAngle;
	}
	
	public static int adjustObjectAngle(int objectAngle, int targetAngle, int rotationSpeed){	
		if(Math.abs(objectAngle - targetAngle) > rotationSpeed){
			if (objectAngle < targetAngle){
				if (Math.abs(objectAngle - targetAngle) < 180){
					if (objectAngle + rotationSpeed >= 360){
						objectAngle += rotationSpeed - 360;
					}else{
						objectAngle += rotationSpeed;
					}
				}else{
					if (objectAngle - rotationSpeed < 0){
						objectAngle -= rotationSpeed - 360;
					}else{
						objectAngle -= rotationSpeed;
					}
				}
			}else{
				if (Math.abs(objectAngle - targetAngle) < 180){
					if (objectAngle - rotationSpeed < 0){
						objectAngle -= rotationSpeed - 360;
					}else{
						objectAngle -= rotationSpeed;
					}
				}else{
					if (objectAngle + rotationSpeed >= 360){
						objectAngle += rotationSpeed - 360;
					}else{
						objectAngle += rotationSpeed;
					}
				}		
			}
		}else{
			objectAngle = targetAngle;
		}
		
		return objectAngle;		
	}
	
	public static int imageAngle(int objectAngle){
		int dif = 360 - objectAngle;
		
		if (objectAngle >= 0 && objectAngle <= 180){objectAngle *= -1;}
		else if (objectAngle > 180 && objectAngle < 360){objectAngle = dif;}
		
		return objectAngle;
	}
}
