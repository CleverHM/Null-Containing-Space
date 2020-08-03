package com.ssafy.pjt1.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.pjt1.dao.UserDao;
import com.ssafy.pjt1.dto.Ability;
import com.ssafy.pjt1.dto.User;

@Service
public class MatchingServiceImpl implements MatchingService{
	// 수준 - 상 : 1, 중 : 2, 하 : 3
	/* 종류 
	 * 백엔드 : cpp, java, python, php
	 * 프론트엔드 : html, css, javascript
	 * DB: sql, nosql
	 * 프레임워크 : spring, django, bootstrap, vue, react
	 * 알고리즘 : algo
	 *
	 * 1. back_cpp
	 * 2. back_java
	 * 3. back_pyhton
	 * 4. back_php
	 * 5. front_html
	 * 6. front_css
	 * 7. front_javascript
	 * 8. db_sql
	 * 9. db_nosql
	 * 10. frame_spring
	 * 11. frame_django
	 * 12. frame_bootstrap
	 * 13. frame_vue
	 * 14. frame_react
	 * 15. algo
	 */
	
	@Autowired
	static UserDao userdao;
	
	static Map<String, List<User> > ablist = new HashMap<>();
	static Map<Integer, Integer> priority = new HashMap<>();
	static List<String> lan = new ArrayList<>();
	
	//static List<User> users = userdao.findAll();	
	//임의 값
	static List<User> users = new ArrayList<>();
	
	
	public List<Integer> match(List<String> back, List<String> front, List<String> database, List<String> frame, String algo){
		List<Integer> matching_user_id = new ArrayList<>();
		
		// 테스트
		User u = null;
		Ability ab = null;
		testdata(u, ab);

		// 각 언어를 hash key값으로 해서 사용자들을 각 언어의 수준에따라 정렬
		init();

		// 사용자가 프로젝트에 사용할 주요 언어들을 lan리스트에 담음(팀원을 뽑는데 사용될 데이터)
		
		for(String s : back) lan.add(s);
		for(String s : front) lan.add(s);
		for(String s : database) lan.add(s);
		for(String s : frame) lan.add(s);
		lan.add(algo);
		
		pick();	
		
		// 
		
		List<Integer> pricnt[] = new ArrayList[lan.size()*3 + 1];
		for(int i=0;i<lan.size()*3+1;i++) pricnt[i] = new ArrayList<Integer>();
		
		for(Integer key : priority.keySet()) pricnt[priority.get(key)].add(key);
		
		
		int total = 5;
		for(int i=lan.size(); i>=0; i--) {
			int n = pricnt[i].size();
			
			if(n <= total) {
				for(Integer k : pricnt[i]) matching_user_id.add(k);
				
				total -= n;
			}
			else {
				int a[] = new int[total];
				
				for(int j=0; j<n; j++) {
					a[i] = (int)(Math.random()*n);
					
					for(int k=0; k<j; k++) 
						if(a[i] == a[j]) j--;
					
				}
				for(int j=0;j<total;j++) matching_user_id.add(pricnt[i].get(a[j]));
			}
		}
		
		return matching_user_id; 
	}
	
	public void pick() {
		String str;
		int x;
		for(int i=0;i<lan.size();i++) {
			for (int j = 0; j < 3; j++) {
				str = lan.get(i);
				x = ablist.get(str).get(j).getUid();

				if (priority.get(x) == null) priority.put(x, 1);
				else priority.put(x, priority.get(x) + 1);
			}
		}
			
	}
	

	public void init() {
		List<User> temp = new ArrayList<>();
		
		for(User u : users) temp.add(u);
		Collections.sort(temp, new Comparator<User>(){
			public int compare(User s1, User s2) {
				return s1.getAbility().getBack_cpp()-s2.getAbility().getBack_cpp();
			}});
		
		ablist.put("cpp", temp);
		
		temp = new ArrayList<>();
		for(User u : users) temp.add(u);
		Collections.sort(temp, new Comparator<User>(){
			public int compare(User s1, User s2) {
				return s1.getAbility().getBack_java()-s2.getAbility().getBack_java();
			}});
		ablist.put("java", temp);
		
		temp = new ArrayList<>();
		for(User u : users) temp.add(u);
		Collections.sort(temp, new Comparator<User>(){
			public int compare(User s1, User s2) {
				return s1.getAbility().getBack_python()-s2.getAbility().getBack_python();
			}});
		ablist.put("python", temp);
		
		temp = new ArrayList<>();
		for(User u : users) temp.add(u);
		Collections.sort(temp, new Comparator<User>(){
			public int compare(User s1, User s2) {
				return s1.getAbility().getBack_php()-s2.getAbility().getBack_php();
			}});
		ablist.put("php", temp);
		
		temp = new ArrayList<>();
		for(User u : users) temp.add(u);
		Collections.sort(temp, new Comparator<User>(){
			public int compare(User s1, User s2) {
				return s1.getAbility().getFront_html()-s2.getAbility().getFront_html();
			}});
		ablist.put("html", temp);
		
		temp = new ArrayList<>();
		for(User u : users) temp.add(u);
		Collections.sort(temp, new Comparator<User>(){
			public int compare(User s1, User s2) {
				return s1.getAbility().getFront_css()-s2.getAbility().getFront_css();
			}});
		ablist.put("css", temp);
		
		temp = new ArrayList<>();
		for(User u : users) temp.add(u);
		Collections.sort(temp, new Comparator<User>(){
			public int compare(User s1, User s2) {
				return s1.getAbility().getFront_javascript()-s2.getAbility().getFront_javascript();
			}});
		ablist.put("javascript", temp);
		
		temp = new ArrayList<>();
		for(User u : users) temp.add(u);
		Collections.sort(temp, new Comparator<User>(){
			public int compare(User s1, User s2) {
				return s1.getAbility().getDb_sql()-s2.getAbility().getDb_sql();
			}});
		ablist.put("sql", temp);
		
		temp = new ArrayList<>();
		for(User u : users) temp.add(u);
		Collections.sort(temp, new Comparator<User>(){
			public int compare(User s1, User s2) {
				return s1.getAbility().getDb_nosql()-s2.getAbility().getDb_nosql();
			}});
		ablist.put("nosql", temp);
		
		temp = new ArrayList<>();
		for(User u : users) temp.add(u);
		Collections.sort(temp, new Comparator<User>(){
			public int compare(User s1, User s2) {
				return s1.getAbility().getFrame_spring()-s2.getAbility().getFrame_spring();
			}});
		ablist.put("spring", temp);
		
		temp = new ArrayList<>();
		for(User u : users) temp.add(u);
		Collections.sort(temp, new Comparator<User>(){
			public int compare(User s1, User s2) {
				return s1.getAbility().getFrame_django()-s2.getAbility().getFrame_django();
			}});
		ablist.put("django", temp);
		
		temp = new ArrayList<>();
		for(User u : users) temp.add(u);
		Collections.sort(temp, new Comparator<User>(){
			public int compare(User s1, User s2) {
				return s1.getAbility().getFrame_bootstrap()-s2.getAbility().getFrame_bootstrap();
			}});
		ablist.put("bootstrap", temp);
		
		temp.clear();
		for(User u : users) temp.add(u);
		Collections.sort(temp, new Comparator<User>(){
			public int compare(User s1, User s2) {
				return s1.getAbility().getFrame_vue()-s2.getAbility().getFrame_vue();
			}});
		ablist.put("vue", temp);
		
		temp = new ArrayList<>();
		for(User u : users) temp.add(u);
		Collections.sort(temp, new Comparator<User>(){
			public int compare(User s1, User s2) {
				return s1.getAbility().getFrame_react()-s2.getAbility().getFrame_react();
			}});
		ablist.put("react", temp);
		
		temp = new ArrayList<>();
		for(User u : users) temp.add(u);
		Collections.sort(temp, new Comparator<User>(){
			public int compare(User s1, User s2) {
				return s1.getAbility().getAlgo()-s2.getAbility().getAlgo();
			}});
		ablist.put("algo", temp);
	}
	
	// 임의 값 생성
	public void testdata(User u, Ability ab) {
		u = new User(1, "a", "123", "a", "a", "a", 16, true);
		ab = new Ability(3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 3, 1);
		u.setAbility(ab);
		users.add(u);

		u = new User(2, "b", "123", "b", "b", "b", 13, false);
		ab = new Ability(1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 1, 2);
		u.setAbility(ab);
		users.add(u);

		u = new User(3, "c", "123", "c", "c", "c", 19, true);
		ab = new Ability(2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 2, 1, 3);
		u.setAbility(ab);
		users.add(u);

		u = new User(4, "d", "123", "d", "d", "d", 22, false);
		ab = new Ability(3, 2, 3, 2, 3, 2, 3, 2, 3, 2, 3, 2, 3, 2, 1);
		u.setAbility(ab);
		users.add(u);

		u = new User(5, "e", "123", "e", "e", "e", 21, true);
		ab = new Ability(2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 2);
		u.setAbility(ab);
		users.add(u);

		u = new User(6, "f", "123", "f", "f", "f", 25, false);
		ab = new Ability(2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 3);
		u.setAbility(ab);
		users.add(u);

		u = new User(7, "g", "123", "g", "g", "g", 28, true);
		ab = new Ability(2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 1, 2, 3, 4);
		u.setAbility(ab);
		users.add(u);
	}

}
