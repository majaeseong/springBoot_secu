package com.jaeseong.shop.controller;

import com.jaeseong.shop.domains.Member;
import com.jaeseong.shop.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Objects;

@Controller
@RequiredArgsConstructor
public class MemberController {


    private final MemberService service;

    @GetMapping("/login")
    public String login(){
        return "member/login.html";
    }

    @GetMapping("/memberList")
    public String memberList(Model model){
        var members = service.getAllMembers();
        model.addAttribute("members",members);
        return "member/list.html";
    }

    @GetMapping("/register")
    public String register(Authentication auth){
        if(!Objects.isNull(auth) && auth.isAuthenticated()){
            return "redirect:memberList";
        }
        return "member/register.html";
    }

    @PostMapping("/register")
    public String addMember(Member member) throws Exception {
        service.addMember(member);
        return "redirect:/memberList";
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/mypage")
    public String myPage(Authentication auth){


        return "member/mypage.html";
    }

}
