package com.revature.rms.core.controllers;

import com.revature.rms.core.dtos.ErrorResponse;
import com.revature.rms.core.exceptions.*;
import com.revature.rms.core.models.Resource;
import com.revature.rms.core.services.ResourceService;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;

import org.springframework.http.HttpStatus;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

/**
 * Serves as a base implementation for business controller classes that handle requests
 * related to resource types.
 *
 * @param <T>
 *
 * @author Wezley Singleton (Github: wsingleton)
 *
 */
public abstract class ResourceController<T extends Resource> {

    protected final ResourceService<T> service;

    public ResourceController(ResourceService service) {
        this.service = service;
    }

    @GetMapping
    public Flux<T> findAll() {
        return service.findAll();
    }

    @GetMapping("/search")
    public Flux<T> findBy(@RequestParam MultiValueMap<String, String> params) {
        return service.findAll(params);
    }

    @GetMapping("/{id}")
    public Mono<T> findById(@PathVariable @NotEmpty String id) {
        return service.findById(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Mono<T> save(@RequestBody @Valid T newObj) {
        return service.save(newObj);
    }

    @PatchMapping("/{id}")
    public Mono<T> update(@PathVariable @NotEmpty String id, @RequestBody @Valid T updatedObj) {
        return service.update(id, updatedObj);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public Mono<Void> deleteById(@PathVariable @NotEmpty String id) {
        return service.deleteById(id);
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Mono<ErrorResponse> handleInvalidRequestException(InvalidRequestException e) {
        return Mono.just(new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage()));
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public Mono<ErrorResponse> handleRetrievalException(ResourceRetrievalException e) {
        return Mono.just(new ErrorResponse(HttpStatus.NOT_FOUND, e.getMessage()));
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.CONFLICT)
    public Mono<ErrorResponse> handlePersistenceException(ResourcePersistenceException e) {
        return Mono.just(new ErrorResponse(HttpStatus.CONFLICT, e.getMessage()));
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public Mono<ErrorResponse> handleInternalServerException(InternalServerException e) {
        return Mono.just(new ErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()));
    }

    @ExceptionHandler
    @ResponseStatus(HttpStatus.NOT_IMPLEMENTED)
    public Mono<ErrorResponse> handleNoImplementationException(NoImplementationException e) {
        return Mono.just(new ErrorResponse(HttpStatus.CONFLICT, e.getMessage()));
    }

}
