import java.util.ArrayList;
import java.util.List;

public class Practice2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 隨機生成 6個 1~49的數字，且不能重複，印出後排序再印一次

		List<Integer> randomList = new ArrayList<>();

		System.out.println("Before Sort :");
		while (true) {
			int random = (int) (49 * Math.random() + 1);

			if (randomList.contains(random)) {
				continue;
			} else {
				randomList.add(random);
			}

			if (randomList.size() == 6) {
				break;
			}
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
