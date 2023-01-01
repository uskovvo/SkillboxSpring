package com.example.mybookshopapp.services;

import com.example.mybookshopapp.data.TagEntity;
import com.example.mybookshopapp.repositories.TagEntityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.TreeSet;

@Service
public class TagService {

    private final TagEntityRepository tagRepository;

    @Autowired
    public TagService(TagEntityRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public List<TagEntity> getAllTags(){
        TreeSet<Integer> integers = new TreeSet<>();
        tagRepository.findAll().forEach(t -> {
            integers.add(t.getCountBooks());
        });

        integers.forEach(System.out::println);
        return tagRepository.findAll();
    }
}
