package com.ruoyi.project.admin.mapper;

import java.util.List;
import com.ruoyi.project.admin.domain.Claim;

/**
 * 认领记录Mapper接口
 * 
 * @author sinvon
 * @date 2025-04-19
 */
public interface ClaimMapper 
{
    /**
     * 查询认领记录
     * 
     * @param id 认领记录主键
     * @return 认领记录
     */
    public Claim selectClaimById(Integer id);

    /**
     * 查询认领记录列表
     * 
     * @param claim 认领记录
     * @return 认领记录集合
     */
    public List<Claim> selectClaimList(Claim claim);

    /**
     * 新增认领记录
     * 
     * @param claim 认领记录
     * @return 结果
     */
    public int insertClaim(Claim claim);

    /**
     * 修改认领记录
     * 
     * @param claim 认领记录
     * @return 结果
     */
    public int updateClaim(Claim claim);

    /**
     * 删除认领记录
     * 
     * @param id 认领记录主键
     * @return 结果
     */
    public int deleteClaimById(Integer id);

    /**
     * 批量删除认领记录
     * 
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteClaimByIds(Integer[] ids);
}
