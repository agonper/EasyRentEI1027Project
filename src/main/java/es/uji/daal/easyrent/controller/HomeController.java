package es.uji.daal.easyrent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Alberto on 05/05/2016.
 */
@Controller
public class HomeController {

    @RequestMapping("/")
    public String index() {
        return "index";
    }
}
