package com.cj;

public class Queens2 {

	public static void main(String[] args) {
		new Queens2().placeQueens(8);
	}

	/**
	 * 標記著某一列是否有皇后
	 */
	boolean[]cols;

	boolean[] leftTop;

	boolean[] rightTop;

	int ways;

	void placeQueens(int n) {
		if (n < 1) return;
		cols = new boolean[n];
		leftTop = new boolean[(n<<1) - 1];
		rightTop = new boolean[leftTop.length];
		place(0);
		System.out.println(n + "皇后一共有"+ ways + "種擺法");
	}


	/**
	 * 從第row行開始擺放皇后
	 *
	 * @param row
	 */
	void place(int row) {
		if (row == cols.length){
			ways++ ;
			return ;
		}

		for (int col = 0; col < cols.length; col++) {
			if (cols[col] == true)continue;
			int ltIndex = row - col + cols.length - 1;
			if (leftTop[ltIndex])continue;
			int rtIndex = row + col;
			if (rightTop[rtIndex])continue;

			cols[col] = true;
			leftTop[ltIndex] = true;
			rightTop[rtIndex] = true;
			place(row + 1);
			//回溯
			cols[col] = false;
			leftTop[ltIndex] = false;
			rightTop[rtIndex] = false;
		}
	}


	/**
	 * 查看row行col列是否可以擺
	 *
	 * @param row
	 * @param col
	 * @return
	 */
//	boolean isValid(int row, int col) {
//		for (int i = 0; i < row; i++) {
//			//直線
//			if (cols[i] == col){
//				System.out.println("["+row+"]["+ col +"]=false");
//
//				return false;
//			}
//			//斜線
//			if (row - i == Math.abs(col - cols[i])){
//				System.out.println("["+row+"]["+ col +"]=false");
//				return false;
//			}
//		}
//
//		System.out.println("["+row+"]["+ col +"]=true");
//		return true;
//	}
}
