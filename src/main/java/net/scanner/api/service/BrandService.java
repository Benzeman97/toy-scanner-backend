package net.scanner.api.service;

import net.scanner.api.dto.response.BrandResponse;

import java.util.List;

public interface BrandService {

    List<BrandResponse> getBrandList();
}
