package com.goomo.hplus.restcontlollers;

import com.goomo.hplus.beans.Product;
import com.goomo.hplus.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class ProductsController {
    //@RequestMapping(method = RequestMethod.GET ) - also can be used
    @Autowired
    private ProductRepository productRepository;

  /*  @GetMapping("/hplus/rest/products")
    @ResponseBody
    public List<Product> getProducts() {
        List<Product> products = new ArrayList<>();
        //call product repository to get data
        productRepository.findAll().forEach(product -> products.add(product));
        return products;
    }*/

    @GetMapping("/hplus/rest/products")
    public ResponseEntity GetProductsByRequestParam(@RequestParam("name") String sname) {
    List<Product> products = productRepository.searchByName(sname);
        return new ResponseEntity<>(products, HttpStatus.OK);
    }

    @GetMapping("/hplus/rest/products/{name}")
    public ResponseEntity getProductsByPathVariable(@PathVariable("name") String name)
    {
        List<Product> products=productRepository.searchByName(name);

        return new ResponseEntity<>(products,HttpStatus.OK);
    }
}
