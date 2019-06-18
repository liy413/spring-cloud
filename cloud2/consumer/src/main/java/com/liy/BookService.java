package com.liy;

import com.netflix.discovery.util.StringUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheKey;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import com.netflix.hystrix.contrib.javanica.command.AsyncResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Future;

@Service
public class BookService {

    @Autowired
    @Qualifier("rs2")
    RestTemplate rs;

    public List<Book> getBooks(List<Integer> ids){
        Book[] books = rs.getForObject("http://provider/book/{1}", Book[].class, StringUtils.join(ids, ","));
        return Arrays.asList(books);
    }

    @HystrixCommand(fallbackMethod = "error",ignoreExceptions = ArithmeticException.class)
    public String hello(){
        int i =  1/0;
        return  rs.getForObject("http://provider/hello",String.class);
    }

    @HystrixCommand
    @CacheResult
    public Future<Book> hello2(@CacheKey int id){
       return  new AsyncResult<Book>(){

           @Override
           public Book invoke() {
               return  rs.getForObject("http://provider/book?id={1}",Book.class,id);
           }
       };
    }

    public String error(Throwable t){
        return "error>>"+t.getMessage();
    }
}
