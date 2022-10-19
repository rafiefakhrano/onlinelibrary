package miniproject.onlib.service;

import java.util.List;

import org.springframework.stereotype.Component;

import miniproject.onlib.entity.Book;
import miniproject.onlib.entity.BookTrack;

@Component
public interface BookTrackService {
	
	public abstract List<BookTrack> getBookByReffId(String reffid);

	public abstract boolean isBookTrackExist(String reffId);
}
