package net.scanner.api.service.impl;

import net.scanner.api.dao.BrandDao;
import net.scanner.api.dao.ProductDao;
import net.scanner.api.dto.response.BrandResponse;
import net.scanner.api.entity.Brand;
import net.scanner.api.entity.Product;
import net.scanner.api.exceptional.DataNotFoundException;
import net.scanner.api.service.BrandService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BrandServiceImpl implements BrandService {

    final private static Logger LOGGER = LogManager.getLogger(BrandServiceImpl.class);

    public static final String BRAND_KEY = "BRAND_LIST";

    private BrandDao brandDao;

    public BrandServiceImpl(BrandDao brandDao){
        this.brandDao=brandDao;
    }
    
    @Override
    @Cacheable(key="#root.target.BRAND_KEY",value="SCANNER")
    public List<BrandResponse> getBrandList() {

        List<Brand> brands = brandDao.getBrandList()
                .orElseThrow(()->{
                    LOGGER.error(String.format("brand list is empty"));
                    throw new DataNotFoundException(String.format("brand list is empty"));
                });

        LOGGER.info(String.format("brand list is returned"));

        return brands.stream().map(b->new BrandResponse(b.getBrandId(),b.getBrandName(),b.getBrandImg()))
                .collect(Collectors.toList());
    }
}
