package com.friendsbook.server.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.friendsbook.server.models.CreatePostModel;
import com.friendsbook.server.models.DatabaseConnection;
import com.friendsbook.server.models.DeletePostModel;
import com.friendsbook.server.models.PostsModel;
import com.friendsbook.server.models.UpdatePostModel;

public class PostsResource {

    Connection db;

    public PostsResource(DatabaseConnection db) {
        try {
            this.db = DriverManager.getConnection(
            		String.format("jdbc:mysql://%s:%d/%s?serverTimezone=UTC", db.getHost(), db.getPort(), db.getDatabase()), 
            		db.getUsername(), 
            		db.getPassword()
            );
        }catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Boolean createPost(CreatePostModel postData) {
        // write post data to database
        try {
            String statement = String.format("INSERT into posts(`message`, `user`, `likes`) values(\"%s\", %d, %d);", postData.getMessage(), postData.getUserId(), 0);
            PreparedStatement ps = db.prepareStatement(statement); 
            int res = ps.executeUpdate();
            if (res > 0) {
                return true;
            }
        }catch(Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<PostsModel> listPosts() {
        // fetch posts information from database 
        PreparedStatement ps=null;
        ResultSet rs=null;
        List<PostsModel> results = new ArrayList<PostsModel>();
        try {
            String qry="select * from posts";
            ps=this.db.prepareStatement(qry);
            rs=ps.executeQuery();
            while(rs.next())
            {
                int id=rs.getInt("id");
                String msg=rs.getString("message");
                int userId=rs.getInt("user"); 
                int likes=rs.getInt("likes");
                // construct post model
                PostsModel post = new PostsModel();
                post.setId(id);
                post.setMessage(msg);
                post.setUserId(userId);
                post.setLikesCount(likes);
                // append it to results list
                results.add(post);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // return the data
        return results;
    }
    
    public List<PostsModel> listPosts(int userId) {
        // fetch posts information from database 
        
        PreparedStatement ps=null;
        ResultSet rs=null;
        List<PostsModel> results = new ArrayList<PostsModel>();
		try {
            String qry=String.format("select * from posts where user=%s", userId);
            ps=this.db.prepareStatement(qry);
            rs=ps.executeQuery();
            if(rs.next())
            {
                int id=rs.getInt("id");
                String msg=rs.getString("message");
                int likes=rs.getInt("likes");
                PostsModel post=new PostsModel();
                post.setId(id);
                post.setMessage(msg);
                post.setUserId(userId);
                post.setLikesCount(likes);
                results .add(post);
            }
            
        } catch (Exception e) {
        	e.printStackTrace();
            
        }
        return results;
    }

    public Boolean updatePost(UpdatePostModel postData) {
        // write code to update the post data in database
        try {
            String statement=String.format("UPDATE posts set message=\"%s\" where id=%d;",postData.getMessage(),postData.getId());
            PreparedStatement ps=this.db.prepareStatement(statement);
            int res=ps.executeUpdate();
            if(res>0){
                return true;
            }
    
        } catch (Exception e) {
          e.printStackTrace();
        }
        return false;
    }

    public Boolean deletePost(DeletePostModel postData){
        // write code to delete data from database
        try {
            String statement=String.format("DELETE from posts where id=%d", postData.getId());
            PreparedStatement ps=this.db.prepareStatement(statement);
            int res=ps.executeUpdate();
            if(res>0){
                return true;
            }
            
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }
    
    public Boolean updatePostLikes(UpdatePostModel postData) {
    	try {
    		// fetch the current likes count for the post
    		String statement = String.format("SELECT likes from posts WHERE id=%d", postData.getId());
    		PreparedStatement ps = this.db.prepareStatement(statement);
    		ResultSet result = ps.executeQuery();
    		if(!result.next()) {
    			return false;
    		}
    		int count = result.getInt("likes");
    		// increment the like count
    		count++;
    		// update the like counts in database
    		statement = String.format("UPDATE posts set likes=%d where id=%d", count, postData.getId());
    		int res = this.db.prepareStatement(statement).executeUpdate();
    		if(res == 0) {
    			return false;
    		}
    		return true;
    	} catch (Exception e) {
    		e.printStackTrace();
    	}
    	return false;
    }
}
