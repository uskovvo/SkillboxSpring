package org.example.app.services;

import org.apache.log4j.Logger;
import org.example.web.dto.Book;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
public class BookRepository implements ProjectRepository<Book>, ApplicationContextAware {

    private final Logger logger = Logger.getLogger(BookRepository.class);
    private final List<Book> repo = new ArrayList<>();
    private ApplicationContext context;

    @Override
    public List<Book> retreiveAll() {
        return new ArrayList<>(repo);
    }

    @Override
    public void store(Book book) {
        if(!book.getAuthor().isEmpty() || !book.getTitle().isEmpty() || book.getSize() != null) {
            book.setId(context.getBean(IdProvider.class).provideId(book));
            logger.info("store new book: " + book);
            repo.add(book);
        }
    }

    @Override
    public boolean removeItemByRegex(String queryRegex) {
        if(!queryRegex.isEmpty()) {
            queryRegex = queryRegex.trim();
            if (isDigit(queryRegex)) {
                Integer intQuery = Integer.parseInt(queryRegex);
                return removeItemBySize(intQuery);
            }else {
                return removeItemByAuthorOrTitleOrId(queryRegex);
            }
        }
        return false;
    }

    private boolean removeItemBySize(Integer intToRemove) {
//        List<Book> newList = new ArrayList<>();
        int count = 0;
        for(Book book: retreiveAll()){
            if(Objects.equals(book.getSize(), intToRemove)){
                logger.info("remove book by Size: " + book);
                count++;
                repo.remove(book);
            }
        }
        return count > 0;
    }

    private boolean removeItemByAuthorOrTitleOrId(String stringToRemove){
//        List<Book> newList = new ArrayList<>();
        int count = 0;
        for (Book book: retreiveAll()){
            if(book.getAuthor().equals(stringToRemove)){
                logger.info("remove book by Author: " + book);
                count++;
                repo.remove(book);
            }else if(book.getTitle().equals(stringToRemove)){
                logger.info("remove book by Title: " + book);
                count++;
                repo.remove(book);
            }else if(book.getId().equals(stringToRemove)){
                logger.info("remove book by Id: " + book);
                repo.remove(book);
                return true;
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
