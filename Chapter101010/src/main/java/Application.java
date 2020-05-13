import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication   //表示托管
@ComponentScan("com.course.server")    //组件扫描，扫描某个包下的类
public class Application {
    public static void main(String [] args){
        SpringApplication.run(Application.class,args);
    }
}
