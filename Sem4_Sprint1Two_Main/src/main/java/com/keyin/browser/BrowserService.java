package com.keyin.browser;

import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

@Service
public class BrowserService {

    private final Stack<Browser> browserStack = new Stack<>();
    private final Stack<Browser> redoStack = new Stack<>();

    public void addToBrowser(String calledMethod, String url, LocalDateTime timestamp) {
        Browser browser = new Browser(calledMethod, url, timestamp);
        browserStack.push(browser);
    }

    public Browser getPeekBrowser() {
        if (browserStack.isEmpty()) {
            System.out.println("No data");
            return null;
        }
        return browserStack.peek();
    }

    public List<Browser> getBrowserData() {
        if (browserStack.isEmpty()) {
            System.out.println("No data");
            return null;
        }
        return new ArrayList<>(browserStack);
    }

    public Browser undoBrowser() {
        if (browserStack.isEmpty()) {
            System.out.println("No data");
            return null;
        }
        redoStack.push(browserStack.pop());
        return browserStack.peek();
    }

    public Browser redoBrowser() {
        if (redoStack.isEmpty()) {
            System.out.println("No data");
            return null;
        }
        browserStack.push(redoStack.pop());
        return browserStack.peek();
    }
}