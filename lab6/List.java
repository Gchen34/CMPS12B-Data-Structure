@SuppressWarnings("overrides")

public class List<T> implements ListInterface<T>{
	private class Node{
		T item; 
		Node next; 
		
		//Node constructor
		Node(T item){
			this.item = item;
			next = null;
		}
	}

	//Fields for List class
	private Node head; //Head of list
	private int size; //Number of items in the list

	//List() constructor
	List(){
		head = null;
		size = 0;   
	}

	//find()
	//Private helper function to find by index
	private Node find(int index){
		Node N = head;
		for(int i = 1; i<index; i++){
			N = N.next;
		}
		return N;
	} 

//ADT Operations------------------------------------
	// isEmpty()
		public boolean isEmpty(){
		return (size == 0);
	}

	// size()
	public int size(){
		return size;
	}
	
	//get()
	public T get(int index) throws ListIndexOutOfBoundsException{
		if( index<1 || index>size){
			throw new ListIndexOutOfBoundsException(
        	    "List get() error: invalid index: " + index);
		}
		Node N = find(index);
		return N.item;
   	}

	//add()
	public void add(int index, T newItem) throws ListIndexOutOfBoundsException{
		if( index<1 || index>size+1){
			throw new ListIndexOutOfBoundsException(
        	    "List add() error: invalid index: " + index);
		}
		if(index == 1){ //Insert new head of list
			Node N = new Node(newItem);
			N.next = head; //Point to head
			head = N; //Head Node points to new Node N
		} else { //At this point index >= 2
			Node P = find(index-1); //Find previous Node
        		Node C = P.next; //Current is next node
        		P.next = new Node(newItem);//P.next points to new Node at index
        		P = P.next;//Point to the next node, newItem
        		P.next = C;//Connect other Node 
		}
		size++;
  	}

	//remove()
	public void remove(int index) throws ListIndexOutOfBoundsException{
		if( index<1 || index>size){
			throw new ListIndexOutOfBoundsException(
         	   "remove(): invalid index: " + index);
		}
		if(index==1){ //Remove head
			Node N = head;
			head = head.next; //Head points to next
			N.next = null;
		}else{
			Node P = find(index-1);//Previous Node
			Node N = P.next;//Next node P.next
			P.next = N.next;//P.next now points to node after N
			N.next = null;//N.next reference now points to null;
		}
		size--;
	}

	//removeAll()
	public void removeAll(){
		head = null;
		size = 0;
	}

	//toString()
	public String toString(){
	StringBuffer sb = new StringBuffer();
	Node N = head;
	for(; N!=null; N=N.next){
		sb.append(N.item).append(" ");
	 }
	return new String(sb);
	}

	//equals()
	@SuppressWarnings("unchecked")
	public boolean equals(Object rhs){
	boolean eq = false;
	List<T> R = null;
	Node N = null;
	Node M = null;
	
	if( this.getClass() == rhs.getClass()){
	R = (List<T>)rhs;
		eq = (this.size == R.size);
	N = this.head;
	M = R.head;
	while(eq && N!=null){
		eq = (N.item == M.item);
	N = N.next;
	M = M.next;
	}
	}
	return eq;
	}

}
