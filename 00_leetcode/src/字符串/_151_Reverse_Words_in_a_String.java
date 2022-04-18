package 字符串;

public class _151_Reverse_Words_in_a_String {

	public String reverseWords(String s) {
		if (s == null || s.length() == 0) return "";
		char[] schar = s.toCharArray();

		//1.格式化字符串 去多餘的空格 把字符串往前對齊
		int cur = 0;//當前存放字符的位置
		boolean space = true;//當前遍歷的字符是否為空格
		int len = 0; //需要紀錄字符串最終有效長度
		for (int i = 0; i < schar.length; i++) {
			if (schar[i] != ' '){
				//遍歷字符不為空
				schar[cur++] = schar[i];
				space = false;
			}else if (space == false){
				//當前遍歷的字符是空格但是前一個字符不是空格
				schar[cur++] = ' ';
				space = true;
			}
		}
		//使用space判斷cur是否是字符長度
		len = space ?(cur - 1):cur;
		if (len == -1)return "";
		//2.反轉字符串
		//先整個字符串反轉
		reverse(schar,0,len);
		//要反轉兩個空格之間的字符串
		int preSpaceIndex = -1;//記錄前一個空格的index
		for (int i = 0; i < len; i++) {
			if (schar[i] != ' ')continue;
			reverse(schar,preSpaceIndex + 1,i);
			preSpaceIndex = i;
		}
		reverse(schar,preSpaceIndex + 1,len);
		return new String(schar,0,len);
	}

	//反轉字符串
	private void reverse(char[] chars,int li, int ri){
		ri--;//[li,ri)
		while (li < ri){
			char tmp = chars[li];
			chars[li] = chars[ri];
			chars[ri] = tmp;
			li++;
			ri--;
		}
	}
}
