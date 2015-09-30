INSERT INTO department(id, name)
VALUES
	(1, 'fit&cs'),
	(2, 'rtf'),
	(3, 'ftgo');

INSERT INTO chair(id, name, department_id)
VALUES
	(1, 'ict', 1),
	(2, 'aso&u', 1),
	(3, 'cdi', 2),
	(4, 'rb', 3);

INSERT INTO class(id, name, department_id)
VALUES
	(1, 'v-411', 1),
	(2, 'asu-411', 1),
	(3, 'rzi-312', 2),
	(4, 'ka-213', 2),
	(5, 'bu-312', 3);

INSERT INTO student(id, first_name, last_name, is_head, group_id, chair_id)
VALUES
	(1, 'viktor', 'pisarenko', FALSE, 1, 1),
	(2, 'roman', 'nabiullin', FALSE, 1, 1),
	(3, 'dmitriy', 'shamrik', TRUE, 1, 1),
	(4, 'ilya', 'seleznev', FALSE, 1, 1),

	(5, 'vasya', 'pupkin', TRUE, 2, 2),
	(6, 'manya', 'pupkin', FALSE, 2, 2),
	(7, 'petya', 'kukuev', FALSE, 2, 2),
	(8, 'tolya', 'berezkin', FALSE, 2, 2),

	(9, 'nikita', 'storozhenko', FALSE, 3, 3),
	(10, 'alina', 'goleva', TRUE, 3, 3),
	(11, 'savva', 'mamantov', FALSE, 3, 3),
	(12, 'vadim', 'skorobogatko', FALSE, 3, 3),
	(21, 'qwe', 'sed', FALSE, 3, 3),

	(13, 'alexey', 'vezner', TRUE, 4, 3),
	(14, 'dorhan', 'tortaev', FALSE, 4, 3),
	(15, 'anton', 'bryakov', FALSE, 4, 3),
	(16, 'natalya', 'senchenkova', FALSE, 4, 3),

	(17, 'boris', 'britva', FALSE, 5, NULL),
	(18, 'igor', 'ivolga', FALSE, 5, NULL),
	(19, 'ivan', 'balalayka', FALSE, 5, NULL),
	(20, 'semen', 'yozh', TRUE, 5, NULL);