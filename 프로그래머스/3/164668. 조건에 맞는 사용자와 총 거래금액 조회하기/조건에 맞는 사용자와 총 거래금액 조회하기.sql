-- 코드를 입력하세요
SELECT 
    u.USER_ID, 
    u.NICKNAME, 
    sum(price) as TOTAL_SALES
from USED_GOODS_USER u
join USED_GOODS_BOARD b
on u.user_id = b.WRITER_ID
where b.status = 'DONE'
group by b.WRITER_ID
having TOTAL_SALES >= '700000'
order by TOTAL_SALES asc;