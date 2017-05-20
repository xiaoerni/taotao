package om.taotao.order.service;

import om.taotao.order.pojo.OrderInfo;

import com.taotao.common.pojo.TaotaoResult;

public interface OrderService {

	TaotaoResult createOrder(OrderInfo orderInfo);
}
