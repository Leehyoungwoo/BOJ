select flavor
from FIRST_HALF
group by flavor
having sum(TOTAL_ORDER)
order by sum(TOTAL_ORDER) desc, SHIPMENT_ID;