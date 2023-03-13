package com.zqi.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.zqi.pojo.Order;
import com.zqi.vo.AdminOrderVo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * order 的数据库进行接口
 */
public interface OrderMapper extends BaseMapper<Order> {
    /**
     * 查询后台管理的数据方法
     * @param offset
     * @param pageSize
     * @return
     */
    List<AdminOrderVo> selectAdminOrder(@Param("offset") int offset, @Param("pageSize") int pageSize);
}
