package quentin.testcreator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import quentin.testcreator.models.Question;
import quentin.testcreator.models.Test;
import quentin.testcreator.models.data.QuestionDao;
import quentin.testcreator.models.data.TestDao;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("t-edit")
public class TestController {

    @Autowired
    TestDao testDao;

    @Autowired
    QuestionDao questionDao;

    @RequestMapping("")
    public String index(Model model){
        model.addAttribute("tests", testDao.findAll());
        model.addAttribute("title", "Tests");
        return "test/index";
    }

    @RequestMapping(value = "addTest", method = RequestMethod.GET)
    public String addTest(Model model){
        model.addAttribute("test", new Test());
        model.addAttribute("title", "Create New Test");
        return "test/addTest";
    }

    @RequestMapping(value = "addTest", method = RequestMethod.POST)
    public String addTest(Model model, @ModelAttribute @Valid Test test, Errors errors){
        if(errors.hasErrors()){
            model.addAttribute("test", test);
            model.addAttribute("title", "Create New Test");
            return "test/addTest";
        }
        testDao.save(test);
        return "redirect:";
    }

    @RequestMapping(value = "view/{Id}", method = RequestMethod.GET)
    public String viewTest(Model model, @PathVariable int Id) {

        System.out.println("trying to view " + Id );
        Optional<Test> optionalTest = testDao.findById(Id);
        if(!optionalTest.isPresent()){
            return "redirect:/t-edit/";
        }
        Test test = optionalTest.get();
        model.addAttribute("test", test);
        model.addAttribute("title", test.getName());
        model.addAttribute("questions", test.getQuestions());
        return "test/viewTest";
    }

    @RequestMapping(value = "delete/{Id}", method = RequestMethod.GET)
    //@DeleteMapping(value = "delete/{Id}")
    public String removeTest(Model model, @PathVariable int Id) {

        System.out.println("trying to delete " + Id );

        Optional<Test> testOptional = testDao.findById(Id);
        if(testOptional.isPresent()) {
            Test test = testOptional.get();
            for (Question question : test.getQuestions() ) {
                questionDao.delete(question);
            }
            testDao.deleteById(Id);
        }
        return "redirect:/t-edit/";
    }

    @RequestMapping(value = "edit/{Id}", method = RequestMethod.GET)
    public String editTestName(Model model, @PathVariable int Id) {

        System.out.println("trying to edit " + Id );
        Optional<Test> optionalTest = testDao.findById(Id);
        if(!optionalTest.isPresent()){
            return "redirect:/t-edit/";
        }
        Test test = optionalTest.get();
        model.addAttribute("test", test);
        model.addAttribute("title", "Edit Test Name");
        return "test/addTest";
    }

    @RequestMapping(value = "edit/{Id}", method = RequestMethod.POST)
    public String editQuestion(Model model, @ModelAttribute @Valid Test test, Errors errors) {
        System.out.println("editing test");
        if(errors.hasErrors()){
            System.out.println("error in test");
            model.addAttribute("test", test);
            model.addAttribute("title", "Edit Test Name");
            model.addAttribute("error", errors);
            return "test/addTest";
        }
        testDao.save(test);
        return "redirect:/t-edit/";
    }

}
