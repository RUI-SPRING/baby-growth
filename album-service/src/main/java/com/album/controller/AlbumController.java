package com.album.controller;


import com.album.config.AlbumProperties;
import com.album.service.AlbumService;
import com.feign.common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


/**
 * 相册表现类
 * createBy：NanMing
 * createTime：2022/8/26
 */
@Api(description = "相册图片服务")
@RestController
@RequestMapping("/album")
public class AlbumController {

    @Autowired
    private AlbumService albumService;

    @Autowired
    private AlbumProperties albumProperties;

    @RequestMapping("/hello")
    public Result helloAlbum(){
        System.out.println(albumProperties.getDateformat());
        return Result.success(albumService.helloAlbum());
    }

    @ApiOperation(value = "文件上传")
    @ApiImplicitParams({@ApiImplicitParam(name = "key", value = "图片key", required = true, dataType = "String"),
            @ApiImplicitParam(name = "file", value = "图片文件", required = true, dataType = "MultipartFile")})
    @RequestMapping("/upload")
    public Result uploadFile(String key, MultipartFile file){
        return Result.success(albumService.uploadFile(key,file));
    }

}
