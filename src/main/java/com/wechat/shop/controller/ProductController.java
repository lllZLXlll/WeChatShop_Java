package com.wechat.shop.controller;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.wechat.shop.service.ProductService;

@Controller
public class ProductController {

	private Logger logger = Logger.getLogger(ProductController.class);

	@Autowired
	private ProductService productService;

	/**
	 * 商品列表多条件查询
	 * 
	 * @param pageNum
	 *            当前页
	 * @param name
	 *            商品名称
	 * @param salesVolumeSort
	 *            销量排序 1：降序
	 * @param priceSort
	 *            价格排序 1：升序 2：降序
	 * @param productClass
	 *            商品类型
	 * @return 分页数据
	 * 
	 * @throws Exception
	 */

	@RequestMapping("/queryProductList")
	@ResponseBody
	public Map<String, Object> queryProductList(Integer pageNum, String name, String salesVolumeSort, String priceSort,
			String productClass) throws Exception {
		try {
			return productService.queryProductList(pageNum, name, salesVolumeSort, priceSort, productClass);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("---!!!--- queryProductList 商品列表多条件查询 异常" + e.toString());
			throw e;
		}
	}

	/**
	 * 商品分类查询
	 * 
	 * @throws Exception
	 */
	@RequestMapping("/queryProductType")
	@ResponseBody
	public Map<String, Object> queryProductType() throws Exception {
		try {
			return productService.queryProductType();
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("---!!!--- queryProductType 商品分类查询 异常" + e.toString());
			throw e;
		}
	}

	/**
	 * 商品详细信息查询
	 * 
	 * @param productId
	 *            商品id
	 * @return 商品详情数据
	 * 
	 * @throws Exception
	 */

	@RequestMapping("/queryProductDetailInfoById")
	@ResponseBody
	public Map<String, Object> queryProductDetailInfoById(Long productId, String openid) throws Exception {
		try {
			return productService.queryProductDetailInfoById(productId, openid);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("---!!!--- queryProductDetailInfoById 商品详情数据查询 异常" + e.toString());
			throw e;
		}
	}

	/**
	 * 商品添加收藏
	 * 
	 * @param productId
	 *            商品id
	 * 
	 * @param openid
	 *            用户id
	 * 
	 * @return 商品收藏是否成功
	 * 
	 * @throws Exception
	 */

	@RequestMapping("/addCollectionProduct")
	@ResponseBody
	public Map<String, Object> addCollectionProduct(Long productId, String openid, Long pageNum) throws Exception {
		try {
			return productService.addCollectionProduct(productId, openid);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("---!!!--- addCollectionProduct 商品添加收藏 异常" + e.toString());
			throw e;
		}
	}

	/**
	 * 商品添加购物车
	 * 
	 * @param openid
	 *            用户id
	 * 
	 * @param productId
	 *            商品id
	 * 
	 * @param productClassId
	 *            商品类型id
	 * 
	 * @param productCount
	 *            商品选择数量
	 * 
	 * @return 商品添加购物车是否成功
	 * 
	 * @throws Exception
	 */

	@RequestMapping("/addShoppingCart")
	@ResponseBody
	public Map<String, Object> addShoppingCart(String openid, Long productId, Long productClassId, Long productCount)
			throws Exception {
		try {
			return productService.addShoppingCart(openid, productId, productClassId, productCount);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("---!!!--- addShoppingCart 商品添加购物车 异常" + e.toString());
			throw e;
		}
	}

	/**
	 * 订单结算信息查询
	 * 
	 * @param openid
	 *            用户md5加密的openid
	 * @return 订单结算信息数据
	 * 
	 * @throws Exception
	 */

	@RequestMapping("/queryOrderSettlementInfo")
	@ResponseBody
	public Map<String, Object> queryOrderSettlementInfo(String openid) throws Exception {
		try {
			return productService.queryOrderSettlementInfo(openid);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("---!!!--- queryOrderSettlementInfo 订单结算信息查询 异常" + e.toString());
			throw e;
		}
	}

	/**
	 * 生成订单
	 * 
	 * @param productId
	 *            商品id
	 * @param productClassId
	 *            商品类型id
	 * @param productCount
	 *            商品购买数量
	 * @param addreddId
	 *            收货地址id
	 * @param describe
	 *            买家留言描述
	 * @param openid
	 *            用户md5加密的openid
	 * @return 订单数据
	 * 
	 * @throws Exception
	 */

	@RequestMapping("/addOrder")
	@ResponseBody
	public Map<String, Object> addOrder(HttpServletRequest request) throws Exception {
		try {
			return productService.addOrder(request);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("---!!!--- addOrder 生成订单 异常" + e.toString());
			throw e;
		}
	}

	/**
	 * 查询已生成完整信息订单
	 * 
	 * @param order
	 *            订单号
	 * 
	 * @return 订单数据
	 * 
	 * @throws Exception
	 */

	@RequestMapping("/queryOrderInfo")
	@ResponseBody
	public Map<String, Object> queryOrderInfo(HttpServletRequest request) throws Exception {
		try {
			return productService.queryOrderInfo(request);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("---!!!--- queryOrderInfo 查询已生成完整信息订单 异常" + e.toString());
			throw e;
		}
	}
	
	/**
	 * 修改订单状态为取消
	 * 
	 * @param order
	 *            订单号
	 * 
	 * @return 订单数据
	 * 
	 * @throws Exception
	 */
	
	@RequestMapping("/updateOrderStatus")
	@ResponseBody
	public Map<String, Object> updateOrderStatus(HttpServletRequest request) throws Exception {
		try {
			return productService.updateOrderStatus(request);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("---!!!--- updateOrderStatus 修改订单状态为取消 异常" + e.toString());
			throw e;
		}
	}
	
	/**
	 * 删除订单
	 * 
	 * @param order
	 *            订单号
	 * 
	 * @return 订单数据
	 * 
	 * @throws Exception
	 */
	
	@RequestMapping("/delOrder")
	@ResponseBody
	public Map<String, Object> delOrder(HttpServletRequest request) throws Exception {
		try {
			return productService.delOrder(request);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("---!!!--- delOrder 删除订单 异常" + e.toString());
			throw e;
		}
	}
	
	/**
	 * 查询全部订单
	 * 
	 * @param openid
	 *            用户md5 加密openid
	 * 
	 * @return 订单数据
	 * 
	 * @throws Exception
	 */

	@RequestMapping("/queryAllOrder")
	@ResponseBody
	public Map<String, Object> queryAllOrder(HttpServletRequest request) throws Exception {
		try {
			return productService.queryAllOrder(request);
		} catch (Exception e) {
			e.printStackTrace();
			logger.error("---!!!--- queryAllOrder 查询全部订单 异常" + e.toString());
			throw e;
		}
	}

}
