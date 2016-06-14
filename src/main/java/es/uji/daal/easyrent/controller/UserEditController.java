package es.uji.daal.easyrent.controller;

import es.uji.daal.easyrent.model.Photo;
import es.uji.daal.easyrent.model.User;
import es.uji.daal.easyrent.repository.UserRepository;
import es.uji.daal.easyrent.utils.FileUploader;
import es.uji.daal.easyrent.utils.PasswordEncryptor;
import es.uji.daal.easyrent.validators.AccountInfoValidator;
import es.uji.daal.easyrent.validators.AddressInfoValidator;
import es.uji.daal.easyrent.validators.ChangePasswordValidator;
import es.uji.daal.easyrent.validators.PersonalDataValidator;
import es.uji.daal.easyrent.view_models.AccountInfoForm;
import es.uji.daal.easyrent.view_models.AddressInfoForm;
import es.uji.daal.easyrent.view_models.ChangePasswordForm;
import es.uji.daal.easyrent.view_models.PersonalDataForm;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpSession;
import java.util.UUID;

/**
 * Created by Alberto on 12/05/2016.
 */
@Controller
@RequestMapping("/user/edit")
public class UserEditController {
    @Autowired
    private UserRepository repository;

    @Autowired
    private PasswordEncryptor passwordEncryptor;

    @Autowired
    private FileUploader fileUploader;

    @RequestMapping("/{id}/account-info")
    public String accountInfo(Model model, @PathVariable String id) {
        User user = repository.findOne(UUID.fromString(id));
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (loggedUser.equals(user)) {
            AccountInfoForm form = new AccountInfoForm();
            form.fillUp(user);
            model.addAttribute("accountInfoForm", form);
            return "user/edit/accountInfo";
        }
        model.addAttribute("user", user);
        return "user/profile";
    }

    @RequestMapping(value = "/{id}/account-info", method = RequestMethod.POST)
    public String processAccountInfo(@ModelAttribute AccountInfoForm accountInfoForm,
                                     @PathVariable String id, BindingResult bindingResult) {
        User user = repository.findOne(UUID.fromString(id));
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (loggedUser.equals(user)) {
            new AccountInfoValidator().validate(accountInfoForm, bindingResult);
            if (!user.getUsername().equalsIgnoreCase(accountInfoForm.getUsername()) &&
                    repository.existsByUsername(accountInfoForm.getUsername())) {
                bindingResult.rejectValue("username", "invalid", "Username already exits");
            }
            if (repository.existsByEmail(accountInfoForm.getEmail()) && !accountInfoForm.getEmail().equals(user.getEmail())) {
                bindingResult.rejectValue("email", "exists", "There is an account with that email");
            }
            if (bindingResult.hasErrors()) {
                return "user/edit/accountInfo";
            }
            accountInfoForm.update(user);
            repository.save(user);
        }
        return "redirect:../../profile/"+id+".html";
    }

    @RequestMapping("/{id}/change-password")
    public String changePassword(Model model, @PathVariable String id) {
        User user = repository.findOne(UUID.fromString(id));
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (loggedUser.equals(user)) {
            ChangePasswordForm form = new ChangePasswordForm();
            model.addAttribute("changePasswordForm", form);
            return "user/edit/changePassword";
        }
        model.addAttribute("user", user);
        return "user/profile";
    }

    @RequestMapping(value = "/{id}/change-password", method = RequestMethod.POST)
    public String processChangePassword(@ModelAttribute ChangePasswordForm changePasswordForm,
                                        @PathVariable String id, BindingResult bindingResult,
                                        HttpSession session) {
        User user = repository.findOne(UUID.fromString(id));
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (loggedUser.equals(user)) {
            new ChangePasswordValidator().validate(changePasswordForm, bindingResult);
            if (!repository.authenticate(user.getUsername(), changePasswordForm.getOldPassword())) {
                bindingResult.rejectValue("oldPassword", "invalid", "Invalid password");
            }
            if (bindingResult.hasErrors()) {
                return "user/edit/changePassword";
            }
            changePasswordForm.setNewPassword(passwordEncryptor.generateHash(changePasswordForm.getNewPassword()));
            changePasswordForm.update(user);
            repository.save(user);
            session.invalidate();
        }
        return "redirect:../../profile/"+id+".html";
    }

    @RequestMapping("/{id}/personal-data")
    public String personalData(Model model, @PathVariable String id) {
        User user = repository.findOne(UUID.fromString(id));
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (loggedUser.equals(user)) {
            PersonalDataForm form = new PersonalDataForm();
            form.fillUp(user);
            model.addAttribute("personalDataForm", form);
            return "user/edit/personalData";
        }
        model.addAttribute("user", user);
        return "user/profile";
    }

    @RequestMapping(value = "/{id}/personal-data", method = RequestMethod.POST)
    public String processPersonalData(@ModelAttribute PersonalDataForm personalDataForm,
                                        @PathVariable String id, BindingResult bindingResult) {
        User user = repository.findOne(UUID.fromString(id));
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (loggedUser.equals(user)) {
            new PersonalDataValidator().validate(personalDataForm, bindingResult);
            if (bindingResult.hasErrors()) {
                return "user/edit/personalData";
            }
            personalDataForm.update(user);
            repository.save(user);
        }
        return "redirect:../../profile/"+id+".html#main-personal-data";
    }

    @RequestMapping("/{id}/address-info")
    public String addressInfo(Model model, @PathVariable String id) {
        User user = repository.findOne(UUID.fromString(id));
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (loggedUser.equals(user)) {
            AddressInfoForm form = new AddressInfoForm();
            form.fillUp(user);
            model.addAttribute("addressInfoForm", form);
            return "user/edit/addressInfo";
        }
        model.addAttribute("user", user);
        return "user/profile";
    }

    @RequestMapping(value = "/{id}/address-info", method = RequestMethod.POST)
    public String processAddressInfo(@ModelAttribute AddressInfoForm addressInfoForm,
                                      @PathVariable String id, BindingResult bindingResult) {
        User user = repository.findOne(UUID.fromString(id));
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (loggedUser.equals(user)) {
            new AddressInfoValidator().validate(addressInfoForm, bindingResult);
            if (bindingResult.hasErrors()) {
                return "user/edit/addressInfo";
            }
            addressInfoForm.update(user);
            repository.save(user);
        }
        return "redirect:../../profile/"+id+".html#main-address-info";
    }

    @RequestMapping(value = "/{id}/upload-picture", method = RequestMethod.POST)
    public String processUploadPicture(@RequestParam("file")MultipartFile file,
                                       @PathVariable String id) {
        User user = repository.findOne(UUID.fromString(id));
        User loggedUser = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (loggedUser.equals(user)) {
            String filename = fileUploader.upload("profile-pics", file);
            // TODO Show error message
            if (filename != null) {
                user.createPhoto(filename);
                repository.save(user);
            }
        }
        return "redirect:../../profile/"+ id + ".html";
    }
}
