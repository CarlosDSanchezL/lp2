package pe.edu.cibertec.web.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pe.edu.cibertec.web.model.User;
import pe.edu.cibertec.web.repository.IUserRepository;

@Service
public class UserService {
	
	@Autowired
	private IUserRepository userRepository;
	
	public User validateUserByNameAndPassword(String name, String password) {
		User u = userRepository.findByNameAndPassword(name, password);
		return u;
	}
	
	public User updateUserLogin(User user) {
//		User u = userRepository.getReferenceById(user.getIduser());
//		u.setLastlogin(new Date());
//		return userRepository.save(u);
		return userRepository.save(user);
	}
	
	public User createUser (User user) {
		return userRepository.save(user);
	}
	
	


	    public List<User> getAllUsers() {
	        return userRepository.findAll();
	    }

	    public User addUser(User user) {
	        return userRepository.save(user);
	    }

	    public User getUserById(Integer id) {
	        return userRepository.findById(id).orElse(null);
	    }

	    public void deleteUser(Integer id) {
	        userRepository.deleteById(id);
	    }
	}

