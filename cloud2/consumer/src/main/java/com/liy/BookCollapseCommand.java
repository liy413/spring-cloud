package com.liy;

import com.netflix.hystrix.HystrixCollapser;
import com.netflix.hystrix.HystrixCollapserKey;
import com.netflix.hystrix.HystrixCollapserProperties;
import com.netflix.hystrix.HystrixCommand;
import org.bouncycastle.cert.ocsp.Req;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class BookCollapseCommand extends HystrixCollapser<List<Book>,Book,Integer> {

    BookService bs;

    Integer id;

    public BookCollapseCommand( BookService bs, Integer id) {
        super(Setter.withCollapserKey(HystrixCollapserKey.Factory.asKey("bookCollapseCommand"))
                .andCollapserPropertiesDefaults(HystrixCollapserProperties.Setter().withTimerDelayInMilliseconds(100)));
        this.bs = bs;
        this.id = id;
    }

    @Override
    public Integer getRequestArgument() {
        return id;
    }

    @Override
    protected HystrixCommand<List<Book>> createCommand(Collection<CollapsedRequest<Book, Integer>> collection) {
        List<Integer> ids = new ArrayList<>(collection.size());
        for (CollapsedRequest<Book, Integer> request: collection) {
            ids.add(request.getArgument());

        }
        return new BookBatchCommand(bs,ids);
    }


    @Override
    protected void mapResponseToRequests(List<Book> books, Collection<CollapsedRequest<Book, Integer>> collection) {
        int count = 0 ;
        for (CollapsedRequest<Book, Integer> request: collection) {
            request.setResponse(books.get(count++));
        }
    }
}
