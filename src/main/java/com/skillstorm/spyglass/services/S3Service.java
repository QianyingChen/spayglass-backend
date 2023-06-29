package com.skillstorm.spyglass.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.skillstorm.spyglass.s3.S3Buckets;

import software.amazon.awssdk.core.ResponseInputStream;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.GetObjectResponse;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import java.io.IOException;
import java.io.InputStream;


@Service
public class S3Service {
	
	private final S3Client s3Client;
	private final String bucketName;

	public S3Service(S3Client s3Client, S3Buckets s3Buckets) {
		this.s3Client = s3Client;
		this.bucketName = s3Buckets.getBucketName();
	}

//	public void putObject(String bucketName, String key, byte[] file) {
//		PutObjectRequest objectRequest = PutObjectRequest.builder()
//				.bucket(bucketName)
//				.key(key)
//				.build();
//		s3Client.putObject(objectRequest, RequestBody.fromBytes(file));
//	}
	  public void putObject(String bucketName, String key, byte[] file, String contentType) {
		    PutObjectRequest objectRequest = PutObjectRequest.builder()
		        .bucket(bucketName)
		        .key(key)
		        .contentType(contentType) // Set the content type of the file
		        .build();
		    s3Client.putObject(objectRequest, RequestBody.fromBytes(file));
		  }

	public byte[] getObject(String bucketName, String key) {
		GetObjectRequest getObjectRequest = GetObjectRequest.builder()
				.bucket(bucketName)
				.key(key)
				.build();

		ResponseInputStream<GetObjectResponse> response = s3Client.getObject(getObjectRequest);

		try (InputStream inputStream = response) {
			return inputStream.readAllBytes();
		} catch (IOException e) {
			throw new RuntimeException("Failed to read object data", e);
		}
	}
}

//@Service
//public class S3Service {
//	
//    private final S3Client s3;
//    private final String bucketName;
//
//    public S3Service(S3Client s3, S3Buckets s3Buckets) {
//        this.s3 = s3;
//        this.bucketName = s3Buckets.getBucketName();
//    }
//
//    public void putObject(String bucketName, String key, byte[] file) {
//        PutObjectRequest objectRequest = PutObjectRequest.builder()
//                .bucket(bucketName)
//                .key(key)
//                .build();
//        s3.putObject(objectRequest, RequestBody.fromBytes(file));
//    }
//
//    public byte[] getObject(String bucketName, String key) {
//        GetObjectRequest getObjectRequest = GetObjectRequest.builder()
//                .bucket(bucketName)
//                .key(key)
//                .build();
//
//        ResponseInputStream<GetObjectResponse> res = s3.getObject(getObjectRequest);
//
//        try {
//            return res.readAllBytes();
//        } catch (IOException e) {
//            throw new RuntimeException(e);
//        }
//
//    }
//
//}
