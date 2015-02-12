package com.ab.abwedding.data;

/**
 * @author ishikawatatsuya
 *
 */
public class Member {

	private String name;
	private String rlname;
	private String comment;
	private int answer;

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getRealName() {
		return rlname;
	}
	public void setRealName(String rlname) {
		this.rlname = rlname;
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

}
