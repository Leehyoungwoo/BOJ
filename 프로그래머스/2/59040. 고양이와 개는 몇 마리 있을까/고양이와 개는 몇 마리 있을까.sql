-- 코드를 입력하세요
SELECT ANIMAL_TYPE, count(*) as count
from ANIMAL_INS
group by ANIMAL_TYPE
ORDER BY FIELD(ANIMAL_TYPE, 'Cat', 'Dog');