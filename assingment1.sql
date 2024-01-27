USE assignment;
SELECT * FROM employee;

SELECT job, deptId FROM employee
WHERE deptId = 10 OR deptId = 30;

SELECT DISTINCT(job) FROM employee;

SELECT deptId FROM employee
ORDER BY deptId ASC; 

SELECT deptId FROM employee
ORDER BY deptId DESC;

SELECT ename, hireDate FROM employee
WHERE hireDate <= '1981-01-01';

SELECT empno,ename,salary FROM employee
ORDER BY salary ASC;

SELECT empno, ename, salary FROM employee
WHERE mgr = 7698;

SELECT * FROM employee
WHERE commission > salary;

SELECT * FROM employee
WHERE job = "Clerk" OR job = "Analyst"
ORDER BY salary DESC;

SELECT * FROM employee
WHERE salary * 12 > 22000 AND salary * 12 < 45000;

SELECT * FROM employee
WHERE LENGTH(ename) = 5 AND ename LIKE 'S%';

SELECT * FROM employee
WHERE empno NOT LIKE '78%';

SELECT * FROM employee
WHERE job = "Clerk" AND deptId = 20;

SELECT job, deptId FROM employee
WHERE deptId = 20 OR deptId = 10
ORDER BY deptId ASC;

SELECT ename FROM employee
WHERE deptId = 20; 

SELECT * FROM employee
WHERE deptId = 20 AND job 
IN (SELECT DISTINCT job FROM employee WHERE deptId = 10);

SELECT * FROM employee 
WHERE salary 
IN(SELECT salary FROM employee WHERE ename = "Ford" OR ename = "Smith")
ORDER BY salary DESC;

SELECT * FROM employee
WHERE job 
IN(SELECT job FROM employee WHERE ename = "Smith" OR ename = "Allen");

SELECT * FROM employee
WHERE deptId = 10 AND job
NOT IN(SELECT job FROM employee WHERE deptId = 30);

SELECT MAX(salary) FROM employee;
SELECT * FROM employee;

SELECT * FROM employee
WHERE salary = (SELECT MAX(salary) FROM employee);

SELECT * FROM employee
WHERE ename LIKE "%a%";

SELECT * FROM employee
WHERE salary > 1100
ORDER BY salary ASC;

SELECT salary FROM employee
WHERE ename = "Blake";

SELECT * FROM employee
WHERE salary > (SELECT salary FROM employee WHERE ename = "Blake");

CREATE VIEW v1 AS
SELECT employee.`ename`, employee.`job`, department.`Dname`, department.`location`
FROM employee
JOIN department ON employee.`deptId` = department.`deptNo`;

SELECT * FROM v1;

ALTER TABLE student
ADD pin BIGINT;