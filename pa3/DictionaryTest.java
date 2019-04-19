public class DictionaryTest {
	public static void main(String[] args) {
		Dictionary A = new Dictionary();
		// test isEmpty() on empty Dictionary
		System.out.print("the Dictionary is empty (should be true): ");
		System.out.println(A.isEmpty());
		// test size() on empty Dicionary
		System.out.print("Dictionary Items(should be 0): ");
		System.out.println(A.size());
		// test lookup() on empty Dicionary should return null
		System.out.print("lookup abc1(should be null):");
		System.out.println(A.lookup("abc1"));
		// test makeEmpty on empty Dictionary
		A.makeEmpty();
		System.out.println("Dictionary State(should be empty): " + A);
		// one element test
		// test insert
		System.out.println("\nINSERT 1 ELEMENT");
		A.insert("abc", "1234");
		System.out.println(A);
		// test isEmpty()
		System.out.print("the Dictionary is empty(should be false): ");
		System.out.println(A.isEmpty());
		// test size()
		System.out.print("Dictionary State(should be 1):  ");
		System.out.println(A.size());
		// test lookup()
		System.out.println("abc value: " + A.lookup("abc"));
		// test delete()
		System.out.println();
		System.out.println("DELETE ITEM 1");
		A.delete("abc");
		// test lookup() after delete
		System.out.println("abc value(should be null): " + A.lookup("abc"));
	}
}
