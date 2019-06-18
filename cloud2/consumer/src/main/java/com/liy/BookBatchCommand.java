package com.liy;

import com.netflix.hystrix.HystrixCommand;
import com.netflix.hystrix.HystrixCommandGroupKey;
import com.netflix.hystrix.HystrixCommandKey;

import java.util.List;

public class BookBatchCommand extends HystrixCommand<List<Book>> {

    BookService bs;

    List<Integer> ids;

    public BookBatchCommand(BookService bs, List<Integer> ids) {
        super(Setter.withGroupKey(HystrixCommandGroupKey.Factory.asKey("CollapsingGroup"))
                .andCommandKey(HystrixCommandKey.Factory.asKey("CollapsingKey")));
        this.bs = bs;
        this.ids = ids;
    }

    @Override
    protected List<Book> run() throws Exception {
        return bs.getBooks(ids);
    }
}
