-- 코드를 입력하세요
SELECT b.Book_id, a.AUTHOR_NAME, date_format(b.PUBLISHED_DATE, '%Y-%m-%d') 
from BOOK b join Author a 
on b.AUTHOR_ID = a.AUTHOR_ID
where CATEGORY = '경제'
order by b.PUBLISHED_DATE;