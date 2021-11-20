package com.syz.es.springdata.controllers;


import com.syz.es.springdata.models.Book;
import com.syz.es.springdata.repositories.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 *  @author: syz
 *  @Date: 2021/11/8 12:22
 *  @Description:
 */ 

@RequestMapping("/book")
@RestController
public class BookController {

    @Autowired
    BookRepository bookRepository;

    @RequestMapping(value = "/add_index", method = RequestMethod.POST)
    public ResponseEntity<String> indexDoc(@RequestBody Book book) {
        System.out.println("book===" + book);
         bookRepository.save(book);
        return new ResponseEntity<>("save executed!", HttpStatus.OK);
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ResponseEntity<Iterable> getAll() {
        Iterable<Book> all = bookRepository.findAll();
        return new ResponseEntity<>(all, HttpStatus.OK);
    }

    @RequestMapping(value = "/name", method = RequestMethod.GET)
    public ResponseEntity<Book> getByName(@RequestParam("name") String name) {
        Book book = bookRepository.findByName(name);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    public ResponseEntity<Book> updateBook(@PathVariable("id") String id,
                                           @RequestBody Book updateBook) {
        Book book = bookRepository.findBookById(id);
      /*  if (StringUtils.isNotBlank(updateBook.getId())) {
            book.setId(updateBook.getId());
        }
        if (StringUtils.isNotBlank(updateBook.getName())) {
            book.setName(updateBook.getName());
        }
        if (StringUtils.isNotBlank(updateBook.getAuthor())) {
            book.setAuthor(updateBook.getAuthor());
        }*/
        bookRepository.save(book);
        return new ResponseEntity<>(book, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteBook(@PathVariable("id") String id) {
        bookRepository.deleteById(id);
        return new ResponseEntity<>("delete execute!", HttpStatus.OK);
    }
}
