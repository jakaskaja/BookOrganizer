
CREATE SCHEMA IF NOT EXISTS BookDB DEFAULT CHARACTER SET utf8 ;
USE BookDB ;

DROP TABLE IF EXISTS BookDB.Role ;  

CREATE TABLE BookDB.Role (
  id_role INT NOT NULL AUTO_INCREMENT,
  role_name VARCHAR(45) NOT NULL,
  PRIMARY KEY (id_role)
  );

DROP TABLE IF EXISTS BookDB.Users ;

CREATE TABLE BookDB.Users (
  id_user INT NOT NULL AUTO_INCREMENT,
  mail VARCHAR(45) NOT NULL UNIQUE,
  name VARCHAR(45) NOT NULL,
  last_name VARCHAR(45) NOT NULL,
  password VARCHAR(45) NOT NULL,
  id_role INT NOT NULL,
  PRIMARY KEY (id_user),
  FOREIGN KEY (id_role)
	REFERENCES BookDB.Role (id_role)
  );

DROP TABLE IF EXISTS BookDB.Books;
 

 
INSERT INTO `BookDB`.`Role` (`id_role`, `role_name`) VALUES (1, 'admin');
INSERT INTO `BookDB`.`Role` (`id_role`, `role_name`) VALUES (2, 'user');

INSERT INTO `BookDB`.`Users` (`id_user`, `mail`, `name`, `last_name`, `password`, `id_role`) VALUES ('1', 'user@user.pl', 'Jan', 'Uzytkownik', 'user', '2');
INSERT INTO `BookDB`.`Users` (`id_user`, `mail`, `name`, `last_name`, `password`, `id_role`) VALUES ('2', 'admin@admin.pl', 'Anna', 'Administrator', 'admin', '1');





  