insert into producttype (name, description) values ('Cookie', 'small flour confectionery products of various shapes and low humidity');
insert into producttype (name, description) values ('Ice cream', 'a food product is a dessert, which is a mass frozen in the process of continuous whipping, containing mainly nutritious, flavoring, aromatic and emulsifying substances');
insert into producttype (name, description) values ('Lollipop', 'a type of hard candy, usually consisting of flavored sugar with molasses or corn syrup');
insert into owner (name, surname, telephone_number, email) values ('111', '222', '11-22-337', '4444@111.ru8');
insert into owner (name, surname, telephone_number, email) values ('Bo5', '333', '1122337', 'Bo5@mail.ru');
insert into owner (name, surname, telephone_number, email) values ('333', '444', '1122337', 'Bo5@mail.ru');
insert into provider (name, address, owner_id) values ('Lollipop Corp', 'Sesame street, 123', 1);
insert into provider (name, address, owner_id) values ('Cookie Inc', 'Land of Oz', 2);
insert into provider (name, address, owner_id) values ('Icecream Union', 'Middle-earth', 3);
insert into product (name, price, calories, compound, provider_id) values ('111', 108, 2306, '222', 1);
insert into product (name, price, calories, compound, provider_id) values ('1111', 100, 230, '2222', 2);
insert into product (name, price, calories, compound, provider_id) values ('11111', 100, 235, '22222', 1);
insert into product_producttype (product_id, producttype_id) values (1,1);
insert into product_producttype (product_id, producttype_id) values (1,2);
insert into product_producttype (product_id, producttype_id) values (1,3);
insert into product_producttype (product_id, producttype_id) values (2,1);
insert into product_producttype (product_id, producttype_id) values (2,2);
insert into product_producttype (product_id, producttype_id) values (3,3);