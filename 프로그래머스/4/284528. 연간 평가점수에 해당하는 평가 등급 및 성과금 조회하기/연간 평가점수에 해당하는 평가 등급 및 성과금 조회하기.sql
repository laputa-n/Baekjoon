SELECT EMP_NO,
        EMP_NAME,
        CASE
            WHEN AVG(SCORE) >= 96 THEN 'S'
            WHEN AVG(SCORE) >= 90 THEN 'A'
            WHEN AVG(SCORE) >= 80 THEN 'B'
            ELSE 'C'
        END AS GRADE,
        CASE
            WHEN AVG(SCORE) >= 96 THEN 0.2 * SAL
            WHEN AVG(SCORE) >= 90 THEN 0.15 * SAL
            WHEN AVG(SCORE) >= 80 THEN 0.1 * SAL
            ELSE 0
        END AS BONUS
FROM HR_EMPLOYEES
NATURAL JOIN HR_GRADE
GROUP BY EMP_NO
ORDER BY 1
 