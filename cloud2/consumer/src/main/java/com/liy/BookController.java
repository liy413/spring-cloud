package com.liy;

import com.netflix.hystrix.strategy.concurrency.HystrixRequestContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;

@RestController
public class BookController {

    @Autowired
    @Qualifier("rs2")
    RestTemplate rs;

    @Autowired
    BookService bs;

    @GetMapping("/test")
    public String bstest(){
        return bs.hello();
    }

    @GetMapping("/test6")
    public void test6() throws ExecutionException, InterruptedException {
        HystrixRequestContext hrc = HystrixRequestContext.initializeContext();

        BookCollapseCommand bc1 = new BookCollapseCommand(bs, 12);
        BookCollapseCommand bc2 = new BookCollapseCommand(bs, 13);
        BookCollapseCommand bc3 = new BookCollapseCommand(bs, 14);
        BookCollapseCommand bc4 = new BookCollapseCommand(bs, 15);

        Future<Book> q1 = bc1.queue();
        Future<Book> q2 = bc2.queue();
        Future<Book> q3 = bc3.queue();
        Future<Book> q4 = bc4.queue();

        Book b1 = q1.get();
        Book b2 = q2.get();
        Book b3 = q3.get();
        Book b4 = q4.get();

        System.out.println(b1);
        System.out.println(b2);
        System.out.println(b3);
        System.out.println(b4);

        hrc.close();
    }

    @GetMapping("/testb")
    public void  bstestB() throws ExecutionException, InterruptedException {
        HystrixRequestContext hrc = HystrixRequestContext.initializeContext();
        Future<Book> f = bs.hello2(23);
        Future<Book> f2 = bs.hello2(23);
        Book book = f.get();
        Book book2 = f2.get();
        System.out.println(book);
        System.out.println(book2);
        hrc.close();
    }

    @GetMapping("/comm")
    public void testCommand() throws Exception {
        HystrixRequestContext hrc = HystrixRequestContext.initializeContext();

        BookCommand bc = new BookCommand(rs,2);
        Book b1 = bc.execute();
        BookCommand bc2 = new BookCommand(rs,2);
        Future<Book> future = bc2.queue();
        Book b2 = future.get();
        System.out.println(b1);
        System.out.println(b2);
        hrc.close();
    }



    @GetMapping("/test1")
    public void test1(){
        HashMap<String, Object> map = new HashMap<>();
        map.put("id",67);
        ResponseEntity<String> entity = rs.getForEntity("http://provider/book?id={id}", String.class, map);
        String body = entity.getBody();
        System.out.println(body);
        HttpHeaders headers = entity.getHeaders();
        Set<String> keySet = headers.keySet();
        for (String key: keySet) {
            System.out.println(key+">>"+headers.get(key));
        }


    }

    @GetMapping("/test2")
   public void addBook(){
        Book book = new Book();
        book.setId(1);
        book.setAuthor("aa");
        book.setName("aaaa");
        Book book1 = rs.postForObject("http://provider/book", book, Book.class);

        rs.put("http://provider/book",book);
        rs.delete("http://provider/book/{1}",34);
    }


}
