package com.xtsshop.app.controller.brands;

import com.xtsshop.app.advices.exception.RecordNotFoundException;
import com.xtsshop.app.controller.brands.models.BrandCreateRequest;
import com.xtsshop.app.controller.brands.models.BrandEntityConvertor;
import com.xtsshop.app.db.entities.Brand;
import com.xtsshop.app.db.repositories.BrandRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BrandService {
    private BrandRepository brandRepository;
    private BrandEntityConvertor brandEntityConvertor;

    public BrandService(BrandRepository brandRepository, BrandEntityConvertor brandEntityConvertor) {
        this.brandRepository = brandRepository;
        this.brandEntityConvertor = brandEntityConvertor;
    }

    public List<Brand> listAllBrands(){
       return brandRepository.findAll();
    }

    public Brand create(BrandCreateRequest request){
        Brand brand = brandEntityConvertor.setRequest(request).getEntity();
        return brandRepository.save(brand);
    }

    public void delete(Long id){
        brandRepository.deleteById(id);
    }

    public Brand get(Long id){
        return brandRepository
                .findById(id)
                .orElseThrow(()->new RecordNotFoundException("Brand "+id+" not find"));
    }
}
