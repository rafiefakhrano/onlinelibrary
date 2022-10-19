package miniproject.onlib.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import miniproject.onlib.entity.BookTrack;
import miniproject.onlib.repository.BookTrackRepository;
import miniproject.onlib.service.BookTrackService;

public class BookTrackServiceImpl implements BookTrackService{
	
	@Autowired
	private BookTrackRepository bookTrackRepository;

	@Override
	public List<BookTrack> getBookByReffId(String reffId) {
		return bookTrackRepository.getBookTrackByReffId(reffId);
	}

	@Override
	public boolean isBookTrackExist(String reffId) {
		List<BookTrack> bookTrack = bookTrackRepository.getBookTrackByReffId(reffId);
		boolean isBookTrackExist = false;
		if(bookTrack.size() > 0) {
			isBookTrackExist = true;
		}
		return false;
	}
}
