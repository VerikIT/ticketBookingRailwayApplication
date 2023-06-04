-- trains
insert into trains (number,train_name)values ('105К','Київ-Одеса');
insert into trains (number,train_name)values ('022Л','ЛЬвів-Харків');
insert into trains (number,train_name)values ('038К','Київ-Запоріжжя');
--stations
--train_id 1,105К,Київ-Одеса
insert into stations (city,train_id,time)values ('Київ',1,'21:17');
insert into stations (city,train_id,time)values ('Вінниця',1,'00:15');
insert into stations (city,train_id,time)values ('Одеса',1,'06:15');
--train_id 2,022Л,ЛЬвів-Харків
insert into stations (city,train_id,time)values ('Львів',2,'18:35');
insert into stations (city,train_id,time)values ('Тернопіль',2,'20:30');
insert into stations (city,train_id,time)values ('Хмельницький',2,'22:20');
insert into stations (city,train_id,time)values ('Київ',2,'03:02');
insert into stations (city,train_id,time)values ('Полтава',2,'07:37');
insert into stations (city,train_id,time)values ('Харків',2,'10:18');
--train_id 3,038К,Київ-Запоріжжя
insert into stations (city,train_id,time)values ('Київ',3,'21:18');
insert into stations (city,train_id,time)values ('Дніпро',3,'05:08');
insert into stations (city,train_id,time)values ('Запоріжжя',3,'07:38');





