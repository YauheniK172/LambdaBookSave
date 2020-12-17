package com.amazonaws.lambda.book.service;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import com.amazonaws.lambda.book.model.Book;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClientBuilder;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;

public class DynamoDBSevice {
	public PutItemOutcome saveBook(Book book) {
		AmazonDynamoDB client = AmazonDynamoDBClientBuilder.defaultClient();

		DynamoDB dynamoDB = new DynamoDB(client);

		Table table = dynamoDB.getTable("Books");

		final Map<String, String> infoMap = new HashMap<String, String>();
		infoMap.put("name", book.getName());
		infoMap.put("author", book.getAuthor());

		String bookdId = UUID.randomUUID().toString();

		try {
			System.out.println("Adding a new item to dynamodb...");
			PutItemOutcome outcome = table
					.putItem(new Item().withPrimaryKey("BookId", bookdId).withMap("info", infoMap));

			System.out.println("Add to dynamodb succeeded");

			return outcome;

		} catch (Exception e) {
			throw new RuntimeException("Unable to add item to dynamodb: ", e);
		}
	}
}
