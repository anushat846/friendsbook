package com.friendsbook.server.models;

public class DeletePostModel {
    int id;
    
    public DeletePostModel() {}

    public DeletePostModel(int id) {
        super();
        this.id = id;
    }

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}
}
