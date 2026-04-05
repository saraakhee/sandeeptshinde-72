package com.example.demo.controller;

import com.example.demo.annotation.LogType;
import com.example.demo.exception.ProductNotfoundException;
import com.example.demo.model.MessageContext;
import com.example.demo.model.Product;
import com.example.demo.service.MiddlewareService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;


@RestController
@RequestMapping(value = "/say")
public class DemoApplication {
    private static final Logger logger = LoggerFactory.getLogger(DemoApplication.class);
    MiddlewareService middlewareService;
    DemoApplication(MiddlewareService middlewareService) {
        this.middlewareService = middlewareService;
    }
    @Value("${spring.application.name}")
    String appname;
    private static Map<String, Product> productRepo = new HashMap<>();
    static{
        Product honey = new Product();
        honey.setId("1");
        honey.setName("Honey");
        productRepo.put(honey.getId(), honey);

        Product almond = new Product();
        almond.setId("2");
        almond.setName("Almond");
        productRepo.put(almond.getId(), almond);
    }
    /* @GetMapping("/hello")
       public String hello(){
         return "Hello my name is "+appname;
    }*/
    @GetMapping("/products/get")
    //@LogType(value="Sandeep",number=5)
    public ResponseEntity<Object> getProduct() {
    //public ResponseEntity<Object> getProduct(@RequestHeader("Authorization") String authHeader) {
        //String jwt = authHeader.substring(7);
        return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);
    }
    @GetMapping("/products/get/{id}")
    public ResponseEntity<Object> getProductById(@PathVariable("id") String id) throws ProductNotfoundException {
        if(!productRepo.containsKey(id)){
            String errormsg = "Could not find Data with provided key :"+id;
            throw new ProductNotfoundException(errormsg);
        }
        return new ResponseEntity<>(productRepo.get(id), HttpStatus.OK);
    }
    @PostMapping("/products/post")
    public ResponseEntity<Object> createProduct(@RequestBody Product product){
        productRepo.put(product.getId(),product);
        return new ResponseEntity<>("Product is created successfully",HttpStatus.CREATED);
    }
    /*@PostMapping("/products/batch")
    public ResponseEntity<BatchProductResponse> batchCreate(@RequestBody BatchProductRequest request) {
        return new ResponseEntity<>(productRepo.values(), HttpStatus.OK);
    }*/
    @PutMapping("/products/put/{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable("id") String id,@RequestBody Product product){
        productRepo.remove(id);
        productRepo.put(product.getId(),product);
        return new ResponseEntity<>("Product is updated successfully",HttpStatus.CREATED);
    }
    @PostMapping("/message/post")
    public ResponseEntity<Object> processMessage(@RequestBody MessageContext msg){
        String uniqueId = UUID.randomUUID().toString();
        MessageContext message = middlewareService.process(uniqueId,msg.getPayload());
        return new ResponseEntity<>("Product is created successfully",HttpStatus.CREATED);
    }
}
