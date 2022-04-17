import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * This class will reading a dictionary file and 2 Strings from the commandline. The first String will be a filename to be read 
 * and the second String is an operation code. Appropriate actions will be taken if the input is correct, but an error message
 * will be displayed if otherwise.
 * @author Shyue Shi Leong
 *
 */
public class Main {
	public static void main(String args[]){
		int num1 = 2*3^4;
		System.out.println(num1);
		/*try{
			String fileName="dictionary.txt";
			//this arraylist contains all the words in the dictionary and distributed according to their length
			ArrayList<ArrayList<String>>arr=readFile(fileName);
			//put the arraylist into WordHandler class
			WordHandler wordHandler = new WordHandler(arr);
			
			int temp = Integer.parseInt(args[1]);
			if(temp==1){
				//read the file
				FileReader fr = new FileReader(args[0]);
				BufferedReader br = new BufferedReader(fr);
				
				//read a line and convert all to capital letters
				String line = br.readLine().toUpperCase();
				int count=1;
				while(line!=null){
					line=line.toUpperCase();
					String str[]=line.split(" ");
					String source = str[0];
					String target = str[1];
					Ladder results = wordHandler.getShortestPath(source, target);
					//print the appropriate results based on results
					if(results==null){
						System.out.println(count+". No word-ladder possible between "+source+" and "+target+".");
					}
					else{
						System.out.println(count+". Word-ladder between "+source+" and "+target+" of length "+results.getLength()+" has been found: ");
						ArrayList<String>list = results.getPath();
						for(int i=0;i<results.getLength();i++){
							System.out.println("\t"+list.get(i));
						}
					}
					System.out.println();
					count++;
					line=br.readLine();
				}
				br.close();
			}
			else if(temp==2){
				//read the file
				FileReader fr = new FileReader(args[0]);
				BufferedReader br = new BufferedReader(fr);
				String line = br.readLine();
				if(line.contains(" ")){
					br.close();
					throw new NumberFormatException();
				}
				int count=1;
				while(line!=null){
					int sum = 0;
					sum=wordHandler.count(line);
					//print the appropriate results depending on the sum
					if(sum==1){
						System.out.println(count+". "+line+": "+sum+" other word can be made.");
					}
					else if(sum>=0){
						System.out.println(count+". "+line+": "+sum+" other words can be made.");
					}
					else{
						System.out.println(count+". "+line+": not contained in word-list.");
					}
					count++;
					line=br.readLine();
				}
				br.close();
			}
			else{
				//print an error message if the operation code included is invalid
				throw new NumberFormatException();
			}
			
		}
		catch(IOException e){
			//error message to display if the filename is wrong or the file is not found
			System.out.println("Error.   Usage should be: \"Main <FILE_NAME> <OP_CODE>\"");
		}
		catch(ArrayIndexOutOfBoundsException e){
			//error message to display if the argument needed from the commandline is insufficient for processing
			System.out.println("Error.   Usage should be: \"Main <FILE_NAME> <OP_CODE>\"");
			System.out.println("An array index is out of bounds! ");
		}
		catch(NumberFormatException e){
			//error message to display if the operation code is invalid
			System.out.println("Error.   Usage should be: \"Main <FILE_NAME> <OP_CODE>\"");
			System.out.println("Invalid Operation Code!!");
		}*/
	}
	
	
	/**
	 * read in the file and sort them in an arraylist according to their length
	 * @param fileName
	 * @return an arraylist containing the words sorting by their length
	 */
	public static ArrayList<ArrayList<String>> readFile(String fileName){
		ArrayList<ArrayList<String>>arr = new ArrayList<ArrayList<String>>();
		ArrayList<String> arr2 = new ArrayList<String>();
		try{
			FileReader fr = new FileReader(fileName);
			BufferedReader br = new BufferedReader(fr);
			int longest =0;
			String word = br.readLine().toUpperCase();
			//read in the file and store all the words in arr2 and find the longest length of word
			while(word!=null){
				word=word.toUpperCase();
				if(longest<word.length()){
					longest=word.length();
				}
				arr2.add(word);
				word=br.readLine();
				
			}
			br.close();
			
			//instantiating all the ArrayList
			for(int i=0;i<longest;i++){
				ArrayList<String>temp = new ArrayList<String>();
				arr.add(temp);
			}
			
			//loop through the whole arr2 and placing the word in the appropriate location
			for(int i=0;i<arr2.size();i++){
				int length = arr2.get(i).length()-1;
				ArrayList<String> gr = arr.get(length);
				String temp = arr2.get(i);
				gr.add(temp);
			}
		}
		catch(IOException e){
			System.out.println("Usage should be: \"Main <FILE_NAME> <OP_CODE>\"");
		}
		return arr;
		
	}
	
}
