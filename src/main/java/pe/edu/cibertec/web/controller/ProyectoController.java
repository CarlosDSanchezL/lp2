package pe.edu.cibertec.web.controller;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pe.edu.cibertec.web.model.Role;
import pe.edu.cibertec.web.model.User;
import pe.edu.cibertec.web.repository.IPersonRepository;
import pe.edu.cibertec.web.repository.IRoleRepository;
import pe.edu.cibertec.web.repository.IUserRepository;
import pe.edu.cibertec.web.service.UserService;

@Controller
public class ProyectoController {
	
	@Autowired
	private IRoleRepository repos;
	
	@Autowired
	private IPersonRepository reposPerson;
	
	@Autowired
	private IUserRepository userRepository;
	
	@Autowired
	private UserService userService;
	
	@GetMapping("/greeting")
	public String greeting (@RequestParam(name="name", required=false, defaultValue="World") String name, Model model) {
		model.addAttribute("name", name);
		return "greeting";
		
	}
	
	@GetMapping("/listar")
	public String listRole(Model model) {
		try {
			model.addAttribute("ltsRole", repos.findAll());
			model.addAttribute("ltsPerson", reposPerson.findAll());
			model.addAttribute("ltsUser", userRepository.findAll());
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		return "listado";
	}
	
	@GetMapping("/login")
	public String loginView(Model model) {
		System.out.println("Mostrando login");
		model.addAttribute("userLogin", new User());
		return "login";
	}
	
	@PostMapping("/login")
	public String login(@ModelAttribute User user, Model model) {
		System.out.println("Validando login");		
		String redirect = "login";
		User u = userService.validateUserByNameAndPassword(user.getName(), user.getPassword());
		if (u != null) {
			u.setLastlogin(new Date());
			System.out.println("Actualizando usuario");
			User updUser = userService.updateUserLogin(u);
			model.addAttribute("userLogin", updUser);
			redirect = "greeting";
		} else {
			model.addAttribute("errors", "Usuario o clave incorrectos");
			model.addAttribute("userLogin", new User());
		}
		
		return redirect;
	}

    @GetMapping
    public List<User> getAllUsers() {
        return userService.getAllUsers();
    }

    @PostMapping
    public User addUser(@RequestBody User user) {
        return userService.addUser(user);
    }

    @GetMapping("agregar")
    public User getUserById(@PathVariable Integer id) {
        return userService.getUserById(id);
    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id) {
        userService.deleteUser(id);
    }
}
	
	

