package com.xtsshop.app.domain.service.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage.url")
public class StorageUrlProperties {
    private String urlRoot = "storage";
    public String getUrlRoot(){ return urlRoot; }
}