public class Dictionary implements DictionaryInterface {

// Inner private Node class
private class Node {
		String key;
		String value;
		Node next;

		Node(String key, String value) {
			this.key = key;
			this.value = value;
			next = null;
		}
	}
// private variables
private Node head;
private int numItems;
// private inner helper method finKey()
// returns a Reference Node to the Node whos key field equals the key parameter
	private Node findKey(String key) {
		Node N = head;
		while (N != null) {
			if (N.key.equals(key)) {
				return N;
			} else {
				N = N.next;
			}
		}
		return null;
	}
// constructor for the Dictionary class
	public Dictionary() {
		head = null;
		numItems = 0;
	}
// isEmpty()
// pre: none
// returns true if this IntgerList is empty, false otherwise
	public boolean isEmpty() {
		return (numItems == 0);
	}
// size()
// pre: none
// returns the number of entries in this Dictionary
	public int size() {
		return numItems;
	}
// lookup()
// pre: none
// returns value associated key, or null reference if no such key exists
	public String lookup(String key) {
		Node N = findKey(key);
		Node H = head;
		if (N == null)
			return null;
		while (H != N) {
			H = H.next;
		}
		return H.value;
	}
// insert()
// inserts new (key,value) pair into this Dictionary
// pre: lookup(key)==null
	public void insert(String key, String value) throws DuplicateKeyException {
		Node N = findKey(key);
		if (N != null) {
			throw new DuplicateKeyException("Dictionary Error: cannot insert duplicate keys");
		}
		if (head == null) {
			head = new Node(key, value);
		} else {
			Node H = head;
			while (H.next != null) {
				H = H.next;
			}
			H.next = new Node(key, value);
		}

		numItems++;
	}
// delete()
// deletes pair with the given key
// pre: lookup(key)!=null
	public void delete(String key) throws KeyNotFoundException {
		Node N = findKey(key);
		if (N == null) {
			throw new KeyNotFoundException("cannot delete non-existent key");
		}
		Node H = head;
		Node C = H.next;
		if (H == N) {
			head = C;
			H = C;
		} else if (H != N) {
			while (C != N) {
				H = H.next;
				C = C.next;
			}
			H.next = C.next;
			if (C.next == null)
				H.next = null;
		}
		numItems--;
	}
// makeEmpty()
//pre: none
//return result
	public void makeEmpty() {
		head = null;
		numItems = 0;
	}
// toString()
// returns a String representation of this Dictionary
// overrides Object's toString() method
// pre: none
	public String toString() {
		StringBuffer sb = new StringBuffer();
		Node H = head;
		for (; H != null; H = H.next) {
			sb.append(H.key).append(" ").append(H.value).append("\n");
		}
		return new String(sb);
	}
} 
