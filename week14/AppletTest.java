package week14;

import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.sql.*;

public class AppletTest extends Applet implements ActionListener {
	Choice menu;
	Label label; // �� ����
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
		menu.add("�ǽ�1_Left");
		menu.add("�ǽ�1_Right");
		menu.add("�ǽ�2");
		menu.add("�ǽ�3");
		menu.add("�ǽ�4");
		add(menu);

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
		String item = menu.getSelectedItem();
		// ���ùڽ����� ���õ� ���� ���� ����

		if (st.equals("execute")) {
			if (item.equals("�ǽ�1_Left")) {
				ex1("Left");
			} else if (item.equals("�ǽ�1_Right")) {
				ex1("Right");
			} else if (item.equals("�ǽ�2")) {
				ex2();
			} else if (item.equals("�ǽ�3")) {

			} else if (item.equals("�ǽ�4")) {

			}

		}
	}

	public void ex1(String str) {
		Connection con = null;
		Statement stmt = null;

		try {
			con = DriverManager.getConnection(url, user, pass);
			con.setCatalog("20165164");
			stmt = con.createStatement();

			ResultSet result = null;
			if (str.equals("Left")) {
				result = stmt.executeQuery(
						"select a.course_id,b.course_id from course a Left join section b on a.course_id=b.course_id");
			} else if (str.equals("Right")) {
				result = stmt.executeQuery(
						"select a.course_id,b.course_id from course a Right join section b on a.course_id=b.course_id");
			}
			String count = Area.getText();
			int c = count.length();
			Area.replaceText(" ", 0, c);

			while (result.next()) {
				String course = result.getString(1);
				String section = result.getString(2);

				String value = "course.course_id  " + course + "\tsection.course_id:  " + section + "\n";
				int index = Area.getText().length();
				Area.insertText(value, index); // ���â�� ����� �߰�
			}
			con.close();
			stmt.close();
		} catch (

		Exception e) {
			System.out.println(e);
		}
	}

	public void ex2() {
		Connection con = null;
		Statement stmt = null;

		try {
			con = DriverManager.getConnection(url, user, pass);
			con.setCatalog("20165164");
			stmt = con.createStatement();

			ResultSet result1 = stmt.executeQuery("select ID,name from instructor");
			ResultSet result2 = stmt.executeQuery("select ID,count(ID) from teaches group by ID ");

			String count = Area.getText();
			int c = count.length();
			Area.replaceText(" ", 0, c);

			while (result1.next()) {
				String id = result1.getString(1);
				String name = result1.getString(2);
				int idcount = 0;

				while (result2.next()) {
					if (id == result2.getString(1)) {
						idcount=result2.getInt(2);
					}

				}

				String value = "id  " + id + "name:  " + name +"count: "+idcount+"\n";
				int index = Area.getText().length();
				Area.insertText(value, index); // ���â�� ����� �߰�
			}
			con.close();
			stmt.close();
		} catch (

		Exception e) {
			System.out.println(e);
		}
	}

	public void ex3() {
		Connection con = null;
		Statement stmt = null;

		try {
			con = DriverManager.getConnection(url, user, pass);
			con.setCatalog("20165164");
			stmt = con.createStatement();

			ResultSet result = stmt.executeQuery(
					"select a.course_id,b.course_id from course a Left join section b on a.course_id=b.course_id");

			String count = Area.getText();
			int c = count.length();
			Area.replaceText(" ", 0, c);

			while (result.next()) {
				String course = result.getString(1);
				String section = result.getString(2);

				String value = "course.course_id  " + course + "\tsection.course_id:  " + section + "\n";
				int index = Area.getText().length();
				Area.insertText(value, index); // ���â�� ����� �߰�
			}
			con.close();
			stmt.close();
		} catch (

		Exception e) {
			System.out.println(e);
		}
	}

	public void ex4() {
		Connection con = null;
		Statement stmt = null;

		try {
			con = DriverManager.getConnection(url, user, pass);
			con.setCatalog("20165164");
			stmt = con.createStatement();

			ResultSet result = stmt.executeQuery(
					"select a.course_id,b.course_id from course a Left join section b on a.course_id=b.course_id");

			String count = Area.getText();
			int c = count.length();
			Area.replaceText(" ", 0, c);

			while (result.next()) {
				String course = result.getString(1);
				String section = result.getString(2);

				String value = "course.course_id  " + course + "\tsection.course_id:  " + section + "\n";
				int index = Area.getText().length();
				Area.insertText(value, index); // ���â�� ����� �߰�
			}
			con.close();
			stmt.close();
		} catch (

		Exception e) {
			System.out.println(e);
		}
	}
}