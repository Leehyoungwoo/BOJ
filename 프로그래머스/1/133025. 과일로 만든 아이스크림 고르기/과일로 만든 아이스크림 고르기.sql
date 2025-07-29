select f.FLAVOR
from FIRST_HALF f
join ICECREAM_INFO i on f.FLAVOR = i.FLAVOR
where i.INGREDIENT_TYPE = 'fruit_based'
group by f.flavor
having sum(f.TOTAL_ORDER) >= 3000;
