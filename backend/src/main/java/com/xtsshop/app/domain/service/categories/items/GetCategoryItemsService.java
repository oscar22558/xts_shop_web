package com.xtsshop.app.domain.service.categories.items;

import com.xtsshop.app.db.entities.Item;
import com.xtsshop.app.domain.request.categories.GetCategoryItemsRequest;
import com.xtsshop.app.datasource.repositories.categories.CategoryItemRepo;
import com.xtsshop.app.datasource.requests.categories.BrandSearchOptions;
import com.xtsshop.app.datasource.requests.categories.PriceSearchOptions;
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
        categoryItemRepo.setTopLevelCategoryIds(getTopLevelCategoryIdsFromRequest(request));
        categoryItemRepo.setPriceSearchOptions(getPriceOptionsFromRequest(request));
        categoryItemRepo.setBrandSearchOptions(new BrandSearchOptions(request.getBrandIds()));
        return categoryItemRepo.findItemsUnderCategories();
    }
    private List<Long> getTopLevelCategoryIdsFromRequest(GetCategoryItemsRequest request){
            return request.getCategoryId().flatMap((id)->{
                List<Long> returnList = new ArrayList<>();
                returnList.add(id);
                return Optional.of(returnList);
            }).orElse(request.getCategoryIds());
    }
    private PriceSearchOptions getPriceOptionsFromRequest(GetCategoryItemsRequest request){
        return isPriceSearchOptionSet(request)
                ? new PriceSearchOptions(request.getMinPrice().get(), request.getMaxPrice().get())
                : null;
    }
    private boolean isPriceSearchOptionSet(GetCategoryItemsRequest request){
        return request.getMaxPrice().isPresent() && request.getMinPrice().isPresent();
    }
}
