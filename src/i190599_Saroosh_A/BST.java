package i190599_Saroosh_A;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Vector;

class Node{
	String word;
	Node right;
	Node left;
	Node(){
		word="";
		right=null;
		left=null;
	}
	Node(String w){
		word=w;
		right=null;
		left=null;
	}
}

public class BST{
	Node root;
	boolean isEmpty() {
		if (root == null) {
			return true;
		}
		else {
			return false;
		}
	}
	void insert(String w) {
		Node temp = new Node(w);
		if (isEmpty()) {
			root = temp;
		}
		else {
			Node curr = root;
			while (curr != null) {
				if (temp.word.equals(curr.word)) {
					//System.out.println("Duplicates not allowed. Value not inserted");
					return;
				}
				else if ((temp.word.compareTo(curr.word) < 0) && (curr.left == null)) {
					curr.left = temp;
					//System.out.println("Left insertion successfull");
					break;
				}
				else if ((temp.word.compareTo(curr.word) < 0)) {
					curr = curr.left;
				}
				else if ((temp.word.compareTo(curr.word) > 0) && (curr.right == null)) {
					curr.right = temp;
					//System.out.println("Right insertion successfull");
					break;
				}
				else if((temp.word.compareTo(curr.word) > 0)) {
					curr = curr.right;
				}
			}
		}
	}
	void printPreorder(Node t) {
		if (t == null) {
			return;
		}	
		System.out.println(t.word);
		printPreorder(t.left);
		printPreorder(t.right);
	}
	
	void storePreorder(Node t, Vector<String> v) {
		if (t == null) {
			return;
		}	
		v.add(t.word);
		storePreorder(t.left,v);
		storePreorder(t.right,v);
	}
	
	void vocabularyFile(BST bst1) {
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
					if(newCheck!=',') {
						st += newCheck;
					}
					else {
						bst1.insert(st);
						//System.out.println(st);
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
		//bst.printPreorder(bst.root);
	}
	
	boolean search(String str1) {
		Node n1 = root;
		if(n1.word.equals(str1)) {
			return true;
		}
		while(n1!=null) {
			if(str1.compareTo(n1.word)==0) {
				return true;
			}
			else if(str1.compareTo(n1.word)>0) {
				n1=n1.right;
			}
			else if(str1.compareTo(n1.word)<0) {
				n1=n1.left;
			}
		}
		return false;
	}
	
}



/*
		  Iterator<String> it = vec.iterator();
		  while (it.hasNext())
		      System.out.print(it.next());
		 
		
*/
