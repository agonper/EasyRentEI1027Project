package es.uji.daal.easyrent.controller;

import es.uji.daal.easyrent.model.Conversation;
import es.uji.daal.easyrent.model.ConversationMessage;
import es.uji.daal.easyrent.model.User;
import es.uji.daal.easyrent.repository.ConversationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

/**
 * Created by Alberto on 30/06/2016.
 */
@Controller
@RequestMapping("/conversation")
public class ConversationController {

    @Autowired
    private ConversationRepository conversationRepository;

    @RequestMapping("/show/{id}")
    public String show(Model model, @PathVariable("id") String id) {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Conversation conversation = conversationRepository.findOne(UUID.fromString(id));
        if (!conversation.getTenant().equals(loggedUser) && !conversation.getProperty().getOwner().equals(loggedUser)) {
            return "redirect:../../index.html";
        }

        model.addAttribute("conversation", conversation);
        return "conversation/show";
    }

    @RequestMapping(value = "/{id}/publish-message", method = RequestMethod.POST)
    public String publishMessage(@RequestParam("message") String message,
                                 @PathVariable("id") String id) {
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Conversation conversation = conversationRepository.findOne(UUID.fromString(id));
        if (!conversation.getTenant().equals(loggedUser) && !conversation.getProperty().getOwner().equals(loggedUser)) {
            return "redirect:../../index.html";
        }

        ConversationMessage conversationMessage = conversation.createMessage(loggedUser);
        conversationMessage.setMessage(message);
        conversationRepository.save(conversation);

        return "redirect:../show/"+id+".html";
    }
}
