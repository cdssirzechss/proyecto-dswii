package com.crud.servicios.implementacion;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.crud.model.usuario;
import com.crud.servicios.usuarioService;
import com.crud.repository.rolRepository;
import com.crud.repository.usuarioRepository;

@Service
public class usuarioServiceImple implements usuarioService, UserDetailsService{
	
	
    private BCryptPasswordEncoder passwordEncoder; 
	
	@Autowired
	private usuarioRepository usuRepo;
	
	@Autowired
	private rolRepository rolRepo;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		usuario usuario = usuRepo.findByEmail(username);
		
		if (usuario == null) {
			throw new UsernameNotFoundException("Usuario o password inválidos"); }
		
		List<GrantedAuthority> roles = new ArrayList<>();
        roles.add(new SimpleGrantedAuthority(usuario.getRol().getNombre()));
		return new User(usuario.getEmail(), usuario.getPassword() , roles);
	}

	@Override
	public List<usuario> listaporrol(String nombre) {
		return usuRepo.findByRolNombre(nombre);
	}

	@Override
	public usuario obtenerusuarioporid(Long id) {
		return usuRepo.findById(id).orElseThrow(() -> new RuntimeException("usuario no encontrado con ID: " + id));
	}

	@Override
	public void eliminar(Long id) {
		usuRepo.deleteById(id);
	}
	
	public usuario create(usuario usuario) {
        // Encriptar la contraseña antes de guardar
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        return usuRepo.save(usuario); // Guardar el usuario en la base de datos
    }

}
