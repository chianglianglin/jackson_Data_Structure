package DFS;

import java.util.ArrayList;
import java.util.List;

public class _17_Letter_Combinations_of_a_Phone_Number {
	private char[][] lettersArray = {
			{'a','b','c'},{'d','e','f'},{'g','h','i'},
			{'j','k','l'},{'m','n','o'},{'p','q','r','s'},
			{'t','u','v'},{'w','x','y','z'}
	};
	public List<String> letterCombinations(String digits) {
		if (digits == null)return null;
		List<String> list = new ArrayList<>();
		char[] chars = digits.toCharArray();
		if (chars.length == 0)return list;
		char[] string = new char[chars.length];

		dfs(0);
		return list;

	}

	private void dfs(int idx){
		//到了最後一層
		if (idx == chars.length){
			list.add(new String(string));
			return;
		}
		//枚舉這一層

		for(char letter : letters){
			string[idx] = letter;
			dfs(idx + 1);
		}
	}
}
