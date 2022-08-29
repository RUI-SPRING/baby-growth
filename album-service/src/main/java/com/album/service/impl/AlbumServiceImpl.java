package com.album.service.impl;

import com.album.service.AlbumService;
import com.album.util.CosUtil;
import com.feign.client.UserFeign;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public class AlbumServiceImpl implements AlbumService {


    @Autowired
    private UserFeign userFeign;

    @Autowired
    private CosUtil cosUtil;

    @Override
    public String helloAlbum() {
        return userFeign.hello();
    }

    @Override
    public String uploadFile(String key, MultipartFile file){
        return cosUtil.upload(key,file);
    }

}
