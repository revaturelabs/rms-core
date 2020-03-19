package com.revature.rms.core.models;

public abstract class Resource {

    private ResourceMetadata metadata;

    public Resource() {
        super();
    }

    public Resource(ResourceMetadata metadata) {
        this.metadata = metadata;
    }

    public ResourceMetadata getMetadata() {
        return metadata;
    }

    public Resource setMetadata(ResourceMetadata metadata) {
        this.metadata = metadata;
        return this;
    }
}
