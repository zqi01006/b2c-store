package com.zqi.order.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.zqi.clients.ProductClient;
import com.zqi.order.mapper.OrderMapper;
import com.zqi.order.service.OrderService;
import com.zqi.param.OrderParam;
import com.zqi.param.PageParam;
import com.zqi.param.ProductCollectParam;
import com.zqi.pojo.Order;
import com.zqi.pojo.Product;
import com.zqi.to.OrderToProduct;
import com.zqi.utils.R;
import com.zqi.vo.AdminOrderVo;
import com.zqi.vo.CartVo;
import com.zqi.vo.OrderVo;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.weaver.ast.Or;
import org.checkerframework.checker.units.qual.A;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 *
 */
@Service
@Slf4j
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private ProductClient productClient;

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private OrderMapper orderMapper;
    /**
     * 进行订单数据保存业务
     *      1、将购物车数据转成订单数据
     *      2、进行订单数据的批量插入
     *      3、商品库存修改消息
     *      4、发送购物车库存修改消息
     * @param orderParam
     * @return
     */
    @Override
    @Transactional
    public R save(OrderParam orderParam) {
        //直接调用service提供的方法
        //baseMapper内置对象，调用mapper原有的方法

        //准备数据
        List<Integer> cartIds = new ArrayList<>();
        List<OrderToProduct> orderToProducts = new ArrayList<>();
        List<Order> orderList = new ArrayList<>();
        //生成数据
        Integer userId = orderParam.getUserId();
        long orderId =System.currentTimeMillis();
        for (CartVo cartVo : orderParam.getProducts()) {
            cartIds.add(cartVo.getId());//保存删除的购物车项id
            OrderToProduct orderToProduct = new OrderToProduct();
            orderToProduct.setNum(cartVo.getNum());
            orderToProduct.setProductId(cartVo.getProductID());
            orderToProducts.add(orderToProduct); //商品服务修改的数据

            Order order = new Order();
            order.setOrderId(orderId);
            order.setUserId(userId);
            order.setOrderTime(orderId);
            order.setProductId(cartVo.getProductID());
            order.setProductNum(cartVo.getNum());
            order.setProductPrice(cartVo.getPrice());
            orderList.add(order); //添加用户信息
        }
        //订单数量批量保存
        saveBatch(orderList);
        //发送购物车消息
        rabbitTemplate.convertAndSend("topic.ex","clear.cart",cartIds);
        //发送商品服务消息
        rabbitTemplate.convertAndSend("topic.ex","sub.number",orderToProducts);

        return R.ok("订单保存成功！");
    }

    /**
     * 1、查询用户的对应全部订单项
     * 2、利用stream进行订单分组 orderId
     * 3、查询订单的全部商品集合，并使用stream组成map
     * 4、封装返回的OrderVo对象
     * @param userId
     * @return
     */
    @Override
    public R list(Integer userId) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id",userId);
        List<Order> list = list(queryWrapper);

        //分组
        Map<Long, List<Order>> orderMap = list.stream().collect(Collectors.groupingBy(Order::getOrderId));
        //查询商品数据
        List<Integer> productIds = list.stream().map(Order::getProductId).collect(Collectors.toList());

        ProductCollectParam productCollectParam = new ProductCollectParam();
        productCollectParam.setProductIds(productIds);
        List<Product> productList = productClient.cartList(productCollectParam);

        Map<Integer, Product> productMap = productList.stream().collect(Collectors.toMap(Product::getProductId, v -> v));

        //结果封装
        List<List<OrderVo>> result = new ArrayList<>();
        //遍历订单项集合
        for (List<Order> orders : orderMap.values()) {
            List<OrderVo> orderVos = new ArrayList<>();
            for (Order order : orders) {
                OrderVo orderVo = new OrderVo();
                BeanUtils.copyProperties(order,orderVo);
                Product product = productMap.get(order.getProductId());
                orderVo.setProductName(product.getProductName());
                orderVo.setProductPicture(product.getProductPicture());
                orderVos.add(orderVo);
            }
            result.add(orderVos);
        }
        R r = R.ok("订单数据获取成功", result);
        log.info("OrderServiceImpl.list业务结束，结果：{}",r);
        return r;
    }

    @Override
    public R check(Integer productId) {
        QueryWrapper<Order> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_id",productId);

        Long count = baseMapper.selectCount(queryWrapper);

        if (count > 0){
            return R.fail("订单："+count+"商品引用，不能删除!");
        }

        return R.ok("无订单引用，可以删除！");
    }

    /**
     * 后台管理查询订单数据
     * @param pageParam
     * @return
     */
    @Override
    public R adminList(PageParam pageParam) {
        //分页参数计算完毕
        int offset = (pageParam.getCurrentPage() - 1) * pageParam.getPageSize();
        int pageSize = pageParam.getPageSize();

        List<AdminOrderVo> adminOrderVoList = orderMapper.selectAdminOrder(offset, pageSize);

        return R.ok("订单数据查询成功！",adminOrderVoList);
    }
}
