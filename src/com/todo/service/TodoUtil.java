package com.todo.service;

import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "< �� �� �߰� >\n"
				+ "���� : ");
		
		title = sc.nextLine();
		if (list.isDuplicate(title)) {
			System.out.printf("������ �ߺ��� �� �����ϴ�.");
			return;
		}
		
		System.out.println("���� : ");
		desc = sc.next();
		
		TodoItem t = new TodoItem(title, desc);
		list.addItem(t);
		System.out.println("�� �� ��Ͽ� �߰��Ǿ����ϴ�.\n");
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "< �� �� ���� >\n"
				+ "������ �� ���� ������ �Է��ϼ��� : ");
		String title = sc.nextLine();

		
		for (TodoItem item : l.getList()) {
			if (title.equals(item.getTitle())) {
				l.deleteItem(item);
				break;
			}
		}
		
		System.out.println("�� �� ��Ͽ��� �����Ǿ����ϴ�.\n");
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "< �� �� ���� >\n"
				+ "������ �� ���� ������ �Է��ϼ��� : ");
		String title = sc.next().trim();
		if (!l.isDuplicate(title)) {
			System.out.println("������ �������� �ʽ��ϴ�");
			return;
		}

		System.out.println("�� ���� ���ο� ������ �Է��ϼ��� : ");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("������ �ߺ��� �� �����ϴ�.");
			return;
		}
		
		System.out.println("���ο� ������ �Է��ϼ��� : ");
		String new_description = sc.next().trim();
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description);
				l.addItem(t);
				System.out.println("�� ���� �����Ǿ����ϴ�.\n");
			}
		}

	}

	public static void listAll(TodoList l) {
		System.out.println("<�� �� ���>");
		for (TodoItem item : l.getList()) {
			System.out.println("����: " + item.getTitle() + "  ����:  " + item.getDesc());
		}
	}

	public static void loadList(TodoList l, String filename) {
		// TODO Auto-generated method stub
		
	}
	
	public static void saveList(TodoList l, String filename) {
		// TODO Auto-generated method stub
		
	}
}
