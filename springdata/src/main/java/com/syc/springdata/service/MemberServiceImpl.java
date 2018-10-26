package com.syc.springdata.service;

import com.syc.springdata.repositry.MemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberServiceImpl implements MemberService {

    @Autowired
    private MemberRepository memberRepository;

    @Override
    @Transactional
    public void updateMember(String name, int id) {
        memberRepository.updateMemberById(name, id);
    }

    @Override
    @Transactional
    public void deleteMember(int id) {
        memberRepository.deleteMemberById(1);
    }
}
