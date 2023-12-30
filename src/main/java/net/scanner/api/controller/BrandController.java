package net.scanner.api.controller;

import net.scanner.api.dto.response.BrandResponse;
import net.scanner.api.dto.response.ProductResponse;
import net.scanner.api.service.BrandService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin(origins = {"https://toyscanner.net"}, maxAge = 3600)
@RestController
@RequestMapping("/api/brand")
public class BrandController {

    private BrandService brandService;

    public BrandController(BrandService brandService){
        this.brandService=brandService;
    }

    @GetMapping(value = "/list",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<BrandResponse>> getBrandList(){
        return new ResponseEntity<>(brandService.getBrandList(), HttpStatus.OK);
    }

}
