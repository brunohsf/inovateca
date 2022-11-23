package com.inovateca.InovaTeca.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Table(name = "usuarios")
@AllArgsConstructor
@NoArgsConstructor
@Data
public class UsuarioModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Integer idUsuario;

    @Column(name = "nome_usuario", nullable = false, length = 300)
    private String nomeUsuario;

    @Column(name = "email_usuario", nullable = false, length = 300, unique = true)
    private String email;

    @Column(name = "senha_usuario", nullable = false, length = 300)
    private String senhaUsuario;
}
