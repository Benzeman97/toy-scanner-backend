package net.scanner.api.service;


import net.scanner.api.dto.request.DealRequest;
import net.scanner.api.dto.response.DealAdminResponse;
import net.scanner.api.dto.response.DealResponse;

import java.text.ParseException;

public interface DealAdminService {

    DealResponse getDealById(int id);

    DealResponse getDealByName(String name);

    DealAdminResponse saveDeal(DealRequest request) throws ParseException;

    boolean deleteDeal(int dealId);


}
