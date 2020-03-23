package com.revature.rms.core.services;

import com.revature.rms.core.exceptions.NoImplementationException;
import com.revature.rms.core.exceptions.ResourcePersistenceException;
import com.revature.rms.core.exceptions.ResourceRetrievalException;
import com.revature.rms.core.models.Resource;
import org.springframework.data.mongodb.core.ReactiveMongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.repository.ReactiveMongoRepository;
import org.springframework.util.MultiValueMap;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.Collections;

/**
 * Serves as a base implementation for business service classes that handle resources
 * coming from the controller layer.
 *
 * @param <T>
 *
 * @author Wezley Singleton (Github: wsingleton)
 *
 */
public abstract class ResourceService<T extends Resource> {

    protected final ReactiveMongoRepository<T, String> repo;
    protected final ReactiveMongoTemplate mongoTemplate;

    public ResourceService(ReactiveMongoRepository<T, String> repo, ReactiveMongoTemplate template) {
        this.repo = repo;
        this.mongoTemplate = template;
    }

    public Flux<T> findAll() {

        return repo.findAll()
                   .switchIfEmpty(Flux.error(ResourceRetrievalException::new));

    }

    public Flux<T> findAll(MultiValueMap<String, String> parameters) {

        // TODO findAll with params implementation
        return Flux.error(NoImplementationException::new);

    }

    public Flux<T> findAll(Iterable<String> ids) {
        return repo.findAllById(ids);
    }

    public Mono<T> findById(String id) {

        return repo.findById(id)
                    .switchIfEmpty(Mono.error(ResourceRetrievalException::new));

    }

    public Mono<T> save(T newObj) {

        return repo.save(newObj)
                   .switchIfEmpty(Mono.error(ResourcePersistenceException::new));
    }

    public Mono<T> update(String id, T updatedObj) {

        return repo.findById(id)
                   .switchIfEmpty(Mono.error(ResourceRetrievalException::new))
                   .flatMap(repo::save);

    }

    public Mono<Void> deleteById(String id) {
        return this.deleteAllById(Collections.singletonList(id)).then();
    }

    public Flux<Void> deleteAllById(Iterable<String> ids) {

        return repo.findAllById(ids)
                    .flatMap(resource -> {
                        Query query = new Query().addCriteria(Criteria.where("id").is(resource.getId()));
                        resource.getMetadata().setActive(false);
                        return mongoTemplate.findAndReplace(query, resource).then();
                    });

    }

}
