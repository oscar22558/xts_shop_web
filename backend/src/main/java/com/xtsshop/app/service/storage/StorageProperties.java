package com.xtsshop.app.service.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public class StorageProperties {

    /**
     * Folder location for storing files
     */
    private String root = "storage";
    private String urlRoot = "storage";
    private boolean production = false;
    public String getEnvRoot(){
        return root + "/" + (production ? "production" : "develop");
    }
    public String getRoot() {
        return root;
    }

    public String getImageRoot() {
        return getEnvRoot() + "/images";
    }
    public String getUrlRoot(){ return urlRoot; }
}
