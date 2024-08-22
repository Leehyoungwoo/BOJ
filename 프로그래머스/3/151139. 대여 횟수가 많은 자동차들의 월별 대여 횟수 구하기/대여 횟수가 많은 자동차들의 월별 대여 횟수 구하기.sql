WITH Filtered_Rentals AS (
    SELECT 
        CAR_ID,
        EXTRACT(YEAR FROM START_DATE) AS YEAR,
        EXTRACT(MONTH FROM START_DATE) AS MONTH
    FROM 
        CAR_RENTAL_COMPANY_RENTAL_HISTORY
    WHERE 
        START_DATE >= '2022-08-01' AND START_DATE < '2022-11-01'
), Car_Rental_Counts AS (
    SELECT 
        CAR_ID,
        COUNT(*) AS TOTAL_RENTALS
    FROM 
        Filtered_Rentals
    GROUP BY 
        CAR_ID
    HAVING 
        COUNT(*) >= 5
)
SELECT 
    EXTRACT(MONTH FROM START_DATE) AS MONTH,
    h.CAR_ID,
    COUNT(*) AS RECORDS
FROM 
    CAR_RENTAL_COMPANY_RENTAL_HISTORY h
JOIN 
    Car_Rental_Counts c ON h.CAR_ID = c.CAR_ID
WHERE 
    START_DATE >= '2022-08-01' AND START_DATE < '2022-11-01'
GROUP BY 
    EXTRACT(MONTH FROM START_DATE), h.CAR_ID
ORDER BY 
    MONTH ASC, CAR_ID DESC;