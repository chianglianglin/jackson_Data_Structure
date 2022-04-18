package 字符串;

public class _796_Rotate_String {

	public boolean rotateString(String s, String goal) {
		if (s == null || goal == null)return false;
		if (s.length() != goal.length()) return false;
		return (s + s).contains(goal);
	}
}
