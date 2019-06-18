package com.liy;

import org.springframework.web.bind.annotation.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;


@RestController
public class BookController {


    @GetMapping("/book/{ids}")
    public List<Book> books(@PathVariable String ids){
        System.out.println(ids);
        String[] split = ids.split(",");
        List<Book> books = new ArrayList<>();
        for (String id: split) {
            Book book = new Book();
            book.setId(Integer.parseInt(id));
            books.add(book);
        }
        return books;
    }

    @GetMapping("/book")
    public Book getBook(int id){
        Book book = new Book();
        book.setId(id);
        System.out.println("id>>>"+id);
        return book;
    }

    @PostMapping("/book")
    public void postBook(@RequestBody Book book){
        System.out.println("post>"+book);
    }


    @PutMapping("/book")
    public void putBook(@RequestBody Book book){
        System.out.println("put>"+book);

    }


    @DeleteMapping("/book/{id}")
    public void delete(@PathVariable int id){
        System.out.println("delete>"+id);
    }

    @PutMapping(value = "/book2")
    public void update(@RequestHeader("name") String name ,@RequestHeader("id") int id) throws UnsupportedEncodingException {
        String decode = URLDecoder.decode(name, "GBK");
        System.out.println(decode);
        Book book = new Book();
        book.setId(id);
        book.setName(decode);
        System.out.println("put2 header>"+book);
    }
}
