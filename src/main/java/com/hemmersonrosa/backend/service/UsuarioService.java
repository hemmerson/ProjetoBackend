package com.hemmersonrosa.backend.service;

import com.hemmersonrosa.backend.dto.UsuarioDTO;
import com.hemmersonrosa.backend.entity.UsuarioEntity;
import com.hemmersonrosa.backend.repository.UsuarioRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepository repository;

    public List<UsuarioDTO> listarUsuarios() {
        List<UsuarioEntity> usuarios = repository.findAll();
        return usuarios.stream().map(UsuarioDTO::new).toList();
    }

    public UsuarioDTO buscarUsuarioPorId(Long id) {
        var entity = repository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
        return new UsuarioDTO(entity);
    }

    public UsuarioDTO inserirUsuario(UsuarioDTO usuario) {
        var usuarioEntity = repository.save(new UsuarioEntity(usuario));
        return new UsuarioDTO(usuarioEntity);
    }

    public UsuarioDTO alterarUsuario(UsuarioDTO usuario) {
       var entity = repository.findById(usuario.getId())
               .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado"));
       BeanUtils.copyProperties(usuario, entity);
       return new UsuarioDTO(repository.save(entity));
    }

    public void excluirUsuario(Long id) {
        if (!repository.existsById(id)) {
            throw new EntityNotFoundException("Usuário não encontrado");
        }
        repository.deleteById(id);
    }
}
