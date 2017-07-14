
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.BitSet;
import java.util.Map;

public class encoder {
	
   public static BitSet encode( File f, huffman tree)
	{
	   
	   Map<Integer, String> codeTable = tree.getCodeTable(); 
	//	int codeLength = codeTable.size();

		BitSet bs = new BitSet();
		//bs.set(codeLength);

		try
		{
				// Get file size
			//int fileSize = (int)f.length() +1;						
	
				// Open the fileinput stream
			//FileInputStream in = new FileInputStream(f);		
			BufferedReader reader = new BufferedReader(new FileReader(f));
			String nextLine = null;
			int bitIndex = 0;
			while((nextLine = reader.readLine()) != null){
				int input=0;
				try{
			    input = Integer.parseInt(nextLine);
				}
				catch(NumberFormatException e){
					continue;
				}
			   String code = codeTable.get(input);
			   
			   for(char c : code.toCharArray()){
				   if(c == '1')
				   {
					   bs.set(bitIndex);
				   }
				   bitIndex++;
			   }
			}
			
			FileOutputStream fos = new FileOutputStream("encoded.bin");
			fos.write(bs.toByteArray());
			fos.close();
			
			long end_time = System.currentTimeMillis();
			
			
			
			
////			int bitIndex = codeLength - 1;
//			for(int i = 0; i < fileSize; i++)
//			{
//					// Read the file byte by byte
//				int b = (int)in.read();
//				char c = (b>=0)? (char)b: (char)0; 
//				BitSet cbs = CodeTable.transCode(c);
//
//					//The logical size of BitSet; used for JDK1.2
//				//int n = cbs.length();
//
//					//The logical size of BitSet; used for JDK1.1.*
//				int n = CodeTable.lengthOf(cbs);
//				for(int j = n - 2; j >= 0; j--)
//				{
//					if (cbs.get(j)) bs.set(bitIndex --);
//					else bitIndex --;
//				}
//			}
			
		}
		catch (IOException e)
		{
			System.out.println("Error: Cannot read file!");
		}

		return bs;
		
	}
//   public BitSet transCode( char c )
//	{
//		CodeTableItem codeItem = get( c );
//		int len = codeItem.len;
//		char code = codeItem.code;
//
//		BitSet bs = new BitSet();
//		bs.set(len);
//
//		transCode(bs, (int)code, 0);
//
//		return bs;
//	}
   
   public static void main(String args[]){
	   //File input = new File("sample_input_small.txt");
	   File input = new File(args[0]);
	   huffman tree = new huffman(input, "binary");
	   encode(input, tree);
	   
   }
}
