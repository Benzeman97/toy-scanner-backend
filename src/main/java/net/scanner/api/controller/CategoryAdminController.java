package net.scanner.api.controller;

import net.scanner.api.dto.request.CategoryRequest;
import net.scanner.api.dto.request.ProductRequest;
import net.scanner.api.dto.response.CategoryAdminResponse;
import net.scanner.api.dto.response.CategoryResponse;
import net.scanner.api.dto.response.ProductAdminResponse;
import net.scanner.api.dto.response.ProductResponse;
import net.scanner.api.service.CategoryAdminService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;

@CrossOrigin(origins = {"https://toyscanner.net"}, maxAge = 3600)
@RestController
@RequestMapping("/api/admin/category")
public class CategoryAdminController {

    private CategoryAdminService categoryAdminService;

    public CategoryAdminController(CategoryAdminService categoryAdminService){
        this.categoryAdminService=categoryAdminService;
    }

    @GetMapping(value = "/{id}",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CategoryResponse> getCategoryById(@PathVariable("id") int id){
        return new ResponseEntity<>(categoryAdminService.getCategoryById(id), HttpStatus.OK);
    }

    @GetMapping(value = "/find",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CategoryResponse> getCategoryByName(@RequestParam("name") String name){
        return new ResponseEntity<>(categoryAdminService.getCategoryByName(name), HttpStatus.OK);
    }

    @PostMapping(produces = {MediaType.APPLICATION_JSON_VALUE},consumes = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<CategoryAdminResponse> saveCategory(@RequestBody CategoryRequest request) throws ParseException {
        return (request.getCtgName().trim().isEmpty()) ?
                new ResponseEntity<>(HttpStatus.BAD_REQUEST) :
                new ResponseEntity<>(categoryAdminService.saveCategory(request), HttpStatus.OK);
    }

    @GetMapping(value = "/delete/{id}",produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<Boolean> deleteCategory(@PathVariable("id") int id){
        return new ResponseEntity<>(categoryAdminService.deleteCategory(id), HttpStatus.OK);
    }

}
