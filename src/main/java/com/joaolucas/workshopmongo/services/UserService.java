package com.joaolucas.workshopmongo.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joaolucas.workshopmongo.domain.User;
import com.joaolucas.workshopmongo.dto.UserDTO;
import com.joaolucas.workshopmongo.repository.UserRepository;
import com.joaolucas.workshopmongo.services.exception.ObjectNotFoundException;

@Service
public class UserService {

	@Autowired
	private UserRepository repo;

	public List<User> findAll() {
		return repo.findAll();
	}

	public Optional<User> findById(String id) {
		Optional<User> user = repo.findById(id);
		if (user.isEmpty()) {
			throw new ObjectNotFoundException("Objeto não encontrado");
		}
		return user;
	}

	public User insert(User obj) {
		return repo.insert(obj);
	}

	public void delete(String id) {
		findById(id);
		repo.deleteById(id);
	}

	public User update(User obj) {
		Optional<User> optionalUser = repo.findById(obj.getId());
		if (optionalUser.isPresent()) {
			User newObj = optionalUser.get();
			updateData(newObj, obj);
			return repo.save(newObj);
		} else {
			throw new ObjectNotFoundException("Objeto não encontrado");
		}
	}

	private void updateData(User newObj, User obj) {
		newObj.setName(obj.getName());
		newObj.setEmail(obj.getEmail());

	}

	public User fromDTO(UserDTO objDto) {
		return new User(objDto.getId(), objDto.getName(), objDto.getEmail());
	}

}
