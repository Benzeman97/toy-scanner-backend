package net.scanner.api.service;


import net.scanner.api.dto.request.CategoryRequest;
import net.scanner.api.dto.response.CategoryAdminResponse;
import net.scanner.api.dto.response.CategoryResponse;

import java.text.ParseException;

public interface CategoryAdminService {

    CategoryResponse getCategoryById(int id);

    CategoryResponse getCategoryByName(String name);

    CategoryAdminResponse saveCategory(CategoryRequest request) throws ParseException;

    boolean deleteCategory(int ctgId);
}
