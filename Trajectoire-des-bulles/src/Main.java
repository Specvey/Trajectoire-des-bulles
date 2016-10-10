import java.util.ArrayList;

public class Main
{
	public static void main(String args[])
	{
		Backtracking bt = new Backtracking("data/norma_N5_tau4_dt2_delai820_000000.txt");
		// bt.afficherLesPoints();
		
		BacktrackingSpecvey bs = new BacktrackingSpecvey("data/norma_N5_tau4_dt2_delai820_000000.txt");
		
		/*ArrayList<Point[]> trajectoires = bs.deroulement();
		
		ArrayList<Point[]> resultat = bs.nettoyageDePrintemps(trajectoires);*/
		
		ArrayList<Point[]> trajectoiresBt = bt.deroulement();
		ArrayList<Point[]> trajectoiresBs = bs.deroulement();
		
		//ArrayList<Point[]> resultat = bt.ne
		
		System.out.println(trajectoiresBt.size());
		System.out.println(trajectoiresBs.size());
		
	}

}