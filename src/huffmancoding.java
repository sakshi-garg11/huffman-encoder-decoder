import java.io.File;

public class huffmancoding {

	public static void main(String[] args) {
		huffmancoding t=new huffmancoding();
		File f = new File(args[0]);
        
		
		
	long start_time = System.nanoTime();
   for (int j=0; j< 10 ; j++){
	 huffman h1 = new huffman(f, "binary");
	  }
  long end_time = System.nanoTime();
System.out.println("Time using binary Heap (microseconds):"+(end_time - start_time)/10000);
	

	  
 start_time = System.nanoTime();
	for (int l=0; l< 10 ; l++){
		huffman h3 = new huffman(f, "pairing");
	}
  end_time = System.nanoTime();
 System.out.println("Time using Pairing Heap (microseconds):"+(end_time - start_time)/10000);
	
	
	start_time = System.nanoTime();
	   for (int k=0; k< 10 ; k++){
		   huffman h2 = new huffman(f, "way4");
		  }
	 end_time = System.nanoTime();
	 System.out.println("Time using 4-Way Heap (microseconds):"+(end_time - start_time)/10000);
	}
}
