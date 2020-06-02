package hello;


import org.springframework.boot.*;
import org.springframework.boot.autoconfigure.*;
//import org.springframework.boot.stereotype.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

//这是一个官方例子
@Controller
@EnableAutoConfiguration
public class SampleController {


    @RequestMapping("/")
    @ResponseBody
    String home(){
        return "hello world!";
    }

    public static void main(String [] args) throws Exception{
        SpringApplication.run(SampleController.class, args);
    }
}
