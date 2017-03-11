package launcher;

import java.awt.Graphics2D;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Arrow {

	public double x,y, direction, rotateSpeed;
	private int speed;
	private AffineTransform trans;
	private Target target;	
	public double cAngle;
	
	public Arrow(Target target,int x ,int y){
		this.x = x;
		this.y = y;
		direction = 0;
		rotateSpeed = 4;
		speed = 4;
		this.target = target;
		cAngle = 0;
	}
	
	public void tick(){
		updatePosition();
	}
	
	public void render(Graphics2D g){
		g.setTransform(trans);
		g.drawImage(getImage(),(int) x - 16,(int) y - 16, 32, 32 , null);
	}
	
	private void updatePosition(){
		
		//x += Math.cos(cAngle / 180 * Math.PI) * speed;
		//y -= Math.sin(cAngle / 180 * Math.PI) * speed;
		
		x += Math.cos(direction / 180 * Math.PI) * speed;
		y -= Math.sin(direction / 180 * Math.PI) * speed;
		
		//getAngle();
		//trans = new AffineTransform();
		//if (Math.abs(direction - cAngle) > rotateSpeed){getSubAngle();}else{direction = cAngle;}
		//trans.rotate(Math.toRadians(adjustDirection()), x, y);
		
		trans = new AffineTransform();
		cAngle = Rotation.getTargetAngle(target.x, target.y,(int) x,(int) y);
		direction = Rotation.adjustObjectAngle((int) direction,(int) cAngle,(int) rotateSpeed);
		trans.rotate(Math.toRadians(Rotation.imageAngle((int) direction)), x, y);
		
	}
	
	private BufferedImage getImage(){
		try{
			return ImageIO.read(this.getClass().getResource("/textures/arrow.png"));
		}catch(IOException e){
			e.printStackTrace();
			System.exit(1);
		}
		
		return null;
	}
}
