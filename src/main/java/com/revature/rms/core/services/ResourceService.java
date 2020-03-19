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

public abstract class ResourceService<T extends Resource> {

    private final ReactiveMongoRepository<T, String> repo;
    private final ReactiveMongoTemplate mongoTemplate;

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

        return repo.findById(id)
                   .flatMap(resource -> {
                       Query query = new Query().addCriteria(Criteria.where("id").is(id));
                       resource.getMetadata().setActive(false);
                       return mongoTemplate.findAndReplace(query, resource).then();
                    });

    }
}