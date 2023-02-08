package uce.edu.ec.devengamiento.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import uce.edu.ec.devengamiento.models.entity.Role;
import uce.edu.ec.devengamiento.models.entity.Usuario;
import uce.edu.ec.devengamiento.models.repository.IUsuarioRepository;

import java.util.ArrayList;
import java.util.List;

@Service("jpaUserDetailsService")
public class AuthUserDetailsService implements UserDetailsService {

    @Autowired
    private IUsuarioRepository repository;

    @Override
    @Transactional(readOnly=true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = repository.findUsuarioByUsername(username);
        if(usuario == null) {
            throw new UsernameNotFoundException("Username: " + username + " no existe en el sistema!");
        }
        List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
        for(Role role: usuario.getRoles()) {
            authorities.add(new SimpleGrantedAuthority(role.getNombreRol()));
        }
        if(authorities.isEmpty()) {
            throw new UsernameNotFoundException("Error en el Login: usuario '" + username + "' no tiene roles asignados!");
        }
        return new User(usuario.getUsername(), usuario.getPassword(), true, true, true, true, authorities);
    }


}
