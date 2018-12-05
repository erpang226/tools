package com.syc.resttemplate.biz.repositry;

import com.syc.resttemplate.biz.entity.Member;
import com.syc.resttemplate.biz.service.MemberService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
public class MemberRepositoryTest {

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private MemberService memberService;

    @Test
    public void findById() {
        Member member = memberRepository.findById(2);
        System.out.println(member);
    }

    @Test
    public void getByAgeAndAddress() {
        List<Member> list = memberRepository.getByAgeAndAddress(35, "nanjing1");
        System.out.println(list);
    }

    @Test
    public void readByNameLike() {
        List<Member> list = memberRepository.readByNameLike("tom%");
        System.out.println(list);
    }

    @Test
    public void readByNameStartingWith() {

        List<Member> list = memberRepository.readByNameStartingWith("tom3");
        System.out.println(list);

    }

    @Test
    public void readByIdIn() {

        List<Member> list = memberRepository.readByIdIn(new int[]{2});
        System.out.println(list);


    }

    @Test
    public void readByIdInOrderByIdDesc() {
        List<Member> list = memberRepository.readByIdInOrderByIdDesc(new int[]{1, 2, 3, 4});
        System.out.println(list);

    }

    @Test
    public void getMemberInfoByName() {

        List<Member> list = memberRepository.getMemberInfoByName("tom");
        System.out.println(list);

    }

    @Test
    public void getMemberInfoByNameAndAge() {

        List<Member> list = memberRepository.getMemberInfoByNameAndAge("tom");

        System.out.println(list);
    }

    @Test
    public void updateMemberById() {
        memberService.updateMember("tom1", 1);
    }

    @Test
    public void deleteMemberById() {
        memberService.deleteMember(1);
    }
}