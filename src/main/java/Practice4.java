import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Practice4 {

	private static String driverName = "com.mysql.cj.jdbc.Driver";
	private static Connection conn;

	public static void main(String[] args) {

		try {
			Class.forName(driverName);
			conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/foodmap?serverTimezone=Asia/Taipei", "root",
					"0906");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("DB connected!");

		
		Scanner scanner = new Scanner(System.in);
		System.out.println("請輸入指令:");
		String sqlType = scanner.nextLine();
		System.out.println("請輸入餐廳名稱:");
		String storeName = scanner.nextLine();
		System.out.println("請輸入地址:");
		String address = scanner.nextLine();
		
		Map<String, Object> data = new HashMap<>();
		String storeType;
		int minCustomerPrice;
		int maxCustomerPrice;
		

		switch (sqlType) {
		case "query":

			query(storeName, address);
			break;

		case "delete":

			delete(storeName, address);
			break;
			
		case "create":
			System.out.println("請輸入餐廳類型:");
			storeType = scanner.nextLine();
			System.out.println("請輸入最小客單價:");
			minCustomerPrice = scanner.nextInt();
			System.out.println("請輸入最高客單價:");
			maxCustomerPrice = scanner.nextInt();
			
			data.put("STORE_NAME", storeName);
			data.put("ADDRESS", address);
			data.put("STORE_TYPE", storeType);
			data.put("MIN_CUSTOMER_PRICE", minCustomerPrice);
			data.put("MAX_CUSTOMER_PRICE", maxCustomerPrice);
			
			create(data);
			break;
			
		case "update":
			System.out.println("請輸入餐廳類型:");
			storeType = scanner.nextLine();
			System.out.println("請輸入最小客單價:");
			minCustomerPrice = scanner.nextInt();
			System.out.println("請輸入最高客單價:");
			maxCustomerPrice = scanner.nextInt();
			
			data.put("STORE_NAME", storeName);
			data.put("ADDRESS", address);
			data.put("STORE_TYPE", storeType);
			data.put("MIN_CUSTOMER_PRICE", minCustomerPrice);
			data.put("MAX_CUSTOMER_PRICE", maxCustomerPrice);
			
			update(data);
			break;
		}

	}
	
	

	public static void query(String storeName, String addr) {
		try {
			PreparedStatement pstmt = conn
					.prepareStatement("select * from restaurant where STORE_NAME = ? and ADDRESS = ?");
			pstmt.setString(1, storeName);
			pstmt.setString(2, addr);

			ResultSet rs = pstmt.executeQuery();

			while (rs.next()) {
				System.out.println("餐廳名: " + rs.getString("STORE_NAME"));
				System.out.println("地址: " + rs.getString("ADDRESS"));
				System.out.println("餐廳類型: " + rs.getString("STORE_TYPE"));
				System.out.println("最高客單價: " + rs.getString("MAX_CUSTOMER_PRICE"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void create(Map<String, Object> data) {

		try {
			PreparedStatement pstmt = conn.prepareStatement(
					"INSERT INTO restaurant (STORE_NAME, ADDRESS, STORE_TYPE, MIN_CUSTOMER_PRICE, MAX_CUSTOMER_PRICE) VALUES (?,?,?,?,?)");

			pstmt.setString(1, (String) (data.get("STORE_NAME")));
			pstmt.setString(2, (String) (data.get("ADDRESS")));
			pstmt.setString(3, (String) (data.get("STORE_TYPE")));
			pstmt.setInt(4, (Integer) (data.get("MIN_CUSTOMER_PRICE")));
			pstmt.setInt(5, (Integer) (data.get("MAX_CUSTOMER_PRICE")));
			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void update(Map<String, Object> data) {

		try {
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE restaurant\r\n" + "	SET STORE_TYPE = ?, MIN_CUSTOMER_PRICE = ?, MAX_CUSTOMER_PRICE = ?\r\n"
							+ "			WHERE STORE_NAME = ? AND ADDRESS = ?;");

			pstmt.setString(1, (String) (data.get("STORE_TYPE")));
			pstmt.setInt(2, (Integer) (data.get("MIN_CUSTOMER_PRICE")));
			pstmt.setInt(3, (Integer) (data.get("MAX_CUSTOMER_PRICE")));
			pstmt.setString(4, (String) (data.get("STORE_NAME")));
			pstmt.setString(5, (String) (data.get("ADDRESS")));

			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void delete(String storeName, String addr) {
		try {
			PreparedStatement pstmt = conn
					.prepareStatement("DELETE FROM restaurant where STORE_NAME = ? AND ADDRESS =?");
			pstmt.setString(1, storeName);
			pstmt.setString(2, addr);

			pstmt.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
