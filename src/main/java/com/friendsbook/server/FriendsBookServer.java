package com.friendsbook.server;

import javax.rmi.CORBA.Stub;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.friendsbook.server.core.PostsResource;
import com.friendsbook.server.models.CreatePostModel;
import com.friendsbook.server.models.DeletePostModel;
import com.friendsbook.server.models.UpdatePostModel;

@RestController
public class FriendsBookServer {

	PostsResource resource = new PostsResource();

    @PutMapping("/posts/create")
	public ResponseEntity createPost(@RequestBody CreatePostModel post) {
		Boolean status = resource.createPost(post);
		if(status) {
			return ResponseEntity.accepted().body("Post has been successfully created");
		} 
		return ResponseEntity.badRequest().body("Failed to create post.");
	}

    @GetMapping("/posts/list")
	public ResponseEntity listPosts() {
		return ResponseEntity.ok().body(resource.listPosts());		
	}

    @PostMapping("/posts/update")
	public ResponseEntity updatePost(@RequestBody UpdatePostModel post) {
		Boolean status = resource.updatePost(post);
		if(status) {
			return ResponseEntity.ok().body("Post successfully updated");
		}
		return ResponseEntity.badRequest().body("Failed to update post.");
	}

    @DeleteMapping("/posts/delete")
	public ResponseEntity deletePost(@RequestBody DeletePostModel post) {
		Boolean status = resource.deletePost(post);
		if(status) {
			return ResponseEntity.ok().body("Post successfully deleted");
		}
		return ResponseEntity.badRequest().body("Failed to delete post.");
	}
}