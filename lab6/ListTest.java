public class ListTest{

   public static void main(String[] args){
      List<String> A = new List<String>();
      List<String> B = new List<String>();
      
	A.add(1, "one");
	A.add(2, "two");
	A.add(3, "three");
	A.add(4, "four");
	A.add(5, "five");
	System.out.println("A: "+A);
	System.out.println("A.size() is "+A.size());
	A.remove(1);
	A.remove(2);	  
	A.remove(3);
	System.out.println("A: "+A);
	System.out.println("A.size() is "+A.size());	

	B.add(1, "hello");
	B.add(2, "world");
	System.out.println("B: "+B);
	System.out.println("B.size() is "+B.size());
	System.out.println("A.equals(B) is "+A.equals(B));
	B.remove(1);
	System.out.println("B: "+B);
	System.out.println("B.size() is "+B.size());
	A.removeAll();
	System.out.println("A: "+A);
        System.out.println("A.size() is "+A.size());
   }
}
	 	      
