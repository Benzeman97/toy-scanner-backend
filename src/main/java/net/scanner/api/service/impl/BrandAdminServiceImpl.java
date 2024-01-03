package net.scanner.api.service.impl;

import net.scanner.api.dao.BrandDao;
import net.scanner.api.dao.ProductDao;
import net.scanner.api.dto.request.BrandRequest;
import net.scanner.api.dto.response.BrandAdminResponse;
import net.scanner.api.dto.response.BrandResponse;
import net.scanner.api.entity.Brand;
import net.scanner.api.entity.Product;
import net.scanner.api.service.BrandAdminService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Objects;

@Service
public class BrandAdminServiceImpl implements BrandAdminService {

    final private static Logger LOGGER = LogManager.getLogger(BrandAdminServiceImpl.class);

    private BrandDao brandDao;

    public BrandAdminServiceImpl(BrandDao brandDao){
        this.brandDao=brandDao;
    }

    @Override
    public BrandResponse getBrandById(int id) {

        Brand brand = brandDao.findById(id)
                .orElse(new Brand());

        int brandId = Objects.isNull(brand.getBrandName()) ? (brandDao.getLastBrand().getBrandId()+1) : brand.getBrandId();

        LOGGER.info(String.format("brand is returned with %d",id));

        return new BrandResponse(brandId,brand.getBrandName(),brand.getBrandImg());

    }

    @Override
    public BrandResponse getBrandByName(String name) {

        Brand brand = brandDao.getBrandByName(name.toLowerCase())
                .orElse(new Brand());

        int brandId = Objects.isNull(brand.getBrandName()) ? (brandDao.getLastBrand().getBrandId()+1) : brand.getBrandId();

        LOGGER.info(String.format("brand is returned with %s",name));

        return new BrandResponse(brandId,brand.getBrandName(),brand.getBrandImg());
    }

    @Override
    public BrandAdminResponse saveBrand(BrandRequest request) throws ParseException {

        Brand brand = brandDao.getBrandByName(request.getBrandName().toLowerCase())
                .orElse(new Brand());

        if(Objects.isNull(brand.getBrandName()))
            brand.setBrandId((brandDao.getLastBrand().getBrandId()+1));

        brand.setBrandName(request.getBrandName());
        brand.setBrandImg(request.getBrandImg());

        brand = brandDao.save(brand);

        LOGGER.info(String.format("brand has been saved with %d",brand.getBrandId()));

        return new BrandAdminResponse(brand.getBrandId(),brand.getBrandName(),true);
    }

    @Override
    public boolean deleteBrand(int brandId) {

        Brand brand = brandDao.findById(brandId)
                .orElse(null);

        if(Objects.isNull(brand)) {
            LOGGER.info(String.format("brand is not found with %d", brandId));
            return false;
        }

        brandDao.delete(brand);

        LOGGER.info(String.format("brand is deleted with %d", brandId));

        return true;
    }
}
