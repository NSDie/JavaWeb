package com.bubble.dao;

import com.bubble.bean.Bubbles;
import com.bubble.bean.BubblesExample;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface BubblesMapper {
    int countByExample(BubblesExample example);

    int deleteByExample(BubblesExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(Bubbles record);

    int insertSelective(Bubbles record);

    List<Bubbles> selectByExampleWithBLOBs(BubblesExample example);

    List<Bubbles> selectByExample(BubblesExample example);

    List<Bubbles> selectNew();

    List<Bubbles> selectUser(int id);

    List<Bubbles> selectUserA(int id);

    Bubbles selectByBid(int id);

    Bubbles selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") Bubbles record, @Param("example") BubblesExample example);

    int updateByExampleWithBLOBs(@Param("record") Bubbles record, @Param("example") BubblesExample example);

    int updateByExample(@Param("record") Bubbles record, @Param("example") BubblesExample example);

    int updateByPrimaryKeySelective(Bubbles record);

    int updateByPrimaryKeyWithBLOBs(Bubbles record);

    int updateByPrimaryKey(Bubbles record);
}