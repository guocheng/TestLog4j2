CREATE TABLE IF NOT EXISTS `smartify`.`error_log` (
  `id` INT NOT NULL AUTO_INCREMENT,
  `log_time` DATETIME NULL,
  `level` VARCHAR(5) NULL,
  `message` VARCHAR(45) NULL,
  `throwable` TEXT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;