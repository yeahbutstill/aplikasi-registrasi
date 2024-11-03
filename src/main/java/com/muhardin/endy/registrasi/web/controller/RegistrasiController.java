package com.muhardin.endy.registrasi.web.controller;

import com.muhardin.endy.registrasi.web.dto.RegistrasiDto;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.support.SessionStatus;

@Controller
@RequestMapping("/register")
public class RegistrasiController {

    @GetMapping("/form")
    public ModelMap displayFormRegister() {
        return new ModelMap().addAttribute("pesertaBaru", new RegistrasiDto());
    }

    @PostMapping("/form")
    public String processFormRegister(@ModelAttribute("pesertaBaru") @Valid RegistrasiDto registrasiDto,
                                      BindingResult errors, SessionStatus status) {
        if (errors.hasErrors()) {
            return "register/form";
        }
        status.setComplete();
        return "redirect:success";
    }

    @GetMapping("/success")
    public void registrationSuccessful() {
        // tampilkan template sukses registrasi
    }
}
