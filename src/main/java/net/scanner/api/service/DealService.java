package net.scanner.api.service;

import net.scanner.api.dto.response.DealListResponse;

import java.util.List;

public interface DealService {

    List<DealListResponse> getDealList();
}
