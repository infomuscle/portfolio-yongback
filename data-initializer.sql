create database portfolio;
use portfolio;


create table account(
	account_id int not null auto_increment
  , login_id varchar(20)
  , password varchar(255)
  , created_date_time datetime
  , updated_date_time datetime
  , primary key(account_id)
);

insert into account(login_id, password, created_date_time, updated_date_time)
values	 ('admin1', '{비밀번호 암호문}', now(), now());


create table http_interface(
	http_interface_id int not null auto_increment
  , cookies varchar(255)
  , referer varchar(255)
  , local_addr varchar(255)
  , remote_addr varchar(255)
  , remote_host varchar(255)
  , request_uri varchar(255)
  , user_agent varchar(255)
  , created_date_time datetime
  , updated_date_time datetime
  , primary key(http_interface_id)
);


create table achievement(
    achievement_id int not null auto_increment
  , created_date_time datetime
  , title varchar(255)
  , description varchar(255)
  , achieved_date date
  , host varchar(255)
  , is_active bit
  , updated_date_time datetime
  , primary key(achievement_id)
);


create table introduction(
    introduction_id int not null auto_increment
  , content varchar(255)
  , is_active bit
  , created_date_time datetime
  , updated_date_time datetime
  , primary key(introduction_id)
);


create table link(
    link_id int not null auto_increment
  , name varchar(255)
  , content varchar(255)
  , is_active bit
  , created_date_time datetime
  , updated_date_time datetime
  , primary key(link_id)
);


create table skill(
    skill_id int not null auto_increment
  , name varchar(255)
  , skill_type varchar(255)
  , is_active bit
  , created_date_time datetime
  , updated_date_time datetime
  , primary key(skill_id)
);


create table experience(
    experience_id int not null auto_increment
  , title varchar(255)
  , description varchar(255)
  , start_year smallint
  , start_month tinyint
  , end_year smallint
  , end_month tinyint
  , is_active bit
  , created_date_time datetime
  , updated_date_time datetime
  , primary key(experience_id)
);


create table experience_detail(
    experience_detail_id int not null auto_increment
  , experience_id int
  , content varchar(255)
  , is_active bit
  , created_date_time datetime
  , updated_date_time datetime
  , primary key(experience_detail_id)
  , foreign key(experience_id) references experience(experience_id)
);


create table project(
    project_id int not null auto_increment
  , name varchar(255)
  , description varchar(255)
  , start_year smallint
  , start_month tinyint
  , end_year smallint
  , end_month tinyint
  , is_active bit
  , created_date_time datetime
  , updated_date_time datetime
  , primary key(project_id)
);


create table project_detail(
    project_detail_id int not null auto_increment
  , project_id int
  , content varchar(255)
  , url varchar(255)
  , is_active bit
  , created_date_time datetime
  , updated_date_time datetime
  , primary key(project_detail_id)
  , foreign key(project_id) references project(project_id)
);


create table project_skill(
    project_skill_id int not null auto_increment
  , project_id int
  , skill_id int
  , created_date_time datetime
  , updated_date_time datetime
  , primary key(project_skill_id)
  , foreign key(project_id) references project(project_id)
  , foreign key(skill_id) references skill(skill_id)
);
