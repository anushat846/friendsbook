package com.friendsbook.server.models;

public class UpdatePostModel {
    int id;
    String message;
    int userId;
	int likes;

    public UpdatePostModel() {}
    
    public UpdatePostModel(int id, String message, int userId ,int likes) {
        super();
        this.id = id;
        this.message = message;
        this.userId = userId;
        this.likes=likes;
    }
    
    public int getLikes() {
		return likes;
	}

	public void setLikes(int likes) {
		this.likes = likes;
	}

	public void setId(int id) {
		this.id = id;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public Object getId() {
    	return this.id;
    }

    public Object getMessage() {
        return this.message;
    }

    public Object getUserId() {
        return this.userId;
    } 
}
