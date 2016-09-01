CREATE DATABASE weiboDB DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci

drop table priMessage;
drop table messageReply;
drop table message;
drop table attenGroup;
drop table user;

--用户表
create table user(
	Uid int not null primary key auto_increment, 
	Uname varchar(20) not null, 
	Ualais varchar(20) not null, 
	Utel varchar(11), 		
	Ulogon varchar(30) not null, 	
	Upassword varchar(50) not null, 	
	Region varchar(100) not null,	
	birth date,						
	Usex char(1), 				
	Ublog varchar(50), 					
	Uinfo varchar(100), 		
	Udatetime date not null, 	
	Uimage varchar(100)	,			
	Uprivacy boolean default true		
);

--2. 关注组attenGruop表（将关注的用户分组）
create table attenGroup(
	NGid int not null primary key auto_increment, 
	Uid int not null, 
	Uider int not null,	
	NGDatetime date not null, 
	Ngname Varchar(20) default '未分组' 
);

/*查粉丝数前两名*/
SELECT Uider, count( * ) AS count  
FROM attenGroup  
GROUP BY Uider  
ORDER BY count DESC
limit 2;
 
--3./*普通消息message表*/
create table message(
	Mid int not null primary key auto_increment, 
	Uid int not null, 
	Cid int,
	Mcontent Varchar(3000) not null,
	Mdatetime datetime not null, 
		constraint FK1 foreign key(Uid) references user(Uid)
)

--4./*普通消息评论messageReply表*/

create table messageReply(
	Rid int not null primary key auto_increment, 
	Mid int not null, 
	Uid int not null,
	Rdatetime date not null,
	Rcontent Varchar(200) not null, 	
		constraint FK5 foreign key(Mid) references message(Mid),	
		constraint FK6 foreign key(Uid) references user(Uid)
);


--5./*私信priMessage表*/

create table priMessage(
	PMid int not null primary key auto_increment ,
	Uid int not null ,
	RUid int not null, 
	PMcontent Varchar(3000) not null, 
	PMdatetime date not null, 
	constraint FK7 foreign key(RUid) references user(Uid),
	constraint FK8 foreign key(Uid) references user(Uid)
)

select * from message where uid in(select uider as uid from attenGroup where uid = 1) or uid=1 order by Mdatetime desc;





