package com.ruoyi.project.common.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ruoyi.common.utils.DateUtils;
import com.ruoyi.project.common.domain.Claim;
import com.ruoyi.project.common.mapper.ClaimMapper;
import com.ruoyi.project.common.service.IClaimService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 认领记录Service业务层处理
 *
 * @author sinvon
 * @date 2025-04-19
 */
@Service
public class ClaimServiceImpl extends ServiceImpl<ClaimMapper, Claim> implements IClaimService {
    @Autowired
    private ClaimMapper claimMapper;

    /**
     * 查询认领记录
     *
     * @param id 认领记录主键
     * @return 认领记录
     */
    @Override
    public Claim selectClaimById(Integer id) {
        return claimMapper.selectClaimById(id);
    }

    /**
     * 查询认领记录列表
     *
     * @param claim 认领记录
     * @return 认领记录
     */
    @Override
    public List<Claim> selectClaimList(Claim claim) {
        return claimMapper.selectClaimList(claim);
    }

    /**
     * 新增认领记录
     *
     * @param claim 认领记录
     * @return 结果
     */
    @Override
    public int insertClaim(Claim claim) {
        claim.setCreateTime(DateUtils.getNowDate());
        return claimMapper.insertClaim(claim);
    }

    /**
     * 修改认领记录
     *
     * @param claim 认领记录
     * @return 结果
     */
    @Override
    public int updateClaim(Claim claim) {
        claim.setUpdateTime(DateUtils.getNowDate());
        return claimMapper.updateClaim(claim);
    }

    /**
     * 批量删除认领记录
     *
     * @param ids 需要删除的认领记录主键
     * @return 结果
     */
    @Override
    public int deleteClaimByIds(Integer[] ids) {
        return claimMapper.deleteClaimByIds(ids);
    }

    /**
     * 删除认领记录信息
     *
     * @param id 认领记录主键
     * @return 结果
     */
    @Override
    public int deleteClaimById(Integer id) {
        return claimMapper.deleteClaimById(id);
    }
}
