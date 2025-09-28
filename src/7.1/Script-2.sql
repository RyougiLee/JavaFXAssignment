use converter;

select * from currency;

select * from currency where abbr_name = 'EUR';

select COUNT(id) from currency;

select * from currency where rate_to_usd = (select max(rate_to_usd) from currency) ;