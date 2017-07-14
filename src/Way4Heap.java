import java.util.Scanner;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.NoSuchElementException;

public class Way4Heap 
{
	//number of children each node has
	private int d = 4;
	 int heapSize;
	private Node[] heap;
	
	//constructor
	public Way4Heap(int capacity)
	{
		heapSize = 0;
		heap = new Node[capacity + 1];
		//Arrays.fill(heap,-1);
	}
	
	//fuction to check if heap is empty
	public boolean isEmpty()
	{
		return heapSize == 0;
	}
	
	//function to check if heap is full
	 public boolean isFull( )
	 {
	     return heapSize == heap.length;
	 }
	 
	//clear heap
	public void clear()
	{
		heapSize = 0;
	}
	
	//function to get index parent of i
	private int parent(int i)
	{
		return (i-1)/d;
	}
	
	//function to get index of kth child of i
	private int kthChild(int i, int k)
	{
		return d*i + k;
	}
	
	//function to insert element
	public void insert(Node n)
	{
		if(isFull() )
			throw new NoSuchElementException("Overflow Exception");
		heap[heapSize++] = n;
		int x=heapSize-1;
		Node tmp = heap[x];    
        while (x > 0 && tmp.frequency < heap[parent(x)].frequency)
        {
        	if(tmp.frequency == heap[parent(x)].frequency){
        		if(tmp.index <0){
        			break;
        		}
        	}
            heap[x] = heap[ parent(x) ];
            x = parent(x);
        }                   
        heap[x] = tmp;
		//heapifyUp(heapSize - 1);
	}
	
	 // Function to find least element 
    public Node findMin( )
    {
        if (isEmpty() )
            throw new NoSuchElementException("Underflow Exception");           
        return heap[0];
    }
    
    // Function to delete min element 
    public Node deleteMin()
    {
        Node keyItem = heap[0];
        delete(0);
        return keyItem;
    }
    
    //Function to delete element at an index 
    public Node delete(int ind)
    {
        if (isEmpty() )
            throw new NoSuchElementException("Underflow Exception");
        Node keyItem = heap[ind];
        heap[ind] = heap[heapSize - 1];
        heapSize--;
        //heapifyDown(ind); 
        
        int child;
        Node tmp = heap[ ind ];
        while (kthChild(ind, 1) < heapSize)
        {
            child = minChild(ind);
            if (heap[child].frequency <= tmp.frequency){
            	if (heap[child].frequency == tmp.frequency){
            		if(tmp.index>=0){
            			break;
            		}
            	}
            
                heap[ind] = heap[child];}
            else
                break;
            ind = child;
        }
        heap[ind] = tmp;
        
        return keyItem;
    }
	
	
    // Function to get smallest child 
    private int minChild(int ind) 
    {
        int bestChild = kthChild(ind, 1);
        int k = 2;
        int pos = kthChild(ind, k);
        while ((k <= d) && (pos < heapSize)) 
        {
            if (heap[pos].frequency < heap[bestChild].frequency) 
                bestChild = pos;
            pos = kthChild(ind, k++);
        }    
        return bestChild;
    }
    
    public void createHeap(int [] freqTable)
    {
    	for(int x=0; x<freqTable.length; x++)
    	{
    		if(freqTable[x] > 0)
    		{
    			Node n=new Node();
    			n.frequency=freqTable[x];
    			n.index=x;
    			insert(n);
    		}
    			
    		
    	}
    	
    }
    static HashMap<Integer, String> map = new HashMap<Integer, String>();

    public void printCodes(Node root, String s)
    	{
    		if(root.left==null && root.right==null)
    		{
    			map.put(root.index, s);
    			return;
    		}
    		printCodes(root.left, s + "0");
    		printCodes(root.right, s + "1");
    	}

    private void createCodeTable() {
    		try{
    			BufferedWriter codeTableWrite = new BufferedWriter(new FileWriter(new File("code_table.txt")));
    			for(Map.Entry<Integer, String> codemap: map.entrySet())
    			{
    				codeTableWrite.write(codemap.getKey()+"==>"+codemap.getValue()+"\n");
    			}
    			System.out.println("Code Table file generated!");
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
