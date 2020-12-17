package com.amazonaws.lambda.book.service;

import java.util.UUID;

import com.amazonaws.regions.Regions;
import com.amazonaws.services.s3.AmazonS3;
import com.amazonaws.services.s3.AmazonS3ClientBuilder;

public class S3Service {
	public void saveBook(String book) {
		Regions clientRegion = Regions.US_EAST_1;
		String bucketName = "books-files-bucket";
		String stringObjKeyName = UUID.randomUUID().toString();

		AmazonS3 s3Client = AmazonS3ClientBuilder.standard().withRegion(clientRegion).build();

		System.out.println("Adding a new item to s3 bucket...");

		s3Client.putObject(bucketName, stringObjKeyName, book);

		System.out.println("Add to s3 bucket succeeded");
	}
}
