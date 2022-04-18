package 映射;

import java.lang.reflect.Array;
import java.util.Arrays;
import java.util.HashMap;

public class _01_Two_Sum {

	public static int[] twoSum(int[] nums, int target) {
//   這個方法不行喔
//        HashMap<Integer, Integer> map = new HashMap<>();
//        for (int i = 0; i < nums.length; i++) {
//            map.put(nums[i],i);
//        }
		//map 在[3,3]時候 會只剩下{{3,1}}
//        for (Integer key : map.keySet()){
//            int other = target - key;
		//所以這個一定不會成立  !map.get(key).equals(map.get(other))
//            if (map.containsKey(other) && !map.get(key).equals(map.get(other))){
//                return new int[]{map.get(key),map.get(other)};
//            }
//        }
//        return new int[]{-1,-1};
		//所以一定要用下面的方法
		int n = nums.length;
		HashMap<Integer, Integer> index = new HashMap<>();
		// 构造一个哈希表：元素映射到相应的索引
		for (int i = 0; i < n; i++)
			index.put(nums[i], i);//一樣會是index 在[3,3]時候 會只剩下{{3,1}}

		for (int i = 0; i < n; i++) {
			//但是永遠都是從第一個開始遍歷 所以一定會跟map中被覆蓋的參數不一樣的下標
			int other = target - nums[i];
			// 如果 other 存在且不是 nums[i] 本身  這個才會成立 index.get(other) != i
			if (index.containsKey(other) && index.get(other) != i)
				return new int[]{i, index.get(other)};
		}

		return new int[]{-1, -1};
	}

	public static void main(String[] args) {
		System.out.println(Arrays.toString(twoSum(new int[]{3, 3}, 6)));
	}
}
