package net.scanner.api.controller;

import net.scanner.api.dto.request.BrandRequest;
import net.scanner.api.dto.request.ProductRequest;
import net.scanner.api.dto.response.BrandAdminResponse;
import net.scanner.api.dto.response.BrandResponse;
import net.scanner.api.dto.response.ProductAdminResponse;
import net.scanner.api.dto.response.ProductResponse;
import net.scanner.api.service.BrandAdminService;
import net.scanner.api.service.ProductAdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@CrossOrigin(origins = {"https://toyscanner.net"}, maxAge = 3600)
@RestController
@RequestMapping("/api/admin/brand")
public class BrandAdminController {

    private BrandAdminService brandAdminService;

    public BrandAdminController(BrandAdminService brandAdminService){
        this.brandAdminService=brandAdminService;
    }

    @GetMapping(value = "/{id}",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<BrandResponse> getBrandById(@PathVariable("id") int id){
        return new ResponseEntity<>(brandAdminService.getBrandById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/find/{name}",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<BrandResponse> getBrandByName(@PathVariable("name") String name){
        return new ResponseEntity<>(brandAdminService.getBrandByName(name), HttpStatus.OK);
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE},consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<BrandAdminResponse> saveProduct(@RequestBody BrandRequest request) throws ParseException {
        return (request.getBrandId()<=0 || request.getBrandName().trim().isEmpty()) ?
                new ResponseEntity<>(HttpStatus.BAD_REQUEST) :
                new ResponseEntity<>(brandAdminService.saveBrand(request), HttpStatus.OK);
    }

    @GetMapping(value = "/delete/{id}",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Boolean> deleteBrand(@PathVariable("id") int id){
        return new ResponseEntity<>(brandAdminService.deleteBrand(id), HttpStatus.OK);
    }

}
