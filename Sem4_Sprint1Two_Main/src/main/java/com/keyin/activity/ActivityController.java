package com.keyin.activity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class ActivityController {

    @Autowired
    ActivityService activityService;

    @DeleteMapping("/undoActivity")
    public void undoActivity(){
        activityService.undoActivity();
    }

    @DeleteMapping("/redoActivity")
    public void redoActivity(){
        activityService.redoActivity();
    }

}
