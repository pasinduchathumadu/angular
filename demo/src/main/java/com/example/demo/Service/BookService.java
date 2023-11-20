package com.example.demo.Service;

import com.example.demo.Entity.BookEntity;
import com.example.demo.ErrorHandling.BookException;
import com.example.demo.Repository.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {

    @Autowired
    private BookRepository bookRepository;


    public List<BookEntity> getAllBooks() {

        return bookRepository.findAll();
    }

    public String addBook(BookEntity bookEntity) {
        try {
            if (isCheck(bookEntity.getBookName()) || isCheck(bookEntity.getAuthor())) {
                throw new BookException("Invalid Book Data");
            }
            bookRepository.save(bookEntity);
            return "Book added successfully";

        } catch (Exception e) {
            throw new BookException("Error adding book" + e.getMessage());
        }


    }


    public List<BookEntity> updateBook(Integer Id, Integer price) {

        try {
            if (price == null) {
                throw new BookException("Invalid Book Data");
            }
            if (!bookRepository.existsById(Id)) {
                throw new BookException("Can not be updated");
            }
            BookEntity existing = bookRepository.findById(Id)
                    .orElseThrow(() -> new BookException("Error in updating book. Existing book is null."));

            if (existing != null) {
                existing.setPrice(price);
                bookRepository.save(existing);
                return bookRepository.findAll();
            }
            else{
                throw  new BookException("Error in updating book. Existing book is null.");
            }


        } catch (Exception e) {
            throw new BookException("Error in updating book");
        }
    }

    private boolean isCheck(String value) {
        return value.trim().isEmpty();
    }

    private boolean isCheckNumber(Integer id) {
        return id != null;

    }

    public List<BookEntity> deleteBook(Integer id) {
        try {
            if (!bookRepository.existsById(id)) {
                throw new BookException("This process can't be done");
            }
            bookRepository.deleteById(id);
            return bookRepository.findAll();

        } catch (Exception e) {
            throw new BookException("Error in deleted!!");
        }
    }

    public List<BookEntity> searchItem(String item) {
        List<BookEntity> book = bookRepository.findAll();
        List<BookEntity> newSearchBook = book.stream().filter(bookEntity -> bookEntity.getAuthor().contains(item)).collect(Collectors.toList());
        return newSearchBook;
    }
    public List<BookEntity> searchBookName(String item) {
        List<BookEntity> book = bookRepository.findAll();
        List<BookEntity> newSearchBook = book.stream().filter(bookEntity -> bookEntity.getBookName().contains(item)).collect(Collectors.toList());
        return newSearchBook;
    }

    public List<String> selectCategory(){
        List<BookEntity> book = bookRepository.findAll();

        return book.stream().map(bookEntity -> bookEntity.getCategory().trim().toLowerCase()).distinct().collect(Collectors.toList());
    }

    public long selectCount() {
        return bookRepository.count();
    }
}
