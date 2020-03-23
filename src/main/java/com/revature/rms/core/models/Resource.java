package com.revature.rms.core.models;

import org.springframework.data.annotation.Id;

/**
 * An abstract representation of a Resource.
 *
 * @author Wezley Singleton (GitHub: wsingleton)
 *
 */
public abstract class Resource {


    /** A unique id string for this resource */
    @Id
    protected String id;

    /** System information related to this resource */
    protected ResourceMetadata metadata;

    public Resource() {
        super();
    }

    public Resource(ResourceMetadata metadata) {
        this.metadata = metadata;
    }

    public Resource(String id, ResourceMetadata metadata) {
        this(metadata);
        this.id = id;
    }

    public String getId() {
        return id;
    }

    public Resource setId(String id) {
        this.id = id;
        return this;
    }

    public ResourceMetadata getMetadata() {
        return metadata;
    }

    public Resource setMetadata(ResourceMetadata metadata) {
        this.metadata = metadata;
        return this;
    }

}
