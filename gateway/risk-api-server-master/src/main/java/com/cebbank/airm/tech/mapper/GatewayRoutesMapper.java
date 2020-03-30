package com.cebbank.airm.tech.mapper;

import com.cebbank.airm.tech.domain.GatewayRoutes;
import com.cebbank.airm.tech.domain.GatewayRoutesExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GatewayRoutesMapper {
    long countByExample(GatewayRoutesExample example);

    int deleteByExample(GatewayRoutesExample example);

    int deleteByPrimaryKey(Long id);

    int insert(GatewayRoutes record);

    int insertSelective(GatewayRoutes record);

    List<GatewayRoutes> selectByExample(GatewayRoutesExample example);

    GatewayRoutes selectByPrimaryKey(Long id);

    int updateByExampleSelective(@Param("record") GatewayRoutes record, @Param("example") GatewayRoutesExample example);

    int updateByExample(@Param("record") GatewayRoutes record, @Param("example") GatewayRoutesExample example);

    int updateByPrimaryKeySelective(GatewayRoutes record);

    int updateByPrimaryKey(GatewayRoutes record);
}