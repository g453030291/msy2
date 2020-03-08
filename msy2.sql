create table arrears_log
(
    id            int auto_increment comment 'id'
        primary key,
    order_id      int           null comment '订单表id',
    arrears_money decimal(7, 2) null comment '还款金额',
    pay_type      varchar(5)    null comment '还款方式',
    arrears_date  datetime      null comment '还款时间'
)
    comment '还款记录表';

create table brand
(
    id               int auto_increment comment 'id'
        primary key,
    name             varchar(20)  null comment '品牌名称',
    person_in_charge varchar(10)  null comment '厂商负责人姓名',
    telephone        varchar(20)  null comment '联系电话',
    address          varchar(200) null comment '厂家详细地址',
    create_date      datetime     null comment '添加时间'
)
    comment '品牌表';

create table cart
(
    id        int auto_increment comment 'id'
        primary key,
    client_id int null comment '用户id',
    goods_id  int null comment '商品id',
    num       int null comment '商品数量'
);

create table client
(
    id            int auto_increment comment 'id'
        primary key,
    name          varchar(10)  null comment '姓名',
    sex           varchar(5)   null comment '性别',
    telephone1    varchar(15)  null comment '联系方式1',
    telephone2    varchar(15)  null comment '联系方式2',
    telephone3    varchar(15)  null comment '联系方式3',
    store_name    varchar(50)  null comment '店铺名称',
    address       varchar(100) null comment '店铺地址',
    verified      varchar(5)   null comment '实名认证(已认证,未认证)',
    level         int          null comment '用户等级',
    create_date   datetime     null comment '创建时间',
    language      varchar(20)  null comment '语言',
    country       varchar(10)  null comment '国家',
    province      varchar(10)  null comment '省份',
    city          varchar(10)  null comment '城市',
    district      varchar(10)  null comment '区县',
    img1          longtext     null comment '图片1',
    img2          longtext     null comment '图片2',
    img3          longtext     null comment '图片3',
    img4          longtext     null comment '图片4',
    img5          longtext     null comment '图片5',
    access_token  varchar(200) null comment '网页授权接口调用凭证',
    expires_in    bigint       null comment 'access_token接口调用凭证超时时间,单位(秒)',
    refresh_token varchar(200) null comment '用户刷新access_token',
    openid        varchar(200) null comment '用户唯一标识',
    scope         varchar(200) null comment '用户授权的作用域',
    nickname      varchar(100) null comment '用户昵称',
    headimgurl    varchar(300) null comment '用户头像',
    privilege     varchar(100) null comment '用户特权信息，json 数组',
    send_province varchar(10)  null comment '配送省份',
    send_city     varchar(10)  null comment '配送城市',
    send_district varchar(10)  null comment '配送区县',
    id_card       varchar(20)  null comment '身份证号'
)
    comment '用户表';

create table goods
(
    id          int auto_increment comment 'id'
        primary key,
    oil_type    varchar(20)   null comment '油品类型',
    brand_id    int           null comment '品牌id',
    brand_name  varchar(20)   null comment '品牌名称',
    goods_name  varchar(20)   null comment '商品名称',
    level_name  varchar(20)   null comment '级别',
    model_name  varchar(20)   null comment '型号',
    guige_name  varchar(20)   null comment '规格',
    price       decimal(5, 2) null comment '单价',
    state       varchar(5)    null comment '商品状态(上下架)',
    img1        longtext      null comment '商品图片',
    create_date datetime      null comment '创建时间'
)
    comment '商品表';

create table msy_order
(
    id              int auto_increment comment 'id'
        primary key,
    client_id       int           null comment '用户id',
    client_name     varchar(10)   null comment '用户姓名',
    store_name      varchar(20)   null comment '店铺名称',
    telephone       varchar(15)   null comment '用户联系电话',
    send_id         int           null comment '配送员工id',
    send_name       varchar(10)   null comment '配送员工名称',
    money           decimal(7, 2) null comment '订单金额',
    order_date      datetime      null comment '下单时间',
    send_date       datetime      null comment '配送到货时间',
    pay_date        datetime      null comment '支付时间',
    pay_state       varchar(5)    null comment '支付状态(未支付,已支付)',
    pay_type        varchar(10)   null comment '支付方式(现金,微信,支付宝,银联)',
    is_arrears      varchar(5)    null comment '是否有欠款(是/否)',
    arrears_money   decimal(7, 2) null comment '欠款金额',
    arrears_date    datetime      null comment '催款时间',
    to_arrears_date datetime      null comment '还款时间',
    repo_id         int           null comment '出货仓库id',
    repo_name       varchar(10)   null comment '出货仓库名称',
    create_date     datetime      null comment '创建时间'
)
    comment '订单表';

create table order_goods
(
    id          int auto_increment comment 'id'
        primary key,
    brand_name  varchar(20)   null comment '品牌',
    goods_name  varchar(20)   null comment '商品名称',
    level_name  varchar(20)   null comment '级别',
    model_name  varchar(20)   null comment '型号',
    guige_name  varchar(20)   null comment '规格',
    order_id    int           null comment '关联order表id',
    goods_id    int           null comment '关联商品表id',
    buy_count   int           null comment '购买数量',
    goods_price decimal(5, 2) null comment '商品单价',
    total_money decimal(7, 2) null comment '本条总价',
    create_date datetime      null comment '创建时间'
)
    comment '订单详情表';

create table repo
(
    id          int auto_increment
        primary key,
    name        varchar(20)  null comment '仓库名称',
    province    varchar(10)  null comment '省',
    city        varchar(10)  null comment '市',
    district    varchar(10)  null comment '区县',
    address     varchar(100) null comment '详细地址',
    create_date datetime     null comment '创建时间'
)
    comment '仓库列表';

create table staff
(
    id          int auto_increment
        primary key,
    client_id   int         null comment '关联用户id,将用户变更为员工',
    name        varchar(10) null comment '姓名',
    telephone   varchar(20) null comment '手机号',
    age         int         null comment '年龄',
    sex         varchar(5)  null comment '性别',
    birthday    datetime    null comment '出生日期',
    work_city   varchar(10) null comment '工作区域',
    address     text        null comment '家庭住址',
    state       varchar(5)  null comment '工作状态(在职/离职)',
    create_date datetime    null comment '创建时间'
)
    comment '员工表';

create table stock
(
    id          int auto_increment comment 'id'
        primary key,
    repo_id     int           null comment '存放仓库',
    repo_name   varchar(20)   null comment '存放仓库名称',
    money       decimal(7, 2) null comment '本次进货总金额',
    stock_date  datetime      null comment '进货时间',
    create_date datetime      null comment '创建时间'
)
    comment '进货记录表';

create table stock_goods
(
    id          int auto_increment
        primary key,
    stock_id    int           null comment '进货表id',
    goods_id    int           null comment '商品表id',
    goods_count int           null comment '进货商品数量',
    price       decimal(7, 2) null comment '进货单价',
    total_price decimal(7, 2) null comment '进货总价'
)
    comment '进货商品详情';

create table verified
(
    id       int auto_increment
        primary key,
    verified tinyint null comment '实名认证状态(0:关闭.1:开启)'
)
    comment '实名认证状态';


