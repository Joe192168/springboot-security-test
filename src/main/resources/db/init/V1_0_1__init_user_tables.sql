-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS "public"."users";
CREATE TABLE "public"."users" (
  "id" SERIAL   primary key,
  "username" varchar(255) COLLATE "pg_catalog"."default",
  "password" varchar(255) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO "public"."users" VALUES (2, 'user', '$2a$10$p/oqSdJHtnCY6WB1KlZK0uA1SMSZQqotDBg5rVhgBXtbzAE4A1m6m');
INSERT INTO "public"."users" VALUES (1, 'admin', '$2a$10$PN6pRc0foimW.WIdHF.Ng.xDo8D48vbHX7V9w6DjSq8B131vEm7EK');


-- ----------------------------
-- Table structure for authority
-- ----------------------------
DROP TABLE IF EXISTS "public"."authority";
CREATE TABLE "public"."authority" (
  "id" SERIAL   primary key,
  "username" varchar(255) COLLATE "pg_catalog"."default",
  "authority" varchar(255) COLLATE "pg_catalog"."default"
)
;

-- ----------------------------
-- Records of authority
-- ----------------------------
INSERT INTO "public"."authority" VALUES (3, 'user', 'USER');
INSERT INTO "public"."authority" VALUES (2, 'admin', 'USER');
INSERT INTO "public"."authority" VALUES (1, 'admin', 'ADMIN');