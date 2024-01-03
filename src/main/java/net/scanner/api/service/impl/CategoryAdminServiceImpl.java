package net.scanner.api.service.impl;

import net.scanner.api.dao.CategoryDao;
import net.scanner.api.dto.request.CategoryRequest;
import net.scanner.api.dto.response.CategoryAdminResponse;
import net.scanner.api.dto.response.CategoryResponse;
import net.scanner.api.entity.Category;
import net.scanner.api.service.CategoryAdminService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Objects;


@Service
public class CategoryAdminServiceImpl implements CategoryAdminService {

    final private static Logger LOGGER = LogManager.getLogger(CategoryAdminServiceImpl.class);

    private CategoryDao categoryDao;

    public CategoryAdminServiceImpl(CategoryDao categoryDao){
        this.categoryDao=categoryDao;
    }

    @Override
    public CategoryResponse getCategoryById(int id) {

        Category category = categoryDao.findById(id)
                .orElse(new Category());

        int ctgId = Objects.isNull(category.getCtgName()) ? (categoryDao.getLastCategory().getCtgId()+1) : category.getCtgId();

        LOGGER.info(String.format("category is returned with %d",id));

        return new CategoryResponse(ctgId,category.getCtgName());

    }

    @Override
    public CategoryResponse getCategoryByName(String name) {

        Category category = categoryDao.getCategoryByName(name.toLowerCase())
                .orElse(new Category());

        int ctgId = Objects.isNull(category.getCtgName()) ? (categoryDao.getLastCategory().getCtgId()+1) : category.getCtgId();

        LOGGER.info(String.format("category is returned with %s",name));

        return new CategoryResponse(ctgId,category.getCtgName());

    }

    @Override
    public CategoryAdminResponse saveCategory(CategoryRequest request) throws ParseException {

        Category category = categoryDao.getCategoryByName(request.getCtgName().toLowerCase())
                .orElse(new Category());

        if(Objects.isNull(category.getCtgName()))
            category.setCtgId((categoryDao.getLastCategory().getCtgId()+1));

        category.setCtgName(request.getCtgName());

        category = categoryDao.save(category);

        LOGGER.info(String.format("category has been saved with %d",category.getCtgId()));

        return new CategoryAdminResponse(category.getCtgId(),category.getCtgName(),true);
    }

    @Override
    public boolean deleteCategory(int ctgId) {

        Category category = categoryDao.findById(ctgId)
                .orElse(null);

        if(Objects.isNull(category)) {
            LOGGER.info(String.format("category is not found with %d", ctgId));
            return false;
        }

        categoryDao.delete(category);

        LOGGER.info(String.format("category is deleted with %d", ctgId));

        return true;
    }
}
