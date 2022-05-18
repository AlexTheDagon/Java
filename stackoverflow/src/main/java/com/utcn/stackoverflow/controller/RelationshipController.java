package com.utcn.stackoverflow.controller;

import com.utcn.stackoverflow.model.Relationship;
import com.utcn.stackoverflow.service.RelationshipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping(value="/relationship")
public class RelationshipController {

    @Autowired
    RelationshipService relationshipService;

    @RequestMapping(method = RequestMethod.GET, value = "/getAllRelationships")
    @ResponseBody
    public List<Relationship> getAllRelationships() {
        return relationshipService.getAllRelationships();
    }

    @RequestMapping(method = RequestMethod.GET, value = "/getRelationship")
    @ResponseBody
    public Relationship getRelationship(@RequestParam(name = "id") Integer id) {
        return relationshipService.getRelationship(id);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/deleteRelationship")
    @ResponseBody
    public String deleteRelationship(@RequestParam(name = "id") Integer id) {
        return relationshipService.deleteRelationship(id);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/createRelationship")
    @ResponseBody
    public Relationship createRelationship(@RequestBody Relationship relationship) { return relationshipService.saveRelationship(relationship); }

    @RequestMapping(method = RequestMethod.POST, value = "/updateRelationship")
    @ResponseBody
    public Relationship updateRelationship( @RequestBody Relationship relationship) { return relationshipService.updateRelationship(relationship.getRelationshipID(), relationship);}
}
