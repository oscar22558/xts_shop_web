package com.xtsshop.app.db.repositories;

import com.xtsshop.app.db.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface ItemJpaRepository extends JpaRepository<Item, Long> {

    @Query(value = "select i from Item i left join PriceHistory p on i.id=p.item.id where p.id in :ids")
    Set<Item> findAllByPriceHistoryIds(List<Long> ids);

    @Query(value = "select i from Item i left join PriceHistory p on i.id=p.item.id where p.id = :id")
    Item findByPriceHistoryId(Long id);

    @Query(value = "select i from Item i where i.category.id in :categoryIds and i.brand.id in :brandIds and i.price between :minPrice and :maxPrice")
    List<Item> findAllForCategoriesWithBrandIdAndPrice(List<Long> categoryIds, List<Long> brandIds, float maxPrice, float minPrice);

    @Query(value = "select i from Item i where i.category.id in :categoryIds and i.brand.id in :brandIds")
    List<Item> findAllForCategoriesWithBrandId(List<Long> categoryIds, List<Long> brandIds);

    @Query(value = "select i from Item i where i.category.id in :categoryIds and i.price between :minPrice and :maxPrice")
    List<Item> findAllForCategoriesWithPrice(List<Long> categoryIds, float maxPrice, float minPrice);

    @Query(value = "select i from Item i where i.category.id in :categoryIds")
    List<Item> findAllForCategories(List<Long> categoryIds);

    @Query(value = "select i from Item i where i.name=:name")
    List<Item> findAllByName(String name);
}
