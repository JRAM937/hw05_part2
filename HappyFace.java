import javafx.scene.canvas.GraphicsContext;
import javafx.scene.shape.ArcType;

public class HappyFace extends Circle{
	
	private Circle leftEye;
	private Circle rightEye;
	private Circle nose;  
	
	@Override
	public void draw(GraphicsContext gc) {
		gc.strokeOval(ul.x, ul.y, lr.x-ul.x, lr.y-ul.y);
		   int center_x = (ul.x + lr.x)/2; 
		   int center_y = (ul.y + lr.y)/2; 
		   int width = (lr.x - ul.x)/2;
		gc.strokeArc(center_x-width/2, center_y-width/2, width, width, 225, 90, ArcType.OPEN);
		
		//create two variables for the widths of nose and eyes
		int eyeWidth = width/4;
		int noseWidth = width/8;
		
		gc.strokeOval(center_x+(eyeWidth/2), center_y-width/4, eyeWidth, eyeWidth);
		gc.strokeOval(center_x-(eyeWidth*1.5), center_y-width/4, eyeWidth, eyeWidth);
		gc.strokeOval(center_x-(noseWidth/2), center_y, noseWidth, noseWidth);
	}

	public HappyFace(Point upperLeft, Point lowerRight) {
		super(upperLeft, lowerRight);
	}
}
