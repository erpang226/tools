package com.syc.springdata.repositry;


import com.syc.springdata.entity.Member;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MemberRepository extends CrudRepository<Member, Integer> {

    // 单挑查询
    Member findById(int id);

    List<Member> getByAgeAndAddress(int gae, String address);

    List<Member> readByNameLike(String name);

    List<Member> readByNameStartingWith(String name);

    List<Member> readByIdIn(int[] ids);

    //带排序条件的in查询
    List<Member> readByIdInOrderByIdDesc(int[] ids);

    @Query("select m from Member m where m.name like %:name%")
    List<Member> getMemberInfoByName(@Param("name") String name);

    @Query(value = "select m.* from member m where m.name=:name and age>=30", nativeQuery = true)
    List<Member> getMemberInfoByNameAndAge(@Param("name") String name);

    @Modifying
    @Query("update Member m set m.name=:name where m.id=:id")
    void updateMemberById(@Param("name") String name, @Param("id") int id);

    @Modifying
    @Query("delete from Member m  where m.id=:id")
    void deleteMemberById(@Param("id") int id);


}
