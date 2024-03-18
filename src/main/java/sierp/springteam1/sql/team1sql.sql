drop database if exists springteam1;
create database springteam1;
use springteam1;

create table part( #파트테이블
   pno int auto_increment,
    pname varchar(30),
    constraint primary key(pno)
);

create table employee( #사원테이블
   eno int auto_increment,
    id varchar(30) not null unique,
   eeducation varchar(10),
    salt varchar(255) ,
    pw varchar(255) not null,
    ename varchar(20) not null,
    email varchar(30) not null unique,
    phone varchar(15) not null unique,
    address varchar(15) not null,
    sex bool not null,
    img varchar(255) default 'default.jpg',
    edate datetime default now(),
    pno int not null,
    constraint e_pk primary key(eno) ,
    foreign key(pno) references part(pno) on delete cascade
);

create table salesproject( #영업해와서 등록하는 프로젝트 테이블
   spjno int auto_increment,
    start_date date not null,
    end_date date not null,
    rank1_count int,
    rank2_count int,
    rank3_count int,
    title varchar(30) not null,
    request varchar(255),
    note varchar(255) default "",
    compannyname varchar(20) not null,
    state int default -1,
    price int  not null,
    constraint primary key(spjno)
);


create table uploadproject( #영업프로젝트 테이블에서 실제 등록하는 테이블
   pjno int auto_increment,
    spjno int,
    constraint up_pjno_pk primary key (pjno),
    constraint up_spjo_fk foreign key (spjno) references salesproject(spjno) on delete cascade
);

create table report( #보고서 보낸 로그
   rno int auto_increment,
    settoeno int, #얘는 보고서 받는사람
   setfromeno int, #얘는 보고서 보낸사람
    state bool default 0,
    title varchar(20),
    content text,
    img longtext ,
    note varchar(255),
    rdate datetime default now(),
    constraint primary key(rno),
    foreign key(settoeno) references employee(eno) on delete cascade,
    foreign key(settoeno) references employee(eno) on delete cascade
);

create table projectlog( #프로젝트-멤버 1:1로그
   eno int,
    pjno int,
    state int default 0,
    score int default 0,
    note Varchar(255),
    constraint foreign key(eno) references employee(eno),
    foreign key(pjno) references uploadproject(pjno) on delete cascade
);

create table projectlike( #좋아요 누른 프로젝트로그
   eno int,
    pjno int,
    foreign key(eno) references employee(eno) on delete cascade,
    foreign key(pjno) references uploadproject(pjno) on delete cascade
);

create table license( #자격증 종류 테이블
   lno int auto_increment,
    lname varchar(50),
    lprice int,
    constraint primary key(lno)
);

create table employeelicense( #사원 자격증 로그
   eno int,
    lno int,
   ldate datetime default now(),
    constraint license_eno_fk foreign key(eno) references employee(eno) on delete cascade,
    constraint license_lno_fk2 foreign key(lno) references license(lno) on delete cascade
);


create table employeecareer( #사원 경력
   eno int,
    companyname varchar(255),
    note varchar(255), #무슨 업무를 맡았는지
    eimg longtext,   #경력 증명서
    start_date date,
    end_date date,
    constraint employeecareer_eno_fk foreign key(eno) references employee(eno) on delete cascade
);


create table price(
   pno int auto_increment,   #1이면 초급, 2이면 중급 3이면고급
   p_start int,
    p_end int, #3,5,7
    pprice int,      #연봉
    constraint primary key(pno)
);

create table salary(
   eno int,
    smonth datetime default now(),
    price text,
   foreign key(eno) references employee(eno) on delete cascade
);

create table salarylog(
   sno int primary key auto_increment,
    eno int,
    smonth datetime default now(),
    price text,
   foreign key(eno) references employee(eno) on delete cascade
);


# 출퇴근 DB 설계
drop table if exists attendance_log;
create table attendance_log(
   jday date,
    stat_time time ,
    end_time time ,
    working_time time,
    jip varchar(20) ,
    jnote varchar(255) ,
    eno int ,
    constraint employee_eno_fk foreign key(eno) references employee(eno)on delete cascade
);

drop table if exists note ;
create table note(
   nno int auto_increment,
    posteno int,
    sendeno int,
    ncontent text,
    ndate datetime default now(),
    reply int default 0,
    constraint primary key(nno),
   constraint foreign key(posteno) references employee(eno) on delete set null,
   constraint foreign key(sendeno) references employee(eno) on delete set null
);



insert into price(p_start,p_end,pprice) values(0,1459,300);
insert into price(p_start,p_end,pprice) values(1460,3650,600);
insert into price(p_start,p_end,pprice) values(3651,999999999,1000);

insert into part values(1,"인사과");
insert into part values(2,"영업");
insert into part value(3,"프로그래머");
select * from employee;

insert into employee(id,ename,eeducation,salt,pw,email,phone,address,pno,sex) values("admina","일사원","초대졸","b467b00048dff114614bc3aaabac1b8b","4ab23e357e7e364233a24c8bf745ba28ea81171de59c22053bf0f2644512d909","admina@naver.com","010-0000-0000","경기도 안산시",1,0);
insert into employee(id,ename,eeducation,pw,email,phone,address,pno,sex) values("adminb","이사원","고졸","adminb","adminb@naver.com","010-1111-1111","경기도 안산시",1,1);
insert into employee(id,ename,eeducation,pw,email,phone,address,pno,sex) values("adminc","삼사원","대졸","adminc","adminc@naver.com","010-2222-2222","경기도 안산시",1,0);

insert into employee(id,ename,pw,email,phone,address,pno,sex) values("salesa","사사원","salesa","salesd@naver.com","010-3333-3333","강남",2,0);
insert into employee(id,ename,pw,email,phone,address,pno,sex) values("salesb","오사원","salesb","salese@naver.com","010-4444-4444","부천",2,1);
insert into employee(id,ename,pw,email,phone,address,pno,sex) values("salesc","육사원","salesc","salesf@naver.com","010-5555-5555","인천",2,0);

insert into employee(id,ename,pw,email,phone,address,pno,sex) values("programmera","칠사원","programmera","prog@naver.com","010-6666-6666","수원",3,0);
insert into employee(id,ename,pw,email,phone,address,pno,sex) values("programmerb","팔사원","programmerb","proh@naver.com","010-7777-7777","구로",3,1);
insert into employee(id,ename,pw,email,phone,address,pno,sex) values("programmerc","구사원","programmerc","proi@naver.com","010-8888-8888","강남",3,0);

insert into employeecareer values(1,"구회사1","","경력증명서","2016-01-01","2024-01-01");
insert into employeecareer values(2,"구회사1","","경력증명서","2016-01-01","2024-01-01");
insert into employeecareer values(3,"구회사2","","경력증명서","2010-01-01","2024-01-01");
insert into employeecareer values(4,"구회사3","","경력증명서","2010-01-01","2024-01-01");
insert into salesproject(start_date,end_date,rank1_count,rank2_count,rank3_count,title,request,compannyname,price)
values("2024-02-29","2024-03-09",2,1,1,"ezen site 차세대 프로젝트1","일잘하는애들로","ezen",5000000),
("2022-01-20","2022-01-21",2,1,1,"ezen site 차세대 프로젝트2","일잘하는애들로","ezen",5000000),
("2022-03-29","2022-06-20",2,1,1,"ezen site 차세대 프로젝트3","일잘하는애들로","ezen",5000000),
("2022-07-29","2022-08-29",2,1,1,"ezen site 차세대 프로젝트4","일잘하는애들로","ezen",5000000),
("2022-07-29","2023-06-09",2,1,1,"ezen site 차세대 프로젝트5","일잘하는애들로","ezen",5000000),
("2023-02-28","2024-01-09",2,1,1,"ezen site 차세대 프로젝트6","일잘하는애들로","ezen",5000000);

insert into salesproject(start_date,end_date,rank1_count,rank2_count,rank3_count,title,request,compannyname,price)
values("2024-03-08","2024-07-10",2,0,0,"국가지원사업1"," ","국가",25000000);

insert into salesproject(start_date,end_date,rank1_count,rank2_count,rank3_count,title,request,compannyname,price)
values("2024-04-20","2025-01-01",3,0,0,"국가지원사업3"," ","국가",30000000);

insert into uploadproject(spjno) values(1);
insert into uploadproject(spjno) values(2);
insert into uploadproject(spjno) values(3);


insert into report(settoeno,setfromeno,title,content) values(1,2,"이거사줘","붕어싸만코");
insert into report(settoeno,setfromeno,title,content) values(1,3,"휴가","ㅇㅇ");



insert into projectlog(eno, pjno) values(2,1);
insert into projectlog(eno, pjno) values(3,1);
insert into projectlog(eno, pjno) values(7,1);
insert into projectlog(eno, pjno) values(6,1);

insert into projectlog(eno, pjno) values(2,2);
insert into projectlog(eno, pjno) values(3,2);

select * from projectlog;
update projectlog set score = 100 where pjno = 1;

insert into projectlike(eno, pjno) values(2,1);
insert into projectlike(eno, pjno) values(2,3);
insert into projectlike(eno, pjno) values(3,2);



select * from employee;
select * from salesproject;
select * from uploadproject;
select * from projectlog;

select * from projectlike;
select * from employeecareer;


# 출퇴근 DB 검색
insert into salarylog(eno,price) values(1,10);
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-01", "08:50:00", "18:00:00", "09:10:00", "192.111.111.11", "비고없음", 2); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-02", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 2); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-05", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 2); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-06", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 2); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-07", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 2); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-08", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 2); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-13", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 2); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-14", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 2); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-15", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 2); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-16", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 2); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-19", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 2); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-20", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 2); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-21", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 2); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-22", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 2); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-23", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 2); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-26", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 2); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-27", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 2); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-28", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 2); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-29", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 2); # 테스트 더미

INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-01", "08:50:00", "18:00:00", "09:10:00", "192.111.111.11", "비고없음", 1); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-02", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 1); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-05", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 1); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-06", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 1); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-07", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 1); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-08", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 1); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-13", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 1); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-14", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 1); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-15", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 1); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-16", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 1); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-19", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 1); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-20", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 1); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-21", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 1); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-22", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 1); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-23", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 1); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-26", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 1); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-27", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 1); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-28", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 1); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-29", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 1); # 테스트 더미

INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-03-04", "08:50:00", "18:00:00", "09:10:00", "192.111.111.11", "비고없음", 1); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-03-05", "08:50:00", "18:00:00", "09:10:00", "192.111.111.11", "비고없음", 1); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-03-06", "08:50:00", "18:00:00", "09:10:00", "192.111.111.11", "비고없음", 1); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-03-07", "08:50:00", "18:00:00", "09:10:00", "192.111.111.11", "비고없음", 1); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-03-08", "08:50:00", "18:00:00", "09:10:00", "192.111.111.11", "비고없음", 1); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-03-11", "08:50:00", "18:00:00", "09:10:00", "192.111.111.11", "비고없음", 1); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-03-12", "08:50:00", "18:00:00", "09:10:00", "192.111.111.11", "비고없음", 1); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-03-13", "08:50:00", "18:00:00", "09:10:00", "192.111.111.11", "비고없음", 1); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-03-14", "08:50:00", "18:00:00", "09:10:00", "192.111.111.11", "비고없음", 1); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-03-15", "08:50:00", "18:00:00", "09:10:00", "192.111.111.11", "비고없음", 1); # 테스트 더미

INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-01", "08:50:00", "18:00:00", "09:10:00", "192.111.111.11", "비고없음", 3); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-02", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 3); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-05", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 3); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-06", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 3); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-07", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 3); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-08", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 3); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-13", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 3); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-14", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 3); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-15", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 3); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-16", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 3); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-19", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 3); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-20", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 3); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-21", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 3); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-22", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 3); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-23", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 3); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-26", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 3); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-27", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 3); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-28", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 3); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-29", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 3); # 테스트 더미

INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-01", "08:50:00", "18:00:00", "09:10:00", "192.111.111.11", "비고없음", 4); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-02", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 4); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-05", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 4); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-06", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 4); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-07", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 4); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-08", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 4); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-13", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 4); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-14", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 4); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-15", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 4); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-16", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 4); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-19", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 4); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-20", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 4); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-21", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 4); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-22", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 4); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-23", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 4); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-26", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 4); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-27", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 4); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-28", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 4); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-29", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 4); # 테스트 더미

INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-01", "08:50:00", "18:00:00", "09:10:00", "192.111.111.11", "비고없음", 5); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-02", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 5); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-05", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 5); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-06", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 5); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-07", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 5); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-08", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 5); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-13", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 5); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-14", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 5); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-15", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 5); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-16", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 5); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-19", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 5); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-20", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 5); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-21", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 5); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-22", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 5); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-23", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 5); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-26", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 5); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-27", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 5); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-28", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 5); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-29", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 5); # 테스트 더미

INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-01", "08:50:00", "18:00:00", "09:10:00", "192.111.111.11", "비고없음", 6); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-02", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 6); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-05", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 6); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-06", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 6); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-07", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 6); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-08", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 6); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-13", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 6); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-14", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 6); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-15", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 6); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-16", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 6); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-19", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 6); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-20", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 6); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-21", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 6); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-22", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 6); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-23", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 6); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-26", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 6); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-27", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 6); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-28", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 6); # 테스트 더미

INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-01", "08:50:00", "18:00:00", "09:10:00", "192.111.111.11", "비고없음", 7); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-02", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 7); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-05", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 7); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-06", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 7); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-07", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 7); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-08", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 7); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-13", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 7); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-14", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 7); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-15", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 7); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-16", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 7); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-19", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 7); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-20", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 7); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-21", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 7); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-22", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 7); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-23", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 7); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-26", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 7); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-27", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 7); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-28", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 7); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-29", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 7); # 테스트 더미

INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-01", "08:50:00", "18:00:00", "09:10:00", "192.111.111.11", "비고없음", 8); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-02", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 8); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-05", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 8); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-06", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 8); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-07", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 8); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-08", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 8); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-13", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 8); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-14", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 8); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-15", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 8); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-16", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 8); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-19", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 8); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-20", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 8); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-21", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 8); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-22", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 8); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-23", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 8); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-26", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 8); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-27", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 8); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-28", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 8); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-29", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 8); # 테스트 더미



INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-01", "08:50:00", "18:00:00", "09:10:00", "192.111.111.11", "비고없음", 9); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-02", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 9); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-05", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 9); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-06", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 9); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-07", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 9); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-08", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 9); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-13", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 9); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-14", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 9); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-15", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 9); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-16", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 9); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-19", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 9); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-20", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 9); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-21", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 9); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-22", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 9); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-23", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 9); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-26", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 9); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-27", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 9); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-28", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 9); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-02-29", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 9); # 테스트 더미

insert into note(posteno, sendeno, ncontent, reply) value(1,2,'안녕하세요. 출장보고 드립니다.', 0);
insert into note(posteno, sendeno, ncontent, reply) value(1,3,'안녕하세요. 지출결의 보고 드립니다.', 0);
insert into note(posteno, sendeno, ncontent, reply) value(1,2,'법인카드 구매내역 입니다..', 0);
insert into note(posteno, sendeno, ncontent, reply) value(2,3,'집에가고싶다', 0);
insert into note(posteno, sendeno, ncontent, reply) value(2,3,'오', 0);
insert into note(posteno, sendeno, ncontent, reply) value(2,1,'확인했습니다.', 1);
insert into note(posteno, sendeno, ncontent, reply) value(3,1,'확인 완료했습니다.', 2);
insert into note(posteno, sendeno, ncontent, reply) value(3,2,'마감일 언제인지 알려주세요', 5);

insert into license values(1,  "정보처리기사",50000 );
insert into license values(2,  "정보보안기사",10000000 );
insert into license values(3, "전자계산기조직응용기사",3000000) ;
insert into license values(4,  "전자계산기기사",20000 );
select * from attendance_log;


UPDATE salesproject SET state=1 WHERE spjno = 1; # 스테이터스 변경 진행중으로 바꾸기

select * from employee;
select * from part;
select * from salesproject;
select * from uploadproject;
select * from projectlog;
SELECT * FROM license;
SELECT * FROM employeelicense;
SELECT * FROM employeecareer;
select * from price;

select * from salary;
select * from salarylog;

select * from attendance_log;
select * from note;

# 박시현 더미 데이터 추가용.


# 내 이전 프로젝트 검색
SELECT DISTINCT b.state, b.title, a.pjno, b.start_date, b.end_date
FROM uploadproject a
JOIN salesproject b ON a.spjno = b.spjno
JOIN projectlog j ON a.pjno = j.pjno
WHERE j.eno = 2 AND j.state = 2;


# 즐겨찾기한 프로젝트 검색
SELECT DISTINCT  p.pjno , s.title , s.compannyname , s.price
FROM projectlike p
INNER JOIN salesproject s ON p.pjno = s.spjno
INNER JOIN employee e ON p.eno = e.eno
WHERE p.eno = 2 and s.state != 2;

-- select * from projectlike p inner join salesproject s ON p.spjno = s.spjno where eno = 1;
-- select * from salesproject;
-- select * from projectlike;
# 내 프로젝트 검색
SELECT p.pjno, p.eno , p.state , s.state , s.start_date , s.end_date , s.title , s.compannyname
FROM projectlog p
INNER JOIN salesproject s ON p.pjno = s.spjno
INNER JOIN employee e ON p.eno = e.eno
WHERE p.eno = 2 AND s.state = 1;


# 종료 프로젝트용 프로젝트 추가
insert into salesproject(start_date,end_date,rank1_count,rank2_count,rank3_count,title,request,compannyname,price)
values("2030-03-08","2030-07-10",5,0,0,"쇼핑몰 구축99"," ","ezen쇼핑몰",30000000);
# 프로젝트 확정시
insert into uploadproject(spjno) values(3);

# 프로젝트 종료 로그
insert into projectlike(eno, pjno) values(1,3);

UPDATE salesproject SET state=1 WHERE spjno = 1; # 스테이터스 변경 진행중으로 바꾸기
UPDATE salesproject SET state=2 WHERE spjno = 2; # 스테이터스 변경 진행중으로 바꾸기
UPDATE salesproject SET state=2 WHERE spjno = 3; # 스테이터스 변경 종료 으로 바꾸기
UPDATE projectlog SET state=2 WHERE pjno = 2; # 스테이터스 변경 진행중으로 바꾸기
UPDATE projectlog SET state=2 WHERE pjno = 3; # 스테이터스 변경 진행중으로 바꾸기
UPDATE projectlog SET state=1 WHERE pjno = 2; # 스테이터스 변경 진행중으로 바꾸기

select * from salarylog join employee using (eno);


 DELIMITER //

CREATE EVENT IF NOT EXISTS update_project_state
ON SCHEDULE EVERY 1 DAY
DO
BEGIN
  UPDATE salesproject
  SET state = CASE
                WHEN start_date > NOW() THEN 0 #시작일자가 오늘 날짜보다 크면 아직 시작안함
                WHEN end_date < NOW() THEN 2 #종료일자가 오늘 날짜보다 작으면 종료된 프로젝트
                ELSE 1 #그 사이인 경우이니 아직 진행중인 프로젝트
              END
  WHERE spjno IN (SELECT spjno FROM uploadproject) and state <> -1;
END//

CREATE TRIGGER update_project_state
AFTER UPDATE
ON salesproject -- 트리거를 부착할 테이블
FOR EACH ROW -- 아래 나올 조건에 해당하는 모든 row에 적용한다는 뜻

BEGIN
  -- 트리거시 실행되는 코드
  IF NEW.state != OLD.state and new.state = 2 THEN -- update 트리거는 old와 new 값이 존재한다.
    UPDATE projectlog SET state = 1 WHERE pjno in (select pjno from salesproject as a inner join uploadproject as b on a.spjno = b.spjno where a.spjno=new.spjno);
  END IF;
END //


CREATE TRIGGER update_project_state2
AFTER insert
ON uploadproject -- 트리거를 부착할 테이블
FOR EACH ROW -- 아래 나올 조건에 해당하는 모든 row에 적용한다는 뜻

BEGIN
  -- 트리거시 실행되는 코드
  IF NEW.spjno IS NOT NULL THEN -- update 트리거는 old와 new 값이 존재한다.
    UPDATE salesproject SET state = 0 WHERE spjno = new.spjno;
  END IF;
END //
DELIMITER ;

select * from uploadproject;
select * from salesproject;
select * from employee;
show events;
show triggers;

select * from salarylog join employee using (eno);