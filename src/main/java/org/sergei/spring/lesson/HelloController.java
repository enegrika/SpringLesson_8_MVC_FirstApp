package org.sergei.spring.lesson;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class HelloController {

    /// возвращает имя HTML файла в нашем WEB-INF/views
    @GetMapping("/hell")

//    @RequestMapping(method = RequestMethod.GET) - УСТАРЕВШИЙ СПОСОБ иногда может использоваться на классе
    public String sayHello(){
        return "hello_world";
    }
}
