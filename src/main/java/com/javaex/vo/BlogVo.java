package com.javaex.vo;

public class BlogVo {
	
	
	//필드
	private String id;
	private String blogTitle;
	private String logoFile;
	
	
	//생성자
	public BlogVo() {
	}
	
	public BlogVo(String id, String blogTitle) {
		this.id = id;
		this.blogTitle = blogTitle;
	}

	public BlogVo(String id, String blogTitle, String logoFile) {
		this.id = id;
		this.blogTitle = blogTitle;
		this.logoFile = logoFile;
	}
	
	
	//gs
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getBlogTitle() {
		return blogTitle;
	}
	public void setBlogTitle(String blogTitle) {
		this.blogTitle = blogTitle;
	}
	public String getLogoFile() {
		return logoFile;
	}
	public void setLogoFile(String logoFile) {
		this.logoFile = logoFile;
	}
	
	
	
	
	//
	@Override
	public String toString() {
		return "BlogVo [id=" + id + ", blogTitle=" + blogTitle + ", logoFile=" + logoFile + "]";
	}
	
}
