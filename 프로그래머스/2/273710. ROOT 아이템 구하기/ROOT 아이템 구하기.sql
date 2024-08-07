-- 코드를 작성해주세요
select i.ITEM_ID, i.ITEM_NAME
from ITEM_INFO i 
join ITEM_TREE t
on i.ITEM_ID = t.ITEM_ID
where t.PARENT_ITEM_ID is null
order by item_id;