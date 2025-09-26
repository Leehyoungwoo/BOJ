-- 코드를 입력하세요
select 
    b.TITLE as TITLE, 
    b.BOARD_ID as BOARD_ID, 
    r.REPLY_ID as REPLY_ID, 
    r.WRITER_ID as WRITER_ID, 
    r.CONTENTS as CONTENTS, 
    date_format(r.CREATED_DATE, '%Y-%m-%d') as CREATED_DATE
from USED_GOODS_REPLY r
left join USED_GOODS_BOARD b
    on b.BOARD_ID = r.BOARD_ID
where date_format(b.created_date, '%Y-%m') = '2022-10'
order by r.CREATED_DATE asc, b.title asc;