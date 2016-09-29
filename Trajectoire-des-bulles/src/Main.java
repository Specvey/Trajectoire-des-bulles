
public class Main
{
	public static void main(String args[])
	{
		 Backtracking bt = new Backtracking();
		
		 Point a = new Point(1, 1, 1);
		 Point b = new Point(1, 0, 1);
		 Point c = new Point(0, 0, 1);
		 
		 System.out.println(bt.calculAngle(a, b ,c));
	}

}