package imcs.config;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
@EnableWebMvc
@ComponentScan(basePackages={"imcs.rest.controller","imcs.repo", "imcs.service", "imcs.entity"})
public class AppConfig extends WebMvcConfigurerAdapter {
	

}
