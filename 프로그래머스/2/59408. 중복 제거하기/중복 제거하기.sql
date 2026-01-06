select count(distinct(name)) as "count"
from ANIMAL_INS
where name is not null;