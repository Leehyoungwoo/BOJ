select f.FLAVOR	
from FIRST_HALF f
join ICECREAM_INFO i on f.flavor = i.flavor
where f.TOTAL_ORDER > 3000
and INGREDIENT_TYPE = 'fruit_based'
order by f.TOTAL_ORDER desc;