import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
public class huffman {

	Node root = null;

	static int cap=0;
	public huffman(File f, String heapName)
	{
		int[] freqs = new int[1000000];

		int Counter = 0;
		

		try
		{
				// Get file size
			//int fileSize = (int)f.length();
			
				// Include EOF in the file size
			//fileSize ++;
	
				// Open the fileinput stream
			FileInputStream in = new FileInputStream(f);
			Scanner s = new Scanner(in);
			int index = -1;
			while(s.hasNext())
			{
				
				
				int msg = s.nextInt();
				freqs[msg]++;
				
			}
			for(int i=0;i<1000000;i++)
			{
				if(freqs[i]>0)
					cap++;
			}
				
		}
		catch (IOException e)
		{
			System.out.println("Error: Cannot read file!");
		}
		
		if(heapName.equals("binary")){
			BinaryHeap bh = new BinaryHeap(cap);
			bh.createHeap(freqs);
			root = getHuffmanTree(bh);
		}
	      if(heapName.equals("way4")){
			Way4Heap wh = new Way4Heap(cap);
			wh.createHeap(freqs);
			root = getHuffmanTree(wh);
		}
		else{
			PairingHeap ph = new PairingHeap(cap);
			ph.createHeap(freqs);
			root = getHuffmanTree(ph);
		}
		
		createCodeTable(root, "");
		
	
	}	 
	
	public Node getHuffmanTree(BinaryHeap heap)
	{
		
	
     	while (heap.heapSize > 1)
		{
			Node t1 = heap.deleteMin();
			Node t2 = heap.deleteMin();
			Node parent = new Node();
			parent.index = -1;
			parent.frequency = t1.frequency+t2.frequency;
			parent.left=t1;
			parent.right=t2;
			heap.insert(parent);
		}

		return heap.deleteMin();
	}
	public Node getHuffmanTree(PairingHeap heap)
	{
		
	
     	while (heap.heapSize > 1)
		{
			Node t1 = heap.deleteMin();
			Node t2 = heap.deleteMin();
			Node parent = new Node();
			parent.index = -1;
			parent.frequency = t1.frequency+t2.frequency;
			parent.left=t1;
			parent.right=t2;
			heap.insert(parent);
		}

		return heap.deleteMin();
	}
	public Node getHuffmanTree(Way4Heap heap)
	{
		
	
     	while (heap.heapSize > 1)
		{
			Node t1 = heap.deleteMin();
			Node t2 = heap.deleteMin();
			Node parent = new Node();
			parent.index = -1;
			parent.frequency = t1.frequency+t2.frequency;
			parent.left=t1;
			parent.right=t2;
			heap.insert(parent);
		}

		return heap.deleteMin();
	}
	static HashMap<Integer, String> map = new HashMap<Integer, String>();

    private void printCodes(Node root, String s)
    	{
    		if(root.left==null && root.right==null)
    		{
    			map.put(root.index, s);
    			return;
    		}
    		printCodes(root.left, s + "0");
    		printCodes(root.right, s + "1");
    	}
    
    public HashMap<Integer, String> getCodeTable(){
 	   return map;
    }

    public void createCodeTable(Node root, String s) {
    	printCodes(root, s);
    		try{
    			BufferedWriter codeTableWrite = new BufferedWriter(new FileWriter(new File("code_table.txt")));
    			for(Map.Entry<Integer, String> codemap: map.entrySet())
    			{
    				codeTableWrite.write(codemap.getKey()+" "+codemap.getValue()+"\n");
    			}
    			//System.out.println("Code Table file generated!");
    			codeTableWrite.close();
    		}
    		catch(IOException e)
    		{
    			System.out.println("Error in Writing.");
    		}
    		catch(Exception e){
    			System.out.println(e.getMessage());
    		}
    	}
}

		

class Node 
{
       
       int index;

       // count
       int frequency;

       // left child
       Node left;

       //right child
       Node right;

       
       public Node() {

       }

       
        // create Node based on index value and frequency count
        
       public Node(int index, int frequency) {
           this.index = index;
           this.frequency = frequency;
       }
       
      
}

	
	


