package quentin.testcreator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import quentin.testcreator.models.MultipleChoiceQuestion;
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
    public String addTF(Model model){
        model.addAttribute("question", new TrueFalseQuestion());
        model.addAttribute("title", "Create New Question");
        return "question/addTrueFalse";
    }

    @RequestMapping(value = "addTF", method = RequestMethod.POST)
    public String addTF(Model model, @ModelAttribute @Valid TrueFalseQuestion question, Errors errors){
        if(errors.hasErrors()){
            return "question/addTrueFalse";
        }
        questionDao.save(question);
        return "redirect:";
    }
    @RequestMapping(value = "addMulti", method = RequestMethod.GET)
    public String addMulti(Model model){
        model.addAttribute("question", new MultipleChoiceQuestion());
        model.addAttribute("title", "Create New Question");
        return "question/addMultipleChoice";
    }

    @RequestMapping(value = "addMulti", method = RequestMethod.POST)
    public String addMulti(Model model, @ModelAttribute @Valid MultipleChoiceQuestion question, Errors errors){
        if(errors.hasErrors()){
            model.addAttribute("question", new MultipleChoiceQuestion());
            model.addAttribute("title", "Create New Question");
            model.addAttribute("error", errors);
            return "question/addMultipleChoice";
        }
        questionDao.save(question);
        return "redirect:";
    }
}
