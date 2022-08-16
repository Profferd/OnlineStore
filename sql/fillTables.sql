INSERT INTO `role` (`id`, `role`) VALUES (2, 'admin');
INSERT INTO `role` (`id`, `role`) VALUES (1, 'user');

INSERT INTO `user` (`id`, `userInfo_id`, `role_id`, `email`, `password`) VALUES (1, 1, 1, 'asdw@gmail.com', '1313');
INSERT INTO `user` (`id`, `userInfo_id`, `role_id`, `email`, `password`) VALUES (2, 2, 1, 'name@gmail.cos', '88ea39439e74fa27c09a4fc0bc8ebe6d00978392');
INSERT INTO `user` (`id`, `userInfo_id`, `role_id`, `email`, `password`) VALUES (3, 3, 1, 'useer@gmail.co', '2778cb15047b69e5e1e166cbb0d8c4323c9595c6');
INSERT INTO `user` (`id`, `userInfo_id`, `role_id`, `email`, `password`) VALUES (4, 4, 2, 'admin@admin.com', '1');

INSERT INTO `userinfo` (`id`, `name`, `surname`, `phone`) VALUES (1, 'Vasya', 'Romanovich', 380123123);
INSERT INTO `userinfo` (`id`, `name`, `surname`, `phone`) VALUES (2, 'Name', 'Surname', 1231231234);
INSERT INTO `userinfo` (`id`, `name`, `surname`, `phone`) VALUES (3, 'User', 'Usersur', 1231231332);
INSERT INTO `userinfo` (`id`, `name`, `surname`, `phone`) VALUES (4, 'Admin', 'Admin', 123456789);

INSERT INTO `userorder` (`id`, `address`, `order_date`, `delivery_date`, `status`) VALUES (1, 'City', '2022-06-23', '2022-06-26', 'pending');
INSERT INTO `userorder` (`id`, `address`, `order_date`, `delivery_date`, `status`) VALUES (2, 'asd', '2022-08-15', '2022-08-19', 'canceled');
INSERT INTO `userorder` (`id`, `address`, `order_date`, `delivery_date`, `status`) VALUES (3, '123', '2022-08-15', '2022-08-25', 'taken');

INSERT INTO `category` (`id`, `category`) VALUES (2, 'Headset');
INSERT INTO `category` (`id`, `category`) VALUES (1, 'Laptop');
INSERT INTO `category` (`id`, `category`) VALUES (3, 'TestCat');

INSERT INTO `product` (`id`, `category_id`, `promotion_id`, `name`, `description`, `price`, `status`, `photo`, `orderNumber`) VALUES (1, 1, NULL, 'Acer', 'descr', 36000, 1, '1.jpg', 1);
INSERT INTO `product` (`id`, `category_id`, `promotion_id`, `name`, `description`, `price`, `status`, `photo`, `orderNumber`) VALUES (3, 3, 1, 'Test', 'This is test', 49999.99, 1, 'test.jpg', 0);
INSERT INTO `product` (`id`, `category_id`, `promotion_id`, `name`, `description`, `price`, `status`, `photo`, `orderNumber`) VALUES (4, 3, NULL, 'Test21', 'This is test2\r\n                        ', 13123.12, 1, 'photo.png', 0);

INSERT INTO `promotion` (`id`, `name`, `description`, `start_date`, `end_date`, `photo`, `discount`) VALUES (1, 'Test', 'Testing', '2022-06-22', '2022-06-24', 'photo.jpg', 25);
INSERT INTO `promotion` (`id`, `name`, `description`, `start_date`, `end_date`, `photo`, `discount`) VALUES (2, 'Test1', 'Testing1', '2022-06-22', '2022-06-24', 'photo1.jpg', 30);

INSERT INTO `order` (`id`, `product_id`, `user_id`, `userOrder_id`, `number`) VALUES (1, 1, 1, 3, 2);
