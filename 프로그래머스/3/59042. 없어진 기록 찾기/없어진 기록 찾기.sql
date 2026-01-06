select o.ANIMAL_ID, o.NAME
from ANIMAL_OUTS o
left join ANIMAL_INS i
on i.ANIMAL_ID = o.ANIMAL_ID
where i.DATETIME is null and o.DATETIME is not null
order by o.ANIMAL_ID, o.name;