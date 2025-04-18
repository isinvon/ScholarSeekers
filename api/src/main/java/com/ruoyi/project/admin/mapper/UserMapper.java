package com.ruoyi.project.admin.mapper;

import java.util.List;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ruoyi.project.admin.domain.Category;
import com.ruoyi.project.admin.domain.User;
import org.apache.ibatis.annotations.Mapper;

/**
 * 用户Mapper接口
 *
 * @author sinvon
 * @date 2025-04-19
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {
    /**
     * 查询用户
     *
     * @param id 用户主键
     * @return 用户
     */
    public User selectUserById(Integer id);

    /**
     * 查询用户列表
     *
     * @param user 用户
     * @return 用户集合
     */
    public List<User> selectUserList(User user);

    /**
     * 新增用户
     *
     * @param user 用户
     * @return 结果
     */
    public int insertUser(User user);

    /**
     * 修改用户
     *
     * @param user 用户
     * @return 结果
     */
    public int updateUser(User user);

    /**
     * 删除用户
     *
     * @param id 用户主键
     * @return 结果
     */
    public int deleteUserById(Integer id);

    /**
     * 批量删除用户
     *
     * @param ids 需要删除的数据主键集合
     * @return 结果
     */
    public int deleteUserByIds(Integer[] ids);
}
