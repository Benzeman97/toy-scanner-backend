package net.scanner.api.service;

import net.scanner.api.dto.request.BrandRequest;
import net.scanner.api.dto.response.BrandAdminResponse;
import net.scanner.api.dto.response.BrandResponse;

import java.text.ParseException;

public interface BrandAdminService {

    BrandResponse getBrandById(int id);

    BrandResponse getBrandByName(String name);

    BrandAdminResponse saveBrand(BrandRequest request) throws ParseException;

    boolean deleteBrand(int brandId);
}
