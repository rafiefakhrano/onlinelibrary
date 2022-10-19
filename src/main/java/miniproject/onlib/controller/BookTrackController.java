package miniproject.onlib.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

import miniproject.onlib.entity.Book;
import miniproject.onlib.entity.BookTrack;
import miniproject.onlib.entity.Response;
import miniproject.onlib.service.BookTrackService;

@RestController
@RequestMapping("bookTrack")
public class BookTrackController {

	@Autowired
	private BookTrackService bookTrackService;
	
	@RequestMapping(value = "/bookTrack/{reffid}", method = RequestMethod.GET)
	public ResponseEntity<Response> getBook(@PathVariable("reffid") String reffId) {
		List<BookTrack> bookTrack = new ArrayList<>();
		try {
			if(bookTrackService.isBookTrackExist(reffId)) {
				bookTrack = bookTrackService.getBookByReffId(reffId);
			} else {
				Response response = new Response();
				response.setResponseCode("99");
				response.setMessage("BookTrack not found");
				response.setBody("");
				return new ResponseEntity<>(response, HttpStatus.OK);
			}
		} catch (Exception e) {
			return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
		}
		Response response = new Response();
		response.setResponseCode("200");
		response.setMessage("Success");
		Gson gson = new Gson();
		String responseBody = gson.toJson(bookTrack);
		response.setBody(responseBody);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}
}
