package com.friendsbook.server;

import java.util.List;

import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import com.friendsbook.server.core.PostsResource;
import com.friendsbook.server.models.CreatePostModel;
import com.friendsbook.server.models.DatabaseConnection;
import com.friendsbook.server.models.DeletePostModel;
import com.friendsbook.server.models.PostsModel;
import com.friendsbook.server.models.UpdatePostModel;

public class PostsResourceTest {
	
	static PostsResource pr;
	int userId = 2;
	int messageId;

	@BeforeClass
	public static void setup() {
		pr = new PostsResource(new DatabaseConnection("localhost", 3306, "friendbook", "root", "admin"));
	}
	
	@Test(testName="create")
	public void createPost() {
		Boolean status = pr.createPost(new CreatePostModel("test message", userId));
		Assert.assertTrue(status, "Expecting a true");
	}
	
	@Test(testName="list",dependsOnMethods = {"createPost"})
	public void listPost() {
		List<PostsModel> posts = pr.listPosts(userId);
		Assert.assertEquals(1, posts.size());
		PostsModel post = posts.get(0);
		Assert.assertEquals("test message", post.getMessage());
		this.messageId = post.getId();
	}
	
	@Test(testName="update", dependsOnMethods = {"createPost", "listPost"})
	public void updatePost() {
		Boolean status = pr.updatePost(new UpdatePostModel(
				this.messageId, "test message new", userId, 0));
		Assert.assertTrue(status, "Expecting a true");
	}
	
	@Test(testName="delete", dependsOnMethods = {"createPost", "listPost", "updatePost"})
	public void deletePost() {
		Boolean status = pr.deletePost(new DeletePostModel(messageId));
		Assert.assertTrue(status, "Expecting a true");
	}
	
	@AfterClass
	public static void teardown() {
		
	}
	
}
