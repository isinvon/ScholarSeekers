package com.ruoyi.framework.handler;

import com.baomidou.mybatisplus.core.handlers.MetaObjectHandler;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.reflection.MetaObject;
import org.springframework.stereotype.Component;

import java.util.Date;

/**
 * 元数据对象处理器
 * 公共字段自动填充服务
 *
 * @author : sinvon
 * @since : 2025年4月19日03:46:56
 */
@Slf4j
@Component
public class MyMetaObjectHandler implements MetaObjectHandler {

    // @Override
    // public void insertFill(MetaObject metaObject) {
    //     log.info("公共字段开始自动填充[insert]...");
    //     this.strictInsertFill(metaObject, "createTime", LocalDateTime.class, LocalDateTime.now());
    // }
    //
    // @Override
    // public void updateFill(MetaObject metaObject) {
    //     log.info("公共字段开始自动填充[update]...");
    //     this.strictUpdateFill(metaObject, "updateTime", LocalDateTime.class, LocalDateTime.now());
    // }

    @Override
    public void insertFill(MetaObject metaObject) {
        this.strictInsertFill(metaObject, "updateTime", Date.class, new Date());
        this.strictInsertFill(metaObject, "createTime", Date.class, new Date());
    }

    @Override
    public void updateFill(MetaObject metaObject) {
        this.strictUpdateFill(metaObject, "updateTime", Date.class, new Date());
    }
}