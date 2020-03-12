package ru.itmo.wp.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ru.itmo.wp.form.NoticeCredentials;
import ru.itmo.wp.service.NoticeService;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

@Controller
public class NotifyPage extends Page {
    private final NoticeService noticeService;

    public NotifyPage(NoticeService noticeService) {
        this.noticeService = noticeService;
    }


    @GetMapping("/notify")
    public String notifyGet(Model model) {
        model.addAttribute("notifyForm", new NoticeCredentials());
        return "NotifyPage";
    }

    @PostMapping("/notify")
    public String notifyPost(@Valid @ModelAttribute("notifyForm") NoticeCredentials notifyForm,
                             BindingResult bindingResult,
                             HttpSession httpSession) {
        if (bindingResult.hasErrors()) {
            return "NotifyPage";
        }
        noticeService.addNotice(notifyForm);
        putMessage(httpSession, "Successfully notified!");
        return "redirect:/";
    }

}
