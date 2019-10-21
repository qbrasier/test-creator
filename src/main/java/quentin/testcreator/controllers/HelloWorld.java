package quentin.testcreator.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
@RequestMapping("hello")
public class HelloWorld {
    @RequestMapping("")
    public String index(){
        return  "hi" ;
    }
}
