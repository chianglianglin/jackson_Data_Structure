package 字符串;

import java.util.HashMap;
import java.util.Map;

public class _3_LongestSubstringWithoutRepeatingCharacters {
	public int lengthOfLongestSubstring(String s) {
		if (s == null) return 0;
		char[] chars = s.toCharArray();
		if (chars.length == 0) return 0;
//		int[] prevIdxes = new int[26];
//		prevIdxes[chars[0] - 'a'] = 0;
		int[] prevIdxes = new int[128];
		for (int i = 0; i < prevIdxes.length; i++) {
			prevIdxes[i] = -1;
		}
		prevIdxes[chars[0]] = 0;
		//以i-1位置字符結尾的最長不重複字符串的開始索引
		int li = 0;
		int max = 1;
		for (int i = 1; i < chars.length; i++) {
			//i位置字符上一次出現的位置
			int pi = prevIdxes[chars[i]];
			if ( li <= pi) {
				li = pi + 1;
			}
			//存儲這個字符出現的位置
			prevIdxes[chars[i]] = i;
			//求出最長不重複的字符串長度
			max = Math.max(max, i - li + 1);
		}
		return max;
	}

	public int lengthOfLongestSubstring2(String s) {
		if (s == null) return 0;
		char[] chars = s.toCharArray();
		if (chars.length == 0) return 0;
		Map<Character, Integer> preIdexs = new HashMap<>();
		preIdexs.put(chars[0], 0);
		//以i-1位置字符結尾的最長不重複字符串的開始索引
		int li = 0;
		int max = 1;
		for (int i = 1; i < chars.length; i++) {
			//i位置字符上一次出現的位置
			Integer pi = preIdexs.getOrDefault(chars[i], -1);
			if (li <= pi) {
				li = pi + 1;
			}
			//存儲這個字符出現的位置
			preIdexs.put(chars[i], i);
			//求出最長不重複的字符串長度
			max = Math.max(max, i - li + 1);
		}
		return max;
	}
}
