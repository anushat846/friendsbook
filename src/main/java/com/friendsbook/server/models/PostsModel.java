package com.friendsbook.server.models;

public class PostsModel {
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	int id;
    String message;
    int userId;
    int likesCount;

    public PostsModel() {}
    public PostsModel(int id, String message, int userId, int likesCount) {
        super();
        this.id = id;
        this.message = message;
        this.userId = userId;
        this.likesCount = likesCount;
    }
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getLikesCount() {
		return likesCount;
	}
	public void setLikesCount(int likesCount) {
		this.likesCount = likesCount;
	}
    
}
