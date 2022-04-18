package com.mj;

import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;

public class CoinChange {

	public static void main(String[] args) {
		Integer[] faces = {25,50,5,1};
		Arrays.sort(faces,(Integer f1,Integer f2) ->f2 - f1);

		coinChange2(faces,41);
	}

	static void coinChange3(int[] faces,int money){
		Arrays.sort(faces);
		int coins = 0 , idx = faces.length - 1;
		while (idx >=0){
			while (money >= faces[idx]){
				money -= faces[idx];
				coins++;
			}
			idx--;
		}
	}

	static void coinChange(){
		int[] faces = {25,10,5,1};
		Arrays.sort(faces);
		int money = 41,coins = 0;
		for (int i = faces.length - 1; i >= 0; i--) {
			if (money < faces[i]){
				continue;
			}
			money -= faces[i];
			coins++;
			i = faces.length;
		}
	}

	static void coinChange2(Integer[] array,int money){
		int i = 0, coins = 0;
		while (i < array.length){
			if (money < array[i]){
				i++;
				continue;
			}
			System.out.println(array[i]);
			money -= array[i];
			coins++;
		}

		System.out.println(coins);
	}
}
