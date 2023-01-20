package uce.edu.ec.devengamiento.models.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import uce.edu.ec.devengamiento.models.entity.Usuario;
import uce.edu.ec.devengamiento.models.repository.IUsuarioRepository;
import uce.edu.ec.devengamiento.models.service.IUsuarioService;

import java.util.List;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

    @Autowired
    private IUsuarioRepository repository;

    @Override
    public List<Usuario> findAll() {
        return (List<Usuario>) repository.findAll();
    }

    @Override
    public Usuario findById(Long id) {
        return repository.findById(id).orElse(new Usuario());
    }

    @Override
    public Usuario findUserByNickname(String username) {
        return repository.findUsuarioByUsername(username);
    }

    @Override
    public void save(Usuario usuario) {
        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        usuario.setPassword(passwordEncoder.encode(usuario.getPassword()));
        repository.save(usuario);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public void update(Long id, Usuario usuario) {
        if (repository.existsById(id)) {
            repository.save(usuario);
        }
    }
}
