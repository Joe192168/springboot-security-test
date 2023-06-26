-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS t_user;
CREATE TABLE t_user  (
  id SERIAL   primary key,
  username varchar(20) NULL,
  password varchar(100) NULL,
  sex int4 NULL,
  email varchar(30) NULL,
  mobile varchar(15) NULL,
  status int4,
  create_time timestamp(0),
  update_time timestamp(0)
);

COMMENT ON COLUMN public.t_user.id IS '主键';
COMMENT ON COLUMN public.t_user.username IS '用户名';
COMMENT ON COLUMN public.t_user.password IS '登陆密码';
COMMENT ON COLUMN public.t_user.sex IS '性别';
COMMENT ON COLUMN public.t_user.email IS '邮箱';
COMMENT ON COLUMN public.t_user.mobile IS '手机号';
COMMENT ON COLUMN public.t_user.status IS '状态 0：正常，1：删除';
COMMENT ON COLUMN public.t_user.create_time IS '创建时间';
COMMENT ON COLUMN public.t_user.update_time IS '修改时间';


INSERT INTO "public"."t_user" VALUES (1, 'admin', '$2a$10$p/oqSdJHtnCY6WB1KlZK0uA1SMSZQqotDBg5rVhgBXtbzAE4A1m6m', 1, NULL, NULL, 0, '2023-06-08 15:00:02', NULL);
INSERT INTO "public"."t_user" VALUES (2, 'TUser', '$10$p/oqSdJHtnCY6WB1KlZK0uA1SMSZQqotDBg5rVhgBXtbzAE4A1m6m', 1, NULL, NULL, 0, '2023-06-08 15:00:05', NULL);


-- ----------------------------
-- Table structure for t_menu
-- ----------------------------
DROP TABLE IF EXISTS t_menu;
CREATE TABLE public.t_menu (
  id SERIAL   primary key,
  name varchar(20) NULL,
  type int4,
  url varchar(200) NULL,
  parent_id int4 NULL,
  parent_ids varchar(100) NULL,
  permission varchar(100) NULL,
  menu_icon varchar(255) NULL,
  remarks varchar(255) NULL,
  create_by varchar(32) NULL,
  create_date timestamp(0),
  update_by varchar(32),
  update_date timestamp(0),
  del_flag int4 DEFAULT 0
);

COMMENT ON COLUMN public.t_menu.id IS '主键';
COMMENT ON COLUMN public.t_menu.name IS '菜单名称';
COMMENT ON COLUMN public.t_menu.type IS '资源类型,1:目录，2：菜单，3：按钮';
COMMENT ON COLUMN public.t_menu.url IS '点击后前往的地址';
COMMENT ON COLUMN public.t_menu.parent_id IS '父编号';
COMMENT ON COLUMN public.t_menu.parent_ids IS '父编号列表';
COMMENT ON COLUMN public.t_menu.permission IS '权限字符串';
COMMENT ON COLUMN public.t_menu.menu_icon IS '图标';
COMMENT ON COLUMN public.t_menu.remarks IS '备注';
COMMENT ON COLUMN public.t_menu.create_by IS '创建者';
COMMENT ON COLUMN public.t_menu.create_date IS '创建日期';
COMMENT ON COLUMN public.t_menu.update_by IS '修改者';
COMMENT ON COLUMN public.t_menu.update_date IS '修改日期';
COMMENT ON COLUMN public.t_menu.del_flag IS '删除标志，0:正常，1:删除';

INSERT INTO public.t_menu(name,type,url,parent_id,parent_ids,permission,menu_icon,remarks,create_by,create_date,update_by,update_date,del_flag)
VALUES ('登陆url', 2, '/login', NULL, NULL, '/login', NULL, NULL, '1', '2023-06-08 15:24:42', NULL, NULL, 0);
INSERT INTO public.t_menu(name,type,url,parent_id,parent_ids,permission,menu_icon,remarks,create_by,create_date,update_by,update_date,del_flag)
VALUES ('查询用户列表', 2, '/user/getUserByName/**', NULL, NULL, '/user/getUserByName', NULL, NULL, '1', '2023-06-08 15:24:42', NULL, NULL, 0);


-- ----------------------------
-- Table structure for t_role
-- ----------------------------
DROP TABLE IF EXISTS t_role;
CREATE TABLE t_role  (
  id SERIAL   primary key,
  role_name varchar(10) NULL,
  role_remark varchar(100) NULL,
  user_id_create int4,
  create_time timestamp(0),
  update_time timestamp(0)
);

COMMENT ON COLUMN public.t_role.id IS '主键';
COMMENT ON COLUMN public.t_role.role_name IS '角色名称';
COMMENT ON COLUMN public.t_role.role_remark IS '角色描述';
COMMENT ON COLUMN public.t_role.user_id_create IS '创建角色用户id';
COMMENT ON COLUMN public.t_role.create_time IS '创建时间';
COMMENT ON COLUMN public.t_role.update_time IS '修改时间';

INSERT INTO public.t_role(role_name,role_remark,user_id_create,create_time,update_time)
VALUES ('admin', '管理员', 1, '2023-06-08 15:22:17', '2023-06-08 15:22:20');
INSERT INTO public.t_role(role_name,role_remark,user_id_create,create_time,update_time)
VALUES ('user', '普通用户', 1, '2023-06-08 15:22:38', '2023-06-08 15:22:41');

-- ----------------------------
-- Table structure for t_role_menu
-- ----------------------------
DROP TABLE IF EXISTS t_role_menu;
CREATE TABLE t_role_menu  (
  id SERIAL   primary key,
  role_id int4,
  menu_id int4
);

COMMENT ON COLUMN public.t_role_menu.id IS '主键';
COMMENT ON COLUMN public.t_role_menu.role_id IS '角色id';
COMMENT ON COLUMN public.t_role_menu.menu_id IS '菜单id';

INSERT INTO public.t_role_menu(role_id,menu_id) VALUES (1, 1);

-- ----------------------------
-- Table structure for t_user_role
-- ----------------------------
DROP TABLE IF EXISTS t_user_role;
CREATE TABLE t_user_role  (
  id SERIAL   primary key,
  user_id int4,
  role_id int4
);

COMMENT ON COLUMN public.t_user_role.id IS '主键';
COMMENT ON COLUMN public.t_user_role.user_id IS '用户id';
COMMENT ON COLUMN public.t_user_role.role_id IS '角色id';

INSERT INTO public.t_user_role(user_id,role_id) VALUES (1, 1);
INSERT INTO public.t_user_role(user_id,role_id) VALUES (1, 2);
INSERT INTO public.t_user_role(user_id,role_id) VALUES (2, 2);