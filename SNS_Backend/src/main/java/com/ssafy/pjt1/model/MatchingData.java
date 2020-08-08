package com.ssafy.pjt1.model;

import java.util.List;

public class MatchingData {
	private boolean leader;
	private List<MatchingMemberData> member;
	
	public MatchingData(boolean leader, List<MatchingMemberData> member) {
		super();
		this.leader = leader;
		this.member = member;
	}

	public boolean isLeader() {
		return leader;
	}

	public void setLeader(boolean leader) {
		this.leader = leader;
	}

	public List<MatchingMemberData> getMember() {
		return member;
	}

	public void setMember(List<MatchingMemberData> member) {
		this.member = member;
	}
}
