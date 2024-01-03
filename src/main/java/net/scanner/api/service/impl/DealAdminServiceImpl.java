package net.scanner.api.service.impl;

import net.scanner.api.dao.DealDao;
import net.scanner.api.dto.request.DealRequest;
import net.scanner.api.dto.response.DealAdminResponse;
import net.scanner.api.dto.response.DealResponse;
import net.scanner.api.entity.Deal;
import net.scanner.api.service.DealAdminService;
import net.scanner.api.service.ProductAdminService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class DealAdminServiceImpl implements DealAdminService {

    final private static Logger LOGGER = LogManager.getLogger(DealAdminServiceImpl.class);

    private DealDao dealDao;
    private ProductAdminService productAdminService;

    public DealAdminServiceImpl(DealDao dealDao,ProductAdminService productAdminService){
        this.dealDao=dealDao;
        this.productAdminService=productAdminService;
    }

    @Override
    public DealResponse getDealById(int id) {

        Deal deal = dealDao.findById(id)
                .orElse(new Deal());

        int dealId = Objects.isNull(deal.getDealName()) ? (dealDao.getLastDeal().getDealId()+1) : deal.getDealId();

        LOGGER.info(String.format("deal is returned with %d",id));

        return new DealResponse(dealId,deal.getDealName(),deal.getDealType());
    }

    @Override
    public DealResponse getDealByName(String name) {

        Deal deal = dealDao.getDealByName(name.toLowerCase())
                .orElse(new Deal());

        int dealId = Objects.isNull(deal.getDealName()) ? (dealDao.getLastDeal().getDealId()+1) : deal.getDealId();

        LOGGER.info(String.format("deal is returned with %s",name));

        return new DealResponse(dealId,deal.getDealName(),deal.getDealType());
    }

    @Override
    public DealAdminResponse saveDeal(DealRequest request) throws ParseException {

        Deal deal = dealDao.getDealByName(request.getDealName())
                .orElse(new Deal());

        if(Objects.isNull(deal.getDealName()))
            deal.setDealId((dealDao.getLastDeal().getDealId()+1));


        deal.setDealName(request.getDealName());
        deal.setDealType(request.getDealType());
        deal.setLaunchedDate(LocalDateTime.now());

        deal = dealDao.save(deal);

        LOGGER.info(String.format("deal has been saved with %d",deal.getDealId()));

        return new DealAdminResponse(deal.getDealId(),deal.getDealName(),deal.getDealType(),true);

    }

    @Override
    public boolean deleteDeal(int dealId) {

        Deal deal = dealDao.findById(dealId)
                .orElse(null);

        if(Objects.isNull(deal)) {
            LOGGER.info(String.format("deal is not found with %d", dealId));
            return false;
        }

       boolean status = productAdminService.setValueForDealId(dealId);

        if(!status)
            return false;

        dealDao.delete(deal);

        LOGGER.info(String.format("deal is deleted with %d", dealId));

        return true;
    }

}
