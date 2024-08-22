-- 코드를 작성해주세요
select id,
    case
        when SIZE_OF_COLONY <= 100 then 'LOW'
        when SIZE_OF_COLONY <= 1000 then 'MEDIUM'
        else 'HIGH'
    END AS SIZE
from ECOLI_DATA
ORDER BY ID;