package es.uji.daal.easyrent.controller;

import es.uji.daal.easyrent.model.Property;
import es.uji.daal.easyrent.repository.PropertyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by Alberto on 15/05/2016.
 */
@Controller
public class SearchController {

    @Autowired
    PropertyRepository repository;

    @RequestMapping("/search")
    public String search(Model model, Pageable pageable,
                         @RequestParam("q") String query) {
        String[] searchParameters = query.split("[\b,;:]");
        List<String> parametersList = Arrays.asList(searchParameters);
        Page<Property> properties = repository.searchBy(query, pageable);

        model.addAttribute("properties", properties.getContent());
        model.addAttribute("totalPages", properties.getTotalPages());
        model.addAttribute("currentPage", properties.getNumber());
        return "search";
    }
}
