package com.inovateca.InovaTeca.controller;

import com.inovateca.InovaTeca.model.AutorModel;
import com.inovateca.InovaTeca.service.AutorService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/autor/v1")
@Api(value = "Autor Controller V1")
public class AutorController {

    @Autowired
    private AutorService service;

    @GetMapping(value = "/{id}")
    @ApiOperation(value = "Retorna um livro atraves do ID", produces = "JSON", response = AutorModel.class)
    public AutorModel findById(@ApiParam(name = "id", required = true, type = "Long") @PathVariable long id) {
        var model = service.findById(id);
        if (model.isPresent()) {
            buildEntityLinks(model.get());
        }
        //.return the model with links
        return model.get();
    }

    @GetMapping
    public ResponseEntity<PagedModel<AutorModel>> findAll(@RequestParam(value = "page", defaultValue = "0") int page, @RequestParam(value = "size", defaultValue = "10") int size, @RequestParam(value = "direction", defaultValue = "asc") String direction, PagedResourcesAssembler<AutorModel> assembler) {
        //..the direction of sort
        var sortDirection = "desc".equalsIgnoreCase(direction) ? Sort.Direction.DESC : Sort.Direction.ASC;

        //..a PageAble object is an object containing the list of resources
        Pageable pageable = PageRequest.of(page, size, Sort.by(sortDirection, "fullName"));

        //..a Page containing the resource models
        Page<AutorModel> pageModel = service.findAll(pageable);

        //..create the HATEOAS links
        for (AutorModel autor : pageModel) {
            buildEntityLinks(autor);
        }
        //return the ResponseEntity
        return new ResponseEntity(assembler.toModel(pageModel), HttpStatus.OK);
    }

    @PutMapping
    public AutorModel update(@RequestBody AutorModel model) {
        return service.update(model);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") long id) {
        service.delete(id);
        return ResponseEntity.ok().build();
    }

    public void buildEntityLinks(AutorModel model) {

        model.add(
                WebMvcLinkBuilder.linkTo(
                        WebMvcLinkBuilder.methodOn(this.getClass())
                                .findById(model.getId())
                ).withSelfRel()
        );
    }
}
