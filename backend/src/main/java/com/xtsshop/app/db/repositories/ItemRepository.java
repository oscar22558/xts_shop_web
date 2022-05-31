package com.xtsshop.app.db.repositories;

import com.xtsshop.app.db.entities.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Set;

public interface ItemRepository extends JpaRepository<Item, Long> {

    @Query(value = "select i from Item i left join PriceHistory p on i.id=p.item.id where p.id in :ids")
    Set<Item> findAllByPriceHistoryIds(List<Long> ids);
    @Query(value = "select i from Item i left join PriceHistory p on i.id=p.item.id where p.id = :id")
    Item findByPriceHistoryId(Long id);
    @Query(value = "select i from Item i where i.category.id in :categoryIds and i.price between :minPrice and :maxPrice")
    List<Item> findAllWithBrandIdAndPrice(List<Long> categoryIds, List<Long> brandIds, float maxPrice, float minPrice);

    @Query(value = "select i from Item i where i.category.id in :categoryIds")
    List<Item> findAllWithBrandId(List<Long> categoryIds, List<Long> brandIds);

    @Query(value = "select i from Item i where i.category.id in :categoryIds and i.price between :minPrice and :maxPrice")
    List<Item> findAllWithPrice(List<Long> categoryIds, float maxPrice, float minPrice);
}
