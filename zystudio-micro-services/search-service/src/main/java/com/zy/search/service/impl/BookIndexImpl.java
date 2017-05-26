package com.zy.search.service.impl;

import com.zy.search.beans.Book;
import com.zy.search.service.BookIndex;
import org.apache.lucene.geo3d.GeoPoint;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.data.elasticsearch.core.query.*;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * @author by zy.
 */
@Service
public class BookIndexImpl implements BookIndex{
    protected Logger logger = LoggerFactory.getLogger(BookIndexImpl.class);
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Override
    public void index(List<Book> list) {
        Objects.requireNonNull(list, "the book list cant be null");
        List<IndexQuery> indexQueries = new ArrayList<IndexQuery>();

        list.forEach((book -> {
            IndexQuery indexQuery1 = new IndexQueryBuilder().
                    withId(book.getId())
                    .withObject(book)
                    .build();
            indexQueries.add(indexQuery1);
        }));
        logger.info("index books {}", list);
        //bulk index
        elasticsearchTemplate.bulkIndex(indexQueries);
    }

    @Override
    public List<Book> query(String name) {
        /*SearchQuery searchQuery = new NativeSearchQueryBuilder()
                .withQuery(queryString(name).field("id"))
                .build();
        Page<SampleEntity> sampleEntities = elasticsearchTemplate.queryForPage(searchQuery,SampleEntity.class);*/

        CriteriaQuery geoLocationCriteriaQuery2 = new CriteriaQuery(
                new Criteria("name").is(name));
        List<Book> res = elasticsearchTemplate.queryForList(geoLocationCriteriaQuery2, Book.class);
        return res;
    }
}