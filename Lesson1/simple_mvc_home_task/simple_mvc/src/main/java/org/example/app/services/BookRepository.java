package org.example.app.services;

import org.apache.log4j.Logger;
import org.example.web.dto.Book;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class BookRepository<B> implements ProjectRepository<Book> {

    private Logger logger = Logger.getLogger(BookRepository.class);
    private final List<Book> repo = new ArrayList<>();

    @Override
    public List<Book> retreiveAll() {
        return new ArrayList<>(repo);
    }

    @Override
    public void store(Book book) {
        if(!book.getAuthor().isEmpty() || !book.getTitle().isEmpty() || book.getSize() != null) {
            book.setId(book.hashCode());
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
                return removeItemById(intQuery);
            }else {
                return removeItemByAuthorOrTitle(queryRegex);
            }
        }
        return false;
    }

    public boolean removeItemById(Integer intToRemove) {
        List<Book> newList = new ArrayList<>();
        for(Book book: retreiveAll()){
            if(book.getId().equals(intToRemove)) {
                logger.info("remove book by Id: " + book);
                return repo.remove(book);
            }
            else if(book.getSize().equals(intToRemove)){
                logger.info("remove book by Size: " + book);
                repo.remove(book);
            }
        }
        return newList.addAll(repo);
    }

    public boolean removeItemByAuthorOrTitle(String stringToRemove){
        List<Book> newList = new ArrayList<>();
        for (Book book: retreiveAll()){
            if(book.getAuthor().equals(stringToRemove)){
                logger.info("remove book by Author: " + book);
                repo.remove(book);
            }else if(book.getTitle().equals(stringToRemove)){
                logger.info("remove book by Title: " + book);
                repo.remove(book);
            }
        }
        return newList.addAll(repo);
    }

    private boolean isDigit(String query){
        try{
            Integer.parseInt(query);
            return true;
        }catch (NumberFormatException e){
            return false;
        }
    }
}
