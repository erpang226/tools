package com.silicon.callcenter.dao;

import com.silicon.callcenter.dao.entity.CallInPlan;
import com.silicon.callcenter.dao.entity.CallInPlanExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface CallInPlanMapper {
    int countByExample(CallInPlanExample example);

    int deleteByExample(CallInPlanExample example);

    int deleteByPrimaryKey(Long callId);

    int insert(CallInPlan record);

    int insertSelective(CallInPlan record);

    List<CallInPlan> selectByExample(CallInPlanExample example);

    CallInPlan selectByPrimaryKey(Long callId);

    int updateByExampleSelective(@Param("record") CallInPlan record, @Param("example") CallInPlanExample example);

    int updateByExample(@Param("record") CallInPlan record, @Param("example") CallInPlanExample example);

    int updateByPrimaryKeySelective(CallInPlan record);

    int updateByPrimaryKey(CallInPlan record);
}