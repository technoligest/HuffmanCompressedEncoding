import java.util.*;
import java.io.*;
public class Assignment3 {
	public static void main(String[]args)throws IOException{
		//create the tree then encode into a file then decode that file.
		Scanner kb= new Scanner(System.in);
		System.out.print("Enter the filename to create the huffman tree from: ");
		String fileName=kb.next();
		createHuffmanTree(fileName);
		encode(fileName);
		decode(fileName);
	}
	public static void decode(String fileName){
		try{
			
			ArrayList<Character>ch= new ArrayList<Character>();
			ArrayList<String>huff= new ArrayList<String>();
			
			StringTokenizer st;
			Scanner inputFile;
			//getting the huffman code
			String temp, result="";
			inputFile = new Scanner(new File("Huffman.txt"));
			while (inputFile.hasNext()){
				st = new StringTokenizer(inputFile.nextLine(), "\t");
				temp=st.nextToken();
				st.nextToken();
				ch.add(temp.charAt(0));
				huff.add(st.nextToken());	
			}
			
			temp=null;
			inputFile = new Scanner(new File("Encoded_"+fileName));
			
			while (inputFile.hasNext()){
				st = new StringTokenizer(inputFile.nextLine());
				while(st.hasMoreTokens()){
					temp=st.nextToken();
					
					int start=0, end=1;
					boolean found=false;
					//cycle through the whole word
					while(start<temp.length()){
						//go letter by letter to see if the letter is found in the code.
						while(!found)
							if(huff.contains(temp.substring(start, end))){
								result+=ch.get(huff.indexOf(temp.substring(start, end)));
								found=true;
							}
							else
								end++;
						found=false;
						start=end;
						end++;
					}
					result+=" ";
				}
				result+="\n";
				
			}
			
			//write the result onto a file
			File outputFile= new File("Decoded_"+fileName);
			outputFile.createNewFile();
			FileWriter writer = new FileWriter(outputFile);
			writer.write(result);
			writer.flush();
			writer.close();
		}
		catch (Exception e){
			System.out.println("File name not acceptable.");
		}		
	}
	public static void encode(String fileName){
		
		try{
			String []arr= new String[256];
			StringTokenizer st;
			Scanner inputFile;
			
			//getting the huffman codes
			String temp, result="";
			inputFile = new Scanner(new File("Huffman.txt"));
			while (inputFile.hasNext()){
				st = new StringTokenizer(inputFile.nextLine(), "\t");
				temp=st.nextToken();
				st.nextToken();
				arr[(byte)temp.charAt(0)]=st.nextToken();
				
			}
			
			//using a huffman tree and a given text file, create the code for each word
			
			inputFile = new Scanner(new File(fileName));
			while (inputFile.hasNext()){
				temp=inputFile.nextLine();
				for(int i=0; i<temp.length(); i++)
					if(temp.charAt(i)==' ')
						result+=" ";
					else
						result+=arr[(byte)temp.charAt(i)];
				result+="\n";
			}
			
			//print the result onto a file
			File outputFile= new File("Encoded_"+fileName);
			outputFile.createNewFile();
			FileWriter writer = new FileWriter(outputFile);
			writer.write(result);
			writer.flush();
			writer.close();
		}
		catch (Exception e){
			System.out.println("File name not acceptable.");
		}	
	}
	
	
	//method asks for file name and creates the huffman tree for encoding it
	public static void createHuffmanTree(String filename){
		try{
			double total=0;
			String tempLine;
			int[] fr= new int [256];
			double[] prob= new double[256];
			ArrayList<BinaryTree<Pair>> pairs= new ArrayList<BinaryTree<Pair>>();
					
			
			//getting all the information from a file and storing it
			Scanner inputFile = new Scanner(new File(filename));
			while (inputFile.hasNext()){
				tempLine= inputFile.nextLine();
				for(int i=0; i<tempLine.length(); i++)
					if(tempLine.charAt(i)!=' '){
						fr[(byte)tempLine.charAt(i)] += 1;
						total+=1;
					}
			}
			inputFile.close();
			
			//adding all the pairs to the arraylist in sorted order
			for(int i=0; i<256; i++){
				if(fr[i]!=0){
					prob[i]=(double)fr[i]/total;
					Pair pr =  new Pair((char)i,(double)fr[i]/total);
					BinaryTree<Pair> bt= new BinaryTree<Pair>();
					bt.setData(pr);
					addSorted(pairs, bt);
				}
			}
			
			//create the huffman tree. Adding all nodes to one big tree
			while(pairs.size()>1)
			{
				BinaryTree<Pair> bt= new BinaryTree<Pair>();
				BinaryTree<Pair> temp;
				
				//adding the left child node
				temp= pairs.remove(0);
				temp.setParent(bt);
				bt.setLeft(temp);

				//adding the right child node
				temp = pairs.remove(0);
				temp.setParent(bt);
				bt.setRight(temp);
				

				bt.setData(new Pair(' ', bt.getLeft().getData().getProb()+bt.getRight().getData().getProb()));
				addSorted(pairs, bt);
			}
			
			//get the codes for the characters and save them in a variable
			String [] st=findEncoding(pairs.get(0));
			String result="";
			for(int i=0; i< st.length; i++){
				if(st[i]!=null)
					result+=(char)i+"\t"+prob[i]+"\t"+st[i]+"\n";
			}
			
			//print the result string onto a file
			File outputFile= new File("Huffman.txt");
			outputFile.createNewFile();
			FileWriter writer = new FileWriter(outputFile);
			writer.write(result);
			writer.flush();
			writer.close();
		}
		catch (Exception e){
			System.out.println("File name not acceptable.");
		}	
	}
	
	
	//methods take in pairs and encode them
	public static void findEncoding(BinaryTree<Pair> t, String[] a, String prefix) {
		if (t.getLeft()==null && t.getRight()==null) 
			a[(byte)( t.getData().getValue() )]= prefix;
		else {
			findEncoding(t.getLeft(), a, prefix+"0");
			findEncoding(t.getRight(), a, prefix+"1");
		}
	}
	public static String[] findEncoding(BinaryTree<Pair> t) {
		String[] result = new String[256];
		findEncoding(t, result, "");
		return result;
	}
	
	
	//take in an array of binary trees and a tree, and add the tree in the correct spot in ascending order
	public static void addSorted(ArrayList<BinaryTree<Pair>> arr, BinaryTree<Pair> t){
		int j;
		for(j=0; j<arr.size(); j++)
			if(t.getData().getProb() < arr.get(j).getData().getProb())
				break;
		arr.add(j, t);
	}
}


