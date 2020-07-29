
-- ----------------------------
-- Records of sys_dept
-- ----------------------------
INSERT INTO `sys_dept` VALUES ('6', '0', '研发部', '1', '0');
INSERT INTO `sys_dept` VALUES ('7', '6', '研發一部', '1', '0');
INSERT INTO `sys_dept` VALUES ('8', '6', '研发二部', '2', '0');
INSERT INTO `sys_dept` VALUES ('9', '0', '销售部', '2', '0');
INSERT INTO `sys_dept` VALUES ('10', '9', '销售一部', '1', '0');
INSERT INTO `sys_dept` VALUES ('11', '0', '产品部', '3', '0');
INSERT INTO `sys_dept` VALUES ('12', '11', '产品一部', '1', '0');
INSERT INTO `sys_dept` VALUES ('13', '0', '测试部', '5', '0');
INSERT INTO `sys_dept` VALUES ('14', '13', '测试一部', '1', '0');
INSERT INTO `sys_dept` VALUES ('15', '13', '测试二部', '2', '0');
INSERT INTO `sys_dept` VALUES ('30', '0', '12332', '1', '0');


-- ----------------------------
-- Records of sys_dict
-- ----------------------------
INSERT INTO `sys_dict` VALUES ('1', '正常', '0', 'del_flag', '删除标记', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('3', '显示', '1', 'show_hide', '显示/隐藏', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('4', '隐藏', '0', 'show_hide', '显示/隐藏', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('5', '是', '1', 'yes_no', '是/否', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('6', '否', '0', 'yes_no', '是/否', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('7', '红色', 'red', 'color', '颜色值', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('8', '绿色', 'green', 'color', '颜色值', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('9', '蓝色', 'blue', 'color', '颜色值', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('10', '黄色', 'yellow', 'color', '颜色值', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('11', '橙色', 'orange', 'color', '颜色值', '50', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('12', '默认主题', 'default', 'theme', '主题方案', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('13', '天蓝主题', 'cerulean', 'theme', '主题方案', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('14', '橙色主题', 'readable', 'theme', '主题方案', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('15', '红色主题', 'united', 'theme', '主题方案', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('16', 'Flat主题', 'flat', 'theme', '主题方案', '60', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('17', '国家', '1', 'sys_area_type', '区域类型', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('18', '省份、直辖市', '2', 'sys_area_type', '区域类型', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('19', '地市', '3', 'sys_area_type', '区域类型', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('20', '区县', '4', 'sys_area_type', '区域类型', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('21', '公司', '1', 'sys_office_type', '机构类型', '60', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('22', '部门', '2', 'sys_office_type', '机构类型', '70', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('23', '小组', '3', 'sys_office_type', '机构类型', '80', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('24', '其它', '4', 'sys_office_type', '机构类型', '90', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('32', '所有数据', '1', 'sys_data_scope', '数据范围', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('33', '所在公司及以下数据', '2', 'sys_data_scope', '数据范围', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('34', '所在公司数据', '3', 'sys_data_scope', '数据范围', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('35', '所在部门及以下数据', '4', 'sys_data_scope', '数据范围', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('36', '所在部门数据', '5', 'sys_data_scope', '数据范围', '50', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('37', '仅本人数据', '8', 'sys_data_scope', '数据范围', '90', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('38', '按明细设置', '9', 'sys_data_scope', '数据范围', '100', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('39', '系统管理', '1', 'sys_user_type', '用户类型', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('40', '部门经理', '2', 'sys_user_type', '用户类型', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('41', '普通用户', '3', 'sys_user_type', '用户类型', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('42', '基础主题', 'basic', 'cms_theme', '站点主题', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('43', '蓝色主题', 'blue', 'cms_theme', '站点主题', '20', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('44', '红色主题', 'red', 'cms_theme', '站点主题', '30', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('67', '接入日志', '1', 'sys_log_type', '日志类型', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('68', '异常日志', '2', 'sys_log_type', '日志类型', '40', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('73', '增删改查', 'crud', 'gen_category', '代码生成分类', '10', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('74', '增删改查（包含从表）', 'crud_many', 'gen_category', '代码生成分类', '20', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('75', '树结构', 'tree', 'gen_category', '代码生成分类', '30', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('76', '=', '=', 'gen_query_type', '查询方式', '10', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('77', '!=', '!=', 'gen_query_type', '查询方式', '20', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('78', '&gt;', '&gt;', 'gen_query_type', '查询方式', '30', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('79', '&lt;', '&lt;', 'gen_query_type', '查询方式', '40', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('80', 'Between', 'between', 'gen_query_type', '查询方式', '50', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('81', 'Like', 'like', 'gen_query_type', '查询方式', '60', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('82', 'Left Like', 'left_like', 'gen_query_type', '查询方式', '70', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('83', 'Right Like', 'right_like', 'gen_query_type', '查询方式', '80', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('84', '文本框', 'input', 'gen_show_type', '字段生成方案', '10', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('85', '文本域', 'textarea', 'gen_show_type', '字段生成方案', '20', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('86', '下拉框', 'select', 'gen_show_type', '字段生成方案', '30', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('87', '复选框', 'checkbox', 'gen_show_type', '字段生成方案', '40', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('88', '单选框', 'radiobox', 'gen_show_type', '字段生成方案', '50', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('89', '日期选择', 'dateselect', 'gen_show_type', '字段生成方案', '60', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('90', '人员选择', 'userselect', 'gen_show_type', '字段生成方案', '70', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('91', '部门选择', 'officeselect', 'gen_show_type', '字段生成方案', '80', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('92', '区域选择', 'areaselect', 'gen_show_type', '字段生成方案', '90', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('93', 'String', 'String', 'gen_java_type', 'Java类型', '10', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('94', 'Long', 'Long', 'gen_java_type', 'Java类型', '20', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('95', '仅持久层', 'dao', 'gen_category', '代码生成分类', '40', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('96', '男', '1', 'sex', '性别', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('97', '女', '2', 'sex', '性别', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('98', 'Integer', 'Integer', 'gen_java_type', 'Java类型', '30', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('99', 'Double', 'Double', 'gen_java_type', 'Java类型', '40', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('100', 'Date', 'java.util.Date', 'gen_java_type', 'Java类型', '50', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('104', 'Custom', 'Custom', 'gen_java_type', 'Java类型', '90', '0', '1', null, '1', null, null, '1');
INSERT INTO `sys_dict` VALUES ('105', '会议通告', '1', 'oa_notify_type', '通知通告类型', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('106', '奖惩通告', '2', 'oa_notify_type', '通知通告类型', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('107', '活动通告', '3', 'oa_notify_type', '通知通告类型', '30', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('108', '草稿', '0', 'oa_notify_status', '通知通告状态', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('109', '发布', '1', 'oa_notify_status', '通知通告状态', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('110', '未读', '0', 'oa_notify_read', '通知通告状态', '10', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('111', '已读', '1', 'oa_notify_read', '通知通告状态', '20', '0', '1', null, '1', null, null, '0');
INSERT INTO `sys_dict` VALUES ('112', '草稿', '0', 'oa_notify_status', '通知通告状态', '10', '0', '1', null, '1', null, '', '0');
INSERT INTO `sys_dict` VALUES ('113', '删除', '0', 'del_flag', '删除标记', null, null, null, null, null, null, '', '');


-- ----------------------------
-- Records of sys_log
-- ----------------------------
INSERT INTO `sys_log` VALUES ('2', '-1', '获取用户信息为空', '登录验证', '4', 'com.jxl.system.controller.LoginController.ajaxLogin()', null, '192.16.8.100', '2018-11-30 06:34:48');
INSERT INTO `sys_log` VALUES ('3', '2', '111111', '登录验证', '9', 'com.jxl.system.controller.LoginController.ajaxLogin()', null, '192.16.8.100', '2018-11-30 06:34:54');
INSERT INTO `sys_log` VALUES ('4', '2', '111111', '登录成功进入首页', '43', 'com.jxl.system.controller.LoginController.index()', null, '192.16.8.100', '2018-11-30 06:34:54');


-- ----------------------------
-- Records of sys_menu
-- ----------------------------
INSERT INTO `sys_menu` VALUES ('1', '0', '模板管理', null, null, '0', 'fa fa-wpforms', '1', '2018-11-29 10:13:25', '2018-11-29 10:13:25');
INSERT INTO `sys_menu` VALUES ('2', '1', '模板一管理', 'template_code/table1', 'template_code:table1:table', '1', null, '1', '2018-11-29 10:13:25', '2018-11-29 10:13:25');
INSERT INTO `sys_menu` VALUES ('3', '2', '新增', null, 'template_code:table1:add', '2', null, '1', '2018-11-29 10:13:25', '2018-11-29 10:13:25');
INSERT INTO `sys_menu` VALUES ('4', '2', '编辑', null, 'template_code:table1:edit', '2', null, '1', '2018-11-29 10:13:25', '2018-11-29 10:13:25');
INSERT INTO `sys_menu` VALUES ('5', '2', '查看', null, 'template_code:table1:get', '2', null, '1', '2018-11-29 10:13:25', '2018-11-29 10:13:25');
INSERT INTO `sys_menu` VALUES ('6', '2', '删除', null, 'template_code:table1:remove', '2', null, '1', '2018-11-29 10:13:25', '2018-11-29 10:13:25');
INSERT INTO `sys_menu` VALUES ('7', '2', '批量删除', null, 'template_code:table1:batchRemove', '2', null, '1', '2018-11-29 10:13:25', '2018-11-29 10:13:25');
INSERT INTO `sys_menu` VALUES ('8', '1', '模板二管理', 'template_code/table2', 'template_code:table2:table', '1', null, '2', '2018-11-29 10:13:25', '2018-11-29 10:13:25');
INSERT INTO `sys_menu` VALUES ('9', '8', '新增', null, 'template_code:table2:add', '2', null, '1', '2018-11-29 10:13:25', '2018-11-29 10:13:25');
INSERT INTO `sys_menu` VALUES ('10', '8', '编辑', null, 'template_code:table2:edit', '2', null, '1', '2018-11-29 10:13:25', '2018-11-29 10:13:25');
INSERT INTO `sys_menu` VALUES ('11', '8', '查看', null, 'template_code:table2:get', '2', null, '1', '2018-11-29 10:13:25', '2018-11-29 10:13:25');
INSERT INTO `sys_menu` VALUES ('12', '8', '删除', null, 'template_code:table2:remove', '2', null, '1', '2018-11-29 10:13:25', '2018-11-29 10:13:25');
INSERT INTO `sys_menu` VALUES ('13', '8', '批量删除', null, 'template_code:table2:batchRemove', '2', null, '1', '2018-11-29 10:13:25', '2018-11-29 10:13:25');
INSERT INTO `sys_menu` VALUES ('14', '1', '模板说明', 'html/template_description.html', null, '1', null, '3', '2018-11-29 10:13:25', '2018-11-29 10:13:25');
INSERT INTO `sys_menu` VALUES ('15', '0', '代码生成管理', null, null, '0', 'fa fa-code', '2', '2018-11-29 10:13:25', '2018-11-29 10:13:25');
INSERT INTO `sys_menu` VALUES ('16', '15', '代码生成', 'common/generator', 'common:generator:generator', '1', null, '1', '2018-11-29 10:13:25', '2018-11-29 10:13:25');
INSERT INTO `sys_menu` VALUES ('17', '16', '包名配置', null, 'common:generator:edit', '2', null, '1', '2018-11-29 10:13:25', '2018-11-29 10:13:25');
INSERT INTO `sys_menu` VALUES ('18', '16', '生成代码', null, 'common:generator:code', '2', null, '1', '2018-11-29 10:13:25', '2018-11-29 10:13:25');
INSERT INTO `sys_menu` VALUES ('19', '16', '批量生成代码', null, 'common:generator:batchcode', '2', null, '1', '2018-11-29 10:13:25', '2018-11-29 10:13:25');
INSERT INTO `sys_menu` VALUES ('20', '0', '系统监控', null, null, '0', 'fa fa-calendar', '3', '2018-11-29 10:13:25', '2018-11-29 10:13:25');
INSERT INTO `sys_menu` VALUES ('21', '20', '运行监控', 'druid/index.html', null, '1', null, '1', '2018-11-29 10:13:25', '2018-11-29 10:13:25');
INSERT INTO `sys_menu` VALUES ('22', '0', '系统管理', null, null, '0', 'fa fa-cog', '4', '2018-11-29 10:13:25', '2018-11-29 10:13:25');
INSERT INTO `sys_menu` VALUES ('23', '20', '系统日志', 'common/log', 'common:log:log', '1', null, '2', '2018-11-29 10:13:25', '2018-11-29 10:13:25');
INSERT INTO `sys_menu` VALUES ('24', '23', '新增', null, 'common:log:add', '2', null, '1', '2018-11-29 10:13:25', '2018-11-29 10:13:25');
INSERT INTO `sys_menu` VALUES ('25', '23', '编辑', null, 'common:log:edit', '2', null, '1', '2018-11-29 10:13:25', '2018-11-29 10:13:25');
INSERT INTO `sys_menu` VALUES ('26', '23', '查看', null, 'common:log:get', '2', null, '1', '2018-11-29 10:13:25', '2018-11-29 10:13:25');
INSERT INTO `sys_menu` VALUES ('27', '23', '删除', null, 'common:log:remove', '2', null, '1', '2018-11-29 10:13:25', '2018-11-29 10:13:25');
INSERT INTO `sys_menu` VALUES ('28', '23', '批量删除', null, 'common:log:batchRemove', '2', null, '1', '2018-11-29 10:13:25', '2018-11-29 10:13:25');
INSERT INTO `sys_menu` VALUES ('29', '22', '字典管理', 'common/dict', 'common:dict:dict', '1', null, '1', '2018-11-29 10:13:25', '2018-11-29 10:13:25');
INSERT INTO `sys_menu` VALUES ('30', '29', '新增', null, 'common:dict:add', '2', null, '1', '2018-11-29 10:13:25', '2018-11-29 10:13:25');
INSERT INTO `sys_menu` VALUES ('31', '29', '编辑', null, 'common:dict:edit', '2', null, '1', '2018-11-29 10:13:25', '2018-11-29 10:13:25');
INSERT INTO `sys_menu` VALUES ('32', '29', '查看', null, 'common:dict:get', '2', null, '1', '2018-11-29 10:13:25', '2018-11-29 10:13:25');
INSERT INTO `sys_menu` VALUES ('33', '29', '删除', null, 'common:dict:remove', '2', null, '1', '2018-11-29 10:13:25', '2018-11-29 10:13:25');
INSERT INTO `sys_menu` VALUES ('34', '29', '批量删除', null, 'common:dict:batchRemove', '2', null, '1', '2018-11-29 10:13:25', '2018-11-29 10:13:25');
INSERT INTO `sys_menu` VALUES ('35', '22', '角色管理', 'system/role', 'system:role:role', '1', null, '4', '2018-11-29 10:13:25', '2018-11-29 10:13:25');
INSERT INTO `sys_menu` VALUES ('36', '35', '新增', null, 'system:role:add', '2', null, '1', '2018-11-29 10:13:25', '2018-11-29 10:13:25');
INSERT INTO `sys_menu` VALUES ('37', '35', '编辑', null, 'system:role:edit', '2', null, '1', '2018-11-29 10:13:25', '2018-11-29 10:13:25');
INSERT INTO `sys_menu` VALUES ('38', '35', '查看', null, 'system:role:get', '2', null, '1', '2018-11-29 10:13:25', '2018-11-29 10:13:25');
INSERT INTO `sys_menu` VALUES ('39', '35', '删除', null, 'system:role:remove', '2', null, '1', '2018-11-29 10:13:25', '2018-11-29 10:13:25');
INSERT INTO `sys_menu` VALUES ('40', '35', '批量删除', null, 'system:role:batchRemove', '2', null, '1', '2018-11-29 10:13:25', '2018-11-29 10:13:25');
INSERT INTO `sys_menu` VALUES ('41', '22', '用户管理', 'system/user', 'system:user:user', '1', null, '2', '2018-11-29 10:13:25', '2018-11-29 10:13:25');
INSERT INTO `sys_menu` VALUES ('42', '41', '新增', null, 'system:user:add', '2', null, '1', '2018-11-29 10:13:25', '2018-11-29 10:13:25');
INSERT INTO `sys_menu` VALUES ('43', '41', '编辑', null, 'system:user:edit', '2', null, '1', '2018-11-29 10:13:25', '2018-11-29 10:13:25');
INSERT INTO `sys_menu` VALUES ('44', '41', '查看', null, 'system:user:get', '2', null, '1', '2018-11-29 10:13:25', '2018-11-29 10:13:25');
INSERT INTO `sys_menu` VALUES ('45', '41', '删除', null, 'system:user:remove', '2', null, '1', '2018-11-29 10:13:25', '2018-11-29 10:13:25');
INSERT INTO `sys_menu` VALUES ('46', '41', '批量删除', null, 'system:user:batchRemove', '2', null, '1', '2018-11-29 10:13:25', '2018-11-29 10:13:25');
INSERT INTO `sys_menu` VALUES ('47', '22', '部门管理', 'system/dept', 'system:dept:dept', '1', null, '3', '2018-11-29 10:13:25', '2018-11-29 10:13:25');
INSERT INTO `sys_menu` VALUES ('48', '47', '新增', null, 'system:dept:add', '2', null, '1', '2018-11-29 10:13:25', '2018-11-29 10:13:25');
INSERT INTO `sys_menu` VALUES ('49', '47', '编辑', null, 'system:dept:edit', '2', null, '1', '2018-11-29 10:13:25', '2018-11-29 10:13:25');
INSERT INTO `sys_menu` VALUES ('50', '47', '查看', null, 'system:dept:get', '2', null, '1', '2018-11-29 10:13:25', '2018-11-29 10:13:25');
INSERT INTO `sys_menu` VALUES ('51', '47', '删除', null, 'system:dept:remove', '2', null, '1', '2018-11-29 10:13:25', '2018-11-29 10:13:25');
INSERT INTO `sys_menu` VALUES ('52', '47', '批量删除', null, 'system:dept:batchRemove', '2', null, '1', '2018-11-29 10:13:25', '2018-11-29 10:13:25');
INSERT INTO `sys_menu` VALUES ('53', '22', '菜单管理', 'system/menu', 'system:menu:menu', '1', null, '5', '2018-11-29 10:13:25', '2018-11-29 10:13:25');
INSERT INTO `sys_menu` VALUES ('54', '53', '新增', null, 'system:menu:add', '2', null, '1', '2018-11-29 10:13:25', '2018-11-29 10:13:25');
INSERT INTO `sys_menu` VALUES ('55', '53', '编辑', null, 'system:menu:edit', '2', null, '1', '2018-11-29 10:13:25', '2018-11-29 10:13:25');
INSERT INTO `sys_menu` VALUES ('56', '53', '查看', null, 'system:menu:get', '2', null, '1', '2018-11-29 10:13:25', '2018-11-29 10:13:25');
INSERT INTO `sys_menu` VALUES ('57', '53', '删除', null, 'system:menu:remove', '2', null, '1', '2018-11-29 10:13:25', '2018-11-29 10:13:25');
INSERT INTO `sys_menu` VALUES ('58', '53', '批量删除', null, 'system:menu:batchRemove', '2', null, '1', '2018-11-29 10:13:25', '2018-11-29 10:13:25');
INSERT INTO `sys_menu` VALUES ('59', '41', '重置密码', '', 'system:user:resetPwd', '2', '', '2', '2018-12-25 03:25:31', null);


-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '超级用户角色', 'admin', '拥有最高权限', '2', '2017-08-12 00:43:52', '2017-08-12 19:14:59');
INSERT INTO `sys_role` VALUES ('2', '代码生成权限', '111', '代码生成权限', '2', '2018-11-09 10:00:47', '2018-11-09 10:00:50');
INSERT INTO `sys_role` VALUES ('7', '33', null, '333', null, null, null);


-- ----------------------------
-- Records of sys_role_menu
-- ----------------------------
INSERT INTO `sys_role_menu` VALUES ('119', '2', '-1');
INSERT INTO `sys_role_menu` VALUES ('120', '2', '22');
INSERT INTO `sys_role_menu` VALUES ('127', '2', '53');
INSERT INTO `sys_role_menu` VALUES ('128', '2', '58');
INSERT INTO `sys_role_menu` VALUES ('129', '2', '57');
INSERT INTO `sys_role_menu` VALUES ('130', '2', '56');
INSERT INTO `sys_role_menu` VALUES ('131', '2', '55');
INSERT INTO `sys_role_menu` VALUES ('132', '2', '54');
INSERT INTO `sys_role_menu` VALUES ('133', '2', '47');
INSERT INTO `sys_role_menu` VALUES ('134', '2', '52');
INSERT INTO `sys_role_menu` VALUES ('135', '2', '51');
INSERT INTO `sys_role_menu` VALUES ('136', '2', '50');
INSERT INTO `sys_role_menu` VALUES ('137', '2', '49');
INSERT INTO `sys_role_menu` VALUES ('138', '2', '48');
INSERT INTO `sys_role_menu` VALUES ('139', '2', '41');
INSERT INTO `sys_role_menu` VALUES ('140', '2', '59');
INSERT INTO `sys_role_menu` VALUES ('141', '2', '46');
INSERT INTO `sys_role_menu` VALUES ('142', '2', '45');
INSERT INTO `sys_role_menu` VALUES ('143', '2', '44');
INSERT INTO `sys_role_menu` VALUES ('144', '2', '43');
INSERT INTO `sys_role_menu` VALUES ('145', '2', '42');
INSERT INTO `sys_role_menu` VALUES ('146', '2', '35');
INSERT INTO `sys_role_menu` VALUES ('147', '2', '40');
INSERT INTO `sys_role_menu` VALUES ('148', '2', '39');
INSERT INTO `sys_role_menu` VALUES ('149', '2', '38');
INSERT INTO `sys_role_menu` VALUES ('150', '2', '37');
INSERT INTO `sys_role_menu` VALUES ('151', '2', '36');
INSERT INTO `sys_role_menu` VALUES ('152', '2', '29');
INSERT INTO `sys_role_menu` VALUES ('153', '2', '34');
INSERT INTO `sys_role_menu` VALUES ('154', '2', '33');
INSERT INTO `sys_role_menu` VALUES ('155', '2', '32');
INSERT INTO `sys_role_menu` VALUES ('156', '2', '31');
INSERT INTO `sys_role_menu` VALUES ('157', '2', '30');
INSERT INTO `sys_role_menu` VALUES ('158', '2', '20');
INSERT INTO `sys_role_menu` VALUES ('159', '2', '23');
INSERT INTO `sys_role_menu` VALUES ('160', '2', '28');
INSERT INTO `sys_role_menu` VALUES ('161', '2', '27');
INSERT INTO `sys_role_menu` VALUES ('162', '2', '26');
INSERT INTO `sys_role_menu` VALUES ('163', '2', '25');
INSERT INTO `sys_role_menu` VALUES ('164', '2', '24');
INSERT INTO `sys_role_menu` VALUES ('165', '2', '21');
INSERT INTO `sys_role_menu` VALUES ('166', '2', '15');
INSERT INTO `sys_role_menu` VALUES ('167', '2', '16');
INSERT INTO `sys_role_menu` VALUES ('168', '2', '19');
INSERT INTO `sys_role_menu` VALUES ('169', '2', '18');
INSERT INTO `sys_role_menu` VALUES ('170', '2', '17');
INSERT INTO `sys_role_menu` VALUES ('171', '2', '1');
INSERT INTO `sys_role_menu` VALUES ('172', '2', '14');
INSERT INTO `sys_role_menu` VALUES ('173', '2', '8');
INSERT INTO `sys_role_menu` VALUES ('174', '2', '13');
INSERT INTO `sys_role_menu` VALUES ('175', '2', '12');
INSERT INTO `sys_role_menu` VALUES ('176', '2', '11');
INSERT INTO `sys_role_menu` VALUES ('177', '2', '10');
INSERT INTO `sys_role_menu` VALUES ('178', '2', '9');
INSERT INTO `sys_role_menu` VALUES ('179', '2', '2');
INSERT INTO `sys_role_menu` VALUES ('180', '2', '7');
INSERT INTO `sys_role_menu` VALUES ('181', '2', '6');
INSERT INTO `sys_role_menu` VALUES ('182', '2', '5');
INSERT INTO `sys_role_menu` VALUES ('183', '2', '4');
INSERT INTO `sys_role_menu` VALUES ('184', '2', '3');


-- ----------------------------
-- Records of sys_table
-- ----------------------------
INSERT INTO `sys_table` VALUES ('40', '王企鹅去委屈委屈委屈委屈委屈为', '区委区委区委区委区委区委', '123', '123123123');
INSERT INTO `sys_table` VALUES ('6', '33', '33', '123', '33');
INSERT INTO `sys_table` VALUES ('11', '133', '1333', '133', '1');
INSERT INTO `sys_table` VALUES ('12', '1', '1', '1', '1');
INSERT INTO `sys_table` VALUES ('13', '1', '1', '1', '1');
INSERT INTO `sys_table` VALUES ('42', '123', '123', '123', '123');


-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '超级管理员', 'f418ab2eb57f40a5fb990d67128de54f', '6', 'admin@example.com', '17699999999', '1', '1', '2017-08-15 21:40:39', '2017-08-15 21:41:00', '96', '2017-12-14 00:00:00', '138', 'ccc', '121;', '北京市', '北京市市辖区', '东城区');
INSERT INTO `sys_user` VALUES ('2', '111111', '代码生成权限', '8ebd19c2ab4bb866f3dbbdfc31c4aab6', '6', null, null, '1', null, null, null, null, null, null, null, null, null, null, null);


-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('110', '1', '1');
INSERT INTO `sys_user_role` VALUES ('111', '2', '2');
INSERT INTO `sys_user_role` VALUES ('115', '2', '7');
