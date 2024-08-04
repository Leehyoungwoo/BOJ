-- 코드를 입력하세요
SELECT * 
from FOOD_PRODUCT
where PRICE = (select max(price) from food_product);