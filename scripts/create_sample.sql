/* Insert test data */
insert into challenger.users
values ("d6fbf948694caf35", "volterr", "max kazakov", "$2a$10$rdHCgQDkMqQSk9IK8rhsVu1XhC/Y/VA5ohsMgGtjasABH7b.WAWzi", "volterr@gmail.com", 7);
insert into challenger.users
values ("d8fbf948694caf35", "Yefim", "yefim krokhin", "$2a$10$rdHCgQDkMqQSk9IK8rhsVu1XhC/Y/VA5ohsMgGtjasABH7b.WAWzi", "cococo@gmail.com", 1);


/* By Vladimir Zhdanov 01.03.2015 */
-- just to insert some picture try to change path depending on your own PC
-- Nipel-Crumple 12.03.2015
insert into `challenger`.`images` values ("123123","Image1","2015-03-12", "S", LOAD_FILE('D:\#1.png'));
insert into `challenger`.`images` values ("5f7f505e8099a263","Image2","2015-03-28", "S", LOAD_FILE('D:\#2.png')); /* By Vladimir Zhdanov on 28.03.2015 */
insert into challenger.challengegroups values ("5d2c117e674b39de", "Challenge group 1 name", "123123");
insert into challenger.challenges values ("876aee6e27926e59", "Challenge 1 title", "Challenge 1  decription", 0, "Challenge 1 hash tag", "5d2c117e674b39de", "5f7f505e8099a263"); /* Added ImageID by Vladimir Zhdanov on 18.03.2015 */
insert into challenger.subscriptions values ("11", "d6fbf948694caf35", "876aee6e27926e59", CURDATE());
insert into challenger.subscriptions values ("12", "d6fbf948694caf35", "876aee6e27926e59", CURDATE());

/*By Yefim*/
insert into challenger.usersettings values ("d6fbf948694caf35",   "English");
insert into challenger.origins values ("twit_id", "Twitter");
insert into challenger.subscriptions values ("13", "d8fbf948694caf35", "876aee6e27926e59", CURDATE());
insert into challenger.subscriptions values ("14", "d8fbf948694caf35", "876aee6e27926e59", CURDATE());
insert into challenger.posts values("577231985401290754", "twit_id", "13", CURDATE());
insert into challenger.posts values("577232167299858433", "twit_id", "13", CURDATE());



commit;