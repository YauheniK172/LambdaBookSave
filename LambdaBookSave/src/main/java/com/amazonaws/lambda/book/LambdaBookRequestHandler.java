package com.amazonaws.lambda.book;

import com.amazonaws.lambda.book.model.Book;
import com.amazonaws.lambda.book.service.BookRequestParser;
import com.amazonaws.lambda.book.service.DynamoDBSevice;
import com.amazonaws.lambda.book.service.S3Service;
import com.amazonaws.services.lambda.runtime.Context;
import com.amazonaws.services.lambda.runtime.RequestHandler;

public class LambdaBookRequestHandler implements RequestHandler<String, String> {

	private BookRequestParser bookRequestParser = new BookRequestParser();

	private DynamoDBSevice dynamoDBSevice = new DynamoDBSevice();

	private S3Service s3Service = new S3Service();

	@Override
	public String handleRequest(String input, Context context) {
		context.getLogger().log("Input: " + input);

		Book bookModel = bookRequestParser.parseBookRequestXml(input);

		dynamoDBSevice.saveBook(bookModel);

		s3Service.saveBook(input);

		return "Item was successfyly saved!";
	}

}
