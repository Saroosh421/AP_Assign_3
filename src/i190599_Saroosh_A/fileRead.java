package i190599_Saroosh_A;

import java.io.FileInputStream;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.FileChannel;
import java.util.Vector;


public class fileRead {
	static BST bst = new BST();
	
	void inputFileRead1(Vector<String> vec) {
		try {
			FileInputStream file1 = new FileInputStream("file1.txt");
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
						//if (newCheck1!='\n') {
						//System.out.println(st1);
						vec.add(st1);	
						st1="";
						//}
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
	
	void inputFileRead2(Vector<String> vec) {	
		try {
			FileInputStream file1 = new FileInputStream("file2.txt");
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
						//if (newCheck1!='\n') {
						//System.out.println(st1);
						vec.add(st1);	
						st1="";
						//}
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
