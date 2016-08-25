insert into "order"(customerid, sellerid, description,totalprice,createtime, liveduration, callbackurl,state) values('jim','xy','testpay',50.00,timestamp('2016-09-01 12:00:00'), 1800,'http://sample', 'OrderStateInit');
insert into orderitem(name, externalid, orderid) values('product1', 'ex1', 1);
insert into orderitem(name, externalid, orderid) values('product2', 'ex2', 1);
insert into payment(amount, paytime, fromaccount,toaccount,referencetext,note,state,"type",orderid) values(50,timestamp('2016-09-01 13:00:00'), 'aly1', 'corp','xxxr','testnote','PaymentStateSuccess','PaymentTypeAlipay',1);