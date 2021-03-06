package wiki.tree;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Tree 위키 실행을 위한 APP 클래스
 *
 * @author chanwook
 */
@SpringBootApplication
@Controller
public class TreeWikiApp {

    public static void main(String[] args) throws Exception {
        SpringApplication.run(TreeWikiApp.class, args);
    }

    @RequestMapping("/")
    public String index() {
        return "redirect:/home";
    }

    @RequestMapping("/home")
    public String viewHome() {
        return "home";
    }
    
    @RequestMapping("/check")
    @ResponseBody
    public String check() {
        return "OK!";
    }

}
