package 動態規劃;

public class _5_Longest_Palindromic_Substring {
	public String longestPalindrome1(String s) {
		if (s == null) return null;
		char[] cs = s.toCharArray();
		if (cs.length == 0) return s;
		//最常回長子串得長段
		int maxLen = 1;
		//最長回文子串的開始索引
		int begin = 0;
		boolean[][] dp = new boolean[cs.length][cs.length];
		//從下到上(i 由大到小)(j從i開始)
		for (int i = cs.length; i >= 0; i--) {
			for (int j = i; j < cs.length; j++) {
				//cs[i,j]的長度
				int len = j - i + 1;
				dp[i][j] = (cs[i] == cs[j])
						&& (len <= 2 || dp[i + 1][j - 1]);
				if (dp[i][j] && len > maxLen) {
					//說明cs[i,j]是回文字串
					maxLen = len;
					begin = i;

				}
			}
		}
		return new String(cs, begin, maxLen);
	}


	/**
	 * 擴展中心
	 *
	 * @param s
	 * @return
	 */
	public String longestPalindrome2(String s) {
		if (s == null) return null;
		char[] cs = s.toCharArray();
		if (cs.length <= 1) return s;
		//最常回長子串得長段
		int maxLen = 1;
		//最長回文子串的開始索引
		int begin = 0;
		for (int i = cs.length - 2; i >= 1; i--) {
			int len1 = palindromeLength(cs, i - 1, i + 1);
			int len2 = palindromeLength(cs, i, i + 1);
			//先互相比較後再跟maxLen比較
			len1 = Math.max(len1, len2);
			if (len1 > maxLen) {
				maxLen = len1;
				//有公式解
				begin = i - ((maxLen - 1) >> 1);
			}
		}
		//處理0好字符的右邊奸細的
		if (cs[0] == cs[1] && maxLen < 2) {
			//cs[0,1[就是最長的回文子串
			begin = 0;
			maxLen = 2;
		}
		return new String(cs, begin, maxLen);
	}

	/**
	 * 從l開始向左,從r開始向右掃描,獲得的最長回文子串的長度
	 *
	 * @param cs
	 * @param l
	 * @param r
	 * @return
	 */
	private int palindromeLength(char[] cs, int l, int r) {
		while (l >= 0 && r < cs.length && cs[l] == cs[r]) {
			l--;
			r++;
		}
		return r - l - 1;
	}

	/**
	 * 擴展中心2優化
	 *
	 * @param s
	 * @return
	 */
	public String longestPalindrome3(String s) {
		if (s == null) return null;
		char[] cs = s.toCharArray();
		if (cs.length <= 1) return s;
		//最常回長子串得長段
		int maxLen = 1;
		//最長回文子串的開始索引
		int begin = 0;
		int i = 0;
		while (i < cs.length) {
			int l = i - 1;
//			int oldi = i;
			int r = i;
			//找到右邊第一個不等於cs[i]的r
			while (++r < cs.length && cs[r] == cs[i]) ;
			//r會成為新的i
			i = r;
			//從l向左,從r向右擴展
//			int l = oldi - 1;
			while (l >= 0 && r < cs.length && cs[l] == cs[r]) {
				l--;
				r++;
			}
			//擴展結束後,cs[l + 1,r)就是剛才找到的最大回文子串
			//++l後,l就是剛才找到的最大回文子串的開始索引
			int len = r - ++l;
			if (len > maxLen) {
				maxLen = len;
				begin = l;
			}
		}
		return new String(cs, begin, maxLen);
	}

	/**
	 * manacher算法
	 *
	 * @param s
	 * @return
	 */
	public String longestPalindrome4(String s) {
		if (s == null) return null;
		char[] cs = s.toCharArray();
		if (cs.length <= 1) return s;
		//預處理
		char[] pp= preprocess(cs);
		int[] m = new int[pp.length];
		int c = 1, r = 1,lastIdx = m.length - 2;
		int maxLen = 0,idx = 0;
		for (int i=2;i < lastIdx; i++){
			if (r > i){
				int li = (c << 1) - i;
				if (i + m[li] <= r){
					m[i] = m[li];
				}else{
					m[i] = r - i;
				}
			}
			//以i為中心,向左右擴展
			while (pp[i + m[i] + 1] == pp[i - m[i] - 1]){
				m[i]++;
			}
			//更新c,r
			if (i + m[i] > r){
				c = i;
				r = i + m[i];
			}
			//找出最大回文子串
			if (m[i] > maxLen){
				maxLen = m[i];
				idx = i;
			}
		}
		int begin = (idx - maxLen) >> 1;
		return new String(cs,begin,begin+maxLen);

	}

	private char[] preprocess(char[] oldCs) {
		char[]cs = new char[(oldCs.length << 1) + 3];
		cs[0] = '^';
		cs[1] = '#';
		cs[cs.length - 1] = '$';
		for (int i = 0; i < oldCs.length; i++) {
			int idx = (i + 1) << 1;
			cs[idx] = oldCs[i];
			cs[idx + 1] = '#';
		}
		return cs;
	}
}
