
public class BindaryST<Key extends Comparable<Key>, Value> {
	public Node root;
	
	// Node节点类
	private class Node {
		public Node left, right;
		public Key key;
		public Value val;
		public int N;
		
		public Node (Key key, Value val, int N) {
			this.key = key;
			this.val = val;
			this.N = N;
		}
	}
	
	public boolean isEmpty () {
		return root == null;
	}
	
	public int size () {
		return size(root);
	}
	
	private int size (Node node) {
		// 根节点
		if (node == null) return 0;
		else return node.N;
	}
	
	public Value get (Key key) {
		return get(root, key);
	}
	
	private Value get (Node node, Key key) {
		if (node == null) return null; // 找不到就直接返回null
		int cmp = key.compareTo(node.key);
		if (cmp < 0) return get(node.left, key);
		else if (cmp > 0) return get(node.right, key);
		return node.val;
	}
	
	public void put (Key key, Value val) {
		put(root, key, val);
	}
	
	private Node put (Node node, Key key, Value val) {
		if (node == null) return new Node(key, val, 1);
		int cmp = key.compareTo(node.key);
		if (cmp < 0) return node.left = put(node.left, key, val);
		else if (cmp > 0) return node.right = put(node.right, key, val);
		node.val = val;
		node.N = size(node.left) + size(node.left) + 1; // 左 + 右 + 自己 
		
		return node;
	}
	
	public Node min () {
		return min(root);
	}
	private Node min (Node node) {
		if (node.left == null) return node;
		else return min(node.left);
	}
	
	public Node max () {
		return max(root);
	}
	private Node max (Node node) {
		if (node.right == null) return node;
		else return max(node.right);
	}
	
	public void delete (Key key) {
		root = delete(root, key);
	}
	private Node delete (Node node, Key key) {
		if (node == null) return null;
		int cmp = key.compareTo(node.key);
		if (cmp < 0) node.left = delete(node.left, key);
		else if (cmp > 0) node.right = delete(node.right, key);
		else {
			// 右节点替换被删除的节点，原左节点放在右节点最小节点中
			Node minNode = min(node.right);
			minNode.left = node.left;
			node = node.right;
		}
		node.N = size(node.left) + size(node.right) + 1;
		return node;
	}
	
	public int rank (Key key) {
		return rank(root, key);
	}
	private int rank(Node node, Key key) {
		if (node == null) return 0;
		int cmp = key.compareTo(node.key);
		if (cmp < 0) return rank(node.left, key);
		else if (cmp > 0) return size(node.left) + rank(node.right, key) + 1; // 要加上左树的个数 和节点本身
		return size(node.left);
	}
	
	public Key select (int i) {
		return select(root, i);
	}
	private Key select (Node node, int i) {
		if (i > node.N) return null;
		int leftNums = size(node.left);
		if (leftNums < i) return select(node.left, i);
		else if (leftNums > i) return select(node.right, i - leftNums);
		return node.key;
	}
	
	public Key celing (Key key) {
		int i = rank(root, key);
		return select(i);
	}
	
	public Key floor (Key key) {
		Value val = get(key);
		int i = rank(root, key);
		if (val == null) i--;
		return select(i);
	}
	
	public static void main (String[] args) {
		BindarySearchST<String, String> bsST = new BindarySearchST<String, String>(20);
		bsST.put("k1", "v1");
		bsST.put("k2", "v2");
		bsST.put("k3", "v3");
		bsST.put("k5", "v5");
		bsST.put("k6", "v6");

		bsST.put("k5", "v51");
		
		System.out.println(bsST.get("k2"));
		System.out.println(bsST.get("k4"));
		System.out.println(bsST.get("k5"));
		System.out.println(bsST.get("k6"));
		
		System.out.println("ceiling of k4 " + bsST.ceiling("k4"));
		System.out.println("floor of k4 " + bsST.floor("k4"));
		
		bsST.delete("k5");
		System.out.println(bsST.get("k5"));
		

	}
}
