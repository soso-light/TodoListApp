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
				+ "< 할 일 추가 >\n"
				+ "분류: ");
		category = sc.next();
		
		System.out.println("제목 : ");
		title = sc.next();
		if (list.isDuplicate(title)) {
			System.out.printf("제목은 중복될 수 없습니다.");
			return;
		}
		
		System.out.println("내용 : ");
		desc = sc.nextLine();
		
		System.out.println("마감일(년/월/일): ");
		due_date = sc.next();
		
		TodoItem t = new TodoItem(category, title, desc, due_date);
		list.addItem(t);
		System.out.println("할 일 목록에 추가되었습니다.\n");
	}

	public static void deleteItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "< 할 일 삭제 >\n"
				+ "삭제할 할 일의 번호을 입력하세요 : ");
		int num = sc.nextInt();
		if(num > l.size()) {
			System.out.println("존재하지 않는 번호입니다."); 
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
		System.out.println("할 일 목록에서 삭제되었습니다.\n");
	}

	public static void updateItem(TodoList l) {
		
		Scanner sc = new Scanner(System.in);
		
		System.out.println("\n"
				+ "< 할 일 수정 >\n"
				+ "수정할 할 일의 번호를 입력하세요 : ");
		int num = sc.nextInt();

		if(num > l.size()) {
			System.out.println("존재하지 않는 번호입니다."); 
			return;
		}
		
		System.out.println("할 일의 새 카테고리를 입력하세요 : ");
		String new_category = sc.next().trim();
		
		System.out.println("할 일의 새 제목을 입력하세요 : ");
		String new_title = sc.next().trim();
		if (l.isDuplicate(new_title)) {
			System.out.println("제목은 중복될 수 없습니다.");
			return;
		}
		
		System.out.println("새로운 내용을 입력하세요 : ");
		String new_description = sc.nextLine().trim();
		
		System.out.println("할 일의 새 마감일을 입력하세요(년/월/일): ");
		String new_due_date = sc.next().trim();
		
		int i=1;
		for (TodoItem item : l.getList()) {
			if (num==i) {
				l.deleteItem(item);
				TodoItem t = new TodoItem(new_category,new_title, new_description, new_due_date);
				l.addItem(t);
				System.out.println("할 일이 수정되었습니다.\n");
				break;
			}
			i++;
		}
	}

	public static void listAll(TodoList l) {
		int num = 1;
		System.out.println("<할 일 목록>");
		for (TodoItem item : l.getList()) {
			System.out.println(num + ". " + item.toString());
			num++;
		}
	}

	public static void loadList(TodoList l, String filename) {
		File file = new File(filename); // File객체 생성 
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
			System.out.println(i+"개의 할 일이 존재함");
		}
		else System.out.println("todolist.txt 파일이 존재하지 않음.");
		
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
			System.out.println("**모든 데이터 저장됨.**");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}