package org.sergei.spring.lesson.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FirstController {
    @GetMapping("/hell1")    // URL для ЗАПРОСА в БРАУЗЕРЕ http://localhost:8080/hell1
    public String helloPage() {
        return "first/hello"; // ПУТЬ К ФАЙЛУ в ПРЕДСТАВЛЕНИИ (папка first файл hello.html)
    }

    @GetMapping("/bye")
    public String byePage(){
        return "first/goodbye";
    }

}
