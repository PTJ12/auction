package xpit.top.action;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import springfox.documentation.oas.annotations.EnableOpenApi;

@SpringBootApplication
@MapperScan("xpit.top.action.mapper")
public class ActionApplication {

    public static void main(String[] args) {
        SpringApplication.run(ActionApplication.class, args);
    }

}
