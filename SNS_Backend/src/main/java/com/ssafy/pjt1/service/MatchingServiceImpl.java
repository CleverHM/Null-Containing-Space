package com.ssafy.pjt1.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
	UserDao userdao;
	
	@Autowired
	UserService userservice;
	
	public class U{
		int userid;
		boolean prefer;
		int percent;
		
		public U() {}
		
		public U(int userid, boolean prefer, int percent) {
			this.userid = userid;
			this.prefer = prefer;
			this.percent = percent;
		}

		public int getUserid() {
			return userid;
		}

		public void setUserid(int userid) {
			this.userid = userid;
		}

		public boolean isPrefer() {
			return prefer;
		}

		public void setPrefer(boolean prefer) {
			this.prefer = prefer;
		}

		public int getPercent() {
			return percent;
		}

		public void setPercent(int percent) {
			this.percent = percent;
		}
	}
	
	
	static Map<String, List<User> > ablist;
	static Map<Integer, Integer> priority;
	static List<String> lan;
	// 최종 선별된 유저들
	static List<U> matching_user;

	
	public List<U> match(int preferProject, List<String> preferTech){
		matching_user = new ArrayList<>();
		ablist = new HashMap<>();
		priority = new HashMap<>();
		lan = new ArrayList<>();
		
		// 모든 유저
		List<User> tusers = userdao.findAll();
		// 선호하지 않는 사람
		List<User> users = new ArrayList<>();
		// 선호하는 사람
		List<User> users2 = new ArrayList<>();
		
		
		// 매칭시스템에 등록이 되었는지 아닌지 분류, 특정한 프로젝트를 선호하는 사람과 선호하지 않는 사람 분류(1차)
		for(User u : tusers) 
			if(u.isMatchok() == true) {
				if(preferProject != u.getPreferProject()) users.add(u); 
				else users2.add(u);
			}
		
		if(users2.size() < 5) {
			for(User u : users2) matching_user.add(new U(u.getUid(), true, 0));
			
		}// 선호하는 사람들 먼저 선별
		else pick1(users2);

		if(users.size() < 5) {
			for(User u : users) matching_user.add(new U(u.getUid(), false, cal(u.getUid(), preferTech)));
		}// 각 언어를 hash key값으로 해서 사용자들을 각 언어의 수준에따라 정렬 (2차)
		else { 
			users = pick2(users);

			// 언어들 중에서 더 잘하는 사람을 선별(3차)
			pick3(preferTech);

			// 비슷한 사람들이 있을 경우 매번 다른 사람들이 선별(4차)
			pick4(preferTech);
		}
		
		return matching_user; 
	}
	
	public int pcal(int x) {
		if(x==1) return 99;
		else if(x==2) return 50;
		else return  0;
	}
	
	public int cal(int uid, List<String> preferTech) {
		Optional<User> user = userservice.findthree(uid);
		Ability ab = user.get().getAbility();
		
		
		int n = 0, percent = 0;
		
		for(String s : preferTech) {
			n++;
			
			if(s.equals("cpp")) percent += pcal(ab.getBack_cpp());
			else if(s.equals("java")) percent += pcal(ab.getBack_java());
			else if(s.equals("python")) percent += pcal(ab.getBack_python());
			else if(s.equals("php")) percent +=pcal(ab.getBack_php());
			else if(s.equals("html")) percent +=pcal(ab.getFront_html());
			else if(s.equals("css")) percent +=pcal(ab.getFront_css());
			else if(s.equals("javascript")) percent +=pcal(ab.getFront_javascript());
			else if(s.equals("sql")) percent +=pcal(ab.getDb_sql());
			else if(s.equals("nosql")) percent +=pcal(ab.getDb_nosql());
			else if(s.equals("spring")) percent +=pcal(ab.getFrame_spring());
			else if(s.equals("django")) percent +=pcal(ab.getFrame_django());
			else if(s.equals("bootstrap")) percent +=pcal(ab.getFrame_bootstrap());
			else if(s.equals("vue")) percent +=pcal(ab.getFrame_vue());
			else if(s.equals("react")) percent +=pcal(ab.getFrame_react());
			else if(s.equals("algo")) percent +=pcal(ab.getAlgo());
		}
		
		percent /= n;
		
		return percent;
	}
	
	public void pick4(List<String> preferTech) {
		List<Integer> pricnt[] = new ArrayList[lan.size()*3 + 1];
		for(int i=0;i<lan.size()*3+1;i++) pricnt[i] = new ArrayList<Integer>();
		
		for(Integer key : priority.keySet()) pricnt[priority.get(key)].add(key);
		
		int total = 5;
		for(int i=lan.size(); i>=0; i--) {
			int n = pricnt[i].size();
			
			if(n <= total) {
				for(Integer k : pricnt[i]) matching_user.add(new U(k, false, cal(k, preferTech)));
				
				total -= n;
			}
			else {
				int a[] = new int[total];
				
				for(int j=0; j<total; j++) {
					a[j] = (int)(Math.random()*n);
					
					for(int k=0; k<j; k++) 
						if(a[j] == a[k]) j--;
					
				}
				for(int j=0; j<total; j++) matching_user.add(new U(pricnt[i].get(a[j]), false, cal(pricnt[i].get(a[j]), preferTech)));
			
				total = 0;
			}
		}
	}
	
	public void pick3(List<String> preferTech) {
		// 사용자가 프로젝트에 사용할 주요 언어들을 lan리스트에 담음(팀원을 뽑는데 사용될 데이터)
		for(String s : preferTech) lan.add(s);
		
		String str;
		int x;
		for(int i=0; i<lan.size(); i++) {
			for (int j = 0; j < 3; j++) {
				str = lan.get(i);
				x = ablist.get(str).get(j).getUid();

				if (priority.get(x) == null) priority.put(x, 1);
				else priority.put(x, priority.get(x) + 1);
			}
		}
			
	}
	

	public List<User> pick2(List<User> users) {
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
		
		return users;
	}
	
	public void  pick1(List<User> users2) {
		int total = 5;
		int n = users2.size();
		
		int a[] = new int[total];
		
		for(int i=0; i<total; i++) {
			a[i] = (int)(Math.random()*n);
			
			for(int j=0; j<i; j++) 
				if(a[i] == a[j]) i--;
			
		}
		for(int i=0; i<total; i++) matching_user.add(new U(users2.get(a[i]).getUid(), true, 0));
		
	}
}
