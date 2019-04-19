// QueueInterface.java
// interface for the Queue ADT

public interface QueueInterface{
	public boolean isEmpty();
	public int length();
	public void enqueue(Object newItem);
	public Object dequeue() throws QueueEmptyException;
	public Object peek() throws QueueEmptyException;
	public void dequeueAll() throws QueueEmptyException;
	public String toString();
}
