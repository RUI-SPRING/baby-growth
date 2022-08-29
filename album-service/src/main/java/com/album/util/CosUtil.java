package com.album.util;

import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.*;
import com.qcloud.cos.region.Region;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.net.URL;
import java.util.Date;
import java.util.List;

@Component
public class CosUtil {

    private final static Logger logger = LoggerFactory.getLogger(CosUtil.class);

    //腾讯云的SecretId
    private static String secretId;

    //腾讯云的SecretKey
    private static String secretKey;

    //腾讯云的bucket (存储桶)
    private static String bucketName;

    //腾讯云的region(bucket所在地区)
    private static String cosRegion;

    @Value("${spring.cos.SecretId}")
    public void setSecretId(String secretId){
        this.secretId = secretId;
    }
    @Value("${spring.cos.SecretKey}")
    public void setSecretKey(String secretKey){
        this.secretKey = secretKey;
    }
    @Value("${spring.cos.bucketName}")
    public void setBucketName(String bucketName){
        this.bucketName = bucketName;
    }

    @Value("${spring.cos.region}")
    public void setCosRegion(String cosRegion){
        this.cosRegion = cosRegion;
    }

    /**
     * 获取cos客户端
     *
     * @return COSClient
     */
    private static COSClient getCOSClient() {
        // 1 初始化用户身份信息（secretId, secretKey）。
        // SECRETID和SECRETKEY请登录访问管理控制台 https://console.cloud.tencent.com/cam/capi 进行查看和管理
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
        // 2 设置 bucket 的地域, COS 地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
        // clientConfig 中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者常见问题 Java SDK 部分。
        Region region = new Region(cosRegion);
        ClientConfig clientConfig = new ClientConfig(region);
        // 这里建议设置使用 https 协议
        // 从 5.6.54 版本开始，默认使用了 https
        clientConfig.setHttpProtocol(HttpProtocol.https);
        // 3 生成 cos 客户端。
        COSClient cosClient = new COSClient(cred, clientConfig);
        return cosClient;
    }

    static String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf(".") + 1);
    }


    /**
     * 上传图片到cos服务器
     * @param key
     * @param file
     * @return
     */
    public String upload(String key,MultipartFile file){
        // 生成cos客户端
        COSClient cosClient = getCOSClient();
        try {
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName ,key, FileUtil.MultipartFileToFile(file));
            PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
            //String etag = putObjectResult.getETag();
            URL url = cosClient.generatePresignedUrl(bucketName, key, new Date(new Date().getTime() + 5 * 60 * 10000));
            logger.info("文件上传成功！url："+url);
            return url.toString();
        }catch (Exception e){
            logger.info("文件上传失败！");
            e.printStackTrace();
        }finally {
            cosClient.shutdown();
        }
        return "false";
    }


    /**
     * 从cos下载图片
     */
    public void download(String key){
        // 生成cos客户端
        COSClient cosClient = getCOSClient();
        try {
            // 设置要下载到的本地路径
            File downFile = new File("test.jpg");
            // 设置要下载的文件所在的 对象桶的名称 和对象键
            GetObjectRequest getObjectRequest = new GetObjectRequest(bucketName, key);
            ObjectMetadata downObjectMeta = cosClient.getObject(getObjectRequest, downFile);
            System.out.println(downObjectMeta.toString());
        }catch (Exception e){
            e.printStackTrace();
        }finally {
            cosClient.shutdown();
        }

    }

    /**
     * 查看所有存储桶
     */
    public List<Bucket> buckets(){
        // 生成cos客户端
        COSClient cosClient = getCOSClient();
        List<Bucket> buckets = cosClient.listBuckets();
//        for (Bucket bucketElement : buckets) {
//            String bucketName = bucketElement.getName();
//            String bucketLocation = bucketElement.getLocation();
//            System.out.println(bucketName);
//            System.out.println(bucketLocation);
//        }
        cosClient.shutdown();
        return buckets;
    }

}
