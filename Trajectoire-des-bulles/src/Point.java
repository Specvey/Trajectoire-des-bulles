import java.util.ArrayList;

public class Point 
{
	private double x;
	private double y;
	private double z;
	
	private ArrayList<Point> voisins;
	private ArrayList<Point> meilleursVoisins;
	
	public Point(double x, double y, double z)
	{
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public Point(Point p)
	{
		this.x = p.getX();
		this.y = p.getY();
		this.z = p.getZ();
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
	
	public boolean compareTo(Point p)
	{
		if(x==p.getX() && y==p.getY() && z==p.getZ())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public void setVoisins(ArrayList<Point> v)
	{
		voisins = new ArrayList<Point>(v);
	}
	
	public void setMeilleursVoisins(ArrayList<Point> v)
	{
		meilleursVoisins = new ArrayList<Point>(v);
	}
	
	public ArrayList<Point> getVoisins()
	{
		return voisins;
	}
	
	public ArrayList<Point> getMeilleursVoisins()
	{
		return meilleursVoisins;
	}
	
	public String toString()
	{
		return x+" "+y+" "+z;
	}
}
