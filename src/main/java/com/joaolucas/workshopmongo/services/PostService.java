package com.joaolucas.workshopmongo.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joaolucas.workshopmongo.domain.Post;
import com.joaolucas.workshopmongo.repository.PostRepository;
import com.joaolucas.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class PostService {

	@Autowired
	private PostRepository repo;

	public Optional<Post> findById(String id) {
		Optional<Post> post = repo.findById(id);
		if (post.isEmpty()) {
			throw new ObjectNotFoundException("Objeto não encontrado");
		}
		return post;
	}

}
