package org.serratec.backend.servicedto.controller;

import java.net.URI;  // Classe usada para criar URIs
import java.util.List;  // Importa a lista de Java
import java.util.Optional;  // Importa a classe Optional para manipulação de objetos que podem ser nulos

import org.serratec.backend.servicedto.domain.Usuario;  // Importa a classe de domínio Usuario
import org.serratec.backend.servicedto.dto.UsuarioDTO;  // Importa o DTO (Data Transfer Object) para Usuario
import org.serratec.backend.servicedto.dto.UsuarioInserirDTO;  // Importa o DTO usado na inserção de novos usuários
import org.serratec.backend.servicedto.service.UsuarioService;  // Importa o serviço responsável pelas regras de negócio de Usuario
import org.springframework.beans.factory.annotation.Autowired;  // Permite a injeção automática de dependências
import org.springframework.http.ResponseEntity;  // Usado para criar respostas HTTP com códigos de status
import org.springframework.security.core.context.SecurityContextHolder;  // Permite acessar o contexto de segurança para autenticação
import org.springframework.security.core.userdetails.UserDetails;  // Representa os detalhes do usuário autenticado
import org.springframework.web.bind.annotation.GetMapping;  // Mapeia requisições GET
import org.springframework.web.bind.annotation.PathVariable;  // Anotação para receber variáveis na URL
import org.springframework.web.bind.annotation.PostMapping;  // Mapeia requisições POST
import org.springframework.web.bind.annotation.RequestBody;  // Indica que o corpo da requisição será convertido para o objeto especificado
import org.springframework.web.bind.annotation.RequestMapping;  // Define o caminho base das rotas do controlador
import org.springframework.web.bind.annotation.RestController;  // Indica que a classe é um controlador REST
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;  // Auxilia na criação de URIs com base nas requisições

@RestController  // Define que a classe é um controlador REST que gerencia requisições HTTP
@RequestMapping("/usuarios")  // Define que todas as requisições deste controlador começam com /usuarios
public class UsuarioController {

    @Autowired  // Injeção automática do serviço de usuários
    UsuarioService usuarioService;

    @GetMapping  // Mapeia o método listar() para requisições GET em /usuarios
    public ResponseEntity<List<UsuarioDTO>> listar() {
        // Obtém os detalhes do usuário autenticado no contexto de segurança
        UserDetails details = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        // Exibe o login do usuário autenticado no console
        System.out.println("Login do usuario: " + details.getUsername());
        // Retorna a lista de todos os usuários usando o serviço, com código de status 200 OK
        return ResponseEntity.ok(usuarioService.findAll());
    }

    @GetMapping("/{id}")  // Mapeia o método buscar() para requisições GET em /usuarios/{id}
    public ResponseEntity<UsuarioDTO> buscar(@PathVariable Long id) {
        // Busca o usuário pelo ID, retornando um Optional que pode conter ou não o usuário
        Optional<Usuario> usuarioOpt = usuarioService.buscar(id);
        // Se o usuário foi encontrado, converte para DTO e retorna com código 200 OK
        if (usuarioOpt.isPresent()) {
            UsuarioDTO usuarioDTO = new UsuarioDTO(usuarioOpt.get());
            return ResponseEntity.ok(usuarioDTO);
        }
        // Caso o usuário não seja encontrado, retorna 404 Not Found
        return ResponseEntity.notFound().build();
    }

    @PostMapping  // Mapeia o método inserir() para requisições POST em /usuarios
    public ResponseEntity<UsuarioDTO> inserir(@RequestBody UsuarioInserirDTO usuarioInserirDTO) {
        // Insere o novo usuário e obtém o DTO do usuário inserido
        UsuarioDTO usuarioDTO = usuarioService.inserir(usuarioInserirDTO);
        // Cria a URI do novo recurso (usuário) criado, usando o ID do usuário
        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()  // Pega a URI da requisição atual
                .path("/{id}")  // Adiciona o ID do novo usuário à URI
                .buildAndExpand(usuarioDTO.getId())  // Substitui o {id} pelo valor real
                .toUri();  // Converte a URI criada em um objeto URI
        // Retorna 201 Created com o DTO do usuário e a URI do novo recurso
        return ResponseEntity.created(uri).body(usuarioDTO);
    }
}
/*
Explicação do Código:
Controller REST:

A classe é anotada com @RestController, o que significa que gerencia 
requisições HTTP e responde com dados (geralmente JSON).

@RequestMapping("/usuarios"): Define que todas as rotas desta classe 
começam com /usuarios.

Injeção de Dependência: 

usuarioService: O serviço que contém as regras 
de negócio e interações com o repositório de usuários é injetado 
automaticamente com @Autowired.

Listagem de Usuários:

listar(): Método para listar todos os usuários. Aqui também é extraído 
o nome de usuário do usuário autenticado com SecurityContextHolder 
para fins de exibição no console. O método retorna uma lista de objetos 
UsuarioDTO, encapsulados em uma ResponseEntity com status 200 (OK).

Busca de Usuário por ID:

buscar(): Este método recebe o ID de um usuário via URL (/usuarios/{id}) 
e busca o usuário correspondente. Se o usuário for encontrado, ele é 
convertido para UsuarioDTO e retornado com status 200 OK. Se não for 
encontrado, retorna 404 (Not Found).

Inserção de Novo Usuário:

inserir(): Método para inserir um novo usuário no sistema. 
Recebe um objeto UsuarioInserirDTO via corpo da requisição 
(@RequestBody). Após a inserção, a URI do novo recurso é criada 
usando ServletUriComponentsBuilder, e o método retorna a resposta 
com status 201 (Created) e o DTO do usuário inserido.
*/