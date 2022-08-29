package com.feign.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@Component
@FeignClient(name="albumservice",path = "/album")
public interface AlbumFeign {

//    @GetMapping("/user/{id}")
//    JSObject findById(@PathVariable("id") Long id);

    @PostMapping(value = "/upload", consumes = "application/json")
    String uploadFile(String key, MultipartFile file);
}
