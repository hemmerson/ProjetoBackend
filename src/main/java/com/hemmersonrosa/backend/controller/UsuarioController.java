package com.hemmersonrosa.backend.controller;

import com.hemmersonrosa.backend.dto.UsuarioDTO;
import com.hemmersonrosa.backend.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping
    public List<UsuarioDTO> listarUsuarios() {
        return service.listarUsuarios();
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsuarioDTO> buscarUsuarioPorId(@PathVariable Long id) {
        return ResponseEntity.ok(service.buscarUsuarioPorId(id));
    }

    @PostMapping
    public ResponseEntity<UsuarioDTO> inserirUsuario(@RequestBody UsuarioDTO dto) {
        var usuarioDTO = service.inserirUsuario(dto);
        URI location = ServletUriComponentsBuilder
                .fromCurrentRequest().path("/{id}").buildAndExpand(usuarioDTO.getId()).toUri();
        return ResponseEntity.created(location).body(usuarioDTO);
    }

    @PutMapping()
    public ResponseEntity<UsuarioDTO> atualizarUsuario(@RequestBody UsuarioDTO dto) {
        return ResponseEntity.ok(service.alterarUsuario(dto));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletarUsuario(@PathVariable Long id) {
        service.excluirUsuario(id);
        return ResponseEntity.noContent().build();
    }
}
