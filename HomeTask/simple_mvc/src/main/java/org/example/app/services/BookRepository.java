package org.example.app.services;

import org.apache.log4j.Logger;
import org.example.web.dto.Book;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class BookRepository implements ProjectRepository<Book>, ApplicationContextAware {

    private final Logger logger = Logger.getLogger(BookRepository.class);
    private ApplicationContext context;

    private final NamedParameterJdbcTemplate jdbsTemplate;

    @Autowired
    public BookRepository(NamedParameterJdbcTemplate jdbsTemplate) {
        this.jdbsTemplate = jdbsTemplate;
    }

    @Override
    public List<Book> retreiveAll() {
        List<Book> books = jdbsTemplate.query("SELECT * FROM books", (ResultSet rs, int rowNum) -> {
            Book book = new Book();
            book.setId(rs.getInt("id"));
            book.setAuthor(rs.getString("author"));
            book.setTitle(rs.getString("title"));
            book.setSize(rs.getInt("size"));
            return book;
        });
        return new ArrayList<>(books);
    }

    @Override
    public void store(Book book) {
        if(!book.getAuthor().isEmpty() || !book.getTitle().isEmpty() || book.getSize() != null) {
            logger.info("store new book: " + book);
            MapSqlParameterSource parameterSource = new MapSqlParameterSource();
            parameterSource.addValue("author", book.getAuthor());
            parameterSource.addValue("title", book.getTitle());
            parameterSource.addValue("size", book.getSize());
            jdbsTemplate.update("INSERT INTO books(author, title, size) VALUES(:author, :title, :size)", parameterSource);
        }
    }

    @Override
    public boolean removeItemByRegex(String queryRegex) {
        if(!queryRegex.isEmpty()) {
            MapSqlParameterSource parameterSource = new MapSqlParameterSource();
            queryRegex = queryRegex.trim();
            if (isDigit(queryRegex)) {
                Integer intQuery = Integer.parseInt(queryRegex);
                return removeItemBySizeOrId(intQuery, parameterSource);
            }else {
                return removeItemByAuthorOrTitle(queryRegex, parameterSource);
            }
        }
        return false;
    }

    private boolean removeItemBySizeOrId(Integer intToRemove, MapSqlParameterSource parameterSource) {
        int count = 0;
        for(Book book: retreiveAll()){
            if(Objects.equals(book.getSize(), intToRemove)){
                parameterSource.addValue("size", intToRemove);
                jdbsTemplate.update("DELETE FROM books WHERE size = :size", parameterSource);
                count++;
                logger.info("remove book by Size: " + book);
            }else if(Objects.equals(book.getId(), intToRemove)){
                parameterSource.addValue("id", intToRemove);
                jdbsTemplate.update("DELETE FROM books WHERE id = :id", parameterSource);
                logger.info("remove book by Id: " + book);
                return true;
            }
        }
        return count > 0;
    }

    private boolean removeItemByAuthorOrTitle(String stringToRemove, MapSqlParameterSource parameterSource){
        int count = 0;
        for (Book book: retreiveAll()){
            if(book.getAuthor().equals(stringToRemove)){
                parameterSource.addValue("author", stringToRemove);
                jdbsTemplate.update("DELETE FROM books WHERE author = :author", parameterSource);
                logger.info("remove book by Author: " + book);
                count++;
            }else if(book.getTitle().equals(stringToRemove)){
                parameterSource.addValue("title", stringToRemove);
                jdbsTemplate.update("DELETE FROM books WHERE title = :title", parameterSource);
                logger.info("remove book by Title: " + book);
                count++;
            }
        }
        return count > 0;
    }


    private boolean isDigit(String query){
        try{
            Integer.parseInt(query);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    private void defaultInit() {
        logger.info("Default INIT in book repo bean");
    }

    private void defaultDestroy() {
        logger.info("Default DESTROY in book repo bean");
    }
}
