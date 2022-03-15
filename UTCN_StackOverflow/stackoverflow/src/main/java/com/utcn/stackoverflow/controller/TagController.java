package com.utcn.stackoverflow.controller;

import com.utcn.stackoverflow.model.Tag;
import com.utcn.stackoverflow.service.TagService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping(value="/tag")
public class TagController {

    @Autowired
    TagService tagService;

    @RequestMapping(method = RequestMethod.GET, value = "/getAllTags")
    @ResponseBody
    public List<Tag> getAllTags() {
        return tagService.getAllTags();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getTag")
    @ResponseBody
    public Tag getTag(@RequestParam(name = "id") Integer id) {
        return tagService.getTag(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/deleteTag")
    @ResponseBody
    public String deleteTag(@RequestParam(name = "id") Integer id) {
        return tagService.deleteTag(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/createTag")
    @ResponseBody
    public Tag createTag(@RequestBody Tag tag) { return tagService.saveTag(tag); }

    @RequestMapping(method = RequestMethod.POST, value = "/updateTag")
    @ResponseBody
    public Tag updateTag( @RequestBody Tag tag) { return tagService.updateTag(tag.getTagID(), tag);}
}
