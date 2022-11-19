package com.inovateca.InovaTeca.model;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.hateoas.RepresentationModel;

import javax.persistence.*;

@Entity
@Table(name = "livros")
@AllArgsConstructor
@NoArgsConstructor
@ApiModel(value = "Livro Model V1")
public class LivroModel extends RepresentationModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Getter
    @Setter
    @ApiModelProperty(notes = "ID gerado automaticamente pelo BD", required = true)
    private long id;

    @Column(name = "titulo", nullable = false, length = 50)
    @Getter
    @Setter
    @ApiModelProperty(notes = "Titulo do livro", required = true)
    private String titulo;

    @ManyToOne
    @JoinColumn(name = "autor_id")
    @Getter
    @Setter
    private AutorModel autor;

}