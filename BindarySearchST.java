/*
 * 二分法查找，符号表
 */
public class BindarySearchST<Key extends Comparable<Key>, Value> {
	
	private Key[] keys;
	private Value[] vals;
	private int N;
	
	@SuppressWarnings("unchecked")
	public BindarySearchST (int capacity) {
		keys = (Key[]) new Comparable[capacity];
		vals = (Value[]) new Comparable[capacity];
	}
	
	public int size () {
		return N;
	}
	
	public Value get (Key key) {
		if (isEmpty()) return null;
		int i = rank(key);
		if (i < N && keys[i].compareTo(key) == 0) return vals[i];
		else return null;
	}
	
	public void put (Key key, Value value) {
		int i = rank(key);
		if (i < N && keys[i].compareTo(key) == 0) vals[i] = value;
		else {
			for (int j = N; j > i; j--) {
				keys[j] = keys[j - 1];
				vals[j] = vals[j - 1];
			}
			keys[i] = key;
			vals[i] = value;
			N++;
		}
	}
	
	public Boolean isEmpty () {
		if (size() == 0) return true;
		else return false;
	}
	
	public int rank (Key key) {
		int lo = 0;
		int hi = N - 1;
		
		while (lo <= hi) {
			int mid = lo + (hi - lo) / 2;
			int cmp = key.compareTo(keys[mid]);
			if (cmp < 0) hi = mid - 1; // mid的前一个
			else if (cmp > 0) lo = mid + 1; // mid的后一个
			else return mid;
		}
		
		return lo;
	}
	
	public Key min () {
		return keys[0];
	}
	
	public Key max () {
		return keys[N - 1];
	}
	
	public Key select (int i) {
		return keys[i];
	}
	
	public Key ceiling (Key key) {
		int i = rank(key);
		return keys[i];
	}
	
	public Key floor (Key key) {
		int i = rank(key);
		if (key.compareTo(keys[i]) == 0) return keys[i];
		else return keys[i - 1];
	}
	
	public void delete (Key key) {
		int i = rank(key);
		if (i < N && key.compareTo(keys[i]) == 0) {
			for (int j = i; j < N; j++) {
				keys[j] = keys[j + 1];
				vals[j] = vals[j + 1];
			}
		}
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