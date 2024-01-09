package net.scanner.api.service.impl;

import net.scanner.api.dao.CategoryDao;
import net.scanner.api.dao.DealDao;
import net.scanner.api.entity.Category;
import net.scanner.api.exceptional.DataNotFoundException;
import net.scanner.api.service.CategoryService;
import net.scanner.api.service.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {

    final private static Logger LOGGER = LogManager.getLogger(DealAdminServiceImpl.class);

    private CategoryDao categoryDao;

    public CategoryServiceImpl(CategoryDao categoryDao){
        this.categoryDao=categoryDao;
    }

    @Override
    public int getCategoryId(String name) {

        Category category = categoryDao.getCategoryByName(name.toLowerCase())
                .orElseThrow(()->{
                    LOGGER.error(String.format("category is not found with %s",name));
                    throw new DataNotFoundException(String.format("category is not found with %s",name));
                });

        return category.getCtgId();
    }
}
