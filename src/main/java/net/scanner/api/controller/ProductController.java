package net.scanner.api.controller;

import net.scanner.api.dto.response.ProductListResponse;
import net.scanner.api.dto.response.ProductResponse;
import net.scanner.api.service.ProductService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = {"https://toyscanner.net"}, maxAge = 3600)
@RestController
@RequestMapping("/api/product")
public class ProductController {

    private ProductService productService;

    public ProductController(ProductService productService){
        this.productService=productService;
    }

    @GetMapping(value = "/new-offers",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<ProductResponse>> getNewOfferItems(){
        return new ResponseEntity<>(productService.getNewOfferItems(), HttpStatus.OK);
    }

    @GetMapping(value = "/under-25",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<ProductResponse>> getUnder25Items(){
        return new ResponseEntity<>(productService.getUnder25Items(), HttpStatus.OK);
    }

    @GetMapping(value = "/new-release",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<ProductResponse>> getNewReleaseItems(){
        return new ResponseEntity<>(productService.getNewReleaseItems(), HttpStatus.OK);
    }

    @GetMapping(value = "/trending",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<ProductResponse>> getTrendingItems(){
        return new ResponseEntity<>(productService.getTrendingItems(), HttpStatus.OK);
    }

    @GetMapping(value = "/best-selling",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<List<ProductResponse>> getBestSellingItems(){
        return new ResponseEntity<>(productService.getBestSellingItems(), HttpStatus.OK);
    }

    @GetMapping(value = "/new-offer-list",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ProductListResponse> getNewOfferItemList(@RequestParam("page") int page){
        return new ResponseEntity<>(productService.getNewOfferItemList(page),HttpStatus.OK);
    }

    @GetMapping(value = "/under-25-list",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ProductListResponse> getUnder25ItemList(@RequestParam("page") int page){
        return new ResponseEntity<>(productService.getUnder25ItemList(page),HttpStatus.OK);
    }

    @GetMapping(value = "/new-release-list",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ProductListResponse> getNewReleaseItemList(@RequestParam("page") int page){
        return new ResponseEntity<>(productService.getNewReleaseItemList(page),HttpStatus.OK);
    }

    @GetMapping(value = "/best-selling-list",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ProductListResponse> getBestSellingItemList(@RequestParam("page") int page){
        return new ResponseEntity<>(productService.getBestSellingItemList(page),HttpStatus.OK);
    }

    @GetMapping(value = "/list-by-brand",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ProductListResponse> getItemsByBrand(@RequestParam("name") String name, @RequestParam("page") int page){
        return new ResponseEntity<>(productService.getItemsByBrand(name,page),HttpStatus.OK);
    }

    @GetMapping(value = "/list-by-search",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<ProductListResponse> getItemsBySearch(@RequestParam("f_value") String f_value,@RequestParam("ctg_id") int ctg_id,@RequestParam("price") String price, @RequestParam("page") int page){
        return new ResponseEntity<>(productService.getItemsBySearch(f_value,ctg_id,price,page),HttpStatus.OK);
    }

}
