package com.example.demo.Controller;

import com.example.demo.DTO.updateDTO;
import com.example.demo.Entity.BookEntity;
import com.example.demo.ErrorHandling.BookException;
import com.example.demo.Service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.hibernate.tool.schema.SchemaToolingLogging.LOGGER;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1")
public class BookController {

    @Autowired
    private BookService bookService;


    @GetMapping("/books")
    public List<BookEntity>getAllBooks(){
        return bookService.getAllBooks();
    }
    @PutMapping("updateBook/{Id}")
    public ResponseEntity<?> updateBook(@PathVariable Integer Id , @RequestBody updateDTO updatedto){

        try{
            List<BookEntity> result = bookService.updateBook(Id,updatedto.getPrice());
            return new ResponseEntity<>(result,HttpStatus.OK);
        }catch (BookException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }  catch(Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/deleteBook/{Id}")
    public ResponseEntity<?>deleteBook(@PathVariable Integer Id ){
        try{
            List<BookEntity> result = bookService.deleteBook(Id);
            return  new ResponseEntity<>(result,HttpStatus.OK);

        }catch (BookException e){
            return  new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/addBook")
    public ResponseEntity<?> addBook(@RequestBody BookEntity bookEntity){
        try{
            String result = bookService.addBook(bookEntity);
            return new ResponseEntity<>(result , HttpStatus.OK);
        }catch (BookException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/search/{item}")
    public ResponseEntity<?> search(@PathVariable String item){
        try{
            List<BookEntity> book = bookService.searchItem(item);
            return new ResponseEntity<>(book,HttpStatus.OK);

        }catch (BookException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/searchBookName/{item}")
    public ResponseEntity<?> searchBookName(@PathVariable String item){
        try{
            List<BookEntity> book = bookService.searchBookName(item);
            return new ResponseEntity<>(book,HttpStatus.OK);

        }catch (BookException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/selectCategory")
    public ResponseEntity<?>selectCategory(){

        try{
            List<String> book =bookService.selectCategory();
            return new ResponseEntity<>(book,HttpStatus.OK);

        }catch (BookException e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.BAD_REQUEST);
        }catch (Exception e){
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("/SelectCount")
    public ResponseEntity<?> selectCount() {
        try {
            long num = bookService.selectCount();
            return new ResponseEntity<>(num, HttpStatus.OK);
        } catch (BookException e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
