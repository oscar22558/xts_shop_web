package com.xtsshop.app.features.brands;

import com.xtsshop.app.advices.exception.RecordNotFoundException;
import com.xtsshop.app.features.brands.models.BrandCreateRequest;
import com.xtsshop.app.features.brands.models.BrandEntityConvertor;
import com.xtsshop.app.db.entities.Brand;
import com.xtsshop.app.db.repositories.BrandJpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService {
    private BrandJpaRepository brandJpaRepository;
    private BrandEntityConvertor brandEntityConvertor;

    public BrandService(BrandJpaRepository brandJpaRepository, BrandEntityConvertor brandEntityConvertor) {
        this.brandJpaRepository = brandJpaRepository;
        this.brandEntityConvertor = brandEntityConvertor;
    }

    public List<Brand> listAllBrands(){
       return brandJpaRepository.findAll();
    }

    public Brand create(BrandCreateRequest request){
        Brand brand = brandEntityConvertor.setRequest(request).getEntity();
        return brandJpaRepository.save(brand);
    }

    public void delete(Long id){
        brandJpaRepository.deleteById(id);
    }

    public Brand get(Long id){
        return brandJpaRepository
                .findById(id)
                .orElseThrow(()->new RecordNotFoundException("Brand "+id+" not find"));
    }
}
