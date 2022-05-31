package com.xtsshop.app.domain.service.categories;

import com.xtsshop.app.db.entities.Item;
import com.xtsshop.app.domain.request.categories.GetCategoryItemsRequest;
import com.xtsshop.app.repository.Categories.CategoryItemRepo;
import com.xtsshop.app.repository.requests.Categories.BrandSearchOptions;
import com.xtsshop.app.repository.requests.Categories.PriceSearchOptions;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class GetCategoryItemsService {

    private CategoryItemRepo categoryItemRepo;

    public GetCategoryItemsService(CategoryItemRepo categoryItemRepo) {
        this.categoryItemRepo = categoryItemRepo;
    }

    public List<Item> getItems(GetCategoryItemsRequest request){
        List<Long> categoryIds = request.getCategoryId().flatMap((id)->{
            List<Long> returnList = new ArrayList<>();
            returnList.add(id);
            return Optional.of(returnList);
        }).orElse(request.getCategoryIds());
        PriceSearchOptions priceOptions = request.getMaxPrice().isPresent() && request.getMinPrice().isPresent()
            ? new PriceSearchOptions(request.getMinPrice().get(), request.getMaxPrice().get())
            : null;
        BrandSearchOptions brandOptions = new BrandSearchOptions(request.getBrandIds());
        return categoryItemRepo.findItemsByTopLevelCategoryIds(categoryIds, Optional.ofNullable(priceOptions), Optional.of(brandOptions));
    }
}
