package com.gpf.myprojectysdq.bean;

import java.io.Serializable;

public class LiuYan implements Serializable{
	
	private int id;
	private String topic_name;
	private String topic_content;
	private String topic_time;
	private String replay_content;
	private String replay_time;
	
	public LiuYan() {
		super();
	}
	public LiuYan(String topic_name, String topic_content,
			String topic_time, String replay_content, String replay_time) {
		super();
		this.topic_name = topic_name;
		this.topic_content = topic_content;
		this.topic_time = topic_time;
		this.replay_content = replay_content;
		this.replay_time = replay_time;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getTopic_name() {
		return topic_name;
	}
	public void setTopic_name(String topic_name) {
		this.topic_name = topic_name;
	}
	public String getTopic_content() {
		return topic_content;
	}
	public void setTopic_content(String topic_content) {
		this.topic_content = topic_content;
	}
	public String getTopic_time() {
		return topic_time;
	}
	public void setTopic_time(String topic_time) {
		this.topic_time = topic_time;
	}
	public String getReplay_content() {
		return replay_content;
	}
	public void setReplay_content(String replay_content) {
		this.replay_content = replay_content;
	}
	public String getReplay_time() {
		return replay_time;
	}
	public void setReplay_time(String replay_time) {
		this.replay_time = replay_time;
	}
	
	
}
