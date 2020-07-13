package Demo;

import com.amazonaws.auth.BasicAWSCredentials;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;
import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.PutObjectRequest;
import com.amazonaws.services.s3.model.PutObjectResult;
import org.json.JSONObject;

import java.io.*;
import java.util.Scanner;

public class Lambda1 implements RequestHandler<Integer, String> {


	public String handleRequest(Integer myCount, Context context){
		BasicAWSCredentials awsCreds = new BasicAWSCredentials(Credentials.access_key_id, Credentials.secret_access_key);

		AmazonS3Client s3Client = new AmazonS3Client(awsCreds);

		String newBucketName = "test" + System.currentTimeMillis();


		s3Client.createBucket(newBucketName);
		String jsonString = new JSONObject()
				.put("JSON1", "Hello World!")
				.toString();

		PutObjectResult response1 = s3Client.putObject(newBucketName, "file." + System.currentTimeMillis(), jsonString);
		System.out.println("Uploaded object encryption status is " +
				response1.getSSEAlgorithm());
		return String.valueOf(myCount);
	}
}


//public class Lambda1{
//	public static void main(String[] args) {
//
//	}
//}