#1
select UPPER(ENAME), LOWER(JOB) from EMP;

#2
select SUBSTRING(ENAME, 1, 3) from EMP;

#3
select CONCAT(ENAME, "/", JOB) "EMPLOYEES" from EMP;

#4
select ENAME, CHAR_LENGTH(ENAME) "characters count" from EMP;

#5
select ENAME, DNAME from EMP inner join DEPT on EMP.DEPTNO=DEPT.DEPTNO;

#6
select DNAME, ENAME from DEPT left JOIN EMP on DEPT.DEPTNO=EMP.DEPTNO;

#7
select DEPT.DEPTNO, DNAME, LOC from DEPT left JOIN EMP ON DEPT.DEPTNO=EMP.DEPTNO where ENAME is NULL;
#or
select DEPTNO, DNAME, LOC from DEPT where DEPTNO not in (select DEPTNO from EMP);

#8
select ENAME, SAL, GRADE from EMP inner join SALGRADE on SAL >= LOSAL and SAL <= HISAL;

#9
select e1.ENAME, e2.ENAME as "MANAGER" from EMP e1 inner join EMP e2 on e1.MGR=e2.EMPNO;

#10
select EMPNO, EMPNAME, JOB, SAL, COMM, DEPT.DEPTNO, DEPT.DNAME from (select * EMP where commision IS NOT NULL) e1 inner join DEPT on e1.DEPTNO=DEPT.DEPTNO;