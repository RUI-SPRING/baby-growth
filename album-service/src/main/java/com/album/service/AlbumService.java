package com.album.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * 跟相册，照片有关的service
 * createBy: Nanming
 * createTime: 2022/8/26
 */
public interface AlbumService {

    String helloAlbum();

    String uploadFile(String key, MultipartFile file);
}
