SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `footballun` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci ;
USE `footballun` ;

-- -----------------------------------------------------
-- Table `footballun`.`group`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `footballun`.`group` ;

CREATE  TABLE IF NOT EXISTS `footballun`.`group` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(10) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `footballun`.`country`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `footballun`.`country` ;

CREATE  TABLE IF NOT EXISTS `footballun`.`country` (
  `id` SMALLINT(6) NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,
  `continent` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
AUTO_INCREMENT = 21
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `footballun`.`team`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `footballun`.`team` ;

CREATE  TABLE IF NOT EXISTS `footballun`.`team` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  `code` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,
  `address` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,
  `website` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,
  `type` VARCHAR(15) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,
  `alias` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,
  `country_id` SMALLINT(6) NOT NULL ,
  PRIMARY KEY (`id`, `country_id`) ,
  CONSTRAINT `fk_team_country1`
    FOREIGN KEY (`country_id` )
    REFERENCES `footballun`.`country` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 21
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

CREATE INDEX `fk_team_country1_idx` ON `footballun`.`team` (`country_id` ASC) ;


-- -----------------------------------------------------
-- Table `footballun`.`squad`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `footballun`.`squad` ;

CREATE  TABLE IF NOT EXISTS `footballun`.`squad` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `team_id` INT(11) NULL DEFAULT NULL ,
  `name` VARCHAR(15) NULL ,
  `group_id` INT(11) NOT NULL ,
  PRIMARY KEY (`id`) ,
  CONSTRAINT `fk_team_id_team`
    FOREIGN KEY (`team_id` )
    REFERENCES `footballun`.`team` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_squad_group1`
    FOREIGN KEY (`group_id` )
    REFERENCES `footballun`.`group` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 21
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

CREATE INDEX `fk_team_id_team_idx` ON `footballun`.`squad` (`team_id` ASC) ;

CREATE INDEX `fk_squad_group1_idx` ON `footballun`.`squad` (`group_id` ASC) ;


-- -----------------------------------------------------
-- Table `footballun`.`competition`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `footballun`.`competition` ;

CREATE  TABLE IF NOT EXISTS `footballun`.`competition` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,
  `year_from` YEAR NULL DEFAULT NULL ,
  `year_to` YEAR NULL DEFAULT NULL ,
  `start_at` TIMESTAMP NULL DEFAULT NULL ,
  `end_at` TIMESTAMP NULL DEFAULT NULL ,
  `host_country_id` SMALLINT(6) NULL DEFAULT NULL ,
  `type` VARCHAR(10) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) ,
  CONSTRAINT `fk_country_id`
    FOREIGN KEY (`host_country_id` )
    REFERENCES `footballun`.`country` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 7
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

CREATE INDEX `fk_country_id_idx` ON `footballun`.`competition` (`host_country_id` ASC) ;


-- -----------------------------------------------------
-- Table `footballun`.`formation`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `footballun`.`formation` ;

CREATE  TABLE IF NOT EXISTS `footballun`.`formation` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(15) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `footballun`.`hero`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `footballun`.`hero` ;

CREATE  TABLE IF NOT EXISTS `footballun`.`hero` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `first_name` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,
  `last_name` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,
  `country` INT(11) NULL DEFAULT NULL ,
  `age` INT(11) NULL DEFAULT NULL ,
  `hight` INT(11) NULL DEFAULT NULL ,
  `weight` INT(11) NULL DEFAULT NULL ,
  `alias` VARCHAR(45) NULL ,
  `status` TINYINT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `footballun`.`position`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `footballun`.`position` ;

CREATE  TABLE IF NOT EXISTS `footballun`.`position` (
  `id` INT(2) NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(15) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
AUTO_INCREMENT = 5
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `footballun`.`hero_role`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `footballun`.`hero_role` ;

CREATE  TABLE IF NOT EXISTS `footballun`.`hero_role` (
  `id` TINYINT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(15) NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `footballun`.`squad_detail`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `footballun`.`squad_detail` ;

CREATE  TABLE IF NOT EXISTS `footballun`.`squad_detail` (
  `id` INT NOT NULL AUTO_INCREMENT ,
  `hero_id` INT(11) NOT NULL ,
  `position_id` INT(2) NOT NULL ,
  `hero_role_id` TINYINT NOT NULL ,
  `squad_id` INT(11) NOT NULL ,
  PRIMARY KEY (`id`) ,
  CONSTRAINT `fk_squad_hero1`
    FOREIGN KEY (`hero_id` )
    REFERENCES `footballun`.`hero` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_squad_position1`
    FOREIGN KEY (`position_id` )
    REFERENCES `footballun`.`position` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_squad_hero_role1`
    FOREIGN KEY (`hero_role_id` )
    REFERENCES `footballun`.`hero_role` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_squad_detail_squad1`
    FOREIGN KEY (`squad_id` )
    REFERENCES `footballun`.`squad` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;

CREATE INDEX `fk_squad_hero1_idx` ON `footballun`.`squad_detail` (`hero_id` ASC) ;

CREATE INDEX `fk_squad_position1_idx` ON `footballun`.`squad_detail` (`position_id` ASC) ;

CREATE INDEX `fk_squad_hero_role1_idx` ON `footballun`.`squad_detail` (`hero_role_id` ASC) ;

CREATE INDEX `fk_squad_detail_squad1_idx` ON `footballun`.`squad_detail` (`squad_id` ASC) ;


-- -----------------------------------------------------
-- Table `footballun`.`matchup_register`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `footballun`.`matchup_register` ;

CREATE  TABLE IF NOT EXISTS `footballun`.`matchup_register` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `is_lineup` BINARY NULL DEFAULT NULL ,
  `squad_detail_id` INT NOT NULL ,
  `position_id` INT(2) NOT NULL ,
  PRIMARY KEY (`id`) ,
  CONSTRAINT `fk_lineup_substitue_squad1`
    FOREIGN KEY (`squad_detail_id` )
    REFERENCES `footballun`.`squad_detail` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_lineup_substitue_position1`
    FOREIGN KEY (`position_id` )
    REFERENCES `footballun`.`position` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

CREATE INDEX `fk_lineup_substitue_squad1_idx` ON `footballun`.`matchup_register` (`squad_detail_id` ASC) ;

CREATE INDEX `fk_lineup_substitue_position1_idx` ON `footballun`.`matchup_register` (`position_id` ASC) ;


-- -----------------------------------------------------
-- Table `footballun`.`stadium`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `footballun`.`stadium` ;

CREATE  TABLE IF NOT EXISTS `footballun`.`stadium` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,
  `city` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,
  `country_id` INT(11) NULL DEFAULT NULL ,
  `seats` INT(11) NULL DEFAULT NULL ,
  `built_since` DATE NULL ,
  `team_id` INT(11) NOT NULL ,
  PRIMARY KEY (`id`) ,
  CONSTRAINT `fk_stadium_team1`
    FOREIGN KEY (`team_id` )
    REFERENCES `footballun`.`team` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

CREATE INDEX `fk_stadium_team1_idx` ON `footballun`.`stadium` (`team_id` ASC) ;


-- -----------------------------------------------------
-- Table `footballun`.`matchup`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `footballun`.`matchup` ;

CREATE  TABLE IF NOT EXISTS `footballun`.`matchup` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,
  `start_at` TIMESTAMP NULL DEFAULT NULL ,
  `end_at` TIMESTAMP NULL DEFAULT NULL ,
  `result` TINYINT(4) NULL DEFAULT NULL ,
  `matchday` TINYINT NULL DEFAULT NULL ,
  `point` TINYINT NULL DEFAULT NULL ,
  `status` TINYINT(4) NULL ,
  `stadium_id` INT(11) NOT NULL ,
  PRIMARY KEY (`id`) ,
  CONSTRAINT `fk_matchup_stadium1`
    FOREIGN KEY (`stadium_id` )
    REFERENCES `footballun`.`stadium` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

CREATE INDEX `fk_matchup_stadium1_idx` ON `footballun`.`matchup` (`stadium_id` ASC) ;


-- -----------------------------------------------------
-- Table `footballun`.`event`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `footballun`.`event` ;

CREATE  TABLE IF NOT EXISTS `footballun`.`event` (
  `id` SMALLINT NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `footballun`.`matchup_detail`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `footballun`.`matchup_detail` ;

CREATE  TABLE IF NOT EXISTS `footballun`.`matchup_detail` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `matchup_id` INT(11) NOT NULL ,
  `event_id` SMALLINT NOT NULL ,
  `timestamp` TIMESTAMP NULL ,
  `updated_minute` TINYINT NULL ,
  `matchup_register_id` INT(11) NOT NULL ,
  PRIMARY KEY (`id`) ,
  CONSTRAINT `fk_matchup_detail_matchup1`
    FOREIGN KEY (`matchup_id` )
    REFERENCES `footballun`.`matchup` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_matchup_detail_event1`
    FOREIGN KEY (`event_id` )
    REFERENCES `footballun`.`event` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_matchup_detail_matchup_register1`
    FOREIGN KEY (`matchup_register_id` )
    REFERENCES `footballun`.`matchup_register` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci
COMMENT = 'Information for team line-up, subtitues, formation, so on';

CREATE INDEX `fk_matchup_detail_matchup1_idx` ON `footballun`.`matchup_detail` (`matchup_id` ASC) ;

CREATE INDEX `fk_matchup_detail_event1_idx` ON `footballun`.`matchup_detail` (`event_id` ASC) ;

CREATE INDEX `fk_matchup_detail_matchup_register1_idx` ON `footballun`.`matchup_detail` (`matchup_register_id` ASC) ;


-- -----------------------------------------------------
-- Table `footballun`.`standing`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `footballun`.`standing` ;

CREATE  TABLE IF NOT EXISTS `footballun`.`standing` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `point` INT(11) NULL ,
  `squad_id` INT(11) NOT NULL ,
  PRIMARY KEY (`id`) ,
  CONSTRAINT `fk_standing_competing_team1`
    FOREIGN KEY (`squad_id` )
    REFERENCES `footballun`.`squad` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

CREATE INDEX `fk_standing_competing_team1_idx` ON `footballun`.`standing` (`squad_id` ASC) ;


-- -----------------------------------------------------
-- Table `footballun`.`stats`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `footballun`.`stats` ;

CREATE  TABLE IF NOT EXISTS `footballun`.`stats` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `stats_type_id` INT(11) NULL DEFAULT NULL ,
  `player_id` INT(11) NULL DEFAULT NULL ,
  `counted_for_team_id` INT(11) NULL DEFAULT NULL ,
  `timestamp` TIMESTAMP NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `footballun`.`stats_type`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `footballun`.`stats_type` ;

CREATE  TABLE IF NOT EXISTS `footballun`.`stats_type` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `type` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `footballun`.`matchup_squad`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `footballun`.`matchup_squad` ;

CREATE  TABLE IF NOT EXISTS `footballun`.`matchup_squad` (
  `matchup_id` INT(11) NOT NULL ,
  `squad_id` INT(11) NOT NULL ,
  `is_home_team` BINARY NULL ,
  `formation_id` INT(11) NOT NULL ,
  PRIMARY KEY (`matchup_id`, `squad_id`) ,
  CONSTRAINT `fk_matchup_has_competing_team_matchup1`
    FOREIGN KEY (`matchup_id` )
    REFERENCES `footballun`.`matchup` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_matchup_has_competing_team_competing_team1`
    FOREIGN KEY (`squad_id` )
    REFERENCES `footballun`.`squad` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_matchup_squad_formation1`
    FOREIGN KEY (`formation_id` )
    REFERENCES `footballun`.`formation` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

CREATE INDEX `fk_matchup_has_competing_team_competing_team1_idx` ON `footballun`.`matchup_squad` (`squad_id` ASC) ;

CREATE INDEX `fk_matchup_has_competing_team_matchup1_idx` ON `footballun`.`matchup_squad` (`matchup_id` ASC) ;

CREATE INDEX `fk_matchup_squad_formation1_idx` ON `footballun`.`matchup_squad` (`formation_id` ASC) ;

USE `footballun` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
