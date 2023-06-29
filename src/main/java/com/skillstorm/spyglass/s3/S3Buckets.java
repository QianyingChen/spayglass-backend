package com.skillstorm.spyglass.s3;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "aws.s3")
public class S3Buckets {

    private String bucketName;

    public String getBucketName() {
        return bucketName;
    }

    public void setBucketName(String bucketName) {
        this.bucketName = bucketName;
    }
}
//@Configuration
//@ConfigurationProperties(prefix = "aws.s3")
//public class S3Buckets {
//
//    private String bucketName;
//    private String key;
//
//    public String getBucketName() {
//        return bucketName;
//    }
//
//    public void setBucketName(String bucketName) {
//        this.bucketName = bucketName;
//    }
//
//    public String getKey() {
//        return key;
//    }
//
//    public void setKey(String key) {
//        this.key = key;
//    }
//}

