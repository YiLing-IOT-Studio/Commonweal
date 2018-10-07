/*
Navicat MySQL Data Transfer

Source Server         : 119.23.202.55
Source Server Version : 50641
Source Host           : 119.23.202.55:3306
Source Database       : commonweal

Target Server Type    : MYSQL
Target Server Version : 50641
File Encoding         : 65001

Date: 2018-10-07 11:18:03
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for activity
-- ----------------------------
DROP TABLE IF EXISTS `activity`;
CREATE TABLE `activity` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(255) NOT NULL,
  `category` varchar(255) NOT NULL,
  `publisher` varchar(255) NOT NULL,
  `img` varchar(255) NOT NULL,
  `publishDate` varchar(255) NOT NULL,
  `activityDate` varchar(255) NOT NULL,
  `remain` int(10) NOT NULL,
  `msg` varchar(255) NOT NULL,
  `place` varchar(255) NOT NULL,
  `deadline` varchar(255) NOT NULL,
  `personalLimit` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of activity
-- ----------------------------
INSERT INTO `activity` VALUES ('1', '中国流动科技馆走进零陵', '主题宣传', '张海洋', 'https://yiban-commonweal.oss-cn-shenzhen.aliyuncs.com/commonweal/公益活动图片/张海洋/1407159765b99aa236f14a.jpg', '2018/09/25', '2018-09-30', '19', '盘志元在致辞上表示，中国流动科技馆巡展是中国科协与地方科协、中国科技馆与省级科技馆共同协作、带动薄弱地区科技馆发展的一次联合行动，此次巡展走进零陵是我区广大群众共享科普成果、提升公民科学素质的一次良好机遇。', '成都', '2018-09-25', '20');
INSERT INTO `activity` VALUES ('2', '开展“迎中秋，保平安，促和谐”志愿服务活动', '社区服务', '张海洋', 'https://yiban-commonweal.oss-cn-shenzhen.aliyuncs.com/commonweal/公益活动图片/张海洋/12358775335ba481c29f209.jpg', '2018/09/25', '2018-10-25', '9', '日前，蒿子港镇开展“迎中秋，保平安，促和谐”志愿服务活动，组织全体机关志愿者和各村（社区）志愿者进行安全隐患大排查和安全知识大宣讲。本次志愿活动以各村（社区）为单位组织并开展，各单位志愿者们认真谋划、精心组织，统一穿上红马甲，前往所联系点开展了形式多样的志愿服务活动。', '鼎城区蒿子港镇', '2018-10-20', '10');
INSERT INTO `activity` VALUES ('3', '海事开展船员“酒驾”专项检查', '主题宣传', '张海洋', 'https://yiban-commonweal.oss-cn-shenzhen.aliyuncs.com/commonweal/公益活动图片/张海洋/21094298155b470a90ac670.png', '2018/09/25', '2018-09-29', '49', '为进一步加强船员管理，严防辖区船员出现酒驾、醉驾等违法行为，近日，零陵区地方海事局在全区范围内开展为期三天的船员“酒驾”专项整治行动。此次“酒驾”专项整治行动，采取不定时、不定点的形式开展，范围覆盖辖区内所有渡口、码头、及采运砂作业点。', '零陵', '2018-10-2', '50');
INSERT INTO `activity` VALUES ('4', '雷锋志愿服务工作队启动', '爱心探访', '张海洋', 'https://yiban-commonweal.oss-cn-shenzhen.aliyuncs.com/commonweal/公益活动图片/张海洋/14896296055ba445572e4e7.jpg', '2018/09/25', '2018-10-29', '20', '娄底市文明办副主任毛肖燕用三个“非常好”肯定了娄底二小此次志愿服务活动，她说，此次活动的主题非常好，紧扣娄底市创建全国文明城市的中心工作，在广大未成年人世界观、人生观、价值观形成的关键时期，以文明润染心田，培养正确的行为习惯、培养社会责任意识，是孩子们健康成长的重要一课，这一课需要学校、老师和家长的参与；活动内容非常好，活动内容立足学校实际、贴近师生生活，能够吸引更多人参与到志愿服务活动中来。', '娄底二小学', '2018-10-6', '20');
INSERT INTO `activity` VALUES ('5', '找到了！90岁邵阳籍台湾老人即将圆团聚梦', '爱心募捐', '张海洋', 'https://yiban-commonweal.oss-cn-shenzhen.aliyuncs.com/commonweal/公益活动图片/张海洋/7861448865af523fe5771d.jpg', '2018/09/25', '2018-10-1', '10', '红网时刻5月9日讯(记者 谢国雄 颜瑶)5月7日晚，红网发布《求扩散丨90岁台湾老人欲寻湖南邵阳亲人 17岁从军后再没回故乡》一文，并经网友广泛传播。5月8日晚，仅一天时间，记者就接到头条寻人志愿者-湖南关爱老兵团队“铁扇公主 、轻颦浅笑”的电话，称已找到王淑兰老人的亲人，就在大祥区二纺机附近。', '邵阳', '2018-10-6', '10');
INSERT INTO `activity` VALUES ('6', '中秋佳节，关爱老人', '爱心探访', '小锋屈', 'https://yiban-commonweal.oss-cn-shenzhen.aliyuncs.com/commonweal/公益活动图片/小锋屈/屏幕快照 2018-09-25 11.22.47.png', '2018/09/25', '2018年9月24日', '29', '在中秋节当天，一起看望该养老院的老人，与其谈论当今社会形式、听他们讲述曾经的故事，与他们共度中秋，一起观看中秋晚会。在当今这个快社会节奏里，老人们成了被淡漠的群体，缺少社会关心。他们需要的是那份被关爱的温暖。在这一丝温暖中感到社会对他们的关怀。', '四川省成都市新都区红光养老院', '2018年9月20日', '30');
INSERT INTO `activity` VALUES ('7', '关爱留守儿童 陪伴快乐成长', '弱势群体', '小锋屈', 'https://yiban-commonweal.oss-cn-shenzhen.aliyuncs.com/commonweal/公益活动图片/小锋屈/屏幕快照 2018-09-25 11.29.14.png', '2018/09/25', '2018年9月1日', '20', '随着进城务工人员越来越多，农村留守儿童的数量也与时剧增。留守儿童的教育问题已经成为我国当前的一个严重社会问题，是关系到社会稳定、和谐的重大问题。要解决这个难题，不能单靠某一方面的力量，需要学校、家庭和政府等多方面协调合作，要全社会共同关爱留守儿童，才能把这个问题解决好。该活动则是按照“结对+帮扶”的项目实施模式、组织自愿者结对帮扶服务。', '四川省成都市新都区新都小学', '2018年8月30日', '20');
INSERT INTO `activity` VALUES ('8', '幸福中国人，植树万里行', '环境保护', '小锋屈', 'https://yiban-commonweal.oss-cn-shenzhen.aliyuncs.com/commonweal/公益活动图片/小锋屈/屏幕快照 2018-09-25 11.36.11.png', '2018/09/25', '2018年3月12日', '50', '以三月份全民植树节为契机，各班各中队组织开展形式多样的爱绿护绿行动和植物种植实践体验活动，做到人人参与，宣传与实际行动相结合，从而让少先队员在活动中体验成功的快乐，增强环保意识，生态意识，以达到为教室、为学校、为社会增添绿色，净化、美化环境的目的。', '新都区绿化带', '2018年3月10日', '50');
INSERT INTO `activity` VALUES ('9', '爱心支教，快乐成长', '弱势群体', '小锋屈', 'https://yiban-commonweal.oss-cn-shenzhen.aliyuncs.com/commonweal/公益活动图片/小锋屈/屏幕快照 2018-09-25 12.34.27.png', '2018/09/25', '2018年7月10日', '20', '随着国家队义务教育的大力支持，全国大部分地区的教育已有了很大改善。但是在一些受地理和经济条件限制的地区，教育状况仍然令人担忧。在这些地区，由于师资队伍的缺乏，教师的教学手法较为传统，家长的思想认识水平不高等因素，造成了学生的学习目标不够明确，学习的积极性受到严重压抑，甚至很多人因为种种得不到应有的教育。', '四川凉山', '2018年7月5日', '20');
INSERT INTO `activity` VALUES ('10', '清明节扫墓活动', '社区服务', '小锋屈', 'https://yiban-commonweal.oss-cn-shenzhen.aliyuncs.com/commonweal/公益活动图片/小锋屈/屏幕快照 2018-09-25 12.57.19.png', '2018/09/25', '2018年4月3日-2018年4月10日', '30', '清明节是我国的传统节日；扫墓，祭拜古人的文化风俗也仍为广大中华儿女的传承。时至今日，我们仍然不能忘记那些曾今为了祖国的伟大事业抛头颅洒热血的革命先烈以及在新时期涌现出的一系列英雄人物，他们所代表的英雄价值也越来越固定成为社会意识的一部分，影响着现代的人们。', '新都市烈士陵园', '2018年4月1日', '30');
INSERT INTO `activity` VALUES ('11', '送温暖·献爱心', '爱心募捐', '小锋屈', 'https://yiban-commonweal.oss-cn-shenzhen.aliyuncs.com/commonweal/公益活动图片/小锋屈/屏幕快照 2018-09-25 13.04.37.png', '2018/09/25', '2018年8月1日', '10000', '李丽雯同学的不幸遭遇，牵动了每一位老师和同学的心，倡议得到同学们用心响应。个性是中文系领导的大力支持。活动中涌现了很多感人的事迹有的同学敲破了尘封多年的储蓄罐；有的同学捐出了生活费和零花钱；有的同学甚至向别人借钱捐款……', '博学广场', '2018年10月1日', '10000');
INSERT INTO `activity` VALUES ('12', '还我一片清净之地', '环境保护', '小锋屈', 'https://yiban-commonweal.oss-cn-shenzhen.aliyuncs.com/commonweal/公益活动图片/小锋屈/屏幕快照 2018-09-25 13.12.17.png', '2018/09/25', '2018年8月31日', '30', '劳动是中华民族的优良传统美德，我们作为21世纪大学生，有着较高的文化素质，应当积极参加劳动。胡锦涛主席曾在八荣八耻中明确提出：以辛勤劳动为荣，以服务人民为荣。我班在学校领导之下，积极组织开展社区服务劳动。', '正因村', '2018年9月15日', '30');
INSERT INTO `activity` VALUES ('13', '红色逐梦之旅', '主题宣传', '小锋屈', 'https://yiban-commonweal.oss-cn-shenzhen.aliyuncs.com/commonweal/公益活动图片/小锋屈/屏幕快照 2018-09-25 13.17.09.png', '2018/09/25', '2018年9月15日', '20', '青年红色筑梦之旅是第三届中国“互联网 ”大学生创新创业大赛举办的同期实践活动。此次活动由教育部组织，承办单位西安电子科技大学实施。两批参赛团队分赴延安，通过大学生创新创业项目对接革命老区经济社会发展需求，助力精准扶贫脱贫。实践团围绕“青春之歌”“红色记忆”“筑梦踏实”三个主题，通过寻访梁家河、走访“八一”敬老院、参观革命旧址、聆听专题辅导、开展青年乡村创客沙龙', '一食堂门口', '2018年9月10日', '20');
INSERT INTO `activity` VALUES ('14', '红色逐梦之旅', '主题宣传', '小锋屈', 'https://yiban-commonweal.oss-cn-shenzhen.aliyuncs.com/commonweal/公益活动图片/小锋屈/屏幕快照 2018-09-25 13.17.09.png', '2018/09/25', '2018年9月15日', '20', '青年红色筑梦之旅是第三届中国“互联网 ”大学生创新创业大赛举办的同期实践活动。此次活动由教育部组织，承办单位西安电子科技大学实施。两批参赛团队分赴延安，通过大学生创新创业项目对接革命老区经济社会发展需求，助力精准扶贫脱贫。实践团围绕“青春之歌”“红色记忆”“筑梦踏实”三个主题，通过寻访梁家河、走访“八一”敬老院、参观革命旧址、聆听专题辅导、开展青年乡村创客沙龙', '一食堂门口', '2018年9月10日', '20');
INSERT INTO `activity` VALUES ('15', '红色逐梦之旅', '主题宣传', '小锋屈', 'https://yiban-commonweal.oss-cn-shenzhen.aliyuncs.com/commonweal/公益活动图片/小锋屈/屏幕快照 2018-09-25 13.17.09.png', '2018/09/25', '2018年9月15日', '20', '青年红色筑梦之旅是第三届中国“互联网 ”大学生创新创业大赛举办的同期实践活动。此次活动由教育部组织，承办单位西安电子科技大学实施。两批参赛团队分赴延安，通过大学生创新创业项目对接革命老区经济社会发展需求，助力精准扶贫脱贫。实践团围绕“青春之歌”“红色记忆”“筑梦踏实”三个主题，通过寻访梁家河、走访“八一”敬老院、参观革命旧址、聆听专题辅导、开展青年乡村创客沙龙', '一食堂门口', '2018年9月10日', '20');
INSERT INTO `activity` VALUES ('16', '还校园一片净土', '社区服务', '小锋屈', 'https://yiban-commonweal.oss-cn-shenzhen.aliyuncs.com/commonweal/公益活动图片/小锋屈/屏幕快照 2018-09-25 13.23.34.png', '2018/09/25', '2018年9月3日', '20', '为了让我们能拥有一个更叫整洁的校园，我们青协于11月5日中午开展了一次“校园清洁”的义务劳动。本次活动主要对南篮球场和足球场的垃圾进行了清洁。虽然此次活动又脏又累，但在我们的脸上却看到一丝的不乐，相反的，我们觉得轻松和满足。我们的行动将影响到全校的同学，这样，校园会在大家的维护下变得更加整洁和美好。能参加此次活动，我感到很荣幸，更以自己作为青协的一份子感到骄傲，通过参加此次活动，我深刻的明白了青协是个团结的大家庭，是一个优秀的组织，我坚信其感谢的明天会更好！', '校园', '2018年9月1日', '20');

-- ----------------------------
-- Table structure for activity_apply
-- ----------------------------
DROP TABLE IF EXISTS `activity_apply`;
CREATE TABLE `activity_apply` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `activityId` int(11) NOT NULL,
  `ybId` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of activity_apply
-- ----------------------------
INSERT INTO `activity_apply` VALUES ('1', '1', '8719085');
INSERT INTO `activity_apply` VALUES ('2', '3', '8719085');
INSERT INTO `activity_apply` VALUES ('3', '6', '8719085');
INSERT INTO `activity_apply` VALUES ('4', '2', '8719085');

-- ----------------------------
-- Table structure for manager_apply
-- ----------------------------
DROP TABLE IF EXISTS `manager_apply`;
CREATE TABLE `manager_apply` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `applyName` varchar(255) NOT NULL,
  `superior` varchar(255) NOT NULL,
  `evidence` varchar(255) NOT NULL,
  `proposer` varchar(255) NOT NULL,
  `status` int(11) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of manager_apply
-- ----------------------------
INSERT INTO `manager_apply` VALUES ('1', '全媒体中心', '微信运营部', 'https://yiban-commonweal.oss-cn-shenzhen.aliyuncs.com/commonweal/申请成为管理员/张海洋/IMG_5265.JPG', '张海洋', '1');

-- ----------------------------
-- Table structure for role
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(255) NOT NULL,
  `role` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of role
-- ----------------------------
INSERT INTO `role` VALUES ('1', '张海洋', 'ROLE_SUPERMANAGER');
INSERT INTO `role` VALUES ('2', '张海洋', 'ROLE_MANAGER');
INSERT INTO `role` VALUES ('3', '杨玉卿', 'ROLE_SUPERMANAGER');
INSERT INTO `role` VALUES ('4', '杨玉卿', 'ROLE_MANAGER');
INSERT INTO `role` VALUES ('5', '小锋屈', 'ROLE_MANAGER');
INSERT INTO `role` VALUES ('6', '小锋屈', 'ROLE_SUPERMANAGER');

-- ----------------------------
-- Table structure for user
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ybId` int(11) NOT NULL,
  `stuId` varchar(255) NOT NULL,
  `stuName` varchar(255) NOT NULL,
  `school` varchar(255) NOT NULL,
  `major` varchar(255) NOT NULL,
  `grade` varchar(255) NOT NULL,
  `telephoneNumber` varchar(255) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '8719085', '201631064218', '张海洋', '计算机科学学院', '物联网工程', '大三', '19940790216');
INSERT INTO `user` VALUES ('2', '8719085', '201631064218', '张海洋', '计算机科学学院', '物联网工程', '大三', '19940790216');
INSERT INTO `user` VALUES ('3', '8719085', '201631064218', '张海洋', '计算机科学学院', '物联网工程', '大三', '19940790216');
INSERT INTO `user` VALUES ('4', '8719085', '201631064218', '张海洋', '计算机科学学院', '物联网工程', '大三', '19940790216');
