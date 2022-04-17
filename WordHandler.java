import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;

/**
 * This class will find the number of word ladders can be form from a word given. 
 * @author Shyue Shi Leong
 *
 */
public class WordHandler {
	private ArrayList<ArrayList<String>> data;
	private ArrayList<ArrayList<HashMap<String, String>>> list=new ArrayList<ArrayList<HashMap<String, String>>>();
	
	/**
	 * an overloaded constructor
	 * @param arr
	 */
	public WordHandler(ArrayList<ArrayList<String>> arr){
		data=arr;
		for(int i=0;i<arr.size();i++){
			ArrayList<HashMap<String, String>> temp = new ArrayList<HashMap<String, String>>();
			list.add(temp);
		}
	}
	
	/**
	 * find the shortest path between 2 words if the 2 words are in the dictionary. If dictionary does not
	 * contain either the source or the target, it return an empty ladder. Otherwise, it will return a ladder
	 * containing the shortest path from the source to the target
	 * @param source
	 * @param target
	 * @param gr
	 * @return ladder
	 */
	public Ladder getShortestPath(String source, String target){
		ArrayList<String> al = data.get(source.length()-1);
		ArrayList<String> dupe = makeCopy(al);
		int index1=-1;
		int index2=-1;
		//loop through and find the index of both words 
		for(int i=0;i<dupe.size();i++){
			if(source.equalsIgnoreCase(dupe.get(i))){
				index1=i;
			}
			if(target.equalsIgnoreCase(dupe.get(i))){
				index2=i;
			}
		}
		
		//return null if either word is not in the dictionary or the length of both words do not match
		if(source.length()!=target.length()||index1==-1||index2==-1){
			return null;
		}
		
		ArrayList<String> path = new ArrayList<String>();
		LinkedList<Ladder> queue = new LinkedList<Ladder>();
		path.add(source);
		queue.add(new Ladder(path, source, 1));
		dupe.remove(source);
		
		while(queue.isEmpty()==false && queue.getLast().equals(target)==false ){
		    Ladder ldr = queue.remove();
		    //return ladder if the target is found
		    if(target.equals(ldr.getLastWord())){
			     return ldr;
			}
		    
		    //loop through the entire dictionary 
		    for(int i=dupe.size()-1;i>=0;i--){
		    	String string = dupe.get(i); 
			    
			    if(check(ldr.getLastWord(),string)){
			    	ArrayList<String> temp = ldr.getPath();
			    	ArrayList<String> newPath = new ArrayList<String>(temp);
			    	newPath.add(string);
			    
			    	queue.add(new Ladder(newPath, string, ldr.getLength()+1));
			    	//remove the word from the dictionary because we do not need to see it again
			    	dupe.remove(i);
			   }
		    }
		}
		return null;
		    
	}
	
	/**
	 * This method will take in a word and form as many word ladder as possible and return the number of 
	 * word ladders formed.
	 * @param line
	 * @return number of word ladders can be formed
	 */
	public int count(String line){
		int length=line.length()-1;
		ArrayList<String> al = data.get(length);
		int index=-1;
		for(int i=0;i<al.size();i++){
			String temp = al.get(i);
			if(line.equalsIgnoreCase(temp)){
				index=i;
			}
		}
		if(index==-1){
			return -1;
		}
		ArrayList<HashMap<String, String>> hash = list.get(line.length()-1);
		if(hash.isEmpty()){
			ArrayList<String> temp = makeCopy(al);
			findWordLadder(line, temp);
		}
		
		for(int i=0;i<hash.size();i++){
			HashMap<String, String>temp = hash.get(i);
			if(temp.containsKey(line)){
				return hash.get(i).size()-1;
			}
			else{
				ArrayList<String> temp1 = makeCopy(al);
				findWordLadder(line, temp1);
			}
		}
		return 0;
	}
	
	/**
	 * This is a helper method to generate the wordLadders
	 * @param word
	 * @param arr
	 */
	public void findWordLadder(String word, ArrayList<String> arr){
		LinkedList<String> queue = new LinkedList<String>();
		ArrayList<HashMap<String, String>> temp = list.get(word.length()-1);
		HashMap<String, String> map = new HashMap<String, String>();
		queue.add(word);
		map.put(word,word);
		arr.remove(word);
		while(queue.isEmpty()==false){
			String s = queue.remove();
			for(int i=arr.size()-1;i>=0;i--){
				String a = arr.get(i);
				if(check(s, a)){
					queue.add(a);
					map.put(a, a);
					arr.remove(i);
				}
			}
		}
		temp.add(map);
	}
	
	/**
	 * make a duplicate of the data
	 * @param list
	 * @return a copy of the list
	 */
	public static ArrayList<String> makeCopy(ArrayList<String> list){
		ArrayList<String> dupe = new ArrayList<String>();
		for(int i=0;i<list.size();i++){
			dupe.add(list.get(i));
		}
		return dupe;
	}
	
	/**
	 * check if the two words have only one letter difference. If the two words have one letter difference,
	 * it will return true and false if otherwise.
	 * @param first
	 * @param second
	 * @return true or false
	 */
	private static boolean check(String first, String second){
		int count=0;
		
		for(int i=0;i<first.length();i++){
			char word1 = first.charAt(i);
			char word2 = second.charAt(i);
			if(word1==word2){
				count++;
			}
		}
		if(count==first.length()-1){
			return true;
		}
		else{
			return false;
		}
	}
}