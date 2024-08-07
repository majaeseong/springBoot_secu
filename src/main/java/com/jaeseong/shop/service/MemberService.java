package com.jaeseong.shop.service;

import com.jaeseong.shop.domains.Member;
import com.jaeseong.shop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    public List<Member> getAllMembers(){
        return memberRepository.findAll();
    }

    public void addMember(Member member) throws Exception {

        boolean existUser = memberRepository.existsByUsername(member.getUsername());

        if(existUser){
            throw new Exception("중복");
        }

        var encodedPassword = passwordEncoder.encode(member.getPassword());
        member.setPassword(encodedPassword);

        memberRepository.save(member);

    }

}
