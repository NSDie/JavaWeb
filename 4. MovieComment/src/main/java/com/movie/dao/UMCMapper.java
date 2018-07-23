package com.movie.dao;

import com.movie.bean.UMC;
import com.movie.bean.UMCExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UMCMapper {
    int countByExample(UMCExample example);

    int deleteByExample(UMCExample example);

    int deleteByPrimaryKey(Integer id);

    int insert(UMC record);

    int insertSelective(UMC record);

    List<UMC> selectByExample(UMCExample example);

    List<UMC> selectByMovieId(Integer id);

    UMC selectByPrimaryKey(Integer id);

    int updateByExampleSelective(@Param("record") UMC record, @Param("example") UMCExample example);

    int updateByExample(@Param("record") UMC record, @Param("example") UMCExample example);

    int updateByPrimaryKeySelective(UMC record);

    int updateByPrimaryKey(UMC record);
}