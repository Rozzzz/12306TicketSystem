/*       12306数据库     */

--车站表
CREATE TABLE station(
    s_ID VARCHAR2(8), --站点编号
    s_name VARCHAR2(12)  PRIMARY KEY,  --站点名
    condition VARCHAR2(10) DEFAULT '使用中' NOT NULL --站点状态
);
CREATE SEQUENCE seq_station_sID  START WITH 1001 INCREMENT BY 1;

create or replace trigger tri_station
before insert on station
for each row
begin
    select 'D'||seq_station_sID.nextval into :new.s_ID from dual;
end;

--火车详情表
CREATE TABLE traininfo(
    t_ID VARCHAR2(10),  --火车编号
    t_init VARCHAR2(12) constraint I_trani REFERENCES station(s_name), --初始地名
    init_ID  INT,  --初始地为几号站
    t_dest VARCHAR2(12) constraint D_trani REFERENCES station(s_name),  --目的地名
    dest_ID INT,  --目的地为几号站
    soft_seat INT  default 0  CHECK ( soft_seat>=0),  --软卧数
    hard_seat INT  DEFAULT 0 CHECK (hard_seat>=0),  --硬卧数
    t_seat  INT  DEFAULT 0 CHECK (t_seat>=0), --硬座数
    soft_price NUMBER(7,2) NOT NULL, --软卧价格 
    hard_price NUMBER(7,2) NOT NULL,  --硬卧价格
    seat_price NUMBER(7,2) NOT NULL, --硬座价格
    start_time  VARCHAR2(16) NOT NULL,      --开始时间
    end_time   VARCHAR2(16) NOT NULL    --结束时间
    --dates CHAR(16),  --日期
    --note VARCHAR2(200), --备注
    --condition VARCHAR2(20) DEFAULT '正常运行' --状态
);

--火车总表
create table train (
       t_ID  varchar(6) ,--火车号
       start_day VARCHAR2(25) ,      --开始时间
       end_day VARCHAR2(25),      --结束时间
       soft_seat  int DEFAULT 0 CHECK (soft_seat>=0),   --软卧
       hard_seat int DEFAULT 0 CHECK (hard_seat>=0),  --硬卧
       t_seat  INT  DEFAULT 0 CHECK ( t_seat>=0),  --硬座
       condition  VARCHAR2(10) DEFAULT '初始化中',  --状态
       times INT --规定付款时间
);

--顾客订单表
create table ticketOrder(
       IDs VARCHAR2(50) PRIMARY KEY,
       accountId VARCHAR2(20),   --references customers(accountId), --12306账号
       c_name varchar2(15),--顾客名
       c_ID varchar2(18) , --顾客身份证号
       t_ID varchar2(20),--车次
       t_init varchar2(50),--初始地
       init_ID  INT ,  --初始地为几号站
       t_dest varchar2(50),--目的地
       dest_ID INT,  --目的地为几号站   
       seat_kind Varchar2(10), --座位种类
       seat_Id varchar2(20),--座位号
       condition varchar2(10) DEFAULT'未付款', --订单状态  
       past  VARCHAR2(25)DEFAULT '空', --到期时间
       datet VARCHAR2(25),  --乘车日期
       datep VARCHAR2(25), --订票日期  
       note VARCHAR2(100),--备注
       price Number(7,2)   --票价  
);

CREATE SEQUENCE seq_ticketOrder_IDs  START WITH 1 INCREMENT BY 1;

create or replace trigger tri_ticketOrder
before insert on ticketOrder
for each row
begin
    select to_char(SYSDATE,'YYYYMMDDHHmmss')||seq_ticketOrder_IDs.nextval into :new.IDs from dual;
end;

--顾客信息表
create table customers(
       cname varchar2(50) ,--顾客名
       c_ID varchar2(18) , --顾客身份证号 
       accountId varchar2(20) not null unique,--12306账号
       sex varchar2(3)  constraint aaaa check(sex in('男','女')), --性别
       e_pwd varchar2(40), --登录密码 
       p_pwd varchar2(40) ,--支付密码
       email VARCHAR2(30) NOT NULL, --用户邮箱号
       question VARCHAR2(30) NOT NULL, --密保问题
       answer VARCHAR2(20)  , --密保答案
       school Varchar2(30) DEFAULT '无', --顾客学校
       stu_ID Varchar2(10) DEFAULT '无', --顾客学生号
       isStudent Varchar2(2) DEFAULT 'n'   --是否可以买学生票
);
--管理员表
CREATE TABLE admins(
    a_ID VARCHAR2(12)PRIMARY KEY,  --管理员ID
    a_pwd VARCHAR2(40) NOT NULL, --管理员密码
    a_name VARCHAR2(10) DEFAULT 'admin' --管理员姓名
);

--火车信息变更表
CREATE TABLE t_daily_report(
    times  VARCHAR2(20),  --修改时间
    a_ID  VARCHAR2(12),--相关人员ID
    t_ID VARCHAR2(6), --火车号
    start_dates VARCHAR2(25),  --日期
    end_dates VARCHAR2(25) DEFAULT '无',
    note VARCHAR2(200),--备注
    defect VARCHAR2(20) --毛病
);

--站点信息变更表 
CREATE TABLE s_daily_report(
    start_dates VARCHAR2(25),  --日期
    end_dates VARCHAR2(25) DEFAULT '无',
    times  VARCHAR2(20),  --修改时间
    s_ID VARCHAR2(8), --站点编号
    a_ID  VARCHAR2(12),--相关人员ID
    s_name VARCHAR2(6), --站点名
    note VARCHAR2(200),--备注
    defect VARCHAR2(20) --毛病
);

--联系人表
CREATE TABLE linkman(
    L_ID varchar2(18) CHECK(L_ID LIKE '__________________'),
    L_name VARCHAR2(15),
   isStudent Varchar2(2) DEFAULT '否',   --是否可以买学生票
    accountId varchar2(20) REFERENCES customers(accountId),
    school Varchar2(30) DEFAULT '无', --顾客学校
    stu_ID Varchar2(10) DEFAULT '无' --顾客学生号
);


insert into admins(a_ID,a_pwd,a_name)values('A1001','a','曾严');
insert into admins(a_ID,a_pwd,a_name)values('A1002','a','冯威');
insert into admins(a_ID,a_pwd,a_name)values('A1003','a','罗昌玲');
