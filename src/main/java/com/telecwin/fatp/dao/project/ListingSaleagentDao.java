package com.telecwin.fatp.dao.project;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.huajin.baymax.db.annotation.MyBatisDao;
import com.telecwin.fatp.po.project.ListingSaleagentPo;

/**
 * ListingSaleagent
 */
@MyBatisDao
public interface ListingSaleagentDao {
	ListingSaleagentPo findSaleAgentById(@Param("exchangeId") int exchangeId, @Param("id") int id);

	void delete(@Param("memberId") Integer memberId, @Param("id") int id);

	void deleteByProjectId(@Param("memberId") Integer memberId, @Param("projectId") int projectId);

	int insert(ListingSaleagentPo listingSaleagentPo);
	
	void insertBatch(List<ListingSaleagentPo> saleAgentList);

	int update(ListingSaleagentPo listingSaleagentPo);

	List<ListingSaleagentPo> select(Map<String, Object> map);

	ListingSaleagentPo getByProjectIdAndSaleMemberId(Map<String, Object> map);

	int findMemberSaleagentNum(Map<String,Object> map);

	List<Integer> selectRecordSaleMemberIds(int projectRecordId);
}
