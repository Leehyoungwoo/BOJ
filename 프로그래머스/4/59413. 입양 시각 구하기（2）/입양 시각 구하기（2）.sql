-- 코드를 입력하세요
with RECURSIVE hour_table as (
    SELECT 0 AS HOUR
    UNION ALL
    SELECT HOUR + 1
    FROM HOUR_TABLE
    WHERE HOUR < 23
)

SELECT h.hour as "HOUR", count(o.DATETIME) as "COUNT"
from hour_table h
left join ANIMAL_OUTS o
ON HOUR(o.DATETIME) = h.HOUR
group by h.hour
order by h.hour;