package com.todo.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;
import java.util.Locale.Category;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String category, title, desc, due_date;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "< �� �� �߰� >\n"
				+ "�з�: ");
		category = sc.next();
		
		System.out.println("���� : ");
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.printf("������ �ߺ��� �� �����ϴ�.");
			return;
		}
		
		System.out.println("���� : ");
		desc = sc.nextLine();
		
		System.out.println("������(��/��/��): ");
		due_date = sc.next();
		
		TodoItem t = new TodoItem(category, title, desc, due_date);
		list.addItem(t);
		System.out.println("�� �� ��Ͽ� �߰��Ǿ����ϴ�.\n");
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "< �� �� ���� >\n"
				+ "������ �� ���� ��ȣ�� �Է��ϼ��� : ");
		int num = sc.nextInt();
		if(num > l.size()) {
			System.out.println("�������� �ʴ� ��ȣ�Դϴ�."); 
			return;
		}

		int i=1;
		for (TodoItem item : l.getList()) {
			if (num==i) {
				System.out.println(i + ". " + item.toString());
				l.deleteItem(item);
				break;
			}
			i++;
		}
		System.out.println("�� �� ��Ͽ��� �����Ǿ����ϴ�.\n");
	}

	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "< �� �� ���� >\n"
				+ "������ �� ���� ��ȣ�� �Է��ϼ��� : ");
		int num = sc.nextInt();

		if(num > l.size()) {
			System.out.println("�������� �ʴ� ��ȣ�Դϴ�."); 
			return;
		}
		
		System.out.println("�� ���� �� ī�װ��� �Է��ϼ��� : ");
		String new_category = sc.next().trim();
		
		System.out.println("�� ���� �� ������ �Է��ϼ��� : ");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("������ �ߺ��� �� �����ϴ�.");
			return;
		}
		
		System.out.println("���ο� ������ �Է��ϼ��� : ");
		String new_description = sc.nextLine().trim();
		
		System.out.println("�� ���� �� �������� �Է��ϼ���(��/��/��): ");
		String new_due_date = sc.next().trim();
		
		int i=1;
		for (TodoItem item : l.getList()) {
			if (num==i) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_category,new_title, new_description, new_due_date);
				l.addItem(t);
				System.out.println("�� ���� �����Ǿ����ϴ�.\n");
				break;
			}
			i++;
		}
	}

	public static void listAll(TodoList l) {
		int num = 1;
		System.out.println("<�� �� ���>");
		for (TodoItem item : l.getList()) {
			System.out.println(num + ". " + item.toString());
			num++;
		}
	}

	public static void loadList(TodoList l, String filename) {
		File file = new File(filename); // File��ü ���� 
		if(file.exists()){ 
			BufferedReader r;
			int i=0;
			try {
				r = new BufferedReader(new FileReader(file));
			 	String line = null; 
			 	try {
					while ((line = r.readLine()) != null){ 
						StringTokenizer st = new StringTokenizer(line, "##");
						String category = st.nextToken();
						String title = st.nextToken();
						String desc = st.nextToken();
						String due_date = st.nextToken();
						String current_date = st.nextToken();
						TodoItem t = new TodoItem(category, title, desc, due_date, current_date);
						l.addItem(t);
						i++;
					}
					r.close(); 
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			} catch (FileNotFoundException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println(i+"���� �� ���� ������");
		}
		else System.out.println("todolist.txt ������ �������� ����.");
		
	}
	
	public static void saveList(TodoList l, String filename) {
		File file = new File(filename); 
		FileWriter w;
		try {
			w = new FileWriter(file, false);
			for (TodoItem item : l.getList()) {
				w.write(item.toSaveString());
			}
			w.close();
			System.out.println("**��� ������ �����.**");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}