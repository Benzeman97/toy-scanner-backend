package net.scanner.api.service.impl;

import net.scanner.api.dao.BrandDao;
import net.scanner.api.dao.DealDao;
import net.scanner.api.dto.request.DealRequest;
import net.scanner.api.dto.response.BrandAdminResponse;
import net.scanner.api.dto.response.BrandResponse;
import net.scanner.api.dto.response.DealAdminResponse;
import net.scanner.api.dto.response.DealResponse;
import net.scanner.api.entity.Brand;
import net.scanner.api.entity.Deal;
import net.scanner.api.service.DealAdminService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Objects;

@Service
public class DealAdminServiceImpl implements DealAdminService {

    final private static Logger LOGGER = LogManager.getLogger(DealAdminServiceImpl.class);

    private DealDao dealDao;

    public DealAdminServiceImpl(DealDao dealDao){
        this.dealDao=dealDao;
    }

    @Override
    public DealResponse getDealById(int id) {

        Deal deal = dealDao.findById(id)
                .orElse(new Deal());

        LOGGER.info(String.format("deal is returned with %d",id));

        return new DealResponse(deal.getDealId(),deal.getDealName());
    }

    @Override
    public DealResponse getDealByName(String name) {

        Deal deal = dealDao.getDealByName(name.toLowerCase())
                .orElse(new Deal());

        LOGGER.info(String.format("deal is returned with %s",name));

        return new DealResponse(deal.getDealId(),deal.getDealName());
    }

    @Override
    public DealAdminResponse saveDeal(DealRequest request) throws ParseException {

        Deal deal = dealDao.getDealByName(request.getDealName())
                .orElse(new Deal());

        deal.setDealName(request.getDealName());

        deal = dealDao.save(deal);

        LOGGER.info(String.format("deal has been saved with %d",deal.getDealId()));

        return new DealAdminResponse(deal.getDealId(),deal.getDealName(),true);

    }

    @Override
    public boolean deleteDeal(int dealId) {

        Deal deal = dealDao.findById(dealId)
                .orElse(null);

        if(Objects.isNull(deal)) {
            LOGGER.info(String.format("deal is not found with %d", dealId));
            return false;
        }

        dealDao.delete(deal);

        LOGGER.info(String.format("deal is deleted with %d", dealId));

        return true;
    }
}
