package 二叉樹;

import java.util.PriorityQueue;
import java.util.Queue;

public class _295_Find_Median_from_Data_Stream {
	class MedianFinder {
		private PriorityQueue<Integer> small;

		private PriorityQueue<Integer> large;

		public MedianFinder() {
			small = new PriorityQueue<>();
			large = new PriorityQueue<>((a, b) ->
				b - a
			);
		}

		public void addNum(int num) {
			if (small.size() >= large.size()){
				small.offer(num);
				large.offer(small.poll());
			}else{
				large.offer(num);
				small.offer(large.poll());
			}
		}

		public double findMedian() {
			if (small.size() < large.size()){
				return large.peek();
			}else if (small.size() > large.size()){
				return small.peek();
			}
			return (large.peek() + small.peek()) / 2.0;
		}
	}
}
