package net.scanner.api.controller;

import net.scanner.api.dto.request.ProductRequest;
import net.scanner.api.dto.response.ProductAdminResponse;
import net.scanner.api.dto.response.ProductResponse;
import net.scanner.api.service.ProductAdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@CrossOrigin(origins = {"https://toyscanner.net"}, maxAge = 3600)
@RestController
@RequestMapping("/api/admin/product")
public class ProductAdminController {

    private ProductAdminService productAdminService;

    public ProductAdminController(ProductAdminService productAdminService){
        this.productAdminService=productAdminService;
    }

    @GetMapping(value = "/{id}",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ProductResponse> getProductById(@PathVariable("id") int id){
        return new ResponseEntity<>(productAdminService.getProductById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/find",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ProductResponse> getProductByName(@RequestParam("name") String name){
        return new ResponseEntity<>(productAdminService.getProductByName(name), HttpStatus.OK);
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE},consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ProductAdminResponse> saveProduct(@RequestBody ProductRequest request) throws ParseException {
        return (request.getProductName().trim().isEmpty() || request.getItemUrl().trim().isEmpty()) ?
                new ResponseEntity<>(HttpStatus.BAD_REQUEST) :
           new ResponseEntity<>(productAdminService.saveProduct(request), HttpStatus.OK);
    }

    @GetMapping(value = "/delete/{id}",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Boolean> deleteProduct(@PathVariable("id") int id){
        return new ResponseEntity<>(productAdminService.deleteProduct(id), HttpStatus.OK);
    }

}
