package com.xtsshop.app.http.items;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.xtsshop.app.db.entities.Image;
import com.xtsshop.app.db.entities.Item;
import com.xtsshop.app.db.repositories.ImageRepository;
import com.xtsshop.app.db.repositories.ItemRepository;
import com.xtsshop.app.service.storage.StorageService;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import org.springframework.test.web.servlet.MockMvc;
import java.util.Comparator;
import java.util.stream.Collectors;

@Getter
@Component("tests.http.items.Util")
public class Util {
    private String route = "/api/items";
    private MockMvc mvc;
    private ObjectMapper mapper;
    private ItemRepository repository;
    private ImageRepository imageRepository;
    private StorageService storageService;
    public Util(
        MockMvc mvc,
        ObjectMapper mapper,
        ItemRepository repository,
        ImageRepository imageRepository,
        @Qualifier("ImageStorageService") StorageService storageService
    ){
        this.mvc = mvc;
        this.mapper = mapper;
        this.repository = repository;
        this.imageRepository = imageRepository;
        this.storageService = storageService;
    }

    public Item latestItem(){
        return repository.findAll().stream()
                .sorted(Comparator.comparingInt(item -> (int) item.getCreatedAt().getTime()))
                .collect(Collectors.toList())
                .get(0);
    }

    public Image latestImage(){
        return imageRepository.findAll().stream()
                .sorted((t1, t2) -> (int)t2.getId() - (int)t1.getId())
                .collect(Collectors.toList())
                .get(0);
    }
    public String getRoute(){
        return route;
    }
    public String getRouteWithId(){
        return route+"/{id}";
    }
    public String getUpdateImageRoute(){
        return route+"/{id}/image";
    }
}
