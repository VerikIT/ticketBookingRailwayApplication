-- trains to
insert into trains (number,train_name,full_price)values ('105К','Київ-Одеса',380);
insert into trains (number,train_name,full_price)values ('022Л','Львів-Харків',550);
insert into trains (number,train_name,full_price)values ('038К','Київ-Запоріжжя',450);
insert into trains (number,train_name,full_price)values ('110Л','Львів-Миколаїв',1000);
insert into trains (number,train_name,full_price)values ('062Ш','Одеса-Дніпро',900);
-- trains from
insert into trains (number,train_name,full_price)values ('106Ш','Одеса-Київ',380);
insert into trains (number,train_name,full_price)values ('021О','Харків-Львів',550);
insert into trains (number,train_name,full_price)values ('038П','Запоріжжя-Київ',450);
insert into trains (number,train_name,full_price)values ('109Ш','Миколаїв-Львів',1000);
insert into trains (number,train_name,full_price)values ('053П','Дніпро-Одеса',900);

--stations
--train_id 1,105К,Київ-Одеса
insert into stations (city,train_id,time)values ('Київ',1,'21:17');
insert into stations (city,train_id,time)values ('Вінниця',1,'00:15');
insert into stations (city,train_id,time)values ('Одеса',1,'06:15');
--train_id 2,022Л,ЛЬвів-Харків
insert into stations (city,train_id,time)values ('Львів',2,'18:35');
insert into stations (city,train_id,time)values ('Тернопіль',2,'20:30');
insert into stations (city,train_id,time)values ('Хмельницький',2,'22:20');
insert into stations (city,train_id,time)values ('Вінниця',2,'01:15');
insert into stations (city,train_id,time)values ('Київ',2,'03:02');
insert into stations (city,train_id,time)values ('Полтава',2,'07:37');
insert into stations (city,train_id,time)values ('Харків',2,'10:18');
--train_id 3,038К,Київ-Запоріжжя
insert into stations (city,train_id,time)values ('Київ',3,'21:18');
insert into stations (city,train_id,time)values ('Дніпро',3,'05:08');
insert into stations (city,train_id,time)values ('Запоріжжя',3,'07:38');
--train_id 4,110Л,ЛЬвів-Миколаїв
insert into stations (city,train_id,time)values ('Львів',4,'16:17');
insert into stations (city,train_id,time)values ('Тернопіль',4,'18:22');
insert into stations (city,train_id,time)values ('Хмельницький',4,'20:21');
insert into stations (city,train_id,time)values ('Вінниця',4,'23:04');
insert into stations (city,train_id,time)values ('Миколаїв',4,'10:57');
--train_id 5,062Ш,Одеса-Дніпро
insert into stations (city,train_id,time)values ('Одеса',5,'18:45');
insert into stations (city,train_id,time)values ('Кропивницький',5,'02:46');
insert into stations (city,train_id,time)values ('Кривий Ріг',5,'07:30');
insert into stations (city,train_id,time)values ('Дніпро',5,'11:03');
--train_id 6,106Ш,Одеса-Київ
insert into stations (city,train_id,time)values ('Одеса',6,'21:32');
insert into stations (city,train_id,time)values ('Вінниця',6,'03:42');
insert into stations (city,train_id,time)values ('Київ',6,'06:52');
--train_id 7,021О,Харків-Львів
insert into stations (city,train_id,time)values ('Харків',7,'19:44');
insert into stations (city,train_id,time)values ('Полтава',7,'21:55');
insert into stations (city,train_id,time)values ('Київ',7,'02:37');
insert into stations (city,train_id,time)values ('Вінниця',7,'05:43');
insert into stations (city,train_id,time)values ('Хмельницький',7,'07:39');
insert into stations (city,train_id,time)values ('Тернопіль',7,'09:31');
insert into stations (city,train_id,time)values ('Львів',7,'11:40');
--train_id 8,038П,Запоріжжя-Київ
insert into stations (city,train_id,time)values ('Запоріжжя',8,'20:35');
insert into stations (city,train_id,time)values ('Дніпро',8,'22:13');
insert into stations (city,train_id,time)values ('Київ',8,'05:57');
--train_id 9,109Ш,Миколаїв-Львів
insert into stations (city,train_id,time)values ('Миколаїв',9,'16:25');
insert into stations (city,train_id,time)values ('Вінниця',9,'03:23');
insert into stations (city,train_id,time)values ('Хмельницький',9,'06:05');
insert into stations (city,train_id,time)values ('Тернопіль',9,'08:10');
insert into stations (city,train_id,time)values ('Львів',9,'10:27');
--train_id 10,062Ш,Одеса-Дніпро
insert into stations (city,train_id,time)values ('Дніпро',10,'17:30');
insert into stations (city,train_id,time)values ('Кривий Ріг',10,'21:51');
insert into stations (city,train_id,time)values ('Кропивницький',10,'02:30');
insert into stations (city,train_id,time)values ('Одеса',10,'10:54');











