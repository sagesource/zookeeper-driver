package org.sagesource.zookeeperdriver.mapper;

import java.util.List;

import org.mybatis.spring.annotation.MapperScan;
import org.sagesource.zookeeperdriver.entity.ZkServerInfo;

@MapperScan
public interface ZkServerInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ZkServerInfo record);

    ZkServerInfo selectByPrimaryKey(Integer id);

    List<ZkServerInfo> selectAll();

    int updateByPrimaryKey(ZkServerInfo record);
}