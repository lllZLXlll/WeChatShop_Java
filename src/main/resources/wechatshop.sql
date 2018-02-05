/*
Navicat MySQL Data Transfer

Source Server         : 微信小程序数据库
Source Server Version : 50628
Source Host           : gz-cdb-0uw0wmgs.sql.tencentcdb.com:63535
Source Database       : wechatshop

Target Server Type    : MYSQL
Target Server Version : 50628
File Encoding         : 65001

Date: 2018-02-05 18:55:50
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for t_admin
-- ----------------------------
DROP TABLE IF EXISTS `t_admin`;
CREATE TABLE `t_admin` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `adminName` varchar(30) NOT NULL COMMENT '管理员用户名',
  `accountNumber` varchar(30) NOT NULL COMMENT '管理员账号',
  `password` varchar(50) NOT NULL COMMENT 'md5加密过后的管理员密码',
  `createTime` datetime NOT NULL COMMENT '管理员创建时间',
  `lastLoginTime` datetime NOT NULL COMMENT '最后登录时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_admin
-- ----------------------------
INSERT INTO `t_admin` VALUES ('1', '系统管理员', '13317071014', 'gnzLDuqKcGxMNKFokfhOew==', '2018-01-19 19:28:32', '2018-01-19 19:28:34');

-- ----------------------------
-- Table structure for t_home_banner
-- ----------------------------
DROP TABLE IF EXISTS `t_home_banner`;
CREATE TABLE `t_home_banner` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `productId` bigint(20) NOT NULL COMMENT '商品id，点击图片链接到的商品id',
  `image` varchar(255) DEFAULT NULL COMMENT 'banner图片',
  `sort` int(11) NOT NULL COMMENT '序号',
  `status` int(11) NOT NULL COMMENT '状态：1.显示，2.隐藏，3.删除',
  `lastUpdateTime` datetime NOT NULL COMMENT '最后一次修改时间',
  `addTime` datetime NOT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8 COMMENT='首页banner数据表\r\n';

-- ----------------------------
-- Records of t_home_banner
-- ----------------------------
INSERT INTO `t_home_banner` VALUES ('1', '1', 'http://img02.tooopen.com/images/20150928/tooopen_sy_143912755726.jpg', '1', '3', '2018-01-20 16:14:30', '2018-01-20 16:14:32');
INSERT INTO `t_home_banner` VALUES ('2', '3', 'http://localhost:8080/zlx/resources/admin/upload/home/20180122135918362.jpg', '1', '3', '2018-01-22 15:23:55', '2018-01-20 16:14:32');
INSERT INTO `t_home_banner` VALUES ('3', '1', 'http://img02.tooopen.com/images/20150928/tooopen_sy_143912755726.jpg', '1', '3', '2018-01-20 16:14:30', '2018-01-20 16:14:32');
INSERT INTO `t_home_banner` VALUES ('4', '1', 'http://img02.tooopen.com/images/20150928/tooopen_sy_143912755726.jpg', '1', '3', '2018-01-20 16:14:30', '2018-01-20 16:14:32');
INSERT INTO `t_home_banner` VALUES ('5', '1', 'http://img02.tooopen.com/images/20150928/tooopen_sy_143912755726.jpg', '1', '3', '2018-01-20 16:14:30', '2018-01-20 16:14:32');
INSERT INTO `t_home_banner` VALUES ('6', '1', 'http://img02.tooopen.com/images/20150928/tooopen_sy_143912755726.jpg', '1', '3', '2018-01-20 16:14:30', '2018-01-20 16:14:32');
INSERT INTO `t_home_banner` VALUES ('7', '1', 'http://img02.tooopen.com/images/20150928/tooopen_sy_143912755726.jpg', '1', '3', '2018-01-20 16:14:30', '2018-01-20 16:14:32');
INSERT INTO `t_home_banner` VALUES ('8', '1', 'http://img02.tooopen.com/images/20150928/tooopen_sy_143912755726.jpg', '1', '3', '2018-01-20 16:14:30', '2018-01-20 16:14:32');
INSERT INTO `t_home_banner` VALUES ('9', '1', 'http://img02.tooopen.com/images/20150928/tooopen_sy_143912755726.jpg', '1', '3', '2018-01-20 16:14:30', '2018-01-20 16:14:32');
INSERT INTO `t_home_banner` VALUES ('10', '14', 'http://img02.tooopen.com/images/20150928/tooopen_sy_143912755726.jpg', '2', '3', '2018-01-22 14:08:08', '2018-01-20 16:14:32');
INSERT INTO `t_home_banner` VALUES ('11', '1', 'http://img02.tooopen.com/images/20150928/tooopen_sy_143912755726.jpg', '1', '3', '2018-01-20 16:14:30', '2018-01-20 16:14:32');
INSERT INTO `t_home_banner` VALUES ('12', '1', 'http://localhost:8080/zlx/resources/admin/upload/home/20180121154704880.jpg', '2', '3', '2018-01-21 15:48:45', '2018-01-21 15:48:45');
INSERT INTO `t_home_banner` VALUES ('13', '2', 'http://localhost:8080/zlx/resources/admin/upload/home/20180122105204351.jpg', '3', '3', '2018-01-22 10:53:24', '2018-01-22 10:52:20');
INSERT INTO `t_home_banner` VALUES ('14', '1', 'http://localhost:8080/zlx/resources/admin/upload/home/20180122105342954.jpg', '4', '3', '2018-01-22 10:53:46', '2018-01-22 10:53:46');
INSERT INTO `t_home_banner` VALUES ('15', '5', 'http://localhost:8080/zlx/resources/admin/upload/home/20180122134339178.jpg', '5', '3', '2018-01-22 13:43:53', '2018-01-22 13:43:53');
INSERT INTO `t_home_banner` VALUES ('16', '14', 'http://localhost:8080/zlx/resources/admin/upload/home/20180122153800931.jpg', '2', '1', '2018-01-23 16:43:47', '2018-01-22 15:38:05');
INSERT INTO `t_home_banner` VALUES ('17', '14', 'http://localhost:8080/zlx/resources/admin/upload/home/20180122153818338.jpg', '7', '3', '2018-01-22 15:38:22', '2018-01-22 15:38:22');
INSERT INTO `t_home_banner` VALUES ('18', '14', 'http://localhost:8080/zlx/resources/admin/upload/home/20180123164409041.jpg', '1', '1', '2018-01-23 16:47:39', '2018-01-23 16:47:39');

-- ----------------------------
-- Table structure for t_home_recommended
-- ----------------------------
DROP TABLE IF EXISTS `t_home_recommended`;
CREATE TABLE `t_home_recommended` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `productId` bigint(20) NOT NULL COMMENT '商品id，点击图片链接到的商品id',
  `sort` int(11) NOT NULL COMMENT '序号',
  `status` int(11) NOT NULL COMMENT '状态：1.显示，2.隐藏，3.删除',
  `lastUpdateTime` datetime NOT NULL COMMENT '最后一次修改时间',
  `addTime` datetime NOT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8 COMMENT='首页推荐商品数据表';

-- ----------------------------
-- Records of t_home_recommended
-- ----------------------------
INSERT INTO `t_home_recommended` VALUES ('1', '1', '1', '1', '2018-01-24 10:59:51', '2018-01-24 10:23:14');
INSERT INTO `t_home_recommended` VALUES ('2', '2', '3', '3', '2018-01-24 10:34:05', '2018-01-24 10:33:42');
INSERT INTO `t_home_recommended` VALUES ('3', '1', '4', '1', '2018-01-24 11:00:04', '2018-01-24 10:34:53');
INSERT INTO `t_home_recommended` VALUES ('4', '1', '5', '1', '2018-01-24 11:00:19', '2018-01-24 10:35:01');
INSERT INTO `t_home_recommended` VALUES ('5', '1', '6', '1', '2018-01-24 10:35:16', '2018-01-24 10:35:16');
INSERT INTO `t_home_recommended` VALUES ('6', '1', '7', '2', '2018-01-24 11:19:42', '2018-01-24 10:35:37');

-- ----------------------------
-- Table structure for t_product_class
-- ----------------------------
DROP TABLE IF EXISTS `t_product_class`;
CREATE TABLE `t_product_class` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '商品分类表id',
  `productId` bigint(11) NOT NULL COMMENT '商品信息表id',
  `class` varchar(255) NOT NULL COMMENT '商品类型 如： 小米手环2 黑色 + 红色腕带',
  `price` decimal(10,2) NOT NULL COMMENT '此分类的商品价格',
  `count` bigint(11) NOT NULL DEFAULT '0' COMMENT '此分类商品库存：只有当卖家在后台管理系统中点击‘确认发货’后才能减少库存',
  `freezeCount` bigint(20) NOT NULL DEFAULT '0' COMMENT '冻结的商品数量：当用户下单后将订单中的数量冻结，剩余库存=库存-冻结数量。待用户付款成功后或订单过期后解冻。',
  `salesVolume` bigint(11) NOT NULL DEFAULT '0' COMMENT '商品销量',
  `productImage` varchar(255) NOT NULL COMMENT '分类图片',
  `addClassTime` datetime NOT NULL COMMENT '添加分类时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=20 DEFAULT CHARSET=utf8 COMMENT='商品选择分类表';

-- ----------------------------
-- Records of t_product_class
-- ----------------------------
INSERT INTO `t_product_class` VALUES ('1', '1', '小米手环2 黑色', '149.00', '3', '1', '0', 'http://www.cncrk.com/up/1605/201605111616066775.jpg', '2017-12-20 11:20:40');
INSERT INTO `t_product_class` VALUES ('2', '1', '小米手环2 红色', '149.99', '15', '2', '1', 'http://i.wsloan.com/newsfile/1512281317806.jpg', '2017-12-20 11:21:39');
INSERT INTO `t_product_class` VALUES ('3', '2', '小米手环2 红色2', '149.99', '2', '0', '20', 'http://www.cncrk.com/up/1605/201605111616066775.jpg', '2017-12-20 11:21:39');
INSERT INTO `t_product_class` VALUES ('4', '3', '小米手环2 红色', '149.99', '3', '0', '3', 'http://www.cncrk.com/up/1605/201605111616066775.jpg', '2017-12-20 11:21:39');
INSERT INTO `t_product_class` VALUES ('5', '4', '小米手环2 红色', '149.99', '4', '0', '4', 'http://www.cncrk.com/up/1605/201605111616066775.jpg', '2017-12-20 11:21:39');
INSERT INTO `t_product_class` VALUES ('6', '5', '小米手环2 红色', '149.99', '5', '0', '5', 'http://www.cncrk.com/up/1605/201605111616066775.jpg', '2017-12-20 11:21:39');
INSERT INTO `t_product_class` VALUES ('7', '6', '小米手环2 红色', '149.99', '6', '0', '6', 'http://www.cncrk.com/up/1605/201605111616066775.jpg', '2017-12-20 11:21:39');
INSERT INTO `t_product_class` VALUES ('8', '7', '小米手环2 红色', '149.99', '7', '0', '7', 'http://www.cncrk.com/up/1605/201605111616066775.jpg', '2017-12-20 11:21:39');
INSERT INTO `t_product_class` VALUES ('9', '8', '小米手环2 红色', '149.99', '8', '0', '8', 'http://www.cncrk.com/up/1605/201605111616066775.jpg', '2017-12-20 11:21:39');
INSERT INTO `t_product_class` VALUES ('10', '9', '小米手环2 红色', '149.99', '9', '0', '9', 'http://www.cncrk.com/up/1605/201605111616066775.jpg', '2017-12-20 11:21:39');
INSERT INTO `t_product_class` VALUES ('11', '10', '小米手环2 红色', '149.99', '10', '0', '10', 'http://www.cncrk.com/up/1605/201605111616066775.jpg', '2017-12-20 11:21:39');
INSERT INTO `t_product_class` VALUES ('12', '11', '小米手环2 红色', '149.99', '11', '0', '11', 'http://www.cncrk.com/up/1605/201605111616066775.jpg', '2017-12-20 11:21:39');
INSERT INTO `t_product_class` VALUES ('13', '12', '小米手环2 红色', '149.99', '12', '0', '12', 'http://www.cncrk.com/up/1605/201605111616066775.jpg', '2017-12-20 11:21:39');
INSERT INTO `t_product_class` VALUES ('14', '13', '小米手环2 红色', '149.99', '13', '0', '13', 'http://www.cncrk.com/up/1605/201605111616066775.jpg', '2017-12-20 11:21:39');
INSERT INTO `t_product_class` VALUES ('15', '14', '小米手环2 红色', '149.99', '14', '0', '14', 'http://www.cncrk.com/up/1605/201605111616066775.jpg', '2017-12-20 11:21:39');
INSERT INTO `t_product_class` VALUES ('16', '15', '小米手环2 红色', '149.99', '15', '0', '15', 'http://www.cncrk.com/up/1605/201605111616066775.jpg', '2017-12-20 11:21:39');
INSERT INTO `t_product_class` VALUES ('17', '26', '分类测试-1', '109.58', '1', '0', '0', 'http://localhost:8080/zlx/resources/admin/upload/product/class/20180201220140919.jpg', '2018-02-01 22:01:51');
INSERT INTO `t_product_class` VALUES ('18', '26', '分类测试-2', '102.44', '6', '0', '0', 'http://localhost:8080/zlx/resources/admin/upload/product/class/20180201220258257.jpg', '2018-02-01 22:03:01');
INSERT INTO `t_product_class` VALUES ('19', '26', '分类测试-3', '150.91', '6', '0', '0', 'http://localhost:8080/zlx/resources/admin/upload/product/class/20180202151308504.jpg', '2018-02-01 22:05:33');

-- ----------------------------
-- Table structure for t_product_collection
-- ----------------------------
DROP TABLE IF EXISTS `t_product_collection`;
CREATE TABLE `t_product_collection` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '商品收藏id',
  `productId` bigint(10) NOT NULL COMMENT '商品信息表id',
  `openidMd5` varchar(255) NOT NULL COMMENT '用户md5加密后的openid',
  `addTime` datetime NOT NULL COMMENT '商品添加收藏时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=utf8 COMMENT='用户商品收藏表';

-- ----------------------------
-- Records of t_product_collection
-- ----------------------------
INSERT INTO `t_product_collection` VALUES ('21', '8', 'wMM37KSHNhYUWmj9dhl7fg==', '2018-01-03 21:41:13');
INSERT INTO `t_product_collection` VALUES ('24', '5', 'wMM37KSHNhYUWmj9dhl7fg==', '2018-01-03 21:41:24');
INSERT INTO `t_product_collection` VALUES ('40', '12', 'wMM37KSHNhYUWmj9dhl7fg==', '2018-01-09 22:05:10');
INSERT INTO `t_product_collection` VALUES ('41', '15', 'wMM37KSHNhYUWmj9dhl7fg==', '2018-01-11 14:30:26');

-- ----------------------------
-- Table structure for t_product_image
-- ----------------------------
DROP TABLE IF EXISTS `t_product_image`;
CREATE TABLE `t_product_image` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '商品图片表id',
  `productId` bigint(11) NOT NULL COMMENT '商品信息表id',
  `image` varchar(255) NOT NULL COMMENT '图片',
  `addImageTime` datetime NOT NULL COMMENT '添加图片时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=58 DEFAULT CHARSET=utf8 COMMENT='商品图片表';

-- ----------------------------
-- Records of t_product_image
-- ----------------------------
INSERT INTO `t_product_image` VALUES ('1', '1', 'http://www.safetyemc.cn/file/upload/201606/03/101909601.jpg', '2017-12-20 11:17:35');
INSERT INTO `t_product_image` VALUES ('2', '1', 'http://image3.suning.cn/uimg/shp/userItems/146830531247012594_1.jpg', '2017-12-20 11:18:23');
INSERT INTO `t_product_image` VALUES ('3', '1', 'http://www.cncrk.com/up/1605/201605111616066775.jpg', '2017-12-20 11:18:57');
INSERT INTO `t_product_image` VALUES ('4', '1', 'http://i.wsloan.com/newsfile/1512281317806.jpg', '2017-12-20 11:19:23');
INSERT INTO `t_product_image` VALUES ('5', '1', 'http://img.pconline.com.cn/images/product/6195/619592/hengfang_m2.jpg', '2017-12-20 11:19:41');
INSERT INTO `t_product_image` VALUES ('6', '2', 'http://image3.suning.cn/uimg/shp/userItems/146830531247012594_1.jpg', '2017-12-20 19:19:47');
INSERT INTO `t_product_image` VALUES ('7', '3', 'http://image3.suning.cn/uimg/shp/userItems/146830531247012594_1.jpg', '2017-12-20 19:19:58');
INSERT INTO `t_product_image` VALUES ('8', '4', 'http://image3.suning.cn/uimg/shp/userItems/146830531247012594_1.jpg', '2017-12-20 19:20:09');
INSERT INTO `t_product_image` VALUES ('9', '5', 'http://image3.suning.cn/uimg/shp/userItems/146830531247012594_1.jpg', '2017-12-20 19:20:19');
INSERT INTO `t_product_image` VALUES ('11', '7', 'http://image3.suning.cn/uimg/shp/userItems/146830531247012594_1.jpg', '2017-12-20 19:20:43');
INSERT INTO `t_product_image` VALUES ('12', '8', 'http://image3.suning.cn/uimg/shp/userItems/146830531247012594_1.jpg', '2017-12-20 11:18:23');
INSERT INTO `t_product_image` VALUES ('13', '9', 'http://image3.suning.cn/uimg/shp/userItems/146830531247012594_1.jpg', '2017-12-20 11:18:23');
INSERT INTO `t_product_image` VALUES ('14', '10', 'http://image3.suning.cn/uimg/shp/userItems/146830531247012594_1.jpg', '2017-12-20 11:18:23');
INSERT INTO `t_product_image` VALUES ('15', '11', 'http://image3.suning.cn/uimg/shp/userItems/146830531247012594_1.jpg', '2017-12-20 11:18:23');
INSERT INTO `t_product_image` VALUES ('16', '12', 'http://image3.suning.cn/uimg/shp/userItems/146830531247012594_1.jpg', '2017-12-20 11:18:23');
INSERT INTO `t_product_image` VALUES ('17', '13', 'http://image3.suning.cn/uimg/shp/userItems/146830531247012594_1.jpg', '2017-12-20 11:18:23');
INSERT INTO `t_product_image` VALUES ('18', '14', 'http://image3.suning.cn/uimg/shp/userItems/146830531247012594_1.jpg', '2017-12-20 11:18:23');
INSERT INTO `t_product_image` VALUES ('19', '15', 'http://image3.suning.cn/uimg/shp/userItems/146830531247012594_1.jpg', '2017-12-20 11:18:23');
INSERT INTO `t_product_image` VALUES ('48', '24', 'http://image3.suning.cn/uimg/shp/userItems/146830531247012594_1.jpg', '2018-01-30 16:07:14');
INSERT INTO `t_product_image` VALUES ('52', '26', 'http://localhost:8080/zlx/resources/admin/upload/product/20180130201405734.jpg', '2018-01-30 20:14:38');
INSERT INTO `t_product_image` VALUES ('53', '26', 'http://localhost:8080/zlx/resources/admin/upload/product/20180130201412961.jpg', '2018-01-30 20:14:38');
INSERT INTO `t_product_image` VALUES ('54', '26', 'http://localhost:8080/zlx/resources/admin/upload/product/20180130201421183.jpg', '2018-01-30 20:14:38');
INSERT INTO `t_product_image` VALUES ('56', '6', 'http://image3.suning.cn/uimg/shp/userItems/146830531247012594_1.jpg', '2018-01-30 20:17:45');
INSERT INTO `t_product_image` VALUES ('57', '6', 'http://localhost:8080/zlx/resources/admin/upload/product/20180130201743811.jpg', '2018-01-30 20:17:45');

-- ----------------------------
-- Table structure for t_product_imageText
-- ----------------------------
DROP TABLE IF EXISTS `t_product_imageText`;
CREATE TABLE `t_product_imageText` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '商品图文详情表id',
  `productId` bigint(11) NOT NULL COMMENT '商品信息表id',
  `detail` varchar(255) NOT NULL COMMENT '文字说明',
  `image` varchar(255) NOT NULL COMMENT '图片',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=23 DEFAULT CHARSET=utf8 COMMENT='商品图文详情表';

-- ----------------------------
-- Records of t_product_imageText
-- ----------------------------
INSERT INTO `t_product_imageText` VALUES ('1', '1', '', 'https://img.alicdn.com/imgextra/i1/1714128138/TB2ZLfDpVXXXXXmXXXXXXXXXXXX-1714128138.jpg');
INSERT INTO `t_product_imageText` VALUES ('2', '1', '', 'https://img.alicdn.com/imgextra/i2/1714128138/TB22yHrpVXXXXa7XXXXXXXXXXXX-1714128138.jpg');
INSERT INTO `t_product_imageText` VALUES ('3', '1', '', 'https://img.alicdn.com/imgextra/i2/1714128138/TB23q_ipVXXXXb6XXXXXXXXXXXX-1714128138.jpg');
INSERT INTO `t_product_imageText` VALUES ('4', '1', '', 'https://img.alicdn.com/imgextra/i4/1714128138/TB255j8t8NkpuFjy0FaXXbRCVXa-1714128138.jpg');
INSERT INTO `t_product_imageText` VALUES ('5', '1', '', 'https://img.alicdn.com/imgextra/i2/1714128138/TB2Ff10pVXXXXadXpXXXXXXXXXX-1714128138.jpg');
INSERT INTO `t_product_imageText` VALUES ('6', '2', '', 'https://img.alicdn.com/imgextra/i1/1714128138/TB2ZLfDpVXXXXXmXXXXXXXXXXXX-1714128138.jpg');
INSERT INTO `t_product_imageText` VALUES ('7', '3', '', 'https://img.alicdn.com/imgextra/i1/1714128138/TB2ZLfDpVXXXXXmXXXXXXXXXXXX-1714128138.jpg');
INSERT INTO `t_product_imageText` VALUES ('8', '4', '', 'https://img.alicdn.com/imgextra/i1/1714128138/TB2ZLfDpVXXXXXmXXXXXXXXXXXX-1714128138.jpg');
INSERT INTO `t_product_imageText` VALUES ('9', '5', '', 'https://img.alicdn.com/imgextra/i1/1714128138/TB2ZLfDpVXXXXXmXXXXXXXXXXXX-1714128138.jpg');
INSERT INTO `t_product_imageText` VALUES ('10', '6', '', 'https://img.alicdn.com/imgextra/i1/1714128138/TB2ZLfDpVXXXXXmXXXXXXXXXXXX-1714128138.jpg');
INSERT INTO `t_product_imageText` VALUES ('11', '7', '', 'https://img.alicdn.com/imgextra/i1/1714128138/TB2ZLfDpVXXXXXmXXXXXXXXXXXX-1714128138.jpg');
INSERT INTO `t_product_imageText` VALUES ('12', '8', '', 'https://img.alicdn.com/imgextra/i1/1714128138/TB2ZLfDpVXXXXXmXXXXXXXXXXXX-1714128138.jpg');
INSERT INTO `t_product_imageText` VALUES ('13', '9', '', 'https://img.alicdn.com/imgextra/i1/1714128138/TB2ZLfDpVXXXXXmXXXXXXXXXXXX-1714128138.jpg');
INSERT INTO `t_product_imageText` VALUES ('14', '10', '', 'https://img.alicdn.com/imgextra/i1/1714128138/TB2ZLfDpVXXXXXmXXXXXXXXXXXX-1714128138.jpg');
INSERT INTO `t_product_imageText` VALUES ('15', '11', '', 'https://img.alicdn.com/imgextra/i1/1714128138/TB2ZLfDpVXXXXXmXXXXXXXXXXXX-1714128138.jpg');
INSERT INTO `t_product_imageText` VALUES ('16', '12', '', 'https://img.alicdn.com/imgextra/i1/1714128138/TB2ZLfDpVXXXXXmXXXXXXXXXXXX-1714128138.jpg');
INSERT INTO `t_product_imageText` VALUES ('17', '13', '', 'https://img.alicdn.com/imgextra/i1/1714128138/TB2ZLfDpVXXXXXmXXXXXXXXXXXX-1714128138.jpg');
INSERT INTO `t_product_imageText` VALUES ('18', '14', '', 'https://img.alicdn.com/imgextra/i1/1714128138/TB2ZLfDpVXXXXXmXXXXXXXXXXXX-1714128138.jpg');
INSERT INTO `t_product_imageText` VALUES ('19', '15', '', 'https://img.alicdn.com/imgextra/i1/1714128138/TB2ZLfDpVXXXXXmXXXXXXXXXXXX-1714128138.jpg');
INSERT INTO `t_product_imageText` VALUES ('20', '26', '113', 'http://localhost:8080/zlx/resources/admin/upload/product/imageText/20180202172224709.jpg');
INSERT INTO `t_product_imageText` VALUES ('21', '26', '223', 'http://localhost:8080/zlx/resources/admin/upload/product/imageText/20180202172233670.jpg');

-- ----------------------------
-- Table structure for t_product_info
-- ----------------------------
DROP TABLE IF EXISTS `t_product_info`;
CREATE TABLE `t_product_info` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `name` varchar(255) NOT NULL COMMENT '商品名称，需要数据名称模糊搜索商品',
  `typeId` bigint(11) NOT NULL COMMENT '商品类型id，对应商品类型表',
  `productImage` varchar(255) NOT NULL COMMENT '商品主图，在商品列表中展示的图片大图',
  `price` decimal(10,2) NOT NULL COMMENT '商品价格',
  `showPrice` decimal(10,2) DEFAULT NULL COMMENT '商品展示价格，就是打折前的价格',
  `expressFee` decimal(10,2) NOT NULL DEFAULT '0.00' COMMENT '商品快递费',
  `buyCount` int(11) NOT NULL DEFAULT '-1' COMMENT '此商品每个用户限购数量 -1：不限制',
  `downShelves` int(1) NOT NULL DEFAULT '1' COMMENT '是否下架 0：否 1：是',
  `delStatus` int(11) NOT NULL DEFAULT '0' COMMENT '是否删除 0：否 1：是',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 COMMENT='商品信息表';

-- ----------------------------
-- Records of t_product_info
-- ----------------------------
INSERT INTO `t_product_info` VALUES ('1', '小米手环1', '1', 'http://www.safetyemc.cn/file/upload/201606/03/101909601.jpg', '149.00', '155.00', '0.00', '1', '0', '0');
INSERT INTO `t_product_info` VALUES ('2', '小米手环2', '1', 'http://www.safetyemc.cn/file/upload/201606/03/101909601.jpg', '150.00', '200.00', '0.00', '-1', '0', '0');
INSERT INTO `t_product_info` VALUES ('3', '小米手环3', '1', 'http://www.safetyemc.cn/file/upload/201606/03/101909601.jpg', '151.00', '200.00', '0.00', '-1', '0', '0');
INSERT INTO `t_product_info` VALUES ('4', '小米手环4', '1', 'http://www.safetyemc.cn/file/upload/201606/03/101909601.jpg', '152.00', '200.00', '0.00', '-1', '0', '0');
INSERT INTO `t_product_info` VALUES ('5', '小米手环5', '1', 'http://www.safetyemc.cn/file/upload/201606/03/101909601.jpg', '153.00', '200.00', '0.00', '-1', '0', '0');
INSERT INTO `t_product_info` VALUES ('6', '小米手环6', '1', 'http://www.safetyemc.cn/file/upload/201606/03/101909601.jpg', '154.00', '200.00', '0.00', '-1', '0', '0');
INSERT INTO `t_product_info` VALUES ('7', '小米手环7', '1', 'http://www.safetyemc.cn/file/upload/201606/03/101909601.jpg', '155.00', '200.00', '0.00', '-1', '0', '0');
INSERT INTO `t_product_info` VALUES ('8', '小米手环8', '1', 'http://www.safetyemc.cn/file/upload/201606/03/101909601.jpg', '156.00', '200.00', '0.00', '-1', '0', '0');
INSERT INTO `t_product_info` VALUES ('9', '小米手环9', '1', 'http://www.safetyemc.cn/file/upload/201606/03/101909601.jpg', '157.00', '200.00', '0.00', '-1', '0', '0');
INSERT INTO `t_product_info` VALUES ('10', '小米手环10', '1', 'http://www.safetyemc.cn/file/upload/201606/03/101909601.jpg', '158.00', '200.00', '0.00', '-1', '0', '0');
INSERT INTO `t_product_info` VALUES ('11', '小米手环11', '1', 'http://www.safetyemc.cn/file/upload/201606/03/101909601.jpg', '159.00', '200.00', '0.00', '-1', '0', '0');
INSERT INTO `t_product_info` VALUES ('12', '小米手环12', '1', 'http://www.safetyemc.cn/file/upload/201606/03/101909601.jpg', '160.00', '200.00', '0.00', '-1', '0', '0');
INSERT INTO `t_product_info` VALUES ('13', '小米手环13', '1', 'http://www.safetyemc.cn/file/upload/201606/03/101909601.jpg', '161.00', '200.00', '0.00', '-1', '0', '0');
INSERT INTO `t_product_info` VALUES ('14', '小米手环14', '1', 'http://www.safetyemc.cn/file/upload/201606/03/101909601.jpg', '162.00', '200.00', '0.00', '-1', '0', '0');
INSERT INTO `t_product_info` VALUES ('15', '小米手环15', '1', 'http://www.safetyemc.cn/file/upload/201606/03/101909601.jpg', '163.00', '200.00', '0.00', '-1', '0', '0');
INSERT INTO `t_product_info` VALUES ('24', '温馨-保暖内衣男加绒', '2', 'http://localhost:8080/zlx/resources/admin/upload/product/20180130154011047.jpg', '99.10', '129.19', '15.10', '-1', '1', '1');
INSERT INTO `t_product_info` VALUES ('26', '红豆家纺纯棉内衣男士', '2', 'http://localhost:8080/zlx/resources/admin/upload/product/20180130201328310.jpg', '129.99', '139.99', '20.00', '-1', '1', '0');

-- ----------------------------
-- Table structure for t_product_order
-- ----------------------------
DROP TABLE IF EXISTS `t_product_order`;
CREATE TABLE `t_product_order` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `orderNumber` varchar(255) NOT NULL COMMENT '订单号',
  `orderCreateTime` datetime NOT NULL COMMENT '订单创建时间',
  `openidMd5` varchar(255) NOT NULL COMMENT '用户表md5加密的openid',
  `productCount` int(11) NOT NULL COMMENT '商品购买的数量',
  `expressFee` decimal(10,2) NOT NULL COMMENT '商品快递费',
  `totalAmount` decimal(10,2) NOT NULL COMMENT '订单总金额 = 商品价格 x 商品数量 + 快递费',
  `addressId` int(11) NOT NULL COMMENT '收货地址表id',
  `orderType` int(11) NOT NULL DEFAULT '1' COMMENT '订单状态：1：待付款 2：已付款 3：已取消 4：已删除',
  `describes` varchar(255) DEFAULT NULL COMMENT '卖家留言',
  `userName` varchar(255) NOT NULL COMMENT '收货人姓名',
  `telNumber` varchar(255) NOT NULL COMMENT '收货人手机号码',
  `address` varchar(255) NOT NULL COMMENT '收货人详细地址',
  PRIMARY KEY (`id`,`orderNumber`)
) ENGINE=InnoDB AUTO_INCREMENT=110 DEFAULT CHARSET=utf8 COMMENT='商品订单表';

-- ----------------------------
-- Records of t_product_order
-- ----------------------------
INSERT INTO `t_product_order` VALUES ('109', '2018011815082368562552', '2018-01-18 15:08:24', 'wMM37KSHNhYUWmj9dhl7fg==', '1', '0.00', '149.00', '33', '3', '', '张三', '020-81167888', '广东省广州市海珠区 新港中路397号');

-- ----------------------------
-- Table structure for t_product_orderInfo
-- ----------------------------
DROP TABLE IF EXISTS `t_product_orderInfo`;
CREATE TABLE `t_product_orderInfo` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `orderNumber` varchar(255) NOT NULL COMMENT '订单号',
  `productId` bigint(20) NOT NULL COMMENT '商品信息表id',
  `productName` varchar(255) NOT NULL COMMENT '商品名称',
  `productShowPrice` decimal(10,2) NOT NULL COMMENT '商品展示价格',
  `productCount` int(11) NOT NULL COMMENT '商品购买的数量（也是需要冻结的商品数量）',
  `productClassId` bigint(11) NOT NULL COMMENT '商品分类表id',
  `productClassName` varchar(255) NOT NULL COMMENT '商品分类名称',
  `productPrice` decimal(10,2) NOT NULL COMMENT '商品分类价格',
  `productImage` varchar(255) NOT NULL COMMENT '商品分类图片',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=64 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_product_orderInfo
-- ----------------------------
INSERT INTO `t_product_orderInfo` VALUES ('63', '2018011815082368562552', '1', '小米手环1', '155.00', '1', '1', '小米手环2 黑色', '149.00', 'http://www.cncrk.com/up/1605/201605111616066775.jpg');

-- ----------------------------
-- Table structure for t_product_param
-- ----------------------------
DROP TABLE IF EXISTS `t_product_param`;
CREATE TABLE `t_product_param` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '商品参数表id',
  `productId` bigint(11) NOT NULL COMMENT '商品信息表id',
  `paramKey` varchar(255) NOT NULL COMMENT '商品参数key',
  `detail` varchar(255) NOT NULL COMMENT '参数说明 value',
  `addParamTime` datetime NOT NULL COMMENT '添加参数时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8 COMMENT='商品参数表';

-- ----------------------------
-- Records of t_product_param
-- ----------------------------
INSERT INTO `t_product_param` VALUES ('1', '1', '设备类型', '智能手环', '2017-12-20 11:24:53');
INSERT INTO `t_product_param` VALUES ('2', '1', '屏幕尺寸', '0.42英寸', '2017-12-20 11:25:07');
INSERT INTO `t_product_param` VALUES ('3', '1', '屏幕类型', 'OLED显示屏', '2017-12-20 11:25:28');
INSERT INTO `t_product_param` VALUES ('4', '1', '感应器', 'ADI 超低功耗加速度传感器及光电式心律传感器', '2017-12-20 11:25:59');
INSERT INTO `t_product_param` VALUES ('5', '1', '防水防尘', '支持防水，防水等级IP67', '2017-12-20 11:26:16');
INSERT INTO `t_product_param` VALUES ('6', '1', '蓝牙', '支持，Dialog 2 代蓝牙芯片（蓝牙 4.0 BLE）', '2017-12-20 11:26:35');
INSERT INTO `t_product_param` VALUES ('7', '1', '特性功能', '时间显示，计步，心率监测，久坐提醒，消息提醒', '2017-12-20 11:27:02');
INSERT INTO `t_product_param` VALUES ('8', '2', '设备类型', '智能手环2', '2017-12-20 11:24:53');
INSERT INTO `t_product_param` VALUES ('9', '3', '设备类型', '智能手环3', '2017-12-20 11:24:53');
INSERT INTO `t_product_param` VALUES ('10', '4', '设备类型', '智能手环4', '2017-12-20 11:24:53');
INSERT INTO `t_product_param` VALUES ('11', '5', '设备类型', '智能手环5', '2017-12-20 11:24:53');
INSERT INTO `t_product_param` VALUES ('12', '6', '设备类型', '智能手环6', '2017-12-20 11:24:53');
INSERT INTO `t_product_param` VALUES ('13', '7', '设备类型', '智能手环7', '2017-12-20 11:24:53');
INSERT INTO `t_product_param` VALUES ('14', '8', '设备类型', '智能手环8', '2017-12-20 11:24:53');
INSERT INTO `t_product_param` VALUES ('15', '9', '设备类型', '智能手环9', '2017-12-20 11:24:53');
INSERT INTO `t_product_param` VALUES ('16', '10', '设备类型', '智能手环10', '2017-12-20 11:24:53');
INSERT INTO `t_product_param` VALUES ('17', '11', '设备类型', '智能手环11', '2017-12-20 11:24:53');
INSERT INTO `t_product_param` VALUES ('18', '12', '设备类型', '智能手环12', '2017-12-20 11:24:53');
INSERT INTO `t_product_param` VALUES ('19', '13', '设备类型', '智能手环13', '2017-12-20 11:24:53');
INSERT INTO `t_product_param` VALUES ('20', '14', '设备类型', '智能手环14', '2017-12-20 11:24:53');
INSERT INTO `t_product_param` VALUES ('21', '15', '设备类型', '智能手环15', '2017-12-20 11:24:53');
INSERT INTO `t_product_param` VALUES ('23', '26', '11', '11', '2018-02-02 16:29:25');

-- ----------------------------
-- Table structure for t_product_shoppingCart
-- ----------------------------
DROP TABLE IF EXISTS `t_product_shoppingCart`;
CREATE TABLE `t_product_shoppingCart` (
  `id` bigint(10) NOT NULL AUTO_INCREMENT COMMENT '购物车表id',
  `openidMd5` varchar(255) NOT NULL COMMENT '用户md5加密后的openid',
  `productId` bigint(10) NOT NULL COMMENT '商品信息表id',
  `productCount` int(255) NOT NULL COMMENT '商品添加购物车数量',
  `productClassId` bigint(20) NOT NULL COMMENT '商品分类表id',
  `addTime` datetime NOT NULL COMMENT '添加购物车时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=96 DEFAULT CHARSET=utf8 COMMENT='购物车表';

-- ----------------------------
-- Records of t_product_shoppingCart
-- ----------------------------

-- ----------------------------
-- Table structure for t_product_type
-- ----------------------------
DROP TABLE IF EXISTS `t_product_type`;
CREATE TABLE `t_product_type` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '商品类型id',
  `type` varchar(255) NOT NULL COMMENT '类型名称',
  `detils` varchar(255) DEFAULT NULL COMMENT '类型说明',
  `status` int(11) DEFAULT '1' COMMENT '状态： 1：展示 2：隐藏 3：删除',
  `sort` int(11) DEFAULT NULL COMMENT '序号',
  `lastUpdateTime` datetime DEFAULT NULL COMMENT '最后一次修改时间',
  `addTime` datetime DEFAULT NULL COMMENT '添加时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8 COMMENT='商品类型表';

-- ----------------------------
-- Records of t_product_type
-- ----------------------------
INSERT INTO `t_product_type` VALUES ('1', '数码', '手机 手表 手环 电脑 笔记本 耳机', '1', '1', '2018-01-24 13:57:08', '2018-01-24 13:57:24');
INSERT INTO `t_product_type` VALUES ('2', '日常家居', '毛巾 牙刷 杯子 热水壶 豆浆机', '1', '2', '2018-01-24 13:57:10', '2018-01-24 13:57:27');
INSERT INTO `t_product_type` VALUES ('3', '旅游出行', '帐篷 U型枕 太阳镜', '1', '3', '2018-01-24 13:57:13', '2018-01-24 13:57:30');
INSERT INTO `t_product_type` VALUES ('4', '收藏', '工艺品 针织纪念品', '1', '4', '2018-01-24 13:57:18', '2018-01-24 13:57:32');
INSERT INTO `t_product_type` VALUES ('5', '二手转售', '二手商品 二手车', '1', '5', '2018-01-24 13:57:21', '2018-01-24 13:57:35');
INSERT INTO `t_product_type` VALUES ('6', '测试1', '1', '3', '1', '2018-01-24 16:05:06', '2018-01-24 16:05:06');
INSERT INTO `t_product_type` VALUES ('7', '测试2', '2', '3', '2', '2018-01-24 16:09:22', '2018-01-24 16:09:22');
INSERT INTO `t_product_type` VALUES ('8', '测试3', '测试描述', '3', '6', '2018-01-25 12:22:30', '2018-01-25 12:22:30');
INSERT INTO `t_product_type` VALUES ('9', '测试4', '测试4', '3', '7', '2018-01-25 12:29:50', '2018-01-25 12:29:50');
INSERT INTO `t_product_type` VALUES ('10', '测试5', '测试5', '3', '8', '2018-01-25 12:31:56', '2018-01-25 12:31:56');

-- ----------------------------
-- Table structure for t_receivingAddress
-- ----------------------------
DROP TABLE IF EXISTS `t_receivingAddress`;
CREATE TABLE `t_receivingAddress` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '收货地址id',
  `userId` bigint(11) NOT NULL COMMENT '用户id 对应user表中id',
  `userName` varchar(255) NOT NULL COMMENT '收货人姓名',
  `telNumber` varchar(255) NOT NULL COMMENT '收货人手机号码',
  `postalCode` varchar(255) NOT NULL COMMENT '邮编',
  `provinceName` varchar(255) NOT NULL COMMENT '国标收货地址第一级地址',
  `cityName` varchar(255) NOT NULL COMMENT '国标收货地址第二级地址',
  `countyName` varchar(255) NOT NULL COMMENT '国标收货地址第三级地址',
  `detailInfo` varchar(255) NOT NULL COMMENT '详细收货地址信息',
  `nationalCode` varchar(255) NOT NULL COMMENT '收货地址国家码',
  `status` int(1) NOT NULL DEFAULT '0' COMMENT '常用收货地址 0：否，1：是',
  `addAddressTime` datetime NOT NULL COMMENT '添加地址时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8 COMMENT='收货地址记录表';

-- ----------------------------
-- Records of t_receivingAddress
-- ----------------------------
INSERT INTO `t_receivingAddress` VALUES ('33', '1', '张三', '020-81167888', '510000', '广东省', '广州市', '海珠区', '新港中路397号', '510000', '1', '2018-01-17 20:29:25');
INSERT INTO `t_receivingAddress` VALUES ('34', '1', '张三', '020-81167888', '510000', '广东省', '广州市', '海珠区', '新港中路397号', '510000', '0', '2018-01-17 20:29:27');

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT COMMENT '用户数字id标示',
  `openid` varchar(255) NOT NULL COMMENT '微信平台用户唯一标示',
  `openidMd5` varchar(255) NOT NULL COMMENT '经过md5加密过后的openid，返回前台',
  `session_key` varchar(255) DEFAULT NULL COMMENT '本次登录的会话密钥,刷新登录状态后的密钥会改变',
  `nickName` varchar(255) DEFAULT NULL COMMENT '微信昵称',
  `gender` int(1) DEFAULT '0' COMMENT '性别 0：未知，1：男，女：2',
  `avatarUrl` varchar(255) DEFAULT NULL COMMENT '用户头像',
  `country` varchar(255) DEFAULT NULL COMMENT '用户所在国家',
  `province` varchar(255) DEFAULT NULL COMMENT '用户所在省份',
  `city` varchar(255) DEFAULT NULL COMMENT '用户所在城市',
  `firstLoginTime` datetime DEFAULT NULL COMMENT 'x',
  `lastLoginTime` datetime DEFAULT NULL COMMENT '用户最近一次登录时间',
  PRIMARY KEY (`id`,`openid`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', 'oYbcT0b_x6Pq74_hhDEnmEohDn2I', 'wMM37KSHNhYUWmj9dhl7fg==', 'ev3V/hI5HMMTSPD+nFRR4Q==', '张立享', '1', 'https://wx.qlogo.cn/mmopen/vi_32/TGNBoMw6PY80kVgrRtxWN1agniby403tjO4hwFgCDibygb7jcGibu0XplpLGyg8bqcj3w9s8leCzleAFBrbT4MYicw/0', '中国', '江西', '赣州', '2017-12-17 11:36:09', '2018-01-29 16:28:07');
SET FOREIGN_KEY_CHECKS=1;
