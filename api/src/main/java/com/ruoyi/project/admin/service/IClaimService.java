package com.ruoyi.project.admin.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ruoyi.project.admin.domain.Claim;

import java.util.List;

/**
 * 认领记录Service接口
 *
 * @author sinvon
 * @date 2025-04-19
 */
public interface IClaimService extends IService<Claim> {
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
     * 批量删除认领记录
     *
     * @param ids 需要删除的认领记录主键集合
     * @return 结果
     */
    public int deleteClaimByIds(Integer[] ids);

    /**
     * 删除认领记录信息
     *
     * @param id 认领记录主键
     * @return 结果
     */
    public int deleteClaimById(Integer id);
}
