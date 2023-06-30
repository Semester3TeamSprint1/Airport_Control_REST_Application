package com.keyin.action;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Stack;

@RestController
@CrossOrigin
public class ActionController {

    @Autowired
    ActionService actionService;

    @DeleteMapping("/undo")
    public void undo(){
        actionService.undo();
    }

    @DeleteMapping("/redo")
    public void redo(){
        actionService.redo();
    }

}
