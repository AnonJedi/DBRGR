INSERT INTO department(id, name)
VALUES
	(1, 'FIT&CS'),
	(2, 'RTF'),
	(3, 'FTOG');

INSERT INTO chair(id, name, department_id)
VALUES
	(1, 'ICT', 1),
	(2, 'ASO&U', 1),
	(3, 'CDI', 2),
	(4, 'RB', 3);

INSERT INTO class(id, name, department_id)
VALUES
	(1, 'V-411', 1),
	(2, 'ASU-411', 1),
	(3, 'RZI-312', 2),
	(4, 'KA-213', 2),
	(5, 'BU-312', 3);

INSERT INTO student(id, first_name, last_name, is_head, group_id, chair_id)
VALUES
	(1, 'Viktor', 'Pisarenko', FALSE, 1, 1),
	(2, 'Roman', 'Nabiullin', FALSE, 1, 1),
	(3, 'Dmitriy', 'Shamrik', TRUE, 1, 1),
	(4, 'Ilya', 'Seleznev', FALSE, 1, 1),

	(5, 'Vasya', 'Pupkin', TRUE, 2, 2),
	(6, 'Manya', 'Pupkin', FALSE, 2, 2),
	(7, 'Petya', 'Kukuev', FALSE, 2, 2),
	(8, 'Tolya', 'Berezkin', FALSE, 2, 2),

	(9, 'Nikita', 'Storozhenko', FALSE, 3, 3),
	(10, 'Alina', 'Goleva', TRUE, 3, 3),
	(11, 'Savva', 'Mamantov', FALSE, 3, 3),
	(12, 'Vadim', 'Skorobogatko', FALSE, 3, 3),
	(21, 'qwe', 'Sed', FALSE, 3, 3),

	(13, 'Alexey', 'Vezner', TRUE, 4, 3),
	(14, 'Dorhan', 'Tortaev', FALSE, 4, 3),
	(15, 'Anton', 'Bryakov', FALSE, 4, 3),
	(16, 'Natalya', 'Senchenkova', FALSE, 4, 3),

	(17, 'Boris', 'Britva', FALSE, 5, NULL),
	(18, 'Igor', 'Ivolga', FALSE, 5, NULL),
	(19, 'Ivan', 'Balalayka', FALSE, 5, NULL),
	(20, 'Semen', 'Yozh', TRUE, 5, NULL);