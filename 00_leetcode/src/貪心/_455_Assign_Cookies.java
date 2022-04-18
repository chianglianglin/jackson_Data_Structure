package 貪心;

import java.util.Arrays;

public class _455_Assign_Cookies {

	public int findContentChildren(int[] g, int[] s) {

		Arrays.sort(g);
		Arrays.sort(s);
		int count = 0;
		for(int i=0,j=0;i<g.length && j<s.length;i++,j++){
			while (j < s.length && s[j] < g[i]){
				j++;
			}
			if (j < s.length){
				count++;
			}
		}
		return count;
//		int[] a1 = new int[];
//
//
//		int[] cookies = new int[s.length];
//		int count=0;
//		for (int j = 0; j < g.length; j++) {
//			for (int i = 0; i < s.length; i++) {
//
//
//
//				if (s[i] >= g[j]  && cookies[i] == 0){
//					cookies[i] = -1;
//					count++;
//				}
//			}
//		}
//		return count;
	}
}
