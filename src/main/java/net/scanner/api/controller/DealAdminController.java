package net.scanner.api.controller;


import net.scanner.api.dto.request.DealRequest;
import net.scanner.api.dto.request.ProductRequest;
import net.scanner.api.dto.response.DealAdminResponse;
import net.scanner.api.dto.response.DealResponse;
import net.scanner.api.dto.response.ProductAdminResponse;
import net.scanner.api.dto.response.ProductResponse;
import net.scanner.api.service.DealAdminService;
import net.scanner.api.service.ProductAdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@CrossOrigin(origins = {"https://toyscanner.net"}, maxAge = 3600)
@RestController
@RequestMapping("/api/admin/deal")
public class DealAdminController {

    private DealAdminService dealAdminService;

    public DealAdminController(DealAdminService dealAdminService){
        this.dealAdminService=dealAdminService;
    }

    @GetMapping(value = "/{id}",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DealResponse> getDealById(@PathVariable("id") int id){
        return new ResponseEntity<>(dealAdminService.getDealById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/find",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DealResponse> getDealByName(@RequestParam("name") String name){
        return new ResponseEntity<>(dealAdminService.getDealByName(name), HttpStatus.OK);
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE},consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<DealAdminResponse> saveDeal(@RequestBody DealRequest request) throws ParseException {
        return (request.getDealId()<=0 || request.getDealName().trim().isEmpty()) ?
                new ResponseEntity<>(HttpStatus.BAD_REQUEST) :
                new ResponseEntity<>(dealAdminService.saveDeal(request), HttpStatus.OK);
    }

    @GetMapping(value = "/delete/{id}",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Boolean> deleteProduct(@PathVariable("id") int id){
        return new ResponseEntity<>(dealAdminService.deleteDeal(id), HttpStatus.OK);
    }

}
