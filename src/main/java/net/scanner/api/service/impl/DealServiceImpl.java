package net.scanner.api.service.impl;

import net.scanner.api.dao.DealDao;
import net.scanner.api.dto.response.DealListResponse;
import net.scanner.api.entity.Deal;
import net.scanner.api.service.DealService;
import net.scanner.api.service.ProductService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class DealServiceImpl implements DealService {

    final private static Logger LOGGER = LogManager.getLogger(DealAdminServiceImpl.class);

    private DealDao dealDao;
    private ProductService productService;

    public DealServiceImpl(DealDao dealDao,ProductService productService){
        this.dealDao=dealDao;
        this.productService=productService;
    }

    @Override
    public List<DealListResponse> getDealList() {

         List<Deal> deals = dealDao.getDeals();

         List<DealListResponse> dealListResponses = deals.stream().map(d->new DealListResponse(d.getDealId(),d.getDealName(),d.getDealType(),productService.getProductsByDealId(d.getDealId())))
                  .collect(Collectors.toList());

        LOGGER.error(String.format("deal list is returned"));

        return dealListResponses;
    }
}
