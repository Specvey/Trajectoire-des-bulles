import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

public class Backtracking
{
	ArrayList<Point> lesPoints = new ArrayList<Point>();
	ArrayList<Double[]> trajectoires = new ArrayList<Double[]>();
	
	public Backtracking()
	{
		
	}
	
	/**
	 * A partir d'un fichier, on crée l'arraylist de points.
	 * Chaque point a des coordonnées x y z.
	 */
	public void lectureFichier(String nomFichier)
	{
	    FileInputStream fis = null;
	    InputStreamReader isr = null;
	    BufferedReader br = null;
	    String ligneFichier;
	    double x, y, z;
	    try
	    {
	    	fis = new FileInputStream(new File(nomFichier));
	    	isr = new InputStreamReader(fis);
	    	br = new BufferedReader(isr);
	    	// Tant que l'on est pas à la fin du fichier, on continue à lire
	    	while((ligneFichier = br.readLine()) != null)
	    	{
	    		String[] split = ligneFichier.split("   ");
	    		
	    		x = convertStringEnDouble(split[0]);
	    		y = convertStringEnDouble(split[1]);
	    		z = convertStringEnDouble(split[2]);
	    		
	    		lesPoints.add(new Point(x, y, z));
	    	}
	    	br.close();
	    }
	    catch(FileNotFoundException e) // Si aucun fichier n'a été trouvé
	    {
	    	e.printStackTrace();
	    }
	    catch(IOException e) // Si erreur d'écriture ou de lecture
	    {
	    	e.printStackTrace();
	    }
	    finally
	    {
	    	try
	        {
	    		if(fis != null)
	            {
	    			fis.close();
	            }
	         }
	         catch(IOException e)
	         {
	            e.printStackTrace();
	         }
	    }
	}
	
	public double convertStringEnDouble(String texte)
	{
		String[] split = texte.split("e");
		double a = Double.parseDouble(split[0]);
		double b = Double.parseDouble(split[1].substring(1, split[1].length()-1));
		if(split[1].startsWith("+"))
		{
			return Math.pow(a,-b);
		}
		return Math.pow(a,-b);
	}
	
	public double calculAngle(Point p1, Point p2, Point p3)
	{
		double res = 0;
		
		
		
		return res;
	}
	
	public double calculDistance(Point a, Point b)
	{
		double res = Math.sqrt(Math.pow((b.getX() - a.getX()), 2) + Math.pow((b.getY() - a.getY()), 2) + Math.pow((b.getZ() - a.getZ()), 2));

		return res;
	}
	
}
