-- 코드를 작성해주세요
select concat(length, 'cm') as MAX_LENGTH
from FISH_INFO
where LENGTH = (select max(length) from FISH_INFO);