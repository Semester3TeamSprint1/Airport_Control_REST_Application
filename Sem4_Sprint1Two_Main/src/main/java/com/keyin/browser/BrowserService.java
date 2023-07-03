//package com.keyin.browser;
//
//import org.springframework.stereotype.Service;
//
//import java.time.LocalDateTime;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//import java.util.Stack;
//
//@Service
//public class BrowserService {
//
//    private Stack<Browser> browserStack = new Stack<>();
//    private Stack<Browser> redoStack = new Stack<>();
//
//    public void addToBrowser(String calledMethod, String url, LocalDateTime timestamp) {
//        Browser browser = new Browser(calledMethod, url, timestamp);
//        browserStack.push(browser);
//    }
//
//    public Browser getPeekBrowser() {
//        if (browserStack.isEmpty()){
//            System.out.println("no data present");
//            return null;
//        } else {
//            return browserStack.peek();
//        }
//    }
//
//    public List<Browser> getBrowserData() {
//        List<Browser> browserList = new ArrayList<>();
//        browserList.addAll(browserStack);
//
//        if (browserList.isEmpty()){
//            System.out.println("no data present");
//            return null;
//        }
//        return browserList;
//    }
//
//    public Browser undoBrowser(){
//        if(browserStack.isEmpty()){
//            System.out.println("no data present");
//            return null;
//        }
//        redoStack.push(browserStack.peek());
//        browserStack.pop();
//        return browserStack.peek();
//    }
//
//    public Browser redoBrowser() {
//        if (redoStack.isEmpty()){
//            System.out.println("no data present");
//        }
//        browserStack.push(redoStack.peek());
//        redoStack.pop();
//        return browserStack.peek();
//    }
//}
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