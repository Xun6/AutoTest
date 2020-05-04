import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

/**
 * 这是一个 入口类
 * */
@SpringBootApplication    //托管给 SpringBoot
@ComponentScan("com.course")              //扫描某个包下的类(MyGetMethodDemo)，进行管理
public class Application {

    public static void main(String [] args){
        SpringApplication.run(Application.class, args);

    }
}
