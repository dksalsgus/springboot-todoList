package com.example.todo.config.principal;

import com.example.todo.entity.member.Member;
import com.example.todo.repository.MemberRepository;
import javassist.NotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserPrincipalService implements UserDetailsService {

    private final MemberRepository memberRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            Member findMember = memberRepository.findByMemberId(username)
                    .orElseThrow(() -> new NotFoundException("Not Found Member"));
            return new UserPrincipal(findMember);
        } catch (NotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }
}
