/*
Navicat MySQL Data Transfer

Source Server         : LOCA
Source Server Version : 50726
Source Host           : localhost:3306
Source Database       : happy_hrs_db

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2020-05-28 20:23:37
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `cars_tbl`
-- ----------------------------
DROP TABLE IF EXISTS `cars_tbl`;
CREATE TABLE `cars_tbl` (
  `cid` int(11) NOT NULL AUTO_INCREMENT,
  `model` varchar(100) DEFAULT NULL,
  `color` varchar(20) DEFAULT NULL,
  `price` int(7) DEFAULT NULL,
  PRIMARY KEY (`cid`)
) ENGINE=InnoDB AUTO_INCREMENT=105 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of cars_tbl
-- ----------------------------
INSERT INTO `cars_tbl` VALUES ('100', '2020', 'white', '3293');
INSERT INTO `cars_tbl` VALUES ('101', '1920', 'red', '123');
INSERT INTO `cars_tbl` VALUES ('103', 'O9282', 'red', '93248');
INSERT INTO `cars_tbl` VALUES ('104', '(@83', 'pink', '9324');

-- ----------------------------
-- Table structure for `dog_food_tbl`
-- ----------------------------
DROP TABLE IF EXISTS `dog_food_tbl`;
CREATE TABLE `dog_food_tbl` (
  `fid` int(11) NOT NULL AUTO_INCREMENT,
  `milk` varchar(30) DEFAULT NULL,
  `breads` int(11) DEFAULT NULL,
  `createdate` timestamp NULL DEFAULT NULL,
  PRIMARY KEY (`fid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of dog_food_tbl
-- ----------------------------
INSERT INTO `dog_food_tbl` VALUES ('1', '2 Liters', '23', '2020-05-27 10:05:45');
INSERT INTO `dog_food_tbl` VALUES ('2', '4L', '6', '2020-05-27 10:12:07');
INSERT INTO `dog_food_tbl` VALUES ('3', '5L', '6', '2020-05-27 10:20:18');
INSERT INTO `dog_food_tbl` VALUES ('4', '4L', '10', '2020-05-28 09:55:15');

-- ----------------------------
-- Table structure for `open_time_tbl`
-- ----------------------------
DROP TABLE IF EXISTS `open_time_tbl`;
CREATE TABLE `open_time_tbl` (
  `tid` int(11) NOT NULL AUTO_INCREMENT,
  `starttime` time DEFAULT NULL,
  `endtime` time DEFAULT NULL,
  `active` varchar(3) DEFAULT 'No',
  PRIMARY KEY (`tid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of open_time_tbl
-- ----------------------------
INSERT INTO `open_time_tbl` VALUES ('1', '13:53:27', '19:53:36', 'No');
INSERT INTO `open_time_tbl` VALUES ('2', '17:02:36', '17:02:38', 'No');
INSERT INTO `open_time_tbl` VALUES ('3', '17:03:44', '18:03:46', 'Yes');

-- ----------------------------
-- Table structure for `profiles_tbl`
-- ----------------------------
DROP TABLE IF EXISTS `profiles_tbl`;
CREATE TABLE `profiles_tbl` (
  `aid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(100) DEFAULT NULL,
  `password` varchar(30) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `email` varchar(200) DEFAULT NULL,
  `gender` varchar(6) DEFAULT NULL,
  `photo` varchar(1000) DEFAULT NULL,
  `doe` timestamp NULL DEFAULT NULL,
  `role` varchar(40) NOT NULL DEFAULT 'customer',
  PRIMARY KEY (`aid`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of profiles_tbl
-- ----------------------------
INSERT INTO `profiles_tbl` VALUES ('10', 'synergisticit2020@gmail.com', 'jill', 'Mocha!2222', 'nagen@gmail.come222', 'Male', 'https://www.greateralliance.org/wp-content/uploads/2019/10/half_blank31.png', '2020-05-06 19:58:26', 'customer');
INSERT INTO `profiles_tbl` VALUES ('11', 'Ramesh', 'jill', 'JAck', 'jack@gmail.com', 'Female', 'https://encrypted-tbn0.gstatic.com/images?q=tbn%3AANd9GcRN9pkzoAjFb0PB8kIuBm3rrrN_lCisKmPclD37LIQtUzF8F7Pg&usqp=CAU', '2020-05-06 19:58:26', 'customer');
INSERT INTO `profiles_tbl` VALUES ('14', 'jack', 'test', 'gpsprogramy100', 'gpsprogramy@gmail.com', 'Male', 'https://i1.wp.com/rollercoasteryears.com/wp-content/uploads/Thrive-During-Finals-.jpg?resize=1000%2C667&ssl=1', '2020-05-11 19:06:14', 'customer');
INSERT INTO `profiles_tbl` VALUES ('15', 'owuiee', 'chill', 'Amogha', 'nagn@gmail.com', 'Male', 'www.', '2020-05-26 20:23:06', 'customer');
INSERT INTO `profiles_tbl` VALUES ('16', 'owuiee', 'chill', 'Amogha', 'nagn@gmail.com', 'Male', 'www.', '2020-05-26 20:33:30', 'customer');

-- ----------------------------
-- Table structure for `users_tbl`
-- ----------------------------
DROP TABLE IF EXISTS `users_tbl`;
CREATE TABLE `users_tbl` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `userid` varchar(50) DEFAULT NULL,
  `password` varchar(30) DEFAULT NULL,
  `name` varchar(100) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `mobile` bigint(20) DEFAULT NULL,
  `salutation` varchar(4) DEFAULT NULL,
  `image` varchar(500) DEFAULT NULL,
  `createdate` timestamp NULL DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  `role` varchar(20) NOT NULL DEFAULT 'customer',
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of users_tbl
-- ----------------------------
INSERT INTO `users_tbl` VALUES ('5', 'javatech1000@gmail.com', 'test', 'Krishana', 'javatech1000@gmail.com', '2342342344', 'Miss', 'https://citybook.pk/wp-content/uploads/2019/01/tutors-in-lahore-2-1.jpg', '2020-05-07 16:50:58', 'admin');
INSERT INTO `users_tbl` VALUES ('6', 'nagendra.synergisticit@gmail.com', 'w3w3rw', 'Mark', 'nagendra.synergisticit@gmail.com', '32424342', 'Mr.', 'https://clueylearning.com.au/wp-content/uploads/2019/10/What-is-tutoring-Different-types-of-tutoring-explained.jpg', '2020-05-06 18:17:05', 'customer');
INSERT INTO `users_tbl` VALUES ('7', 'Robert', 'test', 'NAGENDRA KUMAR YADAV', 'nagendra.yadav.niit@gmail.com', '9873003702', 'Mr.', 'https://rukminim1.flixcart.com/image/416/416/j84so7k0/poster/r/h/g/large-movie-cars-lightning-mcqueen-car-on-hi-quality-large-print-original-imaesuuq2qehuzah.jpeg?q=70', '2020-05-07 18:07:29', 'customer');
