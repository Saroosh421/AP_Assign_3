package i190599_Saroosh_A;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Scanner;
import java.util.Vector;


public class driver extends Thread {
	public static BST bst = new BST();
	public static BST bst1 = new BST();
	public static fileRead fr = new fileRead();
	
	static Vector<Vector<String>> tok = new Vector<Vector<String>>(); 
	
	public void run() {
		String filename = Thread.currentThread().getName();
		if(filename.equals("wordList.txt")) {
			try {
				FileInputStream file = new FileInputStream("wordList.txt");
				//System.out.println("Open!");
				FileChannel readCh = file.getChannel();
				ByteBuffer readBuff = ByteBuffer.allocate((int)readCh.size());
				String st="";
				char newCheck;
				while(readCh.read(readBuff) > 0) {
					readBuff.flip();
					for(int i=0; i < readBuff.limit(); i++) {
						newCheck = (char)readBuff.get();
						if(newCheck!=' ') {
							st += newCheck;
						}
						else {
							bst.insert(st);
							st="";
						}
					}
				}
				readCh.close();
				file.close();
			}
			catch(IOException ex){
				ex.printStackTrace();  
			}
		}
		else {
			for (int o = 0 ; o < tok.size() ; o++)
			{
				String tok_Nem = tok.get(o).get(0);
				if (filename.compareTo(tok_Nem) == 0)
				{
					try {
						FileInputStream file1 = new FileInputStream(filename);
						FileChannel readCh1 = file1.getChannel();
						ByteBuffer readBuff1 = ByteBuffer.allocate((int)readCh1.size());
						String st1="";
						char newCheck1;
						while(readCh1.read(readBuff1) > 0) {
							readBuff1.flip();
							for(int i=0; i < readBuff1.limit(); i++) {
								newCheck1 = (char)readBuff1.get();
								if(newCheck1=='.' || newCheck1==',' || newCheck1=='?' || newCheck1=='!' || newCheck1==';') {
									continue;
								}
								else if(newCheck1!='\n' && newCheck1!=' ') {
									st1 += newCheck1;
								}
								else {
									tok.get(o).add(st1);
									st1="";
								}
							}
						}
						readCh1.close();
						file1.close();
					}
					catch(IOException ex){
						ex.printStackTrace();  
					}
				}
			}
		}
	}
	
	public static void match(Vector<String> v1, Vector<String> v2, Vector<String> v3) {
		for(int i=0; i<v1.size(); i++) {
			for(int j=0; j<v2.size(); j++) {
				if(v1.get(i).equals(v2.get(j))) {
					v3.add(v1.get(i));
					//v4.add(1);
				}
			}
		}
	}
	
	public static void countWords(Vector<String> v, Vector<String> coMatch, Vector<Integer> county)
	{
	    boolean trav[] = new boolean[v.size()];
	    for(int i=0 ;i<v.size() ;i++) {
	    	trav[i]=false;
	    }	 
	    for (int i = 0; i < v.size(); i++) {
	        if (trav[i] == true) {
	            continue;
	        }
	        int co = 1;
	        for (int k = i+1; k < v.size(); k++) {
	            if (v.get(i).equals(v.get(k))) {
	                trav[k] = true;
	                co++;
	            }
	        }
	        county.add(co);
	        coMatch.add(v.get(i));
	        //System.out.println(v.get(i) + " " + co);
	    }
	}
	
	public static void tokenize(String sent, String[] store,int ind) {
		String st1="";
		for(int i=0; i < sent.length(); i++) {
			if(sent.charAt(i) == '.' || sent.charAt(i)==',' || sent.charAt(i)=='?' || sent.charAt(i)=='!' || sent.charAt(i)==';') {
				continue;
			}
			else if(sent.charAt(i)!='\n' && sent.charAt(i)!=' ') {
				st1 += sent.charAt(i);
			}
			else {
				store[ind]=st1;
				//System.out.println(storeTok[ind]);
				st1="";
				ind++;
			}
		}
		store[ind]=st1;
		//System.out.println(storeTok[ind]);
		ind++;
	}
	
	public static void main(String[] args) {
		int op=0;
		Scanner in = new Scanner(System.in);
		
		int si=1000;
		Vector<String> vec = new Vector<String>(si);
		Vector<String> vec1 = new Vector<String>(si);
		Vector<String> vec2 = new Vector<String>(si);
		Vector<String> vocabVec = new Vector<String>(si);
		Vector<String> matchedWords = new Vector<String>(vec1.size()+vec2.size());
		Vector<Integer> vecMatchCount = new Vector<Integer>(vec1.size()+vec2.size());
		Vector<String> vecMatchWords = new Vector<String>(vec1.size()+vec2.size());
		
		//bst.vocabularyFile(bst1);
		//storePreorder(bst1.root, vocabVec);
		fr.inputFileRead1(vec1);
		fr.inputFileRead2(vec2);
	
		
		for(int i=0; i<vec1.size(); i++) {
			vec.add(vec1.get(i));
		}
		for(int i=0; i<vec2.size(); i++) {
			vec.add(vec2.get(i));
		}
		
		
		
		driver d2 = new driver();
		d2.setName(args[0]);
		d2.start();
		
		//Adding vectors depending upon no of files
		for (int i = 0; i<args.length-1;i++){
			Vector temp = new Vector();
			tok.add(temp);
		}
		//Adding file names on the very first entry of each file vector 
		for (int i=0; i<args.length-1;i++){
			tok.get(i).add(args[i+1]);
		}
		//Running threads and giving names depending upon the names of file
		for (int i=0;i<args.length-1;i++){
			driver d1 = new driver();
			d1.setName(args[i+1]);
			d1.start();
		}
		
		//No of Files
		int noOfFiles=0;
		while(noOfFiles<tok.size()) {
			noOfFiles++;
		}
		noOfFiles++;
		System.out.println("No of files: "+noOfFiles);
		//Files name
		System.out.print("Names of file: ");
		System.out.print(args[0]+"\t");
		for( noOfFiles=0;noOfFiles<tok.size();noOfFiles++) {
			System.out.print(tok.get(noOfFiles).get(0)+"\t");
		}
		System.out.print("\n\n");
		
		/*for(int i=0; i<vec1.size(); i++) {
			vec.add(vec1.get(i));
		}
		for(int i=0; i<vec2.size(); i++) {
			vec.add(vec2.get(i));
		}
		
		match(vec,vocabVec,matchedWords);*/
		//countWords(matchedWords,vecMatchWords,vecMatchCount);
		
		while(true) {
			System.out.println("\n\nFollowing are the functionalities you can perform");
			System.out.println("1. Display BST from vocabulaory file");
			System.out.println("2. Display words from input file");
			System.out.println("3. Matching words and ther frequency");
			System.out.println("4. Search query");
			System.out.println("5. Exit\n");
			
			op = in.nextInt();
			if(op==1) {
				bst.printPreorder(bst.root);
			}
			else if(op==2) {
				for (int i=0;i<tok.size(); i++){
					System.out.println(tok.get(i));
				}
			}
			else if(op==3) {
				//for(int i=0; i<vecMatchWords.size(); i++) {
				//	System.out.println(vecMatchWords.get(i)+"\t"+vecMatchCount.get(i));
				//}
				word[] wordy = new word[1000];
				for(int i=0;i<1000;i++) {
					word w = new word();
					wordy[i] = w;
				}
				int county=0;
				for(int i=0;i<tok.size();i++) {
					for(int j=0;j<tok.get(i).size();j++) {
						boolean c1=bst.search(tok.get(i).get(j));
						if(c1) {
							boolean c2 = false;
							for(int k=0;k<1000;k++) {
								if(tok.get(i).get(j).equals(wordy[k].word1)) {
									wordy[k].freq++;
									c2 = true;
								}
							}
							if(!c2) {
								word w1 = new word();
								w1.word1=tok.get(i).get(j);
								w1.freq=1;
								wordy[county]=w1;
								county++;
							}
						}
					}
				}
				for(int i=0;i<1000;i++) {
					if(wordy[i].freq!=0) {
						System.out.println(wordy[i].word1+"\t"+wordy[i].freq);
					}
				}
			}
			else if(op==4) {
				String inp;
				String[] storeToken= new String[25];
				int ind=0;
				System.out.println("Enter query to search in input files");
				in.nextLine();
				inp = in.nextLine();
				String[] tok1 = inp.split(" ");
				for(int i=0;i<tok1.length;i++) {
					ind++;
				}
				System.out.println("No of words to search: "+ ind);
			}
			else {
				return;
			}
		}
	}
	
}
