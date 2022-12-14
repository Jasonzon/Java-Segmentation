
import java.io.FileNotFoundException;
import java.lang.*;
import java.util.ArrayList;
import java.io.IOException;

public class Main {


    public static Img creerImgLigne() { 
		int[][] tab = {{255},{200},{100}};
		Img image = new Img(tab);
		return image;
    }

    public static InstanceSegmentation creerInstanceLigne() {
		Img image = creerImgLigne();
		ArrayList<Couple<Integer,Integer>> array1 = new ArrayList<Couple<Integer,Integer>>();
		array1.add(new Couple<>(2,0));
		ArrayList<Couple<Integer,Integer>> array2 = new ArrayList<Couple<Integer,Integer>>();
		array2.add(new Couple<>(0,0));
		InstanceSegmentation instance = new InstanceSegmentation(image, array1, array2);
		return instance;
    }

    public static Graphe creerPetitGraphe(){
	Graphe g = new Graphe(3);
	g.set(0,1,10);
	g.set(1,2,20);
	return g;
    }

    public static ArrayList<Integer> testMinCut() {
		Graphe graphe = new Graphe(5);
		graphe.set(0,1,1);
		graphe.set(0,2,10);
		graphe.set(1,2,6);
		graphe.set(2,1,2);
		graphe.set(1,3,8);
		graphe.set(2,4,2);
		graphe.set(3,4,3);

		Reseau reseau = new Reseau(graphe, 0, 4);
		ArrayList<Integer> coupe = reseau.coupeMin();
		return coupe;
    }

    public static void addPoints(ArrayList<Couple<Integer,Integer>> liste, int i, int j, int size){
		for(int x=0;x<size;x++){
	    	for(int y=0;y<size;y++){
				liste.add(new Couple<>(i+x, j+y));
	    	}
		}
    }

    public static void main(String args[]) throws FileNotFoundException, IllegalArgumentException, IOException {
		System.out.println("début Main");
	
		// test à dé-commenter tout à la fin du TP, non noté, juste pour tester avec une "vraie" image!

		
		Img imageFich = new Img("images/baby_2k.pgm");


		ArrayList<Couple<Integer,Integer>> bbB = new ArrayList<>();
		ArrayList<Couple<Integer,Integer>> bbF = new ArrayList<>();
		int c = 3;

		addPoints(bbB,0,0,c);
		addPoints(bbB,7,5,c);
		addPoints(bbB,3,25,c);
		addPoints(bbB,imageFich.nbColonnes()/2,0,c);
		addPoints(bbB,imageFich.nbColonnes()-5,11,c);

		addPoints(bbF,imageFich.nbColonnes()/2,imageFich.nbColonnes()/2-3*c,c);
		addPoints(bbF,imageFich.nbColonnes()/2,imageFich.nbColonnes()/2,c);
		addPoints(bbF,imageFich.nbColonnes()/2-10,imageFich.nbColonnes()/2+3*c,c);
		addPoints(bbF,imageFich.nbColonnes()/2+5,imageFich.nbColonnes()/2+3*c,c);
		addPoints(bbF,imageFich.nbColonnes()/2+5,imageFich.nbColonnes()/2+7*c,c);

		InstanceSegmentation isegFich = new InstanceSegmentation(imageFich,bbF,bbB);
		Img resFich = isegFich.creerImageSegmentee();
		resFich.creerImage("images/outputbaby_2k.pgm");
    }
}
