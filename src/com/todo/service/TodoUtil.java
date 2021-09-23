package com.todo.service;

import java.util.*;

import com.todo.dao.TodoItem;
import com.todo.dao.TodoList;

public class TodoUtil {
	
	public static void createItem(TodoList list) {
		
		String title, desc;
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "< 할 일 추가 >\n"
				+ "제목 : ");
		
		title = sc.nextLine();
		if (list.isDuplicate(title)) {
			System.out.printf("제목은 중복될 수 없습니다.");
			return;
		}
		
		System.out.println("내용 : ");
		desc = sc.next();
		
		TodoItem t = new TodoItem(title, desc);
		list.addItem(t);
		System.out.println("할 일 목록에 추가되었습니다.\n");
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "< 할 일 삭제 >\n"
				+ "삭제할 할 일의 제목을 입력하세요 : ");
		String title = sc.nextLine();

		
		for (TodoItem item : l.getList()) {
			if (title.equals(item.getTitle())) {
				l.deleteItem(item);
				break;
			}
		}
		
		System.out.println("할 일 목록에서 삭제되었습니다.\n");
	}


	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "< 할 일 수정 >\n"
				+ "수정할 할 일의 제목을 입력하세요 : ");
		String title = sc.next().trim();
		if (!l.isDuplicate(title)) {
			System.out.println("제목이 존재하지 않습니다");
			return;
		}

		System.out.println("할 일의 새로운 제목을 입력하세요 : ");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("제목은 중복될 수 없습니다.");
			return;
		}
		
		System.out.println("새로운 내용을 입력하세요 : ");
		String new_description = sc.next().trim();
		for (TodoItem item : l.getList()) {
			if (item.getTitle().equals(title)) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_title, new_description);
				l.addItem(t);
				System.out.println("할 일이 수정되었습니다.\n");
			}
		}

	}

	public static void listAll(TodoList l) {
		System.out.println("<할 일 목록>");
		for (TodoItem item : l.getList()) {
			System.out.println("제목: " + item.getTitle() + "  내용:  " + item.getDesc());
		}
	}

	public static void loadList(TodoList l, String filename) {
		// TODO Auto-generated method stub
		
	}
	
	public static void saveList(TodoList l, String filename) {
		// TODO Auto-generated method stub
		
	}
}
