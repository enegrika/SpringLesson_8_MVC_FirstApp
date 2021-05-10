package org.sergei.spring.lesson.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;

@Controller
@RequestMapping("/first")// ЗДЕСЬ СМОЖЕМ ОБРАТИТЬСЯ В ПАПКУ first
public class FirstController {

//    @GetMapping("/hell1")    // URL для ЗАПРОСА в БРАУЗЕРЕ http://localhost:8080/hell1

//    public String helloPage(HttpServletRequest request) { // HttpServletRequest object CONTAINS ALL info about GET request
    // ALSO IF we don't have these parameters HttpServletRequest object return NULL for this parameters
//        String name = request.getParameter("name");
//        String surname = request.getParameter("surname");
//        System.out.println("Hello, " + name + " " + surname);
//        return "first/hello"; // ПУТЬ К ФАЙЛУ в ПРЕДСТАВЛЕНИИ (папка first файл hello.html)
//    }

    @GetMapping("/hel")

    public String hello(@RequestParam(value = "name", required = false) String name,
                        @RequestParam(value = "surname", required = false) String surname,
                        Model model) { // @requestParam ONLY GET certain value from GET request string key
        // ALSO IF we don't have these parameters HttpServletRequest object return BAD REQUEST HTTP Status 400 to browser
        // but we can FIX it with "required = false" parameter

//        System.out.println("Hello here : " + name + " " + surname);
        model.addAttribute("message", "Hello here : " + name + " " + surname);


        return "first/hello";
    }

    @GetMapping("/bye")
    public String byePage() {
        return "first/goodbye";
    }

    @GetMapping("/calculator")
    public String calculate(@RequestParam(value = "a", required = false) int a,
                            @RequestParam(value = "b", required = false) int b,
                            @RequestParam(value = "action", required = false) String action,
                            Model model) {
        int res;
        switch (action) {
            case "multiply":
                res = a * b;
                break;
            case "addition":
                res = a + b;
                break;
            case "subtraction":
                res = a - b;
                break;
            case "division":
                res = a / b;
                break;
            default:
                res = 0;
        }

        model.addAttribute("result", "Результат " + action + " is : " + res);
        return "first/calculator";
    }

}
