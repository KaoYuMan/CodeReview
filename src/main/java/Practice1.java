
public class Practice1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		for (int i = 1; i <= 9; i++) {
			for (int j = 1; j <= 9; j++) {
				
				if(i * j < 10) {
					System.out.print(i + "*" + j + " =  " + i * j + "  ");
				} else {
					System.out.print(i + "*" + j + " = " + i * j + "  ");
				}
				
			}
			System.out.println();
			System.out.println();
		}
	}

}
