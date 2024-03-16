package com.essentials.services;

import com.essentials.converter.DozerConverter;
import com.essentials.data.VOv1.BookVOV1;
import com.essentials.data.model.Book;
import com.essentials.exceptions.ResourceNotFoundException;
import com.essentials.repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.logging.Logger;

@Service
public class BookService {
    //record log messages related to that specific class.
    // This is useful for tracking the behavior of your service and diagnosing problems while it's running.
    private Logger logger=Logger.getLogger(BookService.class.getName());

    @Autowired
    private BookRepository bookRepository;


    public List<BookVOV1> findAll(){
        logger.info("Finding all books!");

        return DozerConverter.parseListObjects(bookRepository.findAll(), BookVOV1.class);
    }

    public BookVOV1 findById(Long id){
        logger.info("Finding one book!");

        var entity= bookRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("No records found for this id"));
        return DozerConverter.parseObject(entity, BookVOV1.class);
    }

    public BookVOV1 createBook(BookVOV1 book){
        logger.info("Create one book!");

        // You need to go to the model, and then convert the data to VO
        var entity= DozerConverter.parseObject(book, Book.class);

        var vo= DozerConverter.parseObject(bookRepository.save(entity), BookVOV1.class);
        return vo;
    }

    public BookVOV1 updatedBook(BookVOV1 book){
        logger.info("Updated book!");

        // model  atribbuted object Books, for after conversion VOV1
        Book entity=bookRepository.findById(book.getKey()).orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));

        entity.setAuthor(book.getAuthor());
        entity.setLaunchDate(book.getLaunchDate());
        entity.setTitle(book.getTitle());
        entity.setPrice(book.getPrice());


        var vo= DozerConverter.parseObject(bookRepository.save(entity), BookVOV1.class);
        return vo;
    }

    public void delete(Long id) {
        logger.info("Delete one book!");

        var entity=bookRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("No records found for this id"));
        bookRepository.delete(entity);
    }
}
