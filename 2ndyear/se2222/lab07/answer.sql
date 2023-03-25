--question 1--
select ENAME, DEPTNO, HIREDATE from EMP;

--question 2--
select DEPTNO, LOC from DEPT;

--question 3--
select JOB from EMP;

--question 4--
select distinct JOB from EMP;

--question 5--
select ENAME from EMP where JOB="CLERK";

--question 6--
select ENAME, SAL from EMP where JOB="CLERK" and SAL > 1000;

--question 7--
select ENAME, SAL, COMM from EMP where COMM is not NULL;

--question 8--
select ENAME as 'NAME', SAL as 'SALARY', SAL * 12 as 'YEARLY' from EMP;

--question 9--
select ENAME from EMP where ENAME LIKE 'A%';

--question 10--
select ENAME from EMP where ENAME LIKE '_A%';

--question 11--
select distinct JOB from EMP where JOB LIKE '%MAN';

--question 12--
select ENAME, SAL from EMP ORDER BY SAL DESC;

--question 13--
select ENAME, JOB from EMP ORDER BY SAL ASC;

--question 14--
select ENAME, JOB, SAL from EMP where SAL >= 2000 and SAL <= 3000;

--question 15--
select ENAME, JOB, SAL from EMP where SAL != 1000 and SAL != 3000 and SAL != 5000;