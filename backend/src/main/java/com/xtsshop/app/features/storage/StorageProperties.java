package com.xtsshop.app.features.storage;

import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties("storage")
public class StorageProperties {

    private String root = "storage";
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
}
