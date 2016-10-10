import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class Backtracking
{
	private ArrayList<Point> points = new ArrayList<Point>();
	private double pourcentageDistance = 0.1;
	private double angleRadian =  0.349066;
	private double nbreDeVoisinsAGarder = 0.1;
	
	public Backtracking(String nomFichier)
	{
		lectureFichier(nomFichier);
		attribuerVoisins();
	}
	
	/**
	 * A partir d'un fichier, on cr√©e l'arraylist de points.
	 * Chaque point a des coordonn√©es x y z.
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
	    	// Tant que l'on est pas √† la fin du fichier, on continue √† lire
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
	    catch(FileNotFoundException e) // Si aucun fichier n'a √©t√© trouv√©
	    {
	    	e.printStackTrace();
	    }
	    catch(IOException e) // Si erreur d'√©criture ou de lecture
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
	
	public void attribuerVoisins()
	{
		for(Point p: points)
		{
			voisins(p);
			meilleursVoisins(p);
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
	
	public void voisins(Point pO) 
	{
		  ArrayList<Point> voisinage = new ArrayList<Point>();
		  
		  for(Point pV : points)
		  {
			  if(!pO.compareTo(pV))
			  {
				  voisinage.add(pV);
			  }
		  }
		  
		  tri(voisinage, pO);
		  
		  pO.setVoisins(voisinage);
	}
	
	public void meilleursVoisins(Point pO) 
	{
		  
		ArrayList<Point> voisinage = new ArrayList<Point>();
		ArrayList<Point> meilleursVoisins = new ArrayList<Point>();
		  
		for(Point pV : points)
		{
			if(!pO.compareTo(pV))
			{
				voisinage.add(pV);
			}
		}
		  
		tri(voisinage, pO);
		
		
		for(int i = 0; i < (int)(nbreDeVoisinsAGarder*points.size()); i++)
		{
			meilleursVoisins.add(voisinage.get(i));
		}
		  
		pO.setMeilleursVoisins(meilleursVoisins);
		  
	}
	
	
	public ArrayList<Point> tri(ArrayList<Point> al, Point pO)
	{
	   Collections.sort(al, new Comparator<Point>(){
	    public int compare(Point p1, Point p2)
	    {
	      //int comp = (int) (calculDistance(pO,p1) - calculDistance(pO,p2));
	     
	     double comp = Double.compare(calculDistance(pO, p1), calculDistance(pO, p2));
	    	
	     if(comp < 0)
	     {
	    	 return -1;
	     }
	     else if(comp > 0)
	     {
	    	 return 1;
	     }
	     else
	     {
	    	 return 0;
	     }
	     
	     }
	   });
	   return al;
	 }
	
	 public ArrayList<Point[]> deroulement()

     {
		 long time = System.currentTimeMillis();
        Point [] trajectoire;
        ArrayList<Point[]> trajectoires = new ArrayList<Point[]>();
        double distanceP12;
        
        int k =0;
        
        // DÈclaration des points

        for(Point p1:points)
        {
            trajectoire = new Point[5];
            trajectoire[0]= p1;
            for(Point p2:p1.getMeilleursVoisins())
            {
                trajectoire[1]= p2;
                    distanceP12 = calculDistance(p1, p2);
                for(Point p3:p2.getVoisins())
                {
                    // On vÈrifie que p3 n'est pas dans trajectoire
                    if(!Arrays.asList(trajectoire).contains(p3))
                    {
                        trajectoire[2]= p3;
                        // On regarde si p3 est possible pour la distance et pour l'angle
                    // Si p3 est possible, on continue
                     //if( calculDistance(p2, p3)>distanceP12*(1-pourcentageDistance) && calculDistance(p2, p3)<distanceP12*(1+pourcentageDistance) && (Math.PI - calculAngle(p1,p2,p3)) < angleRadian )
                    if(calculDistance(p2, p3) > distanceP12*(1-pourcentageDistance) && calculDistance(p2, p3) < distanceP12*(1+pourcentageDistance))
                    {
                        if((Math.PI - calculAngle(p1,p2,p3)) < angleRadian || (Math.PI - calculAngle(p3,p2,p1)) < angleRadian)
                        {
                            for(Point p4:p3.getVoisins())
                            {
                            	System.out.println("p4");
                                // On vÈrifie que p4 n'est pas dans trajectoire

                        if(!Arrays.asList(trajectoire).contains(p4))

    {

    trajectoire[3]= p4;

                                    // On regarde si p4 est possible pour la distance et pour l'angle
                            // Si p4 est possible, on continue
                             //if( calculDistance(p3, p4)>2*distanceP12*(1-pourcentageDistance) && calculDistance(p3, p4)<2*distanceP12*(1+pourcentageDistance) && (Math.PI - calculAngle(p2,p3,p4)) < 2*angleRadian )
                            if(calculDistance(p3, p4) > 2*distanceP12*(1-pourcentageDistance) && calculDistance(p3, p4) < 2*distanceP12*(1+pourcentageDistance))
                            {
                                if((Math.PI - calculAngle(p2,p3,p4)) < 2*angleRadian || (Math.PI - calculAngle(p4,p3,p2)) < 2*angleRadian)
                                {
                                    for(Point p5:p4.getVoisins())
                                    {
                                    	System.out.println("p5");
                                         // On vÈrifie que p4 n'est pas dans trajectoire

                        if(!Arrays.asList(trajectoire).contains(p5))

    {
    trajectoire[4]= p5;

                                            // On regarde si p5 est possible pour la distance et pour l'angle
                                    // Si p5 est possible, on a trouvÈ une nouvelle trajectoire que l'on ajoute
                                     //if( calculDistance(p4, p5)>distanceP12*(1-pourcentageDistance) && calculDistance(p4, p5)<distanceP12*(1+pourcentageDistance) && (Math.PI - calculAngle(p3,p4,p5)) < angleRadian )
                                    if(calculDistance(p4, p5) > distanceP12*(1-pourcentageDistance) && calculDistance(p4, p5) < distanceP12*(1+pourcentageDistance))
                                    {
                                        if((Math.PI - calculAngle(p3,p4,p5)) < angleRadian || (Math.PI - calculAngle(p3,p4,p5)) < angleRadian)
                                        {
                                            k++;
                                            //System.out.println(k);
                                            trajectoires.add(trajectoire);
                                        }
                                    } //if p5
                                    else
                                    {
                                    	break;
                                    }
                                    } //if p5
                        
                        			//
                                    } // for p5
                            } // if p4
                            
                            } // if p4
                            else
                            {
                            	break;
                            }
                           // 
                            }
                            } // for p4
                    }// if p3
                    
                    }// if p3
                    else
                    {
                    	break;
                    }
                    //break;
                    }
                } // for p3
                    //}//if distance max
            } // for p2
        } // for p1
        
        System.out.println("Temps bt : "+(System.currentTimeMillis()-time));
        
        //System.out.print("fini");
        return trajectoires;
    } // dÈroulement
	 
	 
}
