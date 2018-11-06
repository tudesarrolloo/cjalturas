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