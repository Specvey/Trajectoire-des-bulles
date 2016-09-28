public class Point 
{
	private double x;
	private double y;
	private double z;
	private double distance;
	
	public Point(double x, double y, double z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public double getX()
	{
		return this.x;
	}
	
	public double getY()
	{
		return this.y;
	}
	
	public double getZ()
	{
		return this.z;
	}
	
	public void setDistance(double d)
	{
		distance = d;
	}
	
	public double getDistance()
	{
		return this.distance;
		
	}
	
	public boolean compareTo(Point p)
	{
		if(x==p.getX() && y==p.getY() && z==p.getZ()) return true;
		else return false;
	}
	
	public String toString()
	{
		return x+" "+y+" "+z;
	}
}
