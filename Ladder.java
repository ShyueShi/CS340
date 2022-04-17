import java.util.ArrayList;

/**
 * This class is a class that stores the path from one word to another
 * @author Shyue Shi Leong
 *
 */
public class Ladder {
	private ArrayList<String> path;
	private String lastWord;
	private int length;
	
	//overloaded constructor
	public Ladder(ArrayList<String> p, String word, int l){
		path=p;
		lastWord=word;
		length=l;
	}
	
	/**
	 * a getter method for path
	 * @return path
	 */
	public ArrayList<String> getPath(){
		return path;
	}
	
	/**
	 * a getter method for length
	 * @return length
	 */
	public int getLength(){
		return length;
	}
	
	/**
	 * a getter method for the lastWord
	 * @return lastWord
	 */
	public String getLastWord() {
		return lastWord;
	}
	 
}
