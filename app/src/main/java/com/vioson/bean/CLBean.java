package com.vioson.bean;

import cn.bmob.v3.BmobObject;

public class CLBean extends BmobObject {
	private String content_left;
	private String content_right;
	private String content_mid;
	private boolean isLove;
	
	public String getContent_left() {
		return content_left;
	}

	public void setContent_left(String content_left) {
		this.content_left = content_left;
	}

	public String getContent_right() {
		return content_right;
	}

	public void setContent_right(String content_right) {
		this.content_right = content_right;
	}

	public String getContent_mid() {
		return content_mid;
	}

	public void setContent_mid(String content_mid) {
		this.content_mid = content_mid;
	}

	public boolean isLove() {
		return isLove;
	}

	public void setLove(boolean isLove) {
		this.isLove = isLove;
	}

}
