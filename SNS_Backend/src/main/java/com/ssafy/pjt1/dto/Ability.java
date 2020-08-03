package com.ssafy.pjt1.dto;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;

@Entity
@Table(name = "Ability")
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Ability {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonIgnore
	private int aid;
	
	//백엔드
	private int back_cpp;
	private int back_java;
	private int back_python;
	private int back_php;
	
	//프론트엔드
	private int front_html;
	private int front_css;
	private int front_javascript;
	
	//데이터베이스
	private int db_sql;
	private int db_nosql;
	
	//프레임워크
	private int frame_spring;
	private int frame_django;
	private int frame_bootstrap;
	private int frame_vue;
	private int frame_react;
	
	//알고리즘
	private int algo;
	
	@OneToOne(mappedBy = "ability")
	private User user;

	public Ability(int aid, int back_cpp, int back_java, int back_python, int back_php, int front_html, int front_css,
			int front_javascript, int db_sql, int db_nosql, int frame_spring, int frame_django, int frame_bootstrap,
			int frame_vue, int frame_react, int algo) {
		this.aid = aid;
		this.back_cpp = back_cpp;
		this.back_java = back_java;
		this.back_python = back_python;
		this.back_php = back_php;
		this.front_html = front_html;
		this.front_css = front_css;
		this.front_javascript = front_javascript;
		this.db_sql = db_sql;
		this.db_nosql = db_nosql;
		this.frame_spring = frame_spring;
		this.frame_django = frame_django;
		this.frame_bootstrap = frame_bootstrap;
		this.frame_vue = frame_vue;
		this.frame_react = frame_react;
		this.algo = algo;
	}

	public Ability(int back_cpp, int back_java, int back_python, int back_php, int front_html, int front_css,
			int front_javascript, int db_sql, int db_nosql, int frame_spring, int frame_django, int frame_bootstrap,
			int frame_vue, int frame_react, int algo) {
		this.back_cpp = back_cpp;
		this.back_java = back_java;
		this.back_python = back_python;
		this.back_php = back_php;
		this.front_html = front_html;
		this.front_css = front_css;
		this.front_javascript = front_javascript;
		this.db_sql = db_sql;
		this.db_nosql = db_nosql;
		this.frame_spring = frame_spring;
		this.frame_django = frame_django;
		this.frame_bootstrap = frame_bootstrap;
		this.frame_vue = frame_vue;
		this.frame_react = frame_react;
		this.algo = algo;
	}



	public int getAlgo() {
		return algo;
	}

	public void setAlgo(int algo) {
		this.algo = algo;
	}

	public int getAid() {
		return aid;
	}

	public void setAid(int aid) {
		this.aid = aid;
	}

	public int getBack_cpp() {
		return back_cpp;
	}

	public void setBack_cpp(int back_cpp) {
		this.back_cpp = back_cpp;
	}

	public int getBack_java() {
		return back_java;
	}

	public void setBack_java(int back_java) {
		this.back_java = back_java;
	}

	public int getBack_python() {
		return back_python;
	}

	public void setBack_python(int back_python) {
		this.back_python = back_python;
	}

	public int getBack_php() {
		return back_php;
	}

	public void setBack_php(int back_php) {
		this.back_php = back_php;
	}

	public int getFront_html() {
		return front_html;
	}

	public void setFront_html(int front_html) {
		this.front_html = front_html;
	}

	public int getFront_css() {
		return front_css;
	}

	public void setFront_css(int front_css) {
		this.front_css = front_css;
	}

	public int getFront_javascript() {
		return front_javascript;
	}

	public void setFront_javascript(int front_javascript) {
		this.front_javascript = front_javascript;
	}

	public int getDb_sql() {
		return db_sql;
	}

	public void setDb_sql(int db_sql) {
		this.db_sql = db_sql;
	}

	public int getDb_nosql() {
		return db_nosql;
	}

	public void setDb_nosql(int db_nosql) {
		this.db_nosql = db_nosql;
	}

	public int getFrame_spring() {
		return frame_spring;
	}

	public void setFrame_spring(int frame_spring) {
		this.frame_spring = frame_spring;
	}

	public int getFrame_django() {
		return frame_django;
	}

	public void setFrame_django(int frame_django) {
		this.frame_django = frame_django;
	}

	public int getFrame_bootstrap() {
		return frame_bootstrap;
	}

	public void setFrame_bootstrap(int frame_bootstrap) {
		this.frame_bootstrap = frame_bootstrap;
	}

	public int getFrame_vue() {
		return frame_vue;
	}

	public void setFrame_vue(int frame_vue) {
		this.frame_vue = frame_vue;
	}

	public int getFrame_react() {
		return frame_react;
	}

	public void setFrame_react(int frame_react) {
		this.frame_react = frame_react;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
