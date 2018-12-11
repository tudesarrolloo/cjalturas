ALTER TABLE `cjalturas`.`coach` 
CHANGE COLUMN `sign` `sign` LONGTEXT NULL COMMENT 'Firma digital del instructor' ;

ALTER TABLE `cjalturas`.`group` 
ADD COLUMN `description` VARCHAR(60) NULL AFTER `id_coach`;

ALTER TABLE `cjalturas`.`group` 
ADD COLUMN `date_end` DATE NULL COMMENT 'Fecha en la que finaliza el curso' AFTER `date_start`,
ADD COLUMN `status` TINYINT(1) NULL DEFAULT 1 COMMENT 'Estado del grupo actualmente puede ser 1 = Activo, 0= Inactivo' AFTER `observations`,
CHANGE COLUMN `date_start` `date_start` DATE NOT NULL COMMENT 'Fecha en la que inicia el curso' ;

CREATE TABLE IF NOT EXISTS `cjalturas`.`certificate` (
  `id_certificate` INT NOT NULL AUTO_INCREMENT,
  `id_inscription` INT NOT NULL,
  `date` VARCHAR(30) NOT NULL,
  `code` VARCHAR(20) NULL,
  `certification` VARCHAR(300) NOT NULL COMMENT 'Información del certificado a través de la cual la empresa puede emitir certificados',
  `learner` VARCHAR(200) NOT NULL COMMENT 'Aprendiz certificado',
  `learnerDocument` VARCHAR(30) NOT NULL COMMENT 'Documento del aprendiz',
  `level` VARCHAR(20) NOT NULL COMMENT 'Nivel del curso certificado',
  `intensity` VARCHAR(20) NOT NULL COMMENT 'Intensidad del curso certificado',
  `days` VARCHAR(100) NOT NULL COMMENT 'Días en los que se recibio el curso certificado',
  `city` VARCHAR(20) NOT NULL COMMENT 'Ciudad en la que se emitió la certificación',
  `instructor1` VARCHAR(100) NOT NULL COMMENT 'Nombre del primer instructor que aprobó la certificación',
  `instructor1_charge` VARCHAR(30) NOT NULL COMMENT 'Cargo del primer instructor que aprobó la certificación',
  `instructor1_sign` LONGTEXT NOT NULL COMMENT 'Firma del primer instructor que aprobó la certificación',
  `instructor2` VARCHAR(100) NOT NULL COMMENT 'Nombre del segundo instructor que aprobó la certificación',
  `instructor2_charge` VARCHAR(30) NOT NULL COMMENT 'Cargo del segundo instructor que aprobó la certificación',
  `instructor2_sign` LONGTEXT NOT NULL COMMENT 'Firma del segundo instructor que aprobó la certificación',
  PRIMARY KEY (`id_certificate`),
  INDEX `fk_certificate_inscription_idx` (`id_inscription` ASC) VISIBLE,
  CONSTRAINT `fk_certificate_inscription`
    FOREIGN KEY (`id_inscription`)
    REFERENCES `cjalturas`.`inscription` (`id_inscription`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

ALTER TABLE `cjalturas`.`course` 
ADD COLUMN `intensity` VARCHAR(50) NOT NULL DEFAULT ' ' COMMENT 'Intensidad con la que se dicta el curso' AFTER `course`;

ALTER TABLE `cjalturas`.`course` 
ADD COLUMN `validityDaysCertificate` INT NOT NULL DEFAULT 0 COMMENT 'Días de vigencia por los cuales se expide un certificado para este curso' AFTER `intensity`;

ALTER TABLE `cjalturas`.`group` 
ADD COLUMN `daysCourse` VARCHAR(100) NOT NULL COMMENT 'Días en los que se dictará el curso incluido el mes y año ejemplo (2,3,4,5 de mayo de 2018) esto se usa para la generación del certificado' AFTER `status`;

ALTER TABLE `cjalturas`.`certificate` 
ADD COLUMN `dateExpiration` DATE NOT NULL COMMENT 'Fecha en la que expira el certificado' AFTER `instructor2_sign`;

--29Nov

ALTER TABLE `cjalturas`.`person` 
ADD COLUMN `birthDate` DATE NULL COMMENT 'Fecha de nacimiento' AFTER `email`;

ALTER TABLE `cjalturas`.`certificate` 
ADD COLUMN `learnerTypeDocument` VARCHAR(5) NOT NULL DEFAULT 'CC' AFTER `learner`;

--12 dic
ALTER TABLE `cjalturas`.`certificate` 
CHANGE COLUMN `instructor1_charge` `instructor1_charge` VARCHAR(100) NOT NULL COMMENT 'Cargo del primer instructor que aprobó la certificación' ,
CHANGE COLUMN `instructor2_charge` `instructor2_charge` VARCHAR(100) NOT NULL COMMENT 'Cargo del segundo instructor que aprobó la certificación' ;


