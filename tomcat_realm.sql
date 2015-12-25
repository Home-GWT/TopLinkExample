
DROP DATABASE IF EXISTS addressbook;
CREATE DATABASE addressbook CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE addressbook;


mysqldump -h10.13.71.35 -uroot -p tomcat_realm tomcat_users_group > tomcat_users_group.sql
mysqldump -h10.13.71.35 -uroot -p tomcat_realm tomcat_users > tomcat_users.sql
mysqldump -h10.13.71.35 -uroot -p tomcat_realm tomcat_users_roles > tomcat_users_roles.sql

mysql -h10.13.71.35 -uroot -p1111


DROP TABLE IF EXISTS tomcat_users_roles;
DROP TABLE IF EXISTS tomcat_users;
DROP TABLE IF EXISTS tomcat_roles;
DROP TABLE IF EXISTS tomcat_users_group;


CREATE TABLE tomcat_roles (
  role_name varchar(20) NOT NULL PRIMARY KEY,
  role_description varchar(256) NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

CREATE TABLE tomcat_users_group (
  group_name varchar(20) NOT NULL PRIMARY KEY,
  group_description varchar(256) NULL,
  color varchar(7) DEFAULT '#BBBBBB'
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

CREATE TABLE tomcat_users (
  user_name varchar(20) NOT NULL PRIMARY KEY,
  user_pass varchar(32) NOT NULL,
  user_fio varchar(50) DEFAULT NULL,
  user_ldap varchar(16) DEFAULT NULL,
  email varchar(50) DEFAULT NULL,
  group_name varchar(20) DEFAULT NULL,
  ip varchar(16) DEFAULT NULL,
  regdate date DEFAULT NULL,
  update_ip varchar(16) DEFAULT NULL,
  update_regdate date DEFAULT NULL,
  CONSTRAINT tomcat_users_foreign_key_1 FOREIGN KEY (group_name) REFERENCES tomcat_users_group (group_name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

CREATE TABLE tomcat_users_roles (
  user_name varchar(20) NOT NULL,
  role_name varchar(20) NOT NULL,
  PRIMARY KEY (user_name, role_name),
  CONSTRAINT tomcat_users_roles_foreign_key_1 FOREIGN KEY (user_name) REFERENCES tomcat_users (user_name),
  CONSTRAINT tomcat_users_roles_foreign_key_2 FOREIGN KEY (role_name) REFERENCES tomcat_roles (role_name)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;


INSERT INTO `tomcat_roles` VALUES ('manager-status','позволяет получить доступ только к странице состояния'),('tomcat',''),('manager-jmx','предоставляет доступ к JMX-прокси и к странице состояния'),('admin-script','позволяет получить доступ к текстовым интерфейсам и к страницам состояния'),('manager-script','позволяет получить доступ к текстовым интерфейсам и к страницам состояния'),('manager-gui','предоставляет доступ к JMX-прокси и к странице состояния'),('manager',''),('admin-gui','предоставляет доступ к JMX-прокси и к странице состояния'),('admin','');
INSERT INTO `tomcat_users_group` VALUES ('admin','Use for Administrators Jenkins','#FF0000'),('Aktopus','','#00FFFF'),('Biplane-Admin','','#0000FF'),('Biplane-Buh','','#000000'),('Biplane-Cashier','','#0000A0'),('Biplane-Dept','','#FFA500'),('Biplane-WebCashier','','#800080'),('Irbis','Use only Irbis department','#008000'),('Reports','','#808000'),('Test','','#3B3131'),('Tickets','','#5E5A80'),('undefined','Use for Defoult Group','#25383C'),('Vitrina','PrivatBank of project','#B9C795');
INSERT INTO `tomcat_users`(user_name, user_pass, user_fio, user_ldap, email, group_name, ip, regdate) VALUES ('diver','b18da2b5c42b5f136d754d5ad9c8241e','Великоцкий Вячеслав Андреевич','dn120187vva',NULL,NULL,NULL,NULL),('dmitry','25d55ad283aa400af464c76d713c07ad','Белый Дмитрий Николаевич','nk310182bdn',NULL,NULL,NULL,NULL),('dn010190gma','45f70ebb449ca05e63d1f2f8f5d77005','Гладий Мария Алексеевна','dn010190gma',NULL,NULL,NULL,NULL),('dn021190saj','02eaa5d4a69f441a32750ff7a0122e45','Шумский Александр Юрьевич','dn021190saj',NULL,NULL,NULL,NULL),('dn030681gae','d5a7f14ba0044a04583f68264b0187a8','Горбенко Александр Евгеньевич','dn030681gae',NULL,NULL,NULL,NULL),('dn041189lvs','c20ae6e613882ee07dffa7ff99cacc99','Лущан Владислав Сергеевич','dn041189lvs',NULL,NULL,NULL,NULL),('dn060988mav','29572b8f8435211767d3fd2d9f8ea5f8','Манякин Александр Валериевич','dn060988mav',NULL,NULL,NULL,NULL),('dn080692zaa','74df8a04506d4d30431cb06f7afd67b8','Жмурков Александр Александрович','dn080692zaa',NULL,NULL,NULL,NULL),('dn090880vas','7534aea4414a62ce2c6822be69ce389b','Вознюк Андрей Сергеевич','dn090880vas',NULL,NULL,NULL,NULL),('dn100192pja','ea84dbe0abc548e5451a2c84ed8c1d64','Полинкевич Юлия Александровна','dn100192pja',NULL,NULL,NULL,NULL),('dn101188lvv','4098d61c71dc24b25423781734d79404','Лысенко Владимир Викторович','dn101188lvv',NULL,NULL,NULL,NULL),('dn110793tas','e673a4380e9acb0eab8afda928f60479','Теленко Андрей Сергеевич','dn110793tas',NULL,NULL,NULL,NULL),('dn130490tnv','eb1ce0d91d5b27c8bf5a974ba07233ff','Танаев Николай Викторович','dn130490tnv',NULL,NULL,NULL,NULL),('dn130591bav','f3c48548e8099cbe523793b7475cd01e','Бондаренко Александр Вячеславович','dn130591bav',NULL,NULL,NULL,NULL),('dn150190kad','719aa68685119189b88351e624a166e0','Козуб Алексей Дмитриевич','dn150190kad',NULL,NULL,NULL,NULL),('dn160588bai','e10adc3949ba59abbe56e057f20f883e','Белоусов Артур Игоревич','dn160588bai','artur.belousov@privatbank.ua','admin','10.13.71.46','2013-11-28'),('dn160889gas','51115093833f5e604535fafae6bb091d','Головацкий Андрей Сергеевич','dn160889gas',NULL,NULL,'',NULL),('dn170684sra','454136d655b755a86947060e9d2eb3a7','Штепа Роман Анатольевич','dn170684sra',NULL,NULL,NULL,NULL),('dn180989mis','ea270884adc57775492f10d7c1d03823','Малый Игорь Семенович','dn180989mis',NULL,NULL,NULL,NULL),('dn190491kmg','701a561acf52a1023648bb48b8496eea','Кругликова Марина Геннадьевна','dn190491kmg',NULL,NULL,NULL,NULL),('dn200978lak','273910799eacaacec06aba83c9d54906','Лазарчук Александр Константинович','dn200978lak','DN200978LAK@pbank.com.ua','admin','10.13.71.153','2013-11-29'),('dn220989lms','f9ba3f6b60f8c8771dbd63e0c111ad46','Лаушкина Марина Сергеевна','dn220989lms',NULL,NULL,'',NULL),('dn221290kea','3fd6f1ec303ba765e1bf7ab1649bbcd0','Кондратенко Евгений Александрович','dn221290kea',NULL,NULL,NULL,NULL),('dn230892gmg','a6aa1e1535086627ad5e0040cabcce2f','Галич Максим Геннадьевич','dn230892gmg',NULL,NULL,NULL,NULL),('dn250480tsa','7e8aab1a8900ad5be26385517638ee56','Терещенко Сергей Александрович','dn250480tsa',NULL,NULL,NULL,NULL),('dn250790kva','a7f920f1324e5edb3695d5f42611be0e','Касьяненко Виталий Александрович','dn250790kva',NULL,NULL,NULL,NULL),('dn300189mis','9b763f6da80326d0dff02abac99a7b35',NULL,'dn300189mis',NULL,NULL,NULL,NULL),('dn310784nvv','9910ff29f8c4b13954b6d34427bc83a1','Виктор Никулин Викторович','dn310784nvv',NULL,NULL,NULL,NULL),('dp310588kja','42e214ec593bb64a7cbcd0fe6e8a6c03','Коргут Юля','dp310588kja',NULL,NULL,NULL,NULL),('nk061183cvj','c1d4f351eaf2b4bf115ab322f8cce097','Чумак Владимир Юрьевич','nk061183cvj',NULL,NULL,NULL,NULL),('nk070281sjv','ed80e1fd5d2ed87bce237383bccab330','Шевцов Юрий Викторович','nk070281sjv',NULL,NULL,NULL,NULL),('roma','454136d655b755a86947060e9d2eb3a7','Штепа Роман Анатольевич','dn170684sra',NULL,NULL,NULL,NULL),('Seric','5d0eeac16e1635e8fbdce26684a6771d','Серик Александр Анатольевич','dn050690saa',NULL,NULL,NULL,NULL),('sniper89','51115093833f5e604535fafae6bb091d','Головацкий Андрей Сергеевич','dn160889gas',NULL,NULL,NULL,NULL),('yura','25d55ad283aa400af464c76d713c07ad','Решетник Юрий Владимирович','nk021077rjv',NULL,'admin','<null>',NULL);
INSERT INTO `tomcat_users_roles` VALUES ('diver','admin'),('dmitry','admin'),('dn010190gma','admin'),('dn021190saj','admin'),('dn030681gae','admin'),('dn041189lvs','admin'),('dn060988mav','admin'),('dn080692zaa','admin'),('dn100192pja','admin'),('dn101188lvv','admin'),('dn110793tas','admin'),('dn130490tnv','admin'),('dn130591bav','admin'),('dn150190kad','admin'),('dn160588bai','admin'),('dn160889gas','admin'),('dn170684sra','admin'),('dn180989mis','admin'),('dn190491kmg','admin'),('dn200978lak','admin'),('dn220989lms','admin'),('dn221290kea','admin'),('dn230892gmg','admin'),('dn250480tsa','admin'),('dn250790kva','admin'),('dn300189mis','admin'),('dn310784nvv','admin'),('dp310588kja','admin'),('nk061183cvj','admin'),('nk070281sjv','admin'),('roma','admin'),('Seric','admin'),('sniper89','admin'),('yura','admin'),('diver','admin-gui'),('dmitry','admin-gui'),('dn010190gma','admin-gui'),('dn021190saj','admin-gui'),('dn030681gae','admin-gui'),('dn041189lvs','admin-gui'),('dn060988mav','admin-gui'),('dn080692zaa','admin-gui'),('dn100192pja','admin-gui'),('dn101188lvv','admin-gui'),('dn110793tas','admin-gui'),('dn130490tnv','admin-gui'),('dn130591bav','admin-gui'),('dn160588bai','admin-gui'),('dn160889gas','admin-gui'),('dn170684sra','admin-gui'),('dn180989mis','admin-gui'),('dn190491kmg','admin-gui'),('dn200978lak','admin-gui'),('dn220989lms','admin-gui'),('dn221290kea','admin-gui'),('dn230892gmg','admin-gui'),('dn250480tsa','admin-gui'),('dn250790kva','admin-gui'),('dn300189mis','admin-gui'),('dn310784nvv','admin-gui'),('dp310588kja','admin-gui'),('nk061183cvj','admin-gui'),('nk070281sjv','admin-gui'),('roma','admin-gui'),('Seric','admin-gui'),('sniper89','admin-gui'),('yura','admin-gui'),('diver','admin-script'),('dmitry','admin-script'),('dn010190gma','admin-script'),('dn021190saj','admin-script'),('dn030681gae','admin-script'),('dn041189lvs','admin-script'),('dn060988mav','admin-script'),('dn080692zaa','admin-script'),('dn100192pja','admin-script'),('dn101188lvv','admin-script'),('dn110793tas','admin-script'),('dn130490tnv','admin-script'),('dn130591bav','admin-script'),('dn150190kad','admin-script'),('dn160588bai','admin-script'),('dn160889gas','admin-script'),('dn170684sra','admin-script'),('dn180989mis','admin-script'),('dn190491kmg','admin-script'),('dn200978lak','admin-script'),('dn220989lms','admin-script'),('dn221290kea','admin-script'),('dn230892gmg','admin-script'),('dn250480tsa','admin-script'),('dn250790kva','admin-script'),('dn300189mis','admin-script'),('dn310784nvv','admin-script'),('dp310588kja','admin-script'),('nk061183cvj','admin-script'),('nk070281sjv','admin-script'),('roma','admin-script'),('Seric','admin-script'),('sniper89','admin-script'),('yura','admin-script'),('diver','manager'),('dn021190saj','manager'),('dn030681gae','manager'),('dn041189lvs','manager'),('dn080692zaa','manager'),('dn100192pja','manager'),('dn110793tas','manager'),('dn130490tnv','manager'),('dn130591bav','manager'),('dn160588bai','manager'),('dn160889gas','manager'),('dn200978lak','manager'),('dn230892gmg','manager'),('dn250480tsa','manager'),('dn300189mis','manager'),('dn310784nvv','manager'),('sniper89','manager'),('yura','manager'),('diver','manager-gui'),('dn021190saj','manager-gui'),('dn030681gae','manager-gui'),('dn041189lvs','manager-gui'),('dn080692zaa','manager-gui'),('dn100192pja','manager-gui'),('dn110793tas','manager-gui'),('dn130490tnv','manager-gui'),('dn130591bav','manager-gui'),('dn160588bai','manager-gui'),('dn160889gas','manager-gui'),('dn200978lak','manager-gui'),('dn230892gmg','manager-gui'),('dn250480tsa','manager-gui'),('dn300189mis','manager-gui'),('dn310784nvv','manager-gui'),('sniper89','manager-gui'),('yura','manager-gui'),('diver','manager-jmx'),('dmitry','manager-jmx'),('dn010190gma','manager-jmx'),('dn021190saj','manager-jmx'),('dn030681gae','manager-jmx'),('dn041189lvs','manager-jmx'),('dn060988mav','manager-jmx'),('dn080692zaa','manager-jmx'),('dn100192pja','manager-jmx'),('dn101188lvv','manager-jmx'),('dn110793tas','manager-jmx'),('dn130490tnv','manager-jmx'),('dn130591bav','manager-jmx'),('dn150190kad','manager-jmx'),('dn160588bai','manager-jmx'),('dn160889gas','manager-jmx'),('dn170684sra','manager-jmx'),('dn180989mis','manager-jmx'),('dn190491kmg','manager-jmx'),('dn200978lak','manager-jmx'),('dn220989lms','manager-jmx'),('dn221290kea','manager-jmx'),('dn230892gmg','manager-jmx'),('dn250480tsa','manager-jmx'),('dn250790kva','manager-jmx'),('dn300189mis','manager-jmx'),('dn310784nvv','manager-jmx'),('dp310588kja','manager-jmx'),('nk061183cvj','manager-jmx'),('nk070281sjv','manager-jmx'),('roma','manager-jmx'),('Seric','manager-jmx'),('sniper89','manager-jmx'),('yura','manager-jmx'),('diver','manager-script'),('dmitry','manager-script'),('dn010190gma','manager-script'),('dn021190saj','manager-script'),('dn030681gae','manager-script'),('dn041189lvs','manager-script'),('dn060988mav','manager-script'),('dn080692zaa','manager-script'),('dn100192pja','manager-script'),('dn101188lvv','manager-script'),('dn110793tas','manager-script'),('dn130490tnv','manager-script'),('dn130591bav','manager-script'),('dn160588bai','manager-script'),('dn160889gas','manager-script'),('dn170684sra','manager-script'),('dn180989mis','manager-script'),('dn190491kmg','manager-script'),('dn200978lak','manager-script'),('dn220989lms','manager-script'),('dn221290kea','manager-script'),('dn230892gmg','manager-script'),('dn250480tsa','manager-script'),('dn250790kva','manager-script'),('dn300189mis','manager-script'),('dn310784nvv','manager-script'),('dp310588kja','manager-script'),('nk061183cvj','manager-script'),('nk070281sjv','manager-script'),('roma','manager-script'),('Seric','manager-script'),('sniper89','manager-script'),('yura','manager-script'),('diver','manager-status'),('dmitry','manager-status'),('dn010190gma','manager-status'),('dn021190saj','manager-status'),('dn030681gae','manager-status'),('dn041189lvs','manager-status'),('dn060988mav','manager-status'),('dn080692zaa','manager-status'),('dn100192pja','manager-status'),('dn101188lvv','manager-status'),('dn110793tas','manager-status'),('dn130490tnv','manager-status'),('dn130591bav','manager-status'),('dn150190kad','manager-status'),('dn160588bai','manager-status'),('dn160889gas','manager-status'),('dn170684sra','manager-status'),('dn180989mis','manager-status'),('dn190491kmg','manager-status'),('dn200978lak','manager-status'),('dn220989lms','manager-status'),('dn221290kea','manager-status'),('dn230892gmg','manager-status'),('dn250480tsa','manager-status'),('dn250790kva','manager-status'),('dn300189mis','manager-status'),('dn310784nvv','manager-status'),('dp310588kja','manager-status'),('nk061183cvj','manager-status'),('nk070281sjv','manager-status'),('roma','manager-status'),('Seric','manager-status'),('sniper89','manager-status'),('yura','manager-status'),('diver','tomcat'),('dmitry','tomcat'),('dn010190gma','tomcat'),('dn021190saj','tomcat'),('dn030681gae','tomcat'),('dn041189lvs','tomcat'),('dn060988mav','tomcat'),('dn080692zaa','tomcat'),('dn100192pja','tomcat'),('dn101188lvv','tomcat'),('dn110793tas','tomcat'),('dn130490tnv','tomcat'),('dn130591bav','tomcat'),('dn150190kad','tomcat'),('dn160588bai','tomcat'),('dn160889gas','tomcat'),('dn170684sra','tomcat'),('dn180989mis','tomcat'),('dn190491kmg','tomcat'),('dn200978lak','tomcat'),('dn220989lms','tomcat'),('dn221290kea','tomcat'),('dn230892gmg','tomcat'),('dn250480tsa','tomcat'),('dn250790kva','tomcat'),('dn300189mis','tomcat'),('dn310784nvv','tomcat'),('dp310588kja','tomcat'),('nk061183cvj','tomcat'),('nk070281sjv','tomcat'),('roma','tomcat'),('Seric','tomcat'),('sniper89','tomcat'),('yura','tomcat');






SELECT * FROM tomcat_users_roles WHERE user_name = 'dn200978lak';
SELECT * FROM tomcat_users WHERE user_name = 'dn200978lak';
SELECT * FROM tomcat_users WHERE group_name = 'admin' GROUP BY user_fio;
SELECT user_name, user_fio, user_ldap, email, group_name, ip FROM tomcat_users WHERE group_name IN (SELECT role_name FROM tomcat_roles WHERE role_name = 'admin');


SELECT role_name, role_description, group_description FROM tomcat_roles, tomcat_users_group;
SELECT role_name, role_description, group_description FROM tomcat_roles, tomcat_users_group GROUP BY role_name;
SELECT role_name, role_description, group_description FROM tomcat_roles, tomcat_users_group GROUP BY role_description;
SELECT role_name, role_description, group_description FROM tomcat_roles, tomcat_users_group GROUP BY group_description;
SELECT count(role_name) AS all_roles FROM tomcat_roles, tomcat_users_group;
SELECT role_name FROM tomcat_roles, tomcat_users_group g ORDER BY role_name ASC ;
SELECT role_name FROM tomcat_roles, tomcat_users_group ORDER BY role_name DESC ;
SELECT r.role_name FROM tomcat_roles r, tomcat_users_group g ORDER BY r.role_name ASC ;
SELECT r.role_name FROM tomcat_roles r, tomcat_users_group g ORDER BY r.role_name DESC LIMIT 10;


SELECT r.role_name, g.group_name FROM tomcat_roles r LEFT JOIN tomcat_users_group g ON r.role_name=g.group_name ;
SELECT r.role_name AS ROLES, g.group_name AS GROUPS FROM tomcat_roles r LEFT JOIN tomcat_users_group g ON r.role_name=g.group_name ;
SELECT r.role_name AS ROLES, g.group_name AS GROUPS FROM tomcat_roles r LEFT OUTER JOIN tomcat_users_group g ON r.role_name=g.group_name ;
SELECT r.role_name AS ROLES, g.group_name AS GROUPS FROM tomcat_roles r RIGHT JOIN tomcat_users_group g ON r.role_name=g.group_name ;
SELECT r.role_name AS ROLES, g.group_name AS GROUPS FROM tomcat_roles r RIGHT OUTER JOIN tomcat_users_group g ON r.role_name=g.group_name ;
SELECT r.role_name AS ROLES, g.group_name AS GROUPS, count(g.group_name) AS equals FROM tomcat_roles r LEFT JOIN tomcat_users_group g ON r.role_name=g.group_name ;


SELECT ur.* FROM tomcat_users_roles ur WHERE ur.user_name = 'dn200978lak';
SELECT ur.* FROM tomcat_users_roles ur WHERE ur.user_name = 'dn200978lak' GROUP BY user_name;
SELECT ur.user_name, count(ur.user_name) AS FINDs FROM tomcat_users_roles ur WHERE ur.user_name = 'dn200978lak';
SELECT ur.role_name, count(ur.role_name) AS FINDs FROM tomcat_users_roles ur WHERE ur.role_name = 'admin';
SELECT ur.user_name FROM tomcat_users_roles ur GROUP BY ur.user_name;
SELECT ur.user_name, count(ur.user_name) ROLES FROM tomcat_users_roles ur GROUP BY ur.user_name;

SELECT ur.user_name, count(ur.user_name) AS ROLES FROM tomcat_users_roles ur GROUP BY ur.user_name;






SELECT ur.user_name FROM tomcat_users_roles ur GROUP BY ur.user_name;
#SELECT ur.user_name FROM tomcat_users_roles ur WHERE ur.user_name IN (SELECT user_name FROM tomcat_users_roles GROUP BY user_name);
SELECT ur.user_name FROM tomcat_users_roles ur WHERE ur.user_name = 'dn200978lak';


SELECT count(ur.user_name) AS ROLES FROM tomcat_users_roles ur GROUP BY ur.user_name;
SELECT count(ur.user_name) AS ROLES FROM tomcat_users_roles ur WHERE ur.user_name = 'dn200978lak' GROUP BY ur.user_name;
SELECT ur.user_name, count(ur.user_name) AS ROLES FROM tomcat_users_roles ur WHERE ur.user_name = 'dn200978lak' GROUP BY ur.user_name;
SELECT ur.user_name, ",", count(ur.user_name) AS ROLES FROM tomcat_users_roles ur WHERE ur.user_name = 'dn200978lak' GROUP BY ur.user_name;
SELECT ur.user_name, count(ur.user_name) AS ROLES FROM tomcat_users_roles ur GROUP BY ur.user_name;

CREATE TABLE tmp (
  user_name varchar(20) NOT NULL PRIMARY KEY,
  roles INTEGER(2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 ;

INSERT INTO `tmp` VALUES ('dn200978lak', (SELECT count(ur.user_name) AS ROLES FROM tomcat_users_roles ur WHERE ur.user_name = 'dn200978lak' GROUP BY ur.user_name));






EXPLAIN EXTENDED SELECT ur.user_name FROM tomcat_users_roles ur GROUP BY ur.user_name;
EXPLAIN EXTENDED SELECT ur.user_name FROM tomcat_users_roles ur;

SELECT ur.user_name, count(ur.role_name) FROM tomcat_users_roles ur GROUP BY ur.user_name ORDER BY count(ur.role_name) ASC ;
SELECT ur.user_name, count(ur.role_name) FROM tomcat_users_roles ur GROUP BY ur.user_name ORDER BY count(ur.role_name) DESC ;
SELECT ur.user_name, count(ur.role_name) FROM tomcat_users_roles ur GROUP BY ur.user_name ORDER BY count(ur.role_name) ASC LIMIT 1 ;
SELECT ur.user_name, count(ur.role_name) FROM tomcat_users_roles ur GROUP BY ur.user_name ORDER BY count(ur.role_name) DESC LIMIT 1 ;

SELECT ur.user_name, count(ur.role_name) FROM tomcat_users_roles ur WHERE ur.role_name='admin' GROUP BY ur.user_name ;
SELECT ur.user_name, count(ur.role_name) FROM tomcat_users_roles ur WHERE 'admin'=ur.role_name GROUP BY ur.user_name ;
#SELECT ur.user_name, count(ur.role_name) FROM tomcat_users_roles ur WHERE 9 IN (count(ur.role_name)) GROUP BY ur.user_name ;



Пример: задача на вычисление самого успешного преподавателя в школе.
0) Школа - таблица 'TABLE'
1) Каждый учитель преподает один из предметов для учеников в школе - поле 'TEACHER'
2) Каждый предмет посещает много учеников - поле 'STUDENT'
3) Ученики получают оценку по предмету - поле 'VALUE'
----------------------------------------------------------------------------------
1. первый (или вложенный) запрос группирует по полю 'TEACHER', сортирует строки по среднему балу AVG() в поле 'VALUE' и возвращает только одну запись (LIMIT 1) = TEACHER_ID
2. второй запрос находит запись по TEACHER_ID и возвращает полную строку

SELECT * FROM tomcat_users u WHERE u.user_name=(SELECT ur.user_name FROM tomcat_users_roles ur GROUP BY ur.user_name ORDER BY count(ur.role_name) ASC LIMIT 1);
SELECT * FROM tomcat_users u WHERE u.user_name=(SELECT ur.user_name FROM tomcat_users_roles ur GROUP BY ur.user_name ORDER BY count(ur.role_name) DESC LIMIT 1);


SELECT * FROM tomcat_users WHERE user_name LIKE '%78%';
SELECT * FROM tomcat_users WHERE user_name LIKE '%78%' OR user_name LIKE '%20%';

SELECT DISTINCT tur.user_name, tur.role_name FROM tomcat_users_roles tur ;







||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||||








DROP DATABASE artifactory;

CREATE DATABASE artdb CHARACTER SET utf8 COLLATE utf8_bin;
GRANT ALL ON artdb.* TO 'root'@'localhost' IDENTIFIED BY '1111';
FLUSH PRIVILEGES;


DROP DATABASE artdb;

create database artifactory character set utf8;
GRANT SELECT,INSERT,UPDATE,DELETE,CREATE,DROP,ALTER,INDEX on artifactory.* TO 'root'@'localhost' IDENTIFIED BY '1111';
flush privileges;





mysqldump -uroot -p sonar > sonar.sql
CREATE DATABASE sonar CHARACTER SET utf8 COLLATE utf8_bin;
DROP DATABASE sonar;
CREATE DATABASE sonar;
mysqldump -uroot -p sonar < sonar.sql


mysqldump -uroot -p tomcat_realm > tomcat_realm.sql
CREATE DATABASE tomcat_realm;
mysqldump -uroot -p tomcat_realm < tomcat_realm.sql



==================================================================================================[ allow-all-remote-connections-mysql ]
http://stackoverflow.com/questions/10236000/allow-all-remote-connections-mysql

sudo mcedit /etc/mysql/my.cnf
mysql -uroot -p1111
###mysql> GRANT ALL PRIVILEGES ON sonar.* TO 'root'@10.13.71.160 IDENTIFIED BY '1111';
mysql> GRANT ALL ON *.* to root@'%' IDENTIFIED BY '1111';
/etc/init.d/mysql restart
mysql -h10.13.71.160 -uroot -p1111



==================================================================================================[ создание пользователей в MySQL ]
http://www.mysql.ru/docs/man/Adding_users.html
http://howto.memcrab.com/2012/01/mysql.html
guruadmin.ru/page/mysql-kak-sozdat-polzovatelja-v-mysql
--------------------------------------------------------------------------------------------------

mysql> SHOW GRANTS FOR 'root';
+--------------------------------------------------------------------------------------------------------------------------------+
| Grants for root@%                                                                                                              |
+--------------------------------------------------------------------------------------------------------------------------------+
| GRANT ALL PRIVILEGES ON *.* TO 'root'@'%' IDENTIFIED BY PASSWORD '*89C6B530AA78695E257E55D63C00A6EC9AD3E977' WITH GRANT OPTION |
+--------------------------------------------------------------------------------------------------------------------------------+

mysqldump -uroot -p issues > issues.sql
DROP DATABASE IF EXISTS issues;
DROP DATABASE IF EXISTS Redmine;
CREATE DATABASE issues CHARACTER SET utf8 COLLATE utf8_bin;
CREATE DATABASE Redmine CHARACTER SET utf8 COLLATE utf8_bin;
mysqldump -uroot -p issues < issues.sql
mysqldump -uroot -p Redmine < issues.sql
FLUSH PRIVILEGES;

mysql> CREATE USER 'admin'@'localhost';
mysql> GRANT RELOAD,PROCESS ON *.* TO 'admin'@'localhost';
mysql> SHOW GRANTS FOR 'admin'@'localhost';
+-----------------------------------------------------+
| Grants for admin@localhost                          |
+-----------------------------------------------------+
| GRANT RELOAD, PROCESS ON *.* TO 'admin'@'localhost' |
+-----------------------------------------------------+
mysql -uadmin

mysql> CREATE USER 'vlad'@'%' IDENTIFIED BY 'vlad';
mysql> GRANT SELECT,INSERT,UPDATE,DELETE,CREATE,DROP,ALTER ON Redmine.* TO 'vlad'@'%';
mysql> GRANT SELECT,INSERT,UPDATE,DELETE,CREATE,DROP,ALTER ON issues.* TO 'vlad'@'%';
mysql> SHOW GRANTS FOR 'vlad'@'%';
mysql> CREATE USER 'custom'@'%' IDENTIFIED BY 'custom';
mysql> GRANT SELECT ON Redmine.* TO 'custom'@'%';
mysql> GRANT SELECT ON issues.* TO 'custom'@'%';
mysql> SHOW GRANTS FOR 'custom'@'%';

mysql> CREATE USER 'dashboard_admin'@'%' IDENTIFIED BY 'vlad';
mysql> GRANT SELECT,INSERT,UPDATE,DELETE,CREATE,DROP,ALTER ON Redmine.* TO 'vlad'@'%';
mysql> GRANT SELECT,INSERT,UPDATE,DELETE,CREATE,DROP,ALTER ON issues.* TO 'vlad'@'%';
mysql> SHOW GRANTS FOR 'vlad'@'%';
+-----------------------------------------------------------------------------------------------------+
| Grants for vlad@%                                                                                   |
+-----------------------------------------------------------------------------------------------------+
| GRANT USAGE ON *.* TO 'vlad'@'%' IDENTIFIED BY PASSWORD '*432FEE6D1D2A7015E04C3525DED4E21984205512' |
| GRANT SELECT, INSERT, UPDATE, DELETE, CREATE, DROP ON `Redmine`.* TO 'vlad'@'%'                     |
+-----------------------------------------------------------------------------------------------------+

Аккаунт 'admin'@'localhost' может использоваться только при подключении с localhost.
Аккаунту 'admin'@'localhost' не задан пароль. Этот аккаунт может использоваться только администратором и только с локального хоста. Ему разрешены административные операторы RELOAD и PROCESS.

Аккаунт 'vlad'@'%' использует специальный параметр '%' вместо адреса хоста, благодаря этому он может подключатся к серверу с любого адреса.


DROP DATABASE IF EXISTS admin_acc;
CREATE DATABASE admin_acc CHARACTER SET utf8 COLLATE utf8_bin;
FLUSH PRIVILEGES;

DROP DATABASE IF EXISTS dashboard_dev;
CREATE DATABASE dashboard_dev CHARACTER SET utf8 COLLATE utf8_bin;
FLUSH PRIVILEGES;
use dashboard_dev


mysql> CREATE USER 'vea'@'%' IDENTIFIED BY 'vea';
mysql> GRANT SELECT,INSERT,UPDATE,DELETE,CREATE,DROP,ALTER ON admin_acc.* TO 'vea'@'%';
mysql> SHOW GRANTS FOR 'vea'@'%';

mysql> DROP USER 'metric_user'@'%';
mysql> CREATE USER 'metric_user'@'%' IDENTIFIED BY 'metric';
mysql> GRANT SELECT,INSERT,UPDATE,DELETE,CREATE,DROP,ALTER ON Redmine.* TO 'metric_user'@'%';
mysql> GRANT SELECT,INSERT,UPDATE,DELETE,CREATE,DROP,ALTER ON issues.* TO 'metric_user'@'%';
mysql> GRANT SELECT,INSERT,UPDATE,DELETE,CREATE,DROP,ALTER ON dashboard.* TO 'metric_user'@'%';
mysql> GRANT SELECT,INSERT,UPDATE,DELETE,CREATE,DROP,ALTER ON dashboard_dev.* TO 'metric_user'@'%';
mysql> GRANT INDEX ON dashboard.* TO 'metric_user'@'%';
mysql> GRANT INDEX ON dashboard_dev.* TO 'metric_user'@'%';
mysql> SHOW GRANTS FOR 'metric_user'@'%';


mysql> DROP USER 'ticket_user'@'%';
mysql> CREATE USER 'ticket_user'@'%' IDENTIFIED BY 'ticket';
mysql> GRANT SELECT ON dashboard.* TO 'ticket_user'@'%';
mysql> SHOW GRANTS FOR 'ticket_user'@'%';

mysql> DROP USER 'ticket_user'@'%';
mysql> CREATE USER 'ticket_user'@'%' IDENTIFIED BY 'ticket';
mysql> GRANT SELECT,INSERT,UPDATE,DELETE,CREATE,DROP,ALTER ON dashboard.* TO 'ticket_user'@'%';
mysql> SHOW GRANTS FOR 'ticket_user'@'%';


DROP DATABASE mydb1;
CREATE DATABASE mydb1;

mysql> DROP USER 'mydb1'@'%';
mysql> CREATE USER 'mydb1'@'%' IDENTIFIED BY 'mydb1';
mysql> GRANT SELECT,INSERT,UPDATE,DELETE,CREATE,DROP,ALTER ON mydb1.* TO 'mydb1'@'%';
mysql> SHOW GRANTS FOR 'mydb1'@'%';


mysql> CREATE USER 'andrii'@'%' IDENTIFIED BY 'admin';
mysql> GRANT SELECT,INSERT,UPDATE,DELETE,CREATE,DROP,ALTER ON andrii.* TO 'andrii'@'%';
mysql> SHOW GRANTS FOR 'andrii'@'%';

CREATE DATABASE andrii;



mysqldump -uroot -p issues all_pays_dev > all_pays_dev.sql
mysqldump -uroot -p issues bank > bank.sql
mysqldump -uroot -p issues channel > channel.sql
mysqldump -uroot -p issues hourly_threshold > hourly_threshold.sql
mysqldump -uroot -p issues issue > issue.sql
mysqldump -uroot -p issues mass_pays_auth > mass_pays_auth.sql
mysqldump -uroot -p issues project > project.sql
mysqldump -uroot -p issues status > status.sql
mysqldump -uroot -p issues task > task.sql
mysqldump -uroot -p issues task_results > task_results.sql
mysqldump -uroot -p issues ticket_items_dev > ticket_items_dev.sql
mysqldump -uroot -p issues tracker > tracker.sql
mysqldump -uroot -p issues version > version.sql

mysqldump -uroot -p issues all_pays_dev < all_pays_dev.sql
mysqldump -uroot -p issues bank < bank.sql
mysqldump -uroot -p issues channel < channel.sql
mysqldump -uroot -p issues hourly_threshold < hourly_threshold.sql
mysqldump -uroot -p issues issue < issue.sql
mysqldump -uroot -p issues mass_pays_auth < mass_pays_auth.sql
mysqldump -uroot -p issues project < project.sql
mysqldump -uroot -p issues status < status.sql
mysqldump -uroot -p issues task > task.sql
mysqldump -uroot -p issues task_results < task_results.sql
mysqldump -uroot -p issues ticket_items_dev < ticket_items_dev.sql
mysqldump -uroot -p issues tracker < tracker.sql
mysqldump -uroot -p issues version < version.sql







========================================================================
mysqldump -uroot -p issues all_pays_dev > all_pays_dev.sql
mysqldump -uroot -p issues bank > bank.sql
mysqldump -uroot -p issues channel > channel.sql
mysqldump -uroot -p issues debtload_items > debtload_items.sql
mysqldump -uroot -p issues hourly_threshold > hourly_threshold.sql
mysqldump -uroot -p issues issue > issue.sql
mysqldump -uroot -p issues mass_pays_auth > mass_pays_auth.sql
mysqldump -uroot -p issues project > project.sql
mysqldump -uroot -p issues status > status.sql
mysqldump -uroot -p issues task > task.sql
mysqldump -uroot -p issues task_results > task_results.sql
mysqldump -uroot -p issues ticket_items_dev > ticket_items_dev.sql
mysqldump -uroot -p issues tracker > tracker.sql
mysqldump -uroot -p issues version > version.sql

mysqldump -uroot -p Redmine all_pays_dev < all_pays_dev.sql
mysqldump -uroot -p Redmine bank < bank.sql
mysqldump -uroot -p Redmine channel < channel.sql
mysqldump -uroot -p Redmine debtload_items < debtload_items.sql
mysqldump -uroot -p Redmine hourly_threshold < hourly_threshold.sql
mysqldump -uroot -p Redmine issue < issue.sql
mysqldump -uroot -p Redmine mass_pays_auth < mass_pays_auth.sql
mysqldump -uroot -p Redmine project < project.sql
mysqldump -uroot -p Redmine status < status.sql
mysqldump -uroot -p Redmine task < task.sql
mysqldump -uroot -p Redmine task_results < task_results.sql
mysqldump -uroot -p Redmine ticket_items_dev < ticket_items_dev.sql
mysqldump -uroot -p Redmine tracker < tracker.sql
mysqldump -uroot -p Redmine version < version.sql




mysqladmin -uroot -p1111 flush-hosts
..............................................................................
http://www.mysql.ru/docs/man/Blocked_host.html
http://usemytips.blogspot.com/2009/05/unblock-with-mysqladmin-flush-hosts.html
http://dev.mysql.com/doc/refman/5.5/en/too-many-connections.html
http://guruadmin.ru/page/izmenjaem-opciju-max_connections-v-mysql
http://dev.mysql.com/doc/refman/5.5/en/blocked-host.html
..............................................................................
mysql --version

mysql -uroot -p1111
mysql> show variables like "max_connections";
+-----------------+-------+
| Variable_name   | Value |
+-----------------+-------+
| max_connections | 151   |
+-----------------+-------+
1 row in set (0.00 sec)

mysql> show variables like "max_connect_errors";
+--------------------+-------+
| Variable_name      | Value |
+--------------------+-------+
| max_connect_errors | 10000 |
+--------------------+-------+
1 row in set (0.00 sec)

SET GLOBAL max_connections = 10000;
SET GLOBAL max_connect_errors=10000;

#[mysqld]
#...
#max_connections = 10000
#
#/etc/init.d/mysql restart









mysqldump -uroot -p sonar > sonar.sql
DROP DATABASE IF EXISTS sonar;
CREATE DATABASE sonar;





























