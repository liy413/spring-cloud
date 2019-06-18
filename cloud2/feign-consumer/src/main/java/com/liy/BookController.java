package com.liy;

import com.netflix.discovery.converters.Auto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@RestController
public class BookController{

  /*  @Autowired
    BookService bs;

    @GetMapping("/book1")
    public Book book1() throws UnsupportedEncodingException {
        Book book = new Book();
        book.setId(12);
        book.setName("liy");
        bs.book2(book);
        bs.book3(book);
        bs.book4(14);
        String name = URLDecoder.decode("李预", "GBK");
        bs.book5(name,15);
        return bs.book1(11);
    }*/

  @Autowired
  UserService us;

  @GetMapping("/user")
  public User user(){
      return us.user(80);
  }
}
