package com.joaolucas.workshopmongo.resources;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joaolucas.workshopmongo.domain.Post;
import com.joaolucas.workshopmongo.services.PostService;

@RestController
@RequestMapping(value = "/posts")
public class PostResource {

	@Autowired
	private PostService service;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Post> findById(@PathVariable String id) {
	    Optional<Post> obj = service.findById(id);
	    if (obj.isEmpty()) {
	        return ResponseEntity.notFound().build();
	    } else {
	        return ResponseEntity.ok().body(obj.get());
	    }
	}


}
