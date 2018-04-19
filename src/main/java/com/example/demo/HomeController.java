package com.example.demo;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.ArrayList;

@Controller
public class HomeController {

    @Autowired
    CourseRepository courseRepository;

    @RequestMapping("/")
    public String listCourses(Model model){
        ArrayList<Course> courses = new ArrayList<>();
        Course c = new Course();
        c.setId(123);
        c.setCredit(3);
        c.setTitle("Course Title");
        c.setDescription("this is my course");
        c.setInstructor("Bart Simpson");
        courses.add(c);
        Course d = new Course();
        d.setId(199);
        d.setCredit(2);
        d.setInstructor("Homer Simpson");
        d.setDescription("This is course d");
        courses.add(d);

        //model.addAttribute("courses", courseRepository.findAll());
        model.addAttribute("courses",courses);
        return "list";
    }

    @GetMapping("/add")
    public String courseForm(Model model){
        model.addAttribute("course", new Course());
        return "courseform";
    }

    @PostMapping("/process")
    public String processForm(@Valid Course course, BindingResult result){
        if (result.hasErrors()){
            return "courseform";
        }
        courseRepository.save(course);
        return "redirect:/";
    }

    @RequestMapping("detail/{id}")
    public String showCourse(@PathVariable("id") long id, Model model){
        model.addAttribute("course", courseRepository.findOne(id));
        return "show";

    }

    @RequestMapping("/update/{id}")
    public String updateCourse(@PathVariable("id") long id, Model model){
        model.addAttribute("course", courseRepository.findOne(id));
        return "courseform";
    }

    @RequestMapping("/delete/{id}")
    public String delCourse(@PathVariable("id") long id){
        courseRepository.delete(id);
        return "redirect:/";
    }



}



















