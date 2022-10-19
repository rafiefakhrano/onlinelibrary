package miniproject.onlib.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import miniproject.onlib.entity.Book;
import miniproject.onlib.entity.BookTrack;

@Repository
public interface BookTrackRepository extends JpaRepository<Book, Long> {

	@Query("SELECT * FROM books_track where reffid = :reffid")
	List<BookTrack> getBookTrackByReffId(String reffid);
}
