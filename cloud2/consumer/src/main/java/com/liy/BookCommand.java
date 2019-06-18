package com.liy;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import org.springframework.web.client.RestTemplate;

public class BookCommand extends HystrixCommand<Book> {

    RestTemplate ts;

    int id;

    public BookCommand(RestTemplate ts,int id) {
        super(HystrixCommand.Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("")));
        this.ts = ts;
        this.id = id;
    }



    @Override
    protected Book run() throws Exception {
        //int i =1/0;
        return this.ts.getForObject("http://provider/book?id={1}",Book.class,id);
    }

    @Override
    protected Book getFallback() {
        Book book = new Book();
        book.setId(-1);
        Throwable throwable = getExecutionException();
        System.out.println(throwable);
        return book;
    }

    @Override
    protected String getCacheKey() {
        return String.valueOf(id);
    }
}
