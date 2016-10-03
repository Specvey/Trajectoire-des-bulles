import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class BacktrackingSpecvey
{
	private ArrayList<Point> points = new ArrayList<Point>();
	private double pourcentageDistance = 0.1;
	private double angleRadian =  0.349066;
	
	public BacktrackingSpecvey(String nomFichier)
	{
		lectureFichier(nomFichier);
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
	    		
	    		x = convertStringEnDouble(split[1]);
	    		y = convertStringEnDouble(split[2]);
	    		z = convertStringEnDouble(split[3]);
	    		
	    		points.add(new Point(x, y, z));
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
		double puissance = Double.parseDouble(split[1].substring(0, split[1].length()));
		return a*Math.pow(10,puissance);
	}
	
	public void afficherLesPoints()
	{
		for(Point p:points)
		{
			System.out.println(p.toString());
		}
	}

	public double calculAngle(Point p1, Point p2, Point p3)
	{
		double b = calculDistance(p1, p2);
		double a = calculDistance(p2, p3);
		double c = calculDistance(p1, p3);
		
		double res = Math.acos((Math.pow(c, 2) - Math.pow(a, 2) - Math.pow(b, 2))/(-2*a*b));
		
		return res;
	}
	
	public double calculDistance(Point a, Point b)
	{
		double res = Math.sqrt(Math.pow((b.getX() - a.getX()), 2) + Math.pow((b.getY() - a.getY()), 2) + Math.pow((b.getZ() - a.getZ()), 2));
		return res;
	}
	
	
	public ArrayList<Point[]> deroulement()
    {
        int i1 = 0, i2 = 0, i3 = 0, i4 = 0;
        Point [] trajectoire;
        ArrayList<Point> pointsRestants = new ArrayList<Point>();
        ArrayList<Point[]> trajectoires = new ArrayList<Point[]>();
        double distanceP12;
        
        // On recopie points dans pointsRestants
        for(Point p:points)
        {
        	pointsRestants.add(p);
        }
        
        for(Point p1:pointsRestants)
        {
        	pointsRestants.remove(p1);
        	
            for(Point p2:pointsRestants)
            {
            	distanceP12 = calculDistance(p1, p2);
                pointsRestants.remove(p2);
                
                for(Point p3:pointsRestants)
                {
                	// On regarde si p3 est possible pour la distance et pour l'angle
                    // Si p3 est possible, on continue
                    // if( calculDistance(p2, p3)>distanceP12*(1-pourcentageDistance) && calculDistance(p2, p3)<distanceP12*(1+pourcentageDistance) && (Math.PI - calculAngle(p1,p2,p3)) < angleRadian )
                    if(calculDistance(p2, p3) > distanceP12*(1-pourcentageDistance) && calculDistance(p2, p3) < distanceP12*(1+pourcentageDistance) && (Math.PI - calculAngle(p1,p2,p3)) < angleRadian || (Math.PI - calculAngle(p3,p2,p1)) < angleRadian)
                    {
                    	pointsRestants.remove(p3);
                    	for(Point p4:pointsRestants)
                    	{
                    		// On regarde si p4 est possible pour la distance et pour l'angle
                            // Si p4 est possible, on continue
                            // if( calculDistance(p3, p4)>2*distanceP12*(1-pourcentageDistance) && calculDistance(p3, p4)<2*distanceP12*(1+pourcentageDistance) && (Math.PI - calculAngle(p2,p3,p4)) < 2*angleRadian )
                            if(calculDistance(p3, p4) > 2*distanceP12*(1-pourcentageDistance) && calculDistance(p3, p4) < 2*distanceP12*(1+pourcentageDistance) && (Math.PI - calculAngle(p2,p3,p4)) < 2*angleRadian || (Math.PI - calculAngle(p4,p3,p2)) < 2*angleRadian)
                            {
                            	pointsRestants.remove(p4);
                        
                            	for(Point p5:pointsRestants)
                            	{
                            		// On regarde si p5 est possible pour la distance et pour l'angle
                                    // Si p5 est possible, on a trouvé une nouvelle trajectoire que l'on ajoute
                                    // if( calculDistance(p4, p5)>distanceP12*(1-pourcentageDistance) && calculDistance(p4, p5)<distanceP12*(1+pourcentageDistance) && (Math.PI - calculAngle(p3,p4,p5)) < angleRadian )
                                    if(calculDistance(p4, p5) > distanceP12*(1-pourcentageDistance) && calculDistance(p4, p5) < distanceP12*(1+pourcentageDistance) && (Math.PI - calculAngle(p3,p4,p5)) < angleRadian || (Math.PI - calculAngle(p3,p4,p5)) < angleRadian)
                                    {
                                    	trajectoire = new Point[5];
                                    	trajectoire[0]=p1;
                                    	trajectoire[1]=p2;
                                    	trajectoire[2]=p3;
                                    	trajectoire[3]=p4;
                                    	trajectoire[4]=p5;
                                    	trajectoires.add(trajectoire);
                                    } //if p5
                            	} // for p5
                            	pointsRestants.add(i4, p4);
                            	i4++;
                            } // if p4
                    	} // for p4
                    	i4=0;
                    	pointsRestants.add(i3, p3);
                    	i3++;
                    } // if p3
                } // for p3
                i3=0;
                pointsRestants.add(i2, p2);
                i2++;
            } // for p2
            i2=0;
            pointsRestants.add(i1, p1);
            i1++;
        } // for p1
        return trajectoires;
    } // déroulement
	
	//Si les deux trajectoires sont différentes on renvoie vrai.
	public boolean compareTrajectoires(Point[] t1, Point[] t2)
	{
		for(Point p1:t1)
		{
			for(Point p2:t2)
		    {
				if(p1.compareTo(p2))
		        {
					return false;
		        }
		    }
		}
		return true;
	}
	
	
	public ArrayList<Point[]> recopieArrayTabPoint(ArrayList<Point[]> trajectoires)
	{
		ArrayList<Point[]> trajectoiresTmp = new ArrayList<Point[]>();
		Point[] trajectoire;
		// On recopie trajectoires
		for(Point[] tab:trajectoires)
		{
			trajectoire = new Point[5];
		    for(int i=0; i<5;i++)
		    {
		    	trajectoire[0]=tab[0];
		        trajectoire[1]=tab[1];
		        trajectoire[2]=tab[2];
		        trajectoire[3]=tab[3];
		        trajectoire[4]=tab[4];
		    }
		    trajectoiresTmp.add(trajectoire);
		}
		return trajectoiresTmp;
	}
	
	public ArrayList<Point[]> nettoyageDePrintemps(ArrayList<Point[]> trajectoires)
	{
		ArrayList<Point[]> trajectoiresTmp = recopieArrayTabPoint(trajectoires);
		ArrayList<Point[]> meilleurSerie = new ArrayList<Point[]>();
		int i=0;

		// TODO

		for(Point[] trajectoireA: trajectoiresTmp)
		{
			meilleurSerie.add(trajectoireA);
			trajectoiresTmp.remove(trajectoireA);
		    for(Point[] trajectoireB: trajectoiresTmp)
		    {
		    	
		    }
		    //trajectoiresTmp.add(trajectoire);
		    i++;
		}
		return trajectoires;
	}
	
	
}
