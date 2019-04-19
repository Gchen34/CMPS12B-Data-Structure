public class QueueTest{
  public static void main (String[] args){
    Queue A = new Queue();
    System.out.println("Empty? "+A.isEmpty());
    A.enqueue((int)1);
    A.enqueue((int)2);
    A.enqueue((int)3);
    A.enqueue((int)4);
    A.enqueue((int)5);
    A.enqueue((int)6);
    System.out.println("Empty? "+A.isEmpty());
    System.out.println("Length: "+A.length());
    System.out.println(A);
    A.dequeue();
    System.out.println("Length: "+A.length());
    System.out.println(A);
    A.dequeueAll();
    System.out.println(A.peek());
    System.out.println("Empty? "+A.isEmpty());
  }
}

