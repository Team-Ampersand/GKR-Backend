package Ampersand.GKR.global.logger;

import Ampersand.GKR.domain.file.exception.FileUploadFailedException;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.model.CannedAccessControlList;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.amazonaws.services.s3.model.PutObjectRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Component
@RequiredArgsConstructor
public class LoggingScheduler {

    private final AmazonS3 amazonS3;

    @Value("${cloud.aws.s3.bucket}")
    private String bucket;

    @Value("${cloud.aws.s3.url}")
    private String url;

    @Scheduled(cron = "59 59 23 * * ?", zone = "Asia/Seoul")
    public void sendLog() {

        String logDir = "./src/main/resources/logs/";

        File logDirectory = new File(logDir);

        List<String> logUrlList = new ArrayList<>();

        for(File file : logDirectory.listFiles()) {

            String fileName = file.getName();

            ObjectMetadata objectMetadata = new ObjectMetadata();

            objectMetadata.setContentLength(file.length());

            objectMetadata.setContentType("text/plain");

            try(InputStream inputStream = new FileInputStream(file)) {

                amazonS3.putObject(
                        new PutObjectRequest(bucket, fileName, inputStream, objectMetadata)
                                .withCannedAcl(CannedAccessControlList.PublicRead));
            } catch (IOException e) {

                throw new FileUploadFailedException();
            }

            file.delete();

            logUrlList.add(url + fileName);
        }
    }
}