/**
 * circular rotation
 * ÅÐ¶ÏÁ½¸ö×Ö·û´®ÊÇ·ñ Ñ­»·×Ö·û´®
 * @author Lian
 *
 */
public class RotationStr {
	private static String _oriT;
	
	private static boolean _isRotationStrInner(String s, String t) {
		char firstChar = s.charAt(0);
		int idx = t.indexOf(firstChar);
	
		if(!s.equals(t)) { // to get if two string is equal!! 
			if(idx > 0){
				String _t = t.substring(idx) + t.substring(0, idx);
				if(_oriT.equals(_t)) return false;
				else return _isRotationStrInner(s, _t);
			}else{
				return false;
			}
		}
		
		return true;

	}
	
	public static boolean isRotationStr(String s, String t) {
		_oriT = t;
		return _isRotationStrInner(s, t);
	}
	
	public static void main(String[] args) {
		String s = "ABCABC";
		String t = "BCABCA";
		boolean ret = isRotationStr(s, t);
		
		System.out.println(ret);
		
	}

}
