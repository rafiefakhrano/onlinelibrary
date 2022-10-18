package miniproject.onlib.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Stream;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.mvc.method.annotation.StreamingResponseBody;

import miniproject.onlib.entity.Book;
import miniproject.onlib.repository.BookRepository;
import miniproject.onlib.service.BookService;

public class BookServiceImpl implements BookService{

	EntityManager em;
	
	@Autowired
	private BookRepository bookRepository;
	
	@Autowired
	private JdbcTemplate jdbcTemplate;
	
	@Override
	public Book createBook(Book book) {
		return bookRepository.save(book);
	}

	@Override
	public Book updateBook(Book book) {
		return bookRepository.save(book);
		
	}

	@Override
	public Book getBook(Long id) {
		Optional<Book> optional = bookRepository.findById(id);
		Book book = optional.get();
		return book;
	}

	@Override
	public void deleteBook(Long id) {
		bookRepository.deleteById(id);
	}
	
	@Override
	public List<Book> getBooks(Map<String, String> params) {
		Double pricefrom = 0.00;
		Double priceto = 9999999.00;
		int stockfrom = 0;
		int stockto = 999999999;
		boolean isBookActive = false;
		SimpleDateFormat format = new SimpleDateFormat("dd-MM-yyy");
		String dateSting = "01-01-1900";
		Date owneddatefrom = null;
		Date owneddateto = null;
		String owneddatetoString = "";
		try {
			owneddatefrom = format.parse(dateSting);
			owneddatetoString = format.format(new Date());
			owneddateto = format.parse(owneddatetoString);
		} catch (ParseException e1) {
			
		}
		 

		if(!params.get("pricefrom").equals("0.00")) {
			pricefrom = Double.valueOf(params.get("pricefrom"));
		}
		if(!params.get("priceto").equals("0.00")) {
			priceto = Double.valueOf(params.get("priceto"));
		}
		if(!params.get("stockfrom").equals("0")) {
			stockfrom = Integer.valueOf(params.get("stockfrom"));
		}
		if(!params.get("stockto").equals("0")) {
			stockto = Integer.valueOf(params.get("stockto"));
		}
		if(!params.get("isBookActive").equals("false")) {
			isBookActive = Boolean.valueOf(params.get("isBookActive"));
		}
		if(!params.get("isBookActive").equals("false")) {
			isBookActive = Boolean.valueOf(params.get("isBookActive"));
		}
		if(!params.get("owneddatefrom").equals("01-01-1900")) {
			try {
				owneddatefrom = format.parse(params.get("owneddatefrom"));
			} catch (ParseException e) {
				
			}
		}
		if(!params.get("owneddateto").equals(owneddatetoString)) {
			try {
				owneddateto = format.parse(params.get("owneddateto"));
			} catch (ParseException e) {
				
			}
		}
		return bookRepository.getBooksFilter(params.get("name"), params.get("author"), pricefrom, priceto, params.get("publisher"), stockfrom, stockto, isBookActive, owneddatefrom, owneddateto);
	}

	@Override
	public Boolean isBookExist(Long id) {
		return bookRepository.existsById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public Stream<Book> findAllBooks() {
		jdbcTemplate.setFetchSize(10);
	    return jdbcTemplate.queryForStream("Select * from books",
	            (resultSet, rowNum) ->
        new Book(resultSet.getLong("id"), resultSet.getString("name"), resultSet.getString("author"), resultSet.getDouble("price"), resultSet.getString("publisher"), resultSet.getInt("stock") , resultSet.getBoolean("active"),
    			resultSet.getDate("owned_date"))
	    );
	}


}
