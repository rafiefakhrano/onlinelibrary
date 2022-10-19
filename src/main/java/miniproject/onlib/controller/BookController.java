package miniproject.onlib.controller;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import com.google.gson.Gson;

import miniproject.onlib.entity.Book;
import miniproject.onlib.entity.Response;
import miniproject.onlib.service.BookService;

@RestController
@RequestMapping("book")
public class BookController {

	@Autowired
	private BookService bookService;
	
	@RequestMapping(value = "/addBook", method = RequestMethod.POST)
	public ResponseEntity<Response> addBook(@RequestBody Book book) {
		try {
			book = bookService.createBook(book);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		Response response = new Response();
		response.setResponseCode("200");
		response.setMessage("Success");
		Gson gson = new Gson();
		String responseBody = gson.toJson(book);
		response.setBody(responseBody);
		return new ResponseEntity<Response>(response, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/updateBook", method = RequestMethod.POST)
	public ResponseEntity<Object> updateBook (@RequestBody Book book) {
		try {
			book = bookService.updateBook(book);
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		Response response = new Response();
		response.setResponseCode("200");
		response.setMessage("Success");
		Gson gson = new Gson();
		String responseBody = gson.toJson(book);
		response.setBody(responseBody);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/book/{id}", method = RequestMethod.GET)
	public ResponseEntity<Response> getBook(@PathVariable("id") Long id) {
		Book book = new Book();
		try {
			if(bookService.isBookExist(id)) {
				book = bookService.getBook(id);
			} else {
				Response response = new Response();
				response.setResponseCode("99");
				response.setMessage("Book not found");
				response.setBody(book.toString());
				return new ResponseEntity<>(response, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		Response response = new Response();
		response.setResponseCode("200");
		response.setMessage("Success");
		Gson gson = new Gson();
		String responseBody = gson.toJson(book);
		response.setBody(responseBody);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/books", method = RequestMethod.GET)
	public ResponseEntity<StreamingResponseBody> findAllBooks() {
		Gson gson = new Gson();
		 Stream<Book> books = bookService.findAllBooks();
		    StreamingResponseBody responseBody = httpResponseOutputStream -> {
		        try (Writer writer = new BufferedWriter(new OutputStreamWriter(httpResponseOutputStream))) {
		            books.forEach(book -> {
		                try {
		                    writer.write(gson.toJson(book));
		                    writer.flush();
		                } catch (IOException exception) {
		                    
		                }
		            });
		        } catch (Exception exception) {
		            
		        }
		        
		    };
		    return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.APPLICATION_JSON).body(responseBody);
	}
	
	
	@RequestMapping(value = "/filterBooks", method = RequestMethod.POST)
	public ResponseEntity<Response> getFilterBook(@RequestBody Map<String, String> map) {
		List<Book> book = new ArrayList<>();
		try {
			book = bookService.getBooks(map);
		} catch(Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		Response response = new Response();
		response.setResponseCode("200");
		response.setMessage("Success");
		Gson gson = new Gson();
		String responseBody = gson.toJson(book);
		response.setBody(responseBody);
		return new ResponseEntity<Response>(response, HttpStatus.CREATED);
	}
}
