package com.ab.abwedding.data;

import java.util.List;

/**
 * @author ishikawatatsuya
 *
 */
public class Member {

	private String usrid;
	private String name;
	private String comment;
	private int answer;

	public String getUsrId() {
		return usrid;
	}
	public void setUsrId(String usrid) {
		this.usrid = usrid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public int getAnswer() {
		return answer;
	}
	public void setAnswer(int answer) {
		this.answer = answer;
	}
	public boolean isExist(List<Member> ml){
	
		return true;
	}
}
