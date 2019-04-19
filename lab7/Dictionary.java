public class Dictionary implements DictionaryInterface {
	// inner private Node class
	private class Node {
		String key;
		String value;
		Node left;
		Node right;

		// node constructor
		Node(String key, String value) {
			this.key = key;
			this.value = value;
			left = null;
			right = null;
		}
	}

	// private variables
	private int numPairs;
	private Node root;

	public Dictionary() {
		root = null;
		numPairs = 0;
	}

	// private Node methods
	// findKey()
	private Node findKey(Node R, String key) {
		if (R == null || R.key.equals(key)) {
			return R;
		}
		if (R.key.compareTo(key) >0) {
			return findKey(R.left, key);
		} else {
			return findKey(R.right, key);
		}
	}

	// findParent()
	private Node findParent(Node N, Node R) {
		Node P = null;
		if (N != R) {
			P = R;
			while (P.left != N && P.right != N) {
				if (N.key.compareTo(P.key) < 0) {
					P = P.left;
				} else {
					P = P.right;
				}
			}
		}
		return P;
	}

	// findLeftmost
	private Node findLeftmost(Node R) {
		Node L = R;
		if (L != null)
			for (; L.left != null; L = L.left)
				;
		return L;
	}

	// public functions
	// isEmpty()
	public boolean isEmpty() {
		return (numPairs == 0);
	}

	// size()
	public int size() {
		return numPairs;
	}

	// lookup()
	public String lookup(String key) {
		Node N = findKey(root, key);
		return (N == null ? null : N.value);
	}

	// insert()
	public void insert(String key, String value) throws DuplicateKeyException {
		Node N, A, B;
		if (lookup(key) != null) {
			throw new DuplicateKeyException("Cannot insert(0 duplicate key");
		} else {
			N = new Node(key, value);
			B = null;
			A = root;
			while (A != null) {
				B = A;
				if (key.compareTo(A.key) < 0) {
					A = A.left;
				} else {
					A = A.right;
				}
			}
			if (B == null) {
				root = N;
			} else if (key.compareTo(B.key) < 0) {
				B.left = N;
			} else {
				B.right = N;
			}
		}
		numPairs++;
	}

	// delete()
	public void delete(String key) throws KeyNotFoundException {
		Node N, P, S;
		if (lookup(key) == null) {
			throw new KeyNotFoundException("Cannot delete() non-existent key");
		} else { 
			N = findKey(root, key);
		// case 1 (no children)
		if (N.left == null && N.right == null) { 
			if (N == root) {
				root = null;
			} else {
				P = findParent(N, root);
				if (P.right == N) {
					P.right = null;
				} else {
					P.left = null;
				}
			}
		}
		// case 2 (left but no right child)
		else if(N.right == null) {
			if(N==root) {
				root = N.left;
			}else {
				P = findParent(N,root);
				if(P.right ==N) {
					P.right = N.left;
				}else {P.left = N.left;}
			}
		}
		// case 2 (right but no left child)
		else if(N.left == null) {
			if(N==root) {
				root = N.right;
			} else {
				P = findParent(N, root);
				if(P.right == N) {
					P.right = N.right;
				} else {P.left = N.right;}
			}
		}
		 // case3: (two children: N->left!=NULL && N->right!=NULL)
		else {
			S = findLeftmost(N.right);
			N.key = S.key;
			N.value = S.value;
			P = findParent(S, N);
			if(P.right == S) {
				P.right = S.right;
			}else {P.left=S.right;}
		}
	} numPairs --;
}
	
	//makeEmpty()
	public void makeEmpty() {
		root = null;
		numPairs = 0;
	}
	
	// toString()
	public String toString() {
		Node R = root;
		return (printInOrder(R));
	}

	// printInOrder()
	private String printInOrder(Node R) {
		StringBuffer sb = new StringBuffer();
		if (R != null) {
			sb.append(printInOrder(R.left));
			sb.append(R.key).append(" ").append(R.value).append("\n");
			sb.append(printInOrder(R.right));
		}
		return (new String(sb));

	}

}