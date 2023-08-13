package CR;

import java.util.ArrayList;
import java.util.List;

public class Practice2 {

	public static void main(String[] args) {

		// �H���ͦ� 6�� 1~49���Ʀr�A�B���୫�ơA�L�X��ƧǦA�L�@��

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
