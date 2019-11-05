package quentin.testcreator.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import quentin.testcreator.models.*;
import quentin.testcreator.models.data.QuestionDao;
import quentin.testcreator.models.data.TestDao;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("q-edit")
public class QuestionController {

    @Autowired
    private QuestionDao questionDao;
    
    @Autowired
    private TestDao testDao;

    @RequestMapping(value = "")
    public String index(Model model){
        model.addAttribute("questions", questionDao.findAll());
        model.addAttribute("title", "Questions");
        System.out.println("showing main question page");
        return "question/index";
    }

    @RequestMapping(value = "addTF", method = RequestMethod.GET)
    public String addTF(Model model){
        model.addAttribute("question", new TrueFalseQuestion());
        model.addAttribute("title", "Create New Question");
        model.addAttribute("tests", testDao.findAll());
        return "question/addTrueFalse";
    }

    @RequestMapping(value = "addTF", method = RequestMethod.POST)
    public String addTF(Model model, @ModelAttribute @Valid TrueFalseQuestion question, Errors errors, @RequestParam int testId){
        return addQuestionPost(model, question, errors, testId);
    }
    @RequestMapping(value = "addMulti", method = RequestMethod.GET)
    public String addMulti(Model model){
        model.addAttribute("question", new MultipleChoiceQuestion());
        model.addAttribute("title", "Create New Question");
        model.addAttribute("tests", testDao.findAll());
        return "question/addMultipleChoice";
    }

    @RequestMapping(value = "addMulti", method = RequestMethod.POST)
    public String addMulti(Model model, @ModelAttribute @Valid MultipleChoiceQuestion question, Errors errors, @RequestParam int testId){
        return addQuestionPost(model, question, errors, testId);
    }
    @RequestMapping(value = "addFITB", method = RequestMethod.GET)
    public String addFITB(Model model){
        model.addAttribute("question", new FillInTheBlankQuestion());
        model.addAttribute("title", "Create New Question");
        model.addAttribute("tests", testDao.findAll());
        return "question/addFillInTheBlank";
    }

    @RequestMapping(value = "addFITB", method = RequestMethod.POST)
    public String addFITB(Model model, @ModelAttribute @Valid FillInTheBlankQuestion question, Errors errors, @RequestParam int testId){
        return addQuestionPost(model, question, errors, testId);
    }
    @RequestMapping(value = "addEssay", method = RequestMethod.GET)
    public String addEssay(Model model){
        model.addAttribute("question", new EssayQuestion());
        model.addAttribute("title", "Create New Question");
        model.addAttribute("tests", testDao.findAll());
        return "question/addEssay";
    }

    @RequestMapping(value = "addEssay", method = RequestMethod.POST)
    public String addEssay(Model model, @ModelAttribute @Valid EssayQuestion question, Errors errors, @RequestParam int testId){
        return addQuestionPost(model, question, errors, testId);
    }


    public String addQuestionPost(Model model, Question question, Errors errors, int testId){

        Optional<Test> optionalTest = testDao.findById(testId);
        if(!optionalTest.isPresent() || errors.hasErrors()){
            String template = question.getClass().getSimpleName().replace("Question", "");
            model.addAttribute("question", question);
            model.addAttribute("title", "Create New Question");
            model.addAttribute("tests", testDao.findAll());
            return "question/add" + template;
        }
        Test test = optionalTest.get();
        question.setTest(test);
        questionDao.save(question);
        return "redirect:";
    }




    /*
    @RequestMapping(value = "remove", method = RequestMethod.GET)
    public String displayRemoveCheeseForm(Model model) {
        model.addAttribute("questions", questionDao.findAll());
        model.addAttribute("title", "Remove Question");
        return "question/remove";
    }*/

    @RequestMapping(value = "delete/{Id}", method = RequestMethod.GET)
    //@DeleteMapping(value = "delete/{Id}")
    public String removeQuestion(Model model, @PathVariable int Id) {

        System.out.println("trying to delete " + Id );
        questionDao.deleteById(Id);

        return "redirect:/q-edit/";
    }

    @RequestMapping(value = "edit/{Id}", method = RequestMethod.GET)
    //@DeleteMapping(value = "delete/{Id}")
    public String editQuestion(Model model, @PathVariable int Id) {

        System.out.println("trying to edit " + Id );
        Optional<Question> optionalQuestion = questionDao.findById(Id);
        if(!optionalQuestion.isPresent()){
            return "redirect:/q-edit/";
        }
        Question question = optionalQuestion.get();
        model.addAttribute("question", question);
        model.addAttribute("title", "Edit Question");
        model.addAttribute("tests", testDao.findAll());
        String template = question.getClass().getSimpleName();
        template = template.replace("Question", "");
        return "question/add" + template;
    }
    //TODO: this doesn't like taking the abstract Question as an argument, not sure why
    //FIXED needed "ad hoc polymorphism"?
    //NOT FIXED strange that its calling only the first available method when its not passing a TrueFalse question
    // (and stranger that it works)
    @RequestMapping(value = "edit/{Id}", method = RequestMethod.POST)
    public String editQuestion(Model model, @ModelAttribute @Valid TrueFalseQuestion question, Errors errors) {
        System.out.println("editing TrueFalse");
        if(errors.hasErrors()){
            System.out.println("error in TrueFalse");
            model.addAttribute("question", question);
            model.addAttribute("title", "Edit Question");
            model.addAttribute("tests", testDao.findAll());
            model.addAttribute("error", errors);
            return "question/addTrueFalse";
        }
        questionDao.save(question);
        return "redirect:/q-edit/";
    }
    public String editQuestion(Model model, @ModelAttribute @Valid MultipleChoiceQuestion question, Errors errors) {
        if(errors.hasErrors()){
            System.out.println("error in multipleChoice");
            model.addAttribute("question", question);
            model.addAttribute("title", "Edit Question");
            model.addAttribute("tests", testDao.findAll());
            model.addAttribute("error", errors);
            return "question/addMultipleChoice";
        }
        questionDao.save(question);
        return "redirect:/q-edit/";
    }
    public String editQuestion(Model model, @ModelAttribute @Valid EssayQuestion question, Errors errors) {
        if(errors.hasErrors()){
            System.out.println("error in Essay");
            model.addAttribute("question", question);
            model.addAttribute("title", "Edit Question");
            model.addAttribute("tests", testDao.findAll());
            model.addAttribute("error", errors);
            return "question/addEssay";
        }
        questionDao.save(question);
        return "redirect:/q-edit/";
    }
    public String editQuestion(Model model, @ModelAttribute @Valid FillInTheBlankQuestion question, Errors errors) {
        if(errors.hasErrors()){
            System.out.println("error in FillInTheBlank");
            model.addAttribute("question", question);
            model.addAttribute("title", "Edit Question");
            model.addAttribute("tests", testDao.findAll());
            model.addAttribute("error", errors);
            return "question/addFillInTheBlank";
        }
        questionDao.save(question);
        return "redirect:/q-edit/";
    }

}
