ALTER TABLE
department
ADD COLUMN dean VARCHAR(50) DEFAULT 'dean' NOT NULL;

UPDATE department
SET dean='Makarov'
WHERE Department.id=1;

UPDATE department
SET dean='Levchenko'
WHERE department.id=2;

UPDATE department
SET dean='Belkov'
WHERE department.id=3;