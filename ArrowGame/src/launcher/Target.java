package launcher;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Target {

	public int x,y;
	private int speed;
	private KeyManager keyManager;
	private JoystickManager joystickManager;
	
	public Target(KeyManager keyManager, JoystickManager joystickManager){
		x = 320;
		y = 240;
		speed = 10;
		this.keyManager = keyManager;
		this.joystickManager = joystickManager;
	}
	
	public void tick(){
		updatePosition();
	}
	
	public void render(Graphics2D g){
		g.drawImage(getImage(), x - 16, y - 16, 32, 32 , null);
	}
	
	private void updatePosition(){
		
		if (keyManager.up || joystickManager.checkHat(2)){
			if (y - speed >= 0){
				y -= speed;
			}else{
				y = 0;
			}
		}
		
		if (keyManager.down || joystickManager.checkHat(6)){
			if (y + speed <= 480){
				y += speed;
			}else{
				y = 480;
			}
		}
		
		if (keyManager.left || joystickManager.checkHat(8)){
			if (x - speed >= 0){
				x -= speed;
			}else{
				x = 0;
			}
		}
		
		if (keyManager.right || joystickManager.checkHat(4)){
			if (x + speed <= 640){
				x += speed;
			}else{
				x = 640;
			}
		}
	}
	
	private BufferedImage getImage(){
		try{
			return ImageIO.read(this.getClass().getResource("/textures/target.png"));
		}catch(IOException e){
			e.printStackTrace();
			System.exit(1);
		}
		
		return null;
	}
}
