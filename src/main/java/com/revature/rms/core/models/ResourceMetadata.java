package com.revature.rms.core.models;

import java.time.LocalDateTime;

public class ResourceMetadata {

    private String resourceCreatorId;
    private String lastModifierId;
    private String resourceOwnerId;
    private boolean isActive;
    private LocalDateTime resourceCreationDateTime;
    private LocalDateTime lastModifiedDateTime;

    public ResourceMetadata() {
        super();
    }

    /**
     * Creates a new ResourceMetadata instance where the provided creator id is used as the
     * last modifier id, and the resource creation time and last modified time is set to
     * the time of instantiation.
     *
     * @param creator
     * @param owner
     */
    public ResourceMetadata(String creator, String owner) {
        this.resourceCreatorId = creator;
        this.resourceCreationDateTime = LocalDateTime.now();
        this.lastModifierId = creator;
        this.lastModifiedDateTime = LocalDateTime.now();
        this.resourceOwnerId = owner;
        this.isActive = true;
    }

    public ResourceMetadata(String creatorId, LocalDateTime creationDT, String modId, LocalDateTime modDT, String ownerId, boolean active) {
        this.resourceCreatorId = creatorId;
        this.resourceCreationDateTime = creationDT;
        this.lastModifierId = modId;
        this.lastModifiedDateTime = modDT;
        this.resourceOwnerId = ownerId;
        this.isActive = active;
    }

    public String getResourceCreatorId() {
        return resourceCreatorId;
    }

    public ResourceMetadata setResourceCreatorId(String resourceCreatorId) {
        this.resourceCreatorId = resourceCreatorId;
        return this;
    }

    public String getLastModifierId() {
        return lastModifierId;
    }

    public ResourceMetadata setLastModifierId(String lastModifierId) {
        this.lastModifierId = lastModifierId;
        return this;
    }

    public String getResourceOwnerId() {
        return resourceOwnerId;
    }

    public ResourceMetadata setResourceOwnerId(String resourceOwnerId) {
        this.resourceOwnerId = resourceOwnerId;
        return this;
    }

    public boolean isActive() {
        return isActive;
    }

    public ResourceMetadata setActive(boolean active) {
        isActive = active;
        return this;
    }

    public String getResourceCreationDateTime() {
        return resourceCreationDateTime.toString();
    }

    public ResourceMetadata setResourceCreationDateTime(LocalDateTime resourceCreationDateTime) {
        this.resourceCreationDateTime = resourceCreationDateTime;
        return this;
    }

    public String getLastModifiedDateTime() {
        return lastModifiedDateTime.toString();
    }

    public ResourceMetadata setLastModifiedDateTime(LocalDateTime lastModifiedDateTime) {
        this.lastModifiedDateTime = lastModifiedDateTime;
        return this;
    }

}