package com.comment.model;

public class CommentDto {
	private int reply_id;
	private int borad_id;
	private int depth;
	private int bundle_id;
	private String name;
	private String reply;	
	private String reply_time;
	
	public int getReply_id() {
		return reply_id;
	}
	public void setReply_id(int reply_id) {
		this.reply_id = reply_id;
	}
	public int getBorad_id() {
		return borad_id;
	}
	public void setBorad_id(int borad_id) {
		this.borad_id = borad_id;
	}
	public int getDepth() {
		return depth;
	}
	public void setDepth(int depth) {
		this.depth = depth;
	}
	public int getBundle_id() {
		return bundle_id;
	}
	public void setBundle_id(int bundle_id) {
		this.bundle_id = bundle_id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getReply() {
		return reply;
	}
	public void setReply(String reply) {
		this.reply = reply;
	}
	public String getReply_time() {
		return reply_time;
	}
	public void setReply_time(String reply_time) {
		this.reply_time = reply_time;
	}
	
}
