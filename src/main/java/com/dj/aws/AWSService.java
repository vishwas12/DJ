package com.dj.aws;

import java.io.InputStream;
import java.net.URL;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.amazonaws.AmazonServiceException;
import com.amazonaws.HttpMethod;
import com.amazonaws.SdkClientException;
import com.amazonaws.auth.AWSCredentials;
import com.amazonaws.auth.AWSStaticCredentialsProvider;
import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;
import com.amazonaws.services.s3.Headers;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.GeneratePresignedUrlRequest;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.dj.utils.Constants;

@Component
public class AWSService {


    @Value("${aws.accessId}")
    private String accessId;

    @Value("${aws.secret}")
    private String secret;

    @Value("${aws.media}")
    private String contentBucket;
    
    private static final String MEDIA_IMAGE = "/images/";
    
    private static final String MEDIA_VIDEO = "/videos/";
    
    private static final String MEDIA_AUDIO = "/audio/";
    
    private static final String MEDIA_OTHER = "/other/";

    AWSCredentials credentials;
    AmazonS3 s3client;

    public String getContentKeyName(String type) {
        String keyName = null;

        if (Constants.MediaType.IMAGE.getKey().equalsIgnoreCase(type)) {
            keyName = Constants.MediaType.IMAGE.getValue();
        }else if (Constants.MediaType.VIDEO.getKey().equalsIgnoreCase(type)) {
            keyName = Constants.MediaType.VIDEO.getValue();
        }else if (Constants.MediaType.AUDIO.getKey().equalsIgnoreCase(type)) {
            keyName = Constants.MediaType.AUDIO.getValue();
        }else {
            keyName = Constants.MediaType.OTHER.getValue();
        }

        return keyName;
    }

    @PostConstruct
    public void init() {
       credentials = new BasicAWSCredentials(accessId, secret);
        s3client = AmazonS3ClientBuilder.standard().withCredentials(new AWSStaticCredentialsProvider(credentials))
                .withRegion(Regions.US_EAST_1).build();
    }

    public String uploadContentFile(String fileName, String type, InputStream stream, ObjectMetadata metadata,Long userId) throws
            SdkClientException,
            AmazonServiceException {
        String keyName = userId + getContentKeyName(type) + fileName;
        s3client.putObject(contentBucket, keyName, stream, metadata);
        return s3client.getUrl(contentBucket, keyName).toString();
    }

    public String generateContentS3TempUrl(String type, String fileName,String userId) {
        java.util.Date expiration = new java.util.Date();
        long msec = expiration.getTime();
        msec += 15 * 60 * 1000;
        expiration.setTime(msec);
        String keyName = userId + getContentKeyName(type) + fileName;

        GeneratePresignedUrlRequest generatePresignedUrlRequest =
                new GeneratePresignedUrlRequest(contentBucket,keyName);
        generatePresignedUrlRequest.setMethod(HttpMethod.PUT);
        generatePresignedUrlRequest.setExpiration(expiration);
        generatePresignedUrlRequest.addRequestParameter(
                Headers.S3_CANNED_ACL,
                CannedAccessControlList.PublicRead.toString()
        );


        URL url = s3client.generatePresignedUrl(generatePresignedUrlRequest);
        return url.toString();

    }


}

