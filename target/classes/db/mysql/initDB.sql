/*
SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `footballun` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci ;
USE `footballun` ;

-- -----------------------------------------------------
-- Table `footballun`.`competiting_team`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `footballun`.`competiting_team` ;

CREATE  TABLE IF NOT EXISTS `footballun`.`competiting_team` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `team_id` INT(11) NULL DEFAULT NULL ,
  `group_id` INT(11) NULL DEFAULT NULL ,
  `competition_id` INT(11) NULL DEFAULT NULL ,
  `coach_name` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


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
  `host_country_id` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
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
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


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
-- Table `footballun`.`group`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `footballun`.`group` ;

CREATE  TABLE IF NOT EXISTS `footballun`.`group` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `footballun`.`lineup_subtitue`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `footballun`.`lineup_subtitue` ;

CREATE  TABLE IF NOT EXISTS `footballun`.`lineup_subtitue` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `match_id` INT(11) NULL DEFAULT NULL ,
  `competiting_team_id` INT(11) NULL DEFAULT NULL ,
  `player_id` INT(11) NULL DEFAULT NULL ,
  `position_id` INT(11) NULL DEFAULT NULL ,
  `is_lineup` TINYINT(4) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `footballun`.`match`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `footballun`.`match` ;

CREATE  TABLE IF NOT EXISTS `footballun`.`match` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,
  `team1_id` INT(11) NULL DEFAULT NULL ,
  `team2_id` INT(11) NULL DEFAULT NULL ,
  `home_team_id` INT(11) NULL DEFAULT NULL ,
  `start_at` TIMESTAMP NULL DEFAULT NULL ,
  `end_at` TIMESTAMP NULL DEFAULT NULL ,
  `competition_id` INT(11) NULL DEFAULT NULL ,
  `result` TINYINT(4) NULL DEFAULT NULL ,
  `stats_id` INT(11) NULL DEFAULT NULL ,
  `match_day` INT(11) NULL DEFAULT NULL ,
  `point` SMALLINT(6) NULL DEFAULT NULL ,
  `matchcol` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,
  `played` TINYINT(4) NULL DEFAULT NULL ,
  `stadium_id` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `footballun`.`match_detail`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `footballun`.`match_detail` ;

CREATE  TABLE IF NOT EXISTS `footballun`.`match_detail` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `match_id` INT(11) NULL DEFAULT NULL ,
  `team1_lineup_id` INT(11) NULL DEFAULT NULL ,
  `team2_lineup_id` INT(11) NULL DEFAULT NULL ,
  `team1_formation_id` INT(11) NULL DEFAULT NULL ,
  `team2_formation_id` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci
COMMENT = 'Information for team line-up, subtitues, formation, so on';


-- -----------------------------------------------------
-- Table `footballun`.`player`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `footballun`.`player` ;

CREATE  TABLE IF NOT EXISTS `footballun`.`player` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `first_name` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,
  `last_name` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,
  `shirt_number` INT(11) NULL DEFAULT NULL ,
  `position` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,
  `country` INT(11) NULL DEFAULT NULL ,
  `age` INT(11) NULL DEFAULT NULL ,
  `hight` INT(11) NULL DEFAULT NULL ,
  `weight` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


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
  `reserved_seats` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `footballun`.`standings`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `footballun`.`standings` ;

CREATE  TABLE IF NOT EXISTS `footballun`.`standings` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `team_group_id` INT(11) NULL DEFAULT NULL ,
  `point` INT(11) NULL DEFAULT NULL ,
  `match_played` INT(11) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


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
-- Table `footballun`.`team`
-- -----------------------------------------------------
DROP TABLE IF EXISTS `footballun`.`team` ;

CREATE  TABLE IF NOT EXISTS `footballun`.`team` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `title` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  `code` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,
  `stadium` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,
  `country` SMALLINT(6) NULL DEFAULT NULL ,
  `address` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,
  `website` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

USE `footballun` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
*/