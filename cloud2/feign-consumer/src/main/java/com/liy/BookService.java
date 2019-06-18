package com.liy;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.*;

//@FeignClient("provider")
public interface BookService {

   /* @GetMapping("/book")
    public Book book1(@RequestParam("id") int id);

    @PostMapping("/book")
    public void book2(Book book);

    @PutMapping("/book")
    public void book3(Book book);

    @DeleteMapping("/book/{id}")
    public void book4(@PathVariable("id") int id);

    @PutMapping("/book2")
    public void book5(@RequestHeader("name") String name,@RequestHeader("id") int id);*/
}
