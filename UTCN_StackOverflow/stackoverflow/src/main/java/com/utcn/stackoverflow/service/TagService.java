package com.utcn.stackoverflow.service;

import com.utcn.stackoverflow.model.Tag;
import com.utcn.stackoverflow.repository.ITagRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class TagService {
    @Autowired
    ITagRepository iTagRepository;

    public List<Tag> getAllTags() { return (List<Tag>) iTagRepository.findAll(); }

    public Tag getTag(Integer id) {
        Optional<Tag> tag = iTagRepository.findById(id);
        return tag.orElse(null);
    }

    public String deleteTag(Integer id) {
        try {
            iTagRepository.delete(this.getTag(id));

            return "Delete success.";
        }catch (Exception e){
            return "Delete failed.";
        }
    }

    public Tag saveTag(Tag tag){
        return iTagRepository.save(tag);
    }

    public Tag updateTag(Integer id, Tag tag){
        Tag initialTag = this.getTag(id);

        initialTag.setTagText(tag.getTagText());
        //initialTag.setRelationships(tag.getRelationships());


        return iTagRepository.save(initialTag);
    }
}