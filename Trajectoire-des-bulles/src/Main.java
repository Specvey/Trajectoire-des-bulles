
public class Main
{
	public static void main(String args[])
	{
		Backtracking bt = new Backtracking("data/norma_N5_tau4_dt2_delai820_000000.txt");
		// bt.afficherLesPoints();
		
		BacktrackingSpecvey bs = new BacktrackingSpecvey("data/norma_N5_tau4_dt2_delai820_000000.txt");
		
		Point A = new Point(0,0,0);
		Point B = new Point(8,0,0);
		Point C = new Point(10,2,0);
		
		System.out.println(bs.calculAngle(A,B,C));
	}

}