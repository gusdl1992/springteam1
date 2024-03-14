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
    eeducation varchar(10),
    id varchar(30) not null unique,
    pw varchar(15) not null,
    ename varchar(20) not null,
    email varchar(30) not null unique,
    phone varchar(15) not null unique,
    address varchar(15) not null,
    sex bool not null,
    img varchar(255) default 'default.jpg',
    edate datetime default now(),
    pno int not null,
    constraint e_pk primary key(eno),
    foreign key(pno) references part(pno)
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
    foreign key(settoeno) references employee(eno),
    foreign key(settoeno) references employee(eno)
);

create table projectlog( #프로젝트-멤버 1:1로그
	eno int,
    pjno int,
    state int default 0,
    score int default 0,
    note Varchar(255),
    constraint foreign key(eno) references employee(eno),
    foreign key(pjno) references uploadproject(pjno)
);

create table projectlike( #좋아요 누른 프로젝트로그
	eno int,
    pjno int,
    foreign key(eno) references employee(eno),
    foreign key(pjno) references uploadproject(pjno)
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
    constraint license_eno_fk foreign key(eno) references employee(eno),
    constraint license_lno_fk2 foreign key(lno) references license(lno)
);


create table employeecareer( #사원 경력
	eno int,
    companyname varchar(255),
    note varchar(255), #무슨 업무를 맡았는지
    eimg longtext,	#경력 증명서
    start_date date,
    end_date date,
    constraint employeecareer_eno_fk foreign key(eno) references employee(eno)
);


create table price(
	pno int auto_increment,	#1이면 초급, 2이면 중급 3이면고급
	startyear int,
    endyear int, #3,5,7
    pprice int,		#연봉
    constraint primary key(pno)
);


insert into price(startyear,endyear,pprice) values(0,5,3500);
insert into price(startyear,endyear,pprice) values(6,10,6000);
insert into price(startyear,endyear,pprice) values(11,15,10000);

insert into part values(1,"인사과");
insert into part values(2,"영업");
insert into part value(3,"프로그래머");
insert into employee(id,ename,pw,email,phone,address,pno,sex) values("admina","admina","admina","aaa@aa.aa","phonea","adressa",1,0);
insert into employee(id,ename,pw,email,phone,address,pno,sex) values("adminb","adminb","adminb","bbb@bb.bb","phoneb","adressb",1,1);
insert into employee(id,ename,pw,email,phone,address,pno,sex) values("adminc","adminc","adminc","ccc@cc.cc","phonec","adressc",1,0);

insert into employee(id,ename,pw,email,phone,address,pno,sex) values("salesa","salesa","salesa","ddd@dd.dd","phoned","adressd",2,0);
insert into employee(id,ename,pw,email,phone,address,pno,sex) values("salesb","salesb","salesb","eee@ee.ee","phonee","adresse",2,1);
insert into employee(id,ename,pw,email,phone,address,pno,sex) values("salesc","salesc","salesc","fff@ff.ff","phonef","adressf",2,0);

insert into employee(id,ename,pw,email,phone,address,pno,sex) values("programmera","programmera","programmera","ggg@gg.gg","phoneg","adressg",3,0);
insert into employee(id,ename,pw,email,phone,address,pno,sex) values("programmerb","programmerb","programmerb","hhh@hh.hh","phoneh","adressh",3,1);
insert into employee(id,ename,pw,email,phone,address,pno,sex) values("programmerc","programmerc","programmerc","iii@ii.ii","phonei","adressi",3,0);

insert into employeecareer values(1,"ㅇㅇ","ㅇㅇ","ㅇㅇ","2021-01-01","2024-01-01");
insert into salesproject(start_date,end_date,rank1_count,rank2_count,rank3_count,title,request,compannyname,price)
values("2024-02-29","2024-03-09",3,5,1,"ezen site 차세대 프로젝트","일잘하는애들로","ezen",5000000);

insert into salesproject(start_date,end_date,rank1_count,rank2_count,rank3_count,title,request,compannyname,price)
values("2024-03-08","2024-07-10",5,0,0,"쇼핑몰 구축"," ","ezen쇼핑몰",30000000);

insert into salesproject(start_date,end_date,rank1_count,rank2_count,rank3_count,title,request,compannyname,price)
values("2030-03-08","2030-07-10",5,0,0,"쇼핑몰 구축"," ","ezen쇼핑몰",30000000);

insert into uploadproject(spjno) values(1);
insert into uploadproject(spjno) values(2);


insert into report(settoeno,setfromeno,title,content) values(1,2,"이거사줘","붕어싸만코");
insert into report(settoeno,setfromeno,title,content) values(1,3,"휴가","ㅇㅇ");

truncate projectlog;
insert into projectlog(eno, pjno) values(2,1);
insert into projectlog(eno, pjno) values(3,1);
insert into projectlog(eno, pjno) values(7,1);
insert into projectlog(eno, pjno) values(6,1);

insert into projectlog(eno, pjno) values(2,2);
insert into projectlog(eno, pjno) values(3,2);

insert into projectlike(eno, pjno) values(2,1);
insert into projectlike(eno, pjno) values(2,3);
insert into projectlike(eno, pjno) values(3,2);



select * from employee;
select * from salesproject;
select * from uploadproject;
select * from projectlog;

select * from projectlike;
select * from employeecareer;


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
    constraint employee_eno_fk foreign key(eno) references employee(eno)
);

# 출퇴근 DB 검색

INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-03-13", "08:50:00", "18:00:00", "09:10:00", "192.111.111.11", "비고없음", 2); # 테스트 더미
INSERT INTO attendance_log(jday, stat_time, end_time, working_time, jip, jnote, eno) VALUES("2024-03-12", "08:40:00", "18:00:00", "09:20:00", "192.111.111.11", "비고없음", 2); # 테스트 더미


select * from attendance_log;
select jday , stat_time , end_time , eno from attendance_log where eno = 2 and jday = "2024-03-13";






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

select * from projectlike p inner join salesproject s ON p.pjno = s.pjno where eno = 1;


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
  WHERE spjno IN (SELECT spjno FROM uploadproject);
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

DELIMITER ;

show events;
show triggers;
