package week12;

import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.sql.*;

public class AppletTest extends Applet implements ActionListener {
	Choice menu;
	Choice search; // ���� �ڽ� ����
	Label label; // �� ����
	TextField textField; // ���� �Է¹��� �ؽ�Ʈ �ʵ� ����
	TextArea Area; // ��� ���� ����� �ؽ�Ʈ������ ����
	Button button; // �˻���ư ����

	private String url = "jdbc:inetdae7://210.115.229.77:2433";
	private String user = "20165164";
	private String pass = "gusgks12#$";

	public void init() {
		try {
			Class.forName("com.inet.tds.TdsDriver");
		} catch (ClassNotFoundException e) {
			System.out.println("Class Loading Failed");
		}
	}

	public void start() {
		setLayout(new FlowLayout()); // ���̾ƿ� ��ġ��
		label = new Label("����");
		add(label);

		menu = new Choice();
		menu.add("����");
		menu.add("�˻�");
		menu.add("����");
		menu.add("����");
		add(menu);

		search = new Choice();
		search.add("��ü");
		search.add("�̸�");
		search.add("����");
		search.add("����");
		add(search);
		textField = new TextField(10);
		add(textField);
		button = new Button("execute");
		add(button);
		Area = new TextArea(10, 50);
		add(Area);
		button.addActionListener(this);
	}

	public void actionPerformed(ActionEvent ae) {
		Connection con = null;
		Statement stmt = null;

		String st = ae.getActionCommand(); // �˻���ư�� string ���� ���� ����
		String menuitem = menu.getSelectedItem();
		String item = search.getSelectedItem();
		// ���ùڽ����� ���õ� ���� ���� ����

		if (st.equals("execute")) {
			String n = textField.getText();
			if (menuitem.equals("�˻�")) {
				if (item.equals("��ü")) { // ���ùڽ����� ����ü�� �� ���� ���� ���
					TotalgetDBSearch(); // ��ü �˻� �޼ҵ带 ȣ��
				} else if (item.equals("�̸�")) {
					NamegetDBSearch(n);
				} else if (item.equals("����")) {
					AgegetDBSearch(n);
				} else if (item.equals("����")) {
					SexgetDBSearch(n);
				}
			} else if (menuitem.equals("����")) {
				String[] str = n.split(" ");
				Insert(str[0], Integer.parseInt(str[1]), str[2]);
			} else if (menuitem.equals("����")) {
				customer_Delete(n);
			} else if (menuitem.equals("����")) {
				Update();
			}

		}
	}

	private void TotalgetDBSearch() {
		Connection con = null;
		Statement stmt = null;

		try {
			con = DriverManager.getConnection(url, user, pass);
			con.setCatalog("20165164");
			stmt = con.createStatement();

			ResultSet result = stmt.executeQuery("SELECT * FROM customer");

			// ������� �Ʒ� �� ��° ������ �˻� ��ư�� Ŭ���� �� ���� ��� â�� ���ο� �˻�
			// ����� �����ֱ� ���Ͽ� ������ �˻��� ����� ����� ���� �۾��̴�.

			String count = Area.getText();
			int c = count.length();
			Area.replaceText(" ", 0, c);

			while (result.next()) {
				String Name = result.getString(1);
				String age = Integer.toString(result.getInt(2));
				String sex = result.getString(3);

				String value = "�̸� : " + Name + " ���� : " + age + " ���� : " + sex + "\n";
				int index = Area.getText().length();
				Area.insertText(value, index); // ���â�� ����� �߰�
			}
			con.close();
			stmt.close();
		} catch (Exception ee) {
			System.out.println(ee);
		}
	} // try-end

	private void NamegetDBSearch(String n) {
		Connection con = null;
		Statement stmt = null;

		try {
			con = DriverManager.getConnection(url, user, pass);
			con.setCatalog("20165164");
			stmt = con.createStatement();

			ResultSet result = stmt.executeQuery("SELECT * FROM customer WHERE name ='" + n + "'");

			// ������� �Ʒ� �� ��° ������ �˻� ��ư�� Ŭ���� �� ���� ��� â�� ���ο� �˻�
			// ����� �����ֱ� ���Ͽ� ������ �˻��� ����� ����� ���� �۾��̴�.

			String count = Area.getText();
			int c = count.length();
			Area.replaceText(" ", 0, c);

			while (result.next()) {
				String Name = result.getString(1);
				String age = Integer.toString(result.getInt(2));
				String sex = result.getString(3);

				String value = "�̸� : " + Name + " ���� : " + age + " ���� : " + sex + "\n";
				int index = Area.getText().length();
				Area.insertText(value, index); // ���â�� ����� �߰�
			}
			con.close();
			stmt.close();
		} catch (Exception ee) {
			System.out.println(ee);
		}
	}

	private void AgegetDBSearch(String n) {
		Connection con = null;
		Statement stmt = null;

		try {
			con = DriverManager.getConnection(url, user, pass);
			con.setCatalog("20165164");
			stmt = con.createStatement();

			ResultSet result = stmt.executeQuery("SELECT * FROM customer WHERE age =" + n);

			// ������� �Ʒ� �� ��° ������ �˻� ��ư�� Ŭ���� �� ���� ��� â�� ���ο� �˻�
			// ����� �����ֱ� ���Ͽ� ������ �˻��� ����� ����� ���� �۾��̴�.

			String count = Area.getText();
			int c = count.length();
			Area.replaceText(" ", 0, c);

			while (result.next()) {
				String Name = result.getString(1);
				String age = Integer.toString(result.getInt(2));
				String sex = result.getString(3);

				String value = "�̸� : " + Name + " ���� : " + age + " ���� : " + sex + "\n";
				int index = Area.getText().length();
				Area.insertText(value, index); // ���â�� ����� �߰�
			}
			con.close();
			stmt.close();
		} catch (Exception ee) {
			System.out.println(ee);
		}
	}

	private void SexgetDBSearch(String n) {
		Connection con = null;
		Statement stmt = null;

		try {
			con = DriverManager.getConnection(url, user, pass);
			con.setCatalog("20165164");
			stmt = con.createStatement();

			ResultSet result = stmt.executeQuery("SELECT * FROM customer WHERE sex ='" + n + "'");

			// ������� �Ʒ� �� ��° ������ �˻� ��ư�� Ŭ���� �� ���� ��� â�� ���ο� �˻�
			// ����� �����ֱ� ���Ͽ� ������ �˻��� ����� ����� ���� �۾��̴�.

			String count = Area.getText();
			int c = count.length();
			Area.replaceText(" ", 0, c);

			while (result.next()) {
				String Name = result.getString(1);
				String age = Integer.toString(result.getInt(2));
				String sex = result.getString(3);

				String value = "�̸� : " + Name + " ���� : " + age + " ���� : " + sex + "\n";
				int index = Area.getText().length();
				Area.insertText(value, index); // ���â�� ����� �߰�
			}
			con.close();
			stmt.close();
		} catch (Exception ee) {
			System.out.println(ee);
		}
	}

	private void Insert(String name, int age, String sex) {
		try {
			Connection con;
			Statement stmt;
			// Connection ��ü�� ����Ͽ� DB�� ������ ����
			con = DriverManager.getConnection(url, user, pass);
			con.setCatalog("20165164");
			// ���ǹ��� �ۼ��ϱ� ���Ͽ� Statement ��ü�� ����
			stmt = con.createStatement();

			// ������ ���̺� ���� ����
			stmt.executeUpdate("INSERT INTO customer VALUES(' " + name + " ', ' " + age + " ','" + sex + "')");

			String count = Area.getText();
			int c = count.length();
			Area.replaceText(" ", 0, c);

			Area.insertText("Insert Complete", 0);
			// Statement,Connection ��ü�� ����
			stmt.close();
			con.close();
		} catch (SQLException se) {
			System.err.println(se.getMessage());
		}
	}

	private void customer_Delete(String r) {

		try {
			Connection con;
			Statement stmt;
			con = DriverManager.getConnection(url, user, pass);
			con.setCatalog("20165164");
			// ����� DB���� ���ǹ��� �ۼ��ϱ� ���Ͽ� Statement ��ü�� ����
			stmt = con.createStatement();
			stmt.executeUpdate("delete from customer where name = ' " + r + " ' ");

			String count = Area.getText();
			int c = count.length();
			Area.replaceText(" ", 0, c);

			Area.insertText("Delete Complete", 0);
			stmt.close();
			con.close();
		} catch (SQLException se) {
			System.err.println(se.getMessage());
		}
	}

	private void Update() {
		try {
			Connection con;
			Statement stmt;
			con = DriverManager.getConnection(url, user, pass);
			con.setCatalog("20165164");
			
			stmt = con.createStatement();
			stmt.executeUpdate("update customer set age=age+1");

			String count = Area.getText();
			int c = count.length();
			Area.replaceText(" ", 0, c);

			Area.insertText("Update Complete", 0);
			stmt.close();
			con.close();
		} catch (SQLException se) {
			System.err.println(se.getMessage());
		}
	}
} // end
