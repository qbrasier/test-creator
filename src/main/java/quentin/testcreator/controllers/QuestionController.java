package quentin.testcreator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import quentin.testcreator.models.Question;
import quentin.testcreator.models.TrueFalseQuestion;
import quentin.testcreator.models.data.QuestionDao;

import javax.validation.Valid;

@Controller
@RequestMapping("q-edit")
public class QuestionController {

    @Autowired
    private QuestionDao questionDao;

    @RequestMapping(value = "")
    public String index(Model model){
        model.addAttribute("questions", questionDao.findAll());
        model.addAttribute("title", "Questions");

        return "question/index";
    }

    @RequestMapping(value = "addTF", method = RequestMethod.GET)
    public String add(Model model){
        model.addAttribute(new TrueFalseQuestion());
        model.addAttribute("title", "Create New Question");
        return "question/addTrueFalse";
    }

    @RequestMapping(value = "addTF", method = RequestMethod.POST)
    public String add(Model model, @ModelAttribute @Valid TrueFalseQuestion question, Errors errors){
        if(errors.hasErrors()){
            return "question/addTrueFalse";
        }
        questionDao.save(question);
        return "redirect:";
    }
}
