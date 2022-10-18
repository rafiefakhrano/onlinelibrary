package miniproject.onlib.repository;

import java.util.Date;
import java.util.List;
import java.util.stream.Stream;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import miniproject.onlib.entity.Book;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

	void deleteById(int id);
	
	@Query("SELECT * FROM books WHERE name like '%:name%'AND author LIKE '%:author%'AND price BETWEEN :pricefrom AND :pricetoAND publisher LIKE '%:publisher%'AND stock BETWEEN :stockfrom AND :stocktoAND active = :isBookActiveAND owned_date BETWEEN :owneddatefrom BETWEEN :owneddateto")
	List<Book> getBooksFilter(String name, String author, Double pricefrom, Double priceto, String publisher, int stockfrom, int stockto, boolean isBookActive, Date owneddatefrom, Date owneddateto);
	
    @Query("SELECT * FROM books")
    Stream<Book> getAllBook();
}
