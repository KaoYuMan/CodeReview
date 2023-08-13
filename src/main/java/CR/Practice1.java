package CR;

public class Practice1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for (int i = 1; i <= 9; i++) {
			for (int j = 1; j <= 9; j++) {
				
				int result = i * j;
				
				if(result < 10) {
					System.out.print(i + "*" + j + " =  " + result + "  ");
				} else {
					System.out.print(i + "*" + j + " = " + result + "  ");
				}
			}
			System.out.println();
		}
	}

}
