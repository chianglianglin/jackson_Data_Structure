package com.cj;

public class Queens1 {

	public static void main(String[] args) {
		new Queens1().placeQueens(4);
	}

	/**
	 * 數組索引是行號,數組元素是列號 cols[2] = 5
	 * 在第2行第5列有元素
	 */
	int[] cols;

	int ways;

	void placeQueens(int n) {
		if (n < 1) return;
		cols = new int[n];
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
			if (isValid(row, col)) {
				cols[row] = col;
				place(row+ 1);
			}
		}
	}


	/**
	 * 查看row行col列是否可以擺
	 *
	 * @param row
	 * @param col
	 * @return
	 */
	boolean isValid(int row, int col) {
		for (int i = 0; i < row; i++) {
			//直線
			if (cols[i] == col){
				System.out.println("["+row+"]["+ col +"]=false");

				return false;
			}
			//斜線
			if (row - i == Math.abs(col - cols[i])){
				System.out.println("["+row+"]["+ col +"]=false");
				return false;
			}
		}

		System.out.println("["+row+"]["+ col +"]=true");
		return true;
	}
}
