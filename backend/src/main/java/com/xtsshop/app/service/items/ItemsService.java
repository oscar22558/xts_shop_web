package com.xtsshop.app.service.items;

import com.xtsshop.app.advice.exception.RecordNotFoundException;
import com.xtsshop.app.db.entities.Category;
import com.xtsshop.app.db.entities.Image;
import com.xtsshop.app.db.entities.Item;
import com.xtsshop.app.db.repositories.CategoryRepository;
import com.xtsshop.app.db.repositories.ItemRepository;
import com.xtsshop.app.request.ImageRequest;
import com.xtsshop.app.request.ItemRequest;
import com.xtsshop.app.service.categories.CategoriesGetService;
import com.xtsshop.app.service.categories.CategoriesService;
import com.xtsshop.app.service.images.ImagesService;
import com.xtsshop.app.util.CheckedFunction;
import lombok.Setter;
import org.springframework.expression.spel.ast.OpInc;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.Null;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ItemsService implements CheckedFunction<Long, Optional<Category>> {
    private ItemRepository repository;
    private ImagesService imagesService;
    private CategoryRepository categoryRepository;
    private CategoriesGetService categoriesGetService;
    public ItemsService(
        ItemRepository repository,
        ImagesService imagesService,
        CategoryRepository categoryRepository,
        CategoriesGetService categoriesGetService
    ){
        this.repository = repository;
        this.imagesService = imagesService;
        this.categoryRepository = categoryRepository;
        this.categoriesGetService = categoriesGetService;
    }

    public List<Item> all(){
        return repository.findAll();
    }
    public Item get(Long id) throws RecordNotFoundException {
        return repository.findById(id).orElseThrow(()->new RecordNotFoundException("Item of id"+id+" not found."));
    }

    public List<Item> findItemsByCategoryId(List<Long> categoryIds){
        return categoryRepository.findAllById(categoryIds)
                .stream()
                .map(this::getItemsRecursively)
                .reduce(new ArrayList<>(), (acc, current)->{
                    acc.addAll(current);
                    return acc;
                });
    }

    private List<Item> getItemsRecursively(Category category){
        List<Item> items = category.getItems();
        List<List<Item>> itemsOfChild = category.getSubCategories()
                .stream()
                .map(this::getItemsRecursively)
                .collect(Collectors.toList());
        itemsOfChild.forEach(items::addAll);
        return items;
    }
    public Item create(ItemRequest request) throws RecordNotFoundException{
        Category category = request.getCategoryId().flatMap(CheckedFunction.wrap(this::apply)).orElseThrow(NullPointerException::new);
        Item item = repository.save(request.toEntity(category));
        ImageRequest imageForm = new ImageRequest();
        imageForm.setImage(request.getImage().orElseThrow(NullPointerException::new));
        imageForm.setItem(item);
        Image image = imagesService.create(imageForm);
        item.setImage(image);
        return repository.save(item);
    }

    public Item update(Long id, ItemRequest request) throws RecordNotFoundException {
        Optional<Category> category = request.getCategoryId().flatMap(CheckedFunction.wrap(this::apply));
        Item originalItem = get(id);
        Optional<MultipartFile> uploadedImg = request.getImage();
        Optional<Image> image = uploadedImg.flatMap(img->{
            try{
                ImageRequest imageRequest = new ImageRequest();
                imageRequest.setImage(img);
                imageRequest.setItem(originalItem);
                return Optional.of(imagesService.update(originalItem.getImage().getId(), imageRequest));
            }catch (RecordNotFoundException ex){
                throw new RuntimeException(ex);
            }
        });
        Item item = request.update(originalItem, category);
        image.ifPresent(item::setImage);
        return repository.save(item);
    }

    public void delete(Long id) throws RecordNotFoundException {
        Item item = get(id);
        imagesService.delete(item.getImage().getId());
        repository.delete(item);
    }

    @Override
    public Optional<Category> apply(Long aLong) throws Exception {
        return Optional.ofNullable(categoriesGetService.get(aLong));
    }
}
