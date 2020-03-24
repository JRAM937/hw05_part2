
public class HappyFaceFactory implements ShapeFactory{

	@Override
	public Shape makeShape(Point p) {
		return new HappyFace(p, new Point(p));
	}
	
}
