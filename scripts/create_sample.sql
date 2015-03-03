/* Insert test data */
insert into challenger.users
values ("d6fbf948694caf35", "volterr", "max kazakov", "$2a$10$rdHCgQDkMqQSk9IK8rhsVu1XhC/Y/VA5ohsMgGtjasABH7b.WAWzi", "volterr@gmail.com", 7);

/*By Yefim*/
insert into challenger.usersettings values ("d6fbf948694caf35",   "English");
/* By Vladimir Zhdanov 01.03.2015 */
insert into challenger.challengegroups values ("5d2c117e674b39de", "Challenge group 1 name", "Challenge group 1 icon path");
insert into challenger.challenges values ("876aee6e27926e59", "Challenge 1 title", "Challenge 1  decription", 0, "Challenge 1 hash tag", "5d2c117e674b39de");
insert into challenger.subscriptions values ("11", "d6fbf948694caf35", "876aee6e27926e59", CURDATE());


commit;