package CR;

import java.util.ArrayList;
import java.util.List;

public class Practice2 {

	public static void main(String[] args) {

		// 隨機生成 6個 1~49的數字，且不能重複，印出後排序再印一次

		List<Integer> randomList = new ArrayList<>();

		System.out.println("Before Sort :");
		while (randomList.size() < 6) {
			int random = (int) (49 * Math.random() + 1);

			if (randomList.contains(random)) {
				continue;
			}

			randomList.add(random);
		}

		for (int num : randomList) {
			System.out.print(num + "  ");
		}

		randomList.sort(null);
		System.out.println();
		System.out.println("After Sort :");
		
		for (int num : randomList) {
			System.out.print(num + "  ");
		}

	}

}
