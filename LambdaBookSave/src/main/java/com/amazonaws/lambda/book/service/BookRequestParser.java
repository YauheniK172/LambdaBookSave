package com.amazonaws.lambda.book.service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.amazonaws.lambda.book.model.Book;

public class BookRequestParser {
	public Book parseBookRequestXml(String bookRequest) {
		Book bookModel = null;
		try {
			JAXBContext jaxbContext = JAXBContext.newInstance(Book.class);

			InputStream bookStream = new ByteArrayInputStream(bookRequest.getBytes(StandardCharsets.UTF_8));

			Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
			bookModel = (Book) jaxbUnmarshaller.unmarshal(bookStream);
		} catch (JAXBException e) {
			throw new RuntimeException("Error during parsing book request xml: ", e);
		}

		return bookModel;
	}
}
