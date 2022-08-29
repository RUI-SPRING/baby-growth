import com.qcloud.cos.COSClient;
import com.qcloud.cos.ClientConfig;
import com.qcloud.cos.auth.BasicCOSCredentials;
import com.qcloud.cos.auth.COSCredentials;
import com.qcloud.cos.exception.CosClientException;
import com.qcloud.cos.exception.CosServiceException;
import com.qcloud.cos.http.HttpProtocol;
import com.qcloud.cos.model.*;
import com.qcloud.cos.region.Region;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.swing.*;
import java.io.File;
import java.net.URL;
import java.util.Date;
import java.util.List;

@SpringBootTest
public class AlbumServiceTest {


    @Test
    public void cosTest(){
        // 1 初始化用户身份信息（secretId, secretKey）。
// SECRETID和SECRETKEY请登录访问管理控制台 https://console.cloud.tencent.com/cam/capi 进行查看和管理
        String secretId = "AKIDZzCAsLceS0Xw680pPvFpIYdXHoFeswnQ";
        String secretKey = "UkphMo5A1f5KNCnDRRDmBNM7tdKsCIAo";
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
// 2 设置 bucket 的地域, COS 地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
// clientConfig 中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者常见问题 Java SDK 部分。
        Region region = new Region("ap-shanghai");
        ClientConfig clientConfig = new ClientConfig(region);
// 这里建议设置使用 https 协议
// 从 5.6.54 版本开始，默认使用了 https
        clientConfig.setHttpProtocol(HttpProtocol.https);
// 3 生成 cos 客户端。
        COSClient cosClient = new COSClient(cred, clientConfig);

        String bucket = "jiezishubucket-1313361436"; //存储桶名称，格式：BucketName-APPID


        List<Bucket> buckets = cosClient.listBuckets();
        for (Bucket bucketElement : buckets) {
            String bucketName = bucketElement.getName();
            String bucketLocation = bucketElement.getLocation();
            System.out.println(bucketName);
            System.out.println(bucketLocation);
        }

        String key="test";
        File localFile = new File("C:\\Users\\GeXiaoRui\\Downloads\\1264738.jpg");

        String suffix = localFile.getName().substring(localFile.getName().lastIndexOf(".") + 1);

        PutObjectRequest putObjectRequest = new PutObjectRequest(bucket ,key, localFile);
        PutObjectResult putObjectResult = cosClient.putObject(putObjectRequest);
        String etag = putObjectResult.getETag();

        URL url = cosClient.generatePresignedUrl(bucket, key, new Date(new Date().getTime() + 5 * 60 * 10000));
        System.out.println(url.toString());
        System.out.println(url.getFile());
        System.out.println(etag);

    }

    @Test
    public void cosDownLoadTest(){
        // 1 初始化用户身份信息（secretId, secretKey）。
// SECRETID和SECRETKEY请登录访问管理控制台 https://console.cloud.tencent.com/cam/capi 进行查看和管理
        String secretId = "AKIDZzCAsLceS0Xw680pPvFpIYdXHoFeswnQ";
        String secretKey = "UkphMo5A1f5KNCnDRRDmBNM7tdKsCIAo";
        COSCredentials cred = new BasicCOSCredentials(secretId, secretKey);
// 2 设置 bucket 的地域, COS 地域的简称请参照 https://cloud.tencent.com/document/product/436/6224
// clientConfig 中包含了设置 region, https(默认 http), 超时, 代理等 set 方法, 使用可参见源码或者常见问题 Java SDK 部分。
        Region region = new Region("ap-shanghai");
        ClientConfig clientConfig = new ClientConfig(region);
// 这里建议设置使用 https 协议
// 从 5.6.54 版本开始，默认使用了 https
        clientConfig.setHttpProtocol(HttpProtocol.https);
// 3 生成 cos 客户端。
        COSClient cosClient = new COSClient(cred, clientConfig);

        String bucket = "jiezishubucket-1313361436"; //存储桶名称，格式：BucketName-APPID

        List<Bucket> buckets = cosClient.listBuckets();
        for (Bucket bucketElement : buckets) {
            String bucketName = bucketElement.getName();
            String bucketLocation = bucketElement.getLocation();
            System.out.println(bucketName);
            System.out.println(bucketLocation);
        }

        String key="test";
        // 设置要下载到的本地路径
        File downFile = new File("test.jpg");
        // 设置要下载的文件所在的 对象桶的名称 和对象键
        GetObjectRequest getObjectRequest = new GetObjectRequest(bucket, key);
        ObjectMetadata downObjectMeta = cosClient.getObject(getObjectRequest, downFile);
        System.out.println(downObjectMeta.toString());

    }
}
