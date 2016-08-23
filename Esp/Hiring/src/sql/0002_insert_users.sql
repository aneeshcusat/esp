--//

Insert into FAM_USERS (ID,FIRSTNAME,LASTNAME,USERNAME,PASSWORD) values (1,'aneesh','kumar','aneesh@aneesh.com','fdccbaa75d6d837deb882cf99');


--//@UNDO

delete from FAM_USERS where USERID=1;