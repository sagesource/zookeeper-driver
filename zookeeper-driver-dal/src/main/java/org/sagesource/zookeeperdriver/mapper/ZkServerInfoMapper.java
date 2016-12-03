package org.sagesource.zookeeperdriver.mapper;

import org.apache.ibatis.annotations.Param;
import org.mybatis.spring.annotation.MapperScan;
import org.sagesource.zookeeperdriver.entity.ZkServerInfo;
import org.sagesource.zookeeperdriver.entity.ZkServerInfoExample;

import java.util.List;

@MapperScan
public interface ZkServerInfoMapper {
	int countByExample(ZkServerInfoExample example);

	int deleteByExample(ZkServerInfoExample example);

	int deleteByPrimaryKey(Integer id);

	int insert(ZkServerInfo record);

	int insertSelective(ZkServerInfo record);

	List<ZkServerInfo> selectByExample(ZkServerInfoExample example);

	ZkServerInfo selectByPrimaryKey(Integer id);

	int updateByExampleSelective(@Param("record") ZkServerInfo record, @Param("example") ZkServerInfoExample example);

	int updateByExample(@Param("record") ZkServerInfo record, @Param("example") ZkServerInfoExample example);

	int updateByPrimaryKeySelective(ZkServerInfo record);

	int updateByPrimaryKey(ZkServerInfo record);
}