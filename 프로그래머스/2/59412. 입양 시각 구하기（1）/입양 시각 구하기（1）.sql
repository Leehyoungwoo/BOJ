-- 코드를 입력하세요
SELECT DATE_FORMAT(DATETIME, "%H") as hour, count(*) as COUNT
from ANIMAL_OUTS
where DATE_FORMAT(DATETIME, '%H:%i') between '09:00' and '19:59'
group by DATE_FORMAT(DATETIME, '%H')
order by hour;