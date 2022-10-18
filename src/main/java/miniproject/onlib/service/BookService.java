package miniproject.onlib.service;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import miniproject.onlib.entity.Book;

@Component
public interface BookService {
	public abstract Book createBook(Book book);
	
	public abstract Book updateBook(Book book);
	
	public abstract Book getBook(Long id);
	
	public abstract List<Book> getBooks(Map<String, String> params);
	
	public abstract void deleteBook(Long id);
	
	public abstract Boolean isBookExist(Long id);
	
	public abstract Stream<Book> findAllBooks();
}
