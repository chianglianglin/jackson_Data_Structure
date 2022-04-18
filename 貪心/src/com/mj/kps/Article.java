package com.mj.kps;

public class Article {

	int value;
	int weight;
	double valueDensity;

	public Article(int value, int weight) {
		this.value = value;
		this.weight = weight;
		valueDensity = value* 1.0 / weight;
	}

	@Override
	public String toString() {
		return "Article{" +
				"value=" + value +
				", weight=" + weight +
				", valueDensity=" + valueDensity +
				'}';
	}
}
