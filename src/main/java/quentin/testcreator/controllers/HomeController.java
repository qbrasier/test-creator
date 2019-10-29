package quentin.testcreator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import quentin.testcreator.models.data.QuestionDao;

@Controller
@RequestMapping("")
public class HomeController {

    @Autowired
    private QuestionDao questionDao;

    @RequestMapping(value = "")
    public String index(Model model){
        model.addAttribute("title", "Welcome to the Home Page!");
        return "index";

    }
}
