package es.uji.daal.easyrent.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by Alberto on 05/05/2016.
 */
@Controller
public class IndexController {

    @RequestMapping("/")
    public String root() {
        return "index";
    }

    @RequestMapping("/index")
    public String index() {
        return "index";
    }
}
