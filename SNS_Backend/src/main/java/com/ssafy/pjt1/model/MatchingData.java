package com.ssafy.pjt1.model;

import java.util.List;

public class MatchingData {
	private boolean leader;
	private List<MatchingMemberData> prefermember;
	private List<MatchingMemberData> noprefermember;
	
	public MatchingData(boolean leader, List<MatchingMemberData> prefermember, List<MatchingMemberData> noprefermember) {
		this.leader = leader;
		this.prefermember = prefermember;
		this.noprefermember = noprefermember;
	}

	public boolean isLeader() {
		return leader;
	}

	public void setLeader(boolean leader) {
		this.leader = leader;
	}

	public List<MatchingMemberData> getPrefermember() {
		return prefermember;
	}

	public void setPrefermember(List<MatchingMemberData> prefermember) {
		this.prefermember = prefermember;
	}

	public List<MatchingMemberData> getNoprefermember() {
		return noprefermember;
	}

	public void setNoprefermember(List<MatchingMemberData> noprefermember) {
		this.noprefermember = noprefermember;
	}
	
}
