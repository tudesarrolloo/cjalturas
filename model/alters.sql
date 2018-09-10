ALTER TABLE `cjalturas`.`coach` 
CHANGE COLUMN `sign` `sign` LONGTEXT NULL COMMENT 'Firma digital del instructor' ;

ALTER TABLE `cjalturas`.`group` 
ADD COLUMN `description` VARCHAR(60) NULL AFTER `id_coach`;

ALTER TABLE `cjalturas`.`group` 
ADD COLUMN `date_end` DATE NULL COMMENT 'Fecha en la que finaliza el curso' AFTER `date_start`,
ADD COLUMN `status` TINYINT(1) NULL DEFAULT 1 COMMENT 'Estado del grupo actualmente puede ser 1 = Activo, 0= Inactivo' AFTER `observations`,
CHANGE COLUMN `date_start` `date_start` DATE NOT NULL COMMENT 'Fecha en la que inicia el curso' ;
