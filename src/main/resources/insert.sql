delete from attenGroup;
delete from message;
delete from user;

insert into user values(1,"小明","小明","15570966834","549398044@qq.com","202CB962AC59075B964B07152D234B70","湖南","1995-01-02",'男',"123@qq.com","帅气活泼开朗大方",now(),"..\\file\\1472103457537.jpg",true);
insert into user values(2,"小红","小红","15570966834","352865764@qq.com","202CB962AC59075B964B07152D234B70","湖南","1995-02-02",'女',"123@qq.com","帅气活泼开朗大方",now(),"..\\file\\1472103457537.jpg",true);
insert into user values(3,"小华","小华","15570966834","547602467@qq.com","202CB962AC59075B964B07152D234B70","湖南","1995-01-02",'男',"123@qq.com","帅气活泼开朗大方",now(),"..\\file\\1472103457537.jpg",true);

insert into message values(101,1,null,"据说王宝强和马蓉离婚了！",now());
insert into message values(102,2,null,"发微博不眨眼睛也很厉害！",now());
insert into message values(103,3,null,"开学三天假！",now());


insert into attenGroup values(1,1,2,now(),'朋友');
insert into attenGroup values(2,1,3,now(),'朋友');
insert into attenGroup values(3,2,1,now(),'朋友');
insert into attenGroup values(4,2,3,now(),'朋友');
insert into attenGroup values(5,3,1,now(),'朋友');
insert into attenGroup values(6,3,2,now(),'朋友');


