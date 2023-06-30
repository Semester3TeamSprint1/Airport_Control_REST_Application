package com.keyin.browser;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import java.util.List;

@RestController
@CrossOrigin
public class BrowserController {
    @Autowired
    private BrowserService browserService;

    @GetMapping("/browser")
    public Browser getPeekBrowser() {
        return browserService.getPeekBrowser();
    }

    @GetMapping("/allBrowserData")
    public List<Browser> getAllBrowserData(){
        return browserService.getBrowserData();
    }

    @GetMapping("/undo")
    public Browser undo(){
        return browserService.undo();
    }

    @GetMapping("/redo")
    public Browser redo(){
        return browserService.redo();
    }
}
