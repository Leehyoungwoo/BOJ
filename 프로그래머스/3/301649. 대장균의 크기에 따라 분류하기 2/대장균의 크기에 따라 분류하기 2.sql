-- 코드를 작성해주세
select ID, case
when 
    1 - PERCENT_RANK()
    OVER (
        ORDER BY SIZE_OF_COLONY
    ) <= 0.25 then 'CRITICAL'
when
    1 - PERCENT_RANK()
    OVER (
        ORDER BY SIZE_OF_COLONY
    ) <= 0.50 then 'HIGH'
when
    1 - PERCENT_RANK()
    OVER (
        ORDER BY SIZE_OF_COLONY
    ) <= 0.75 then 'MEDIUM'
when
    1 - PERCENT_RANK()
    OVER (
        ORDER BY SIZE_OF_COLONY
    ) <= 1 then 'LOW' end
    as COLONY_NAME 
from ECOLI_DATA
order by ID;
