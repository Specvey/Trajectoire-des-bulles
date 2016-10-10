import java.util.ArrayList;

public class Main
{
	public static void main(String args[])
	{
		//Backtracking bt = new Backtracking("data/norma_N5_tau4_dt2_delai820_000000.txt");
		// bt.afficherLesPoints();
		
		BacktrakingRecurcif bs = new BacktrakingRecurcif("data/norma_N5_tau4_dt2_delai820_000000.txt");
		
		ArrayList<Point[]> trajectoires = bs.deroulement();
		
		ArrayList<Point[]> resultat = bs.nettoyageDePrintemps(trajectoires);
		
		System.out.println(resultat.size());
		
	}

}