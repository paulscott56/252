package teststormpath;

/**
 * Created by paul on 2016/04/20.
 */
import com.stormpath.sdk.servlet.account.AccountResolver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;

/**
 * @since 1.0.RC5
 */
@Controller
public class HelloController {

    @Autowired
    private HelloService helloService;

    @RequestMapping("/")
    String home(HttpServletRequest request) {
        return "home";
    }

    @RequestMapping("/restricted")
    String restricted(HttpServletRequest request, Model model) {
        String msg = helloService.sayHello(
                AccountResolver.INSTANCE.getAccount(request)
        );
        model.addAttribute("msg", msg);
        return "restricted";
    }

}