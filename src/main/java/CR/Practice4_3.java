package CR;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Practice4_3 {

	private static final String driverName = "com.mysql.cj.jdbc.Driver";
	private static final String url = "jdbc:mysql://localhost:3306/foodmap?serverTimezone=Asia/Taipei";
	private static final String account = "root";
	private static final String password = "tim21615";

	public static void main(String[] args) throws ClassNotFoundException {

		Class.forName(driverName);

		System.out.println("DB connected!");

		Scanner scanner = new Scanner(System.in);
		System.out.println("�п�J���O:");
		String sqlType = scanner.nextLine();
		System.out.println("�п�J�\�U�W��:");
		String storeName = scanner.nextLine();
		System.out.println("�п�J�a�}:");
		String address = scanner.nextLine();

		

		switch (sqlType) {
		case "query":

			query(storeName, address);
			break;

		case "delete":

			delete(storeName, address);
			break;

		case "create":
			
			create(inputMap(scanner, storeName, address));
			break;

		case "update":
			
			update(inputMap(scanner, storeName, address));
			break;

		default:
			System.out.println("�A������O�F");
			break;

		}
		
		scanner.close();
	}
	
	
	public static Map<String, Object> inputMap(Scanner scanner, String storeName, String address) {
		
		
		System.out.println("�п�J�\�U����:");
		String storeType = scanner.nextLine();
		System.out.println("�п�J�̤p�ȳ��:");
		int minCustomerPrice = scanner.nextInt();
		System.out.println("�п�J�̰��ȳ��:");
		int maxCustomerPrice = scanner.nextInt();
		
		Map<String, Object> input = new HashMap<>();
		input.put("STORE_NAME", storeName);
		input.put("ADDRESS", address);
		input.put("STORE_TYPE", storeType);
		input.put("MIN_CUSTOMER_PRICE", minCustomerPrice);
		input.put("MAX_CUSTOMER_PRICE", maxCustomerPrice);
		
		return input;
	}

	
	public static void query(String storeName, String addr) {
		try (Connection conn = DriverManager.getConnection(url, account, password);
				PreparedStatement pstmt = conn
						.prepareStatement("select * from restaurant where STORE_NAME = ? and ADDRESS = ?");) {
			pstmt.setString(1, storeName);
			pstmt.setString(2, addr);

			try (ResultSet rs = pstmt.executeQuery();) {

				while (rs.next()) {
					System.out.println("�\�U�W: " + rs.getString("STORE_NAME"));
					System.out.println("�a�}: " + rs.getString("ADDRESS"));
					System.out.println("�\�U����: " + rs.getString("STORE_TYPE"));
					System.out.println("�̰��ȳ��: " + rs.getString("MAX_CUSTOMER_PRICE"));
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void create(Map<String, Object> data) {

		try (Connection conn = DriverManager.getConnection(url, account, password);
				PreparedStatement pstmt = conn.prepareStatement(
						"INSERT INTO restaurant (STORE_NAME, ADDRESS, STORE_TYPE, MIN_CUSTOMER_PRICE, MAX_CUSTOMER_PRICE) VALUES (?,?,?,?,?)");) {

			pstmt.setString(1, (String) (data.get("STORE_NAME")));
			pstmt.setString(2, (String) (data.get("ADDRESS")));
			pstmt.setString(3, (String) (data.get("STORE_TYPE")));
			pstmt.setInt(4, (Integer) (data.get("MIN_CUSTOMER_PRICE")));
			pstmt.setInt(5, (Integer) (data.get("MAX_CUSTOMER_PRICE")));

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void update(Map<String, Object> data) {

		try (Connection conn = DriverManager.getConnection(url, account, password);
				PreparedStatement pstmt = conn.prepareStatement("UPDATE restaurant\r\n"
						+ "	SET STORE_TYPE = ?, MIN_CUSTOMER_PRICE = ?, MAX_CUSTOMER_PRICE = ?\r\n"
						+ "			WHERE STORE_NAME = ? AND ADDRESS = ?;");) {

			pstmt.setString(1, (String) (data.get("STORE_TYPE")));
			pstmt.setInt(2, (Integer) (data.get("MIN_CUSTOMER_PRICE")));
			pstmt.setInt(3, (Integer) (data.get("MAX_CUSTOMER_PRICE")));
			pstmt.setString(4, (String) (data.get("STORE_NAME")));
			pstmt.setString(5, (String) (data.get("ADDRESS")));

			pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

	public static void delete(String storeName, String addr) {
		try (Connection conn = DriverManager.getConnection(url, account, password);
				PreparedStatement pstmt = conn
						.prepareStatement("DELETE FROM restaurant where STORE_NAME = ? AND ADDRESS =?");) {
			pstmt.setString(1, storeName);
			pstmt.setString(2, addr);

			pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
