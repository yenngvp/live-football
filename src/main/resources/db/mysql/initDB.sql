SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `footballun` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci ;
USE `footballun` ;

-- -----------------------------------------------------
-- Table `footballun`.`country`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `footballun`.`country` (
  `id` SMALLINT(6) NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,
  `continent` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
AUTO_INCREMENT = 41
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `footballun`.`competition`
-- -----------------------------------------------------
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
  INDEX `fk_country_id_idx` (`host_country_id` ASC) ,
  CONSTRAINT `fk_country_id`
    FOREIGN KEY (`host_country_id` )
    REFERENCES `footballun`.`country` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 15
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `footballun`.`event`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `footballun`.`event` (
  `id` SMALLINT(6) NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `footballun`.`formation`
-- -----------------------------------------------------
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
CREATE  TABLE IF NOT EXISTS `footballun`.`group` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(10) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
AUTO_INCREMENT = 13
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `footballun`.`hero`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `footballun`.`hero` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `first_name` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,
  `last_name` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,
  `country` INT(11) NULL DEFAULT NULL ,
  `age` INT(11) NULL DEFAULT NULL ,
  `hight` INT(11) NULL DEFAULT NULL ,
  `weight` INT(11) NULL DEFAULT NULL ,
  `alias` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,
  `status` TINYINT(4) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
AUTO_INCREMENT = 58
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `footballun`.`hero_role`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `footballun`.`hero_role` (
  `id` TINYINT(4) NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(15) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
AUTO_INCREMENT = 6
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `footballun`.`team`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `footballun`.`team` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NOT NULL ,
  `code` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,
  `address` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,
  `website` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,
  `type` VARCHAR(15) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,
  `alias` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,
  `country_id` SMALLINT(6) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_team_country1_idx` (`country_id` ASC) ,
  CONSTRAINT `fk_team_country1`
    FOREIGN KEY (`country_id` )
    REFERENCES `footballun`.`country` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 62
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `footballun`.`stadium`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `footballun`.`stadium` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,
  `city` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,
  `country_id` INT(11) NULL DEFAULT NULL ,
  `seats` INT(11) NULL DEFAULT NULL ,
  `built_since` DATE NULL DEFAULT NULL ,
  `team_id` INT(11) NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_stadium_team1_idx` (`team_id` ASC) ,
  CONSTRAINT `fk_stadium_team1`
    FOREIGN KEY (`team_id` )
    REFERENCES `footballun`.`team` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 22
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `footballun`.`matchup`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `footballun`.`matchup` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,
  `start_at` TIMESTAMP NULL DEFAULT NULL ,
  `end_at` TIMESTAMP NULL DEFAULT NULL ,
  `result` TINYINT(4) NULL DEFAULT NULL ,
  `matchday` TINYINT(4) NULL DEFAULT NULL ,
  `point` TINYINT(4) NULL DEFAULT NULL ,
  `status` TINYINT(4) NULL DEFAULT NULL ,
  `stadium_id` INT(11) NULL DEFAULT NULL ,
  `round` VARCHAR(15) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,
  `featured` TINYINT(1) NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_matchup_stadium1_idx` (`stadium_id` ASC) ,
  CONSTRAINT `fk_matchup_stadium1`
    FOREIGN KEY (`stadium_id` )
    REFERENCES `footballun`.`stadium` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 40
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `footballun`.`position`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `footballun`.`position` (
  `id` INT(2) NOT NULL AUTO_INCREMENT ,
  `name` VARCHAR(15) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
AUTO_INCREMENT = 9
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `footballun`.`squad`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `footballun`.`squad` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `team_id` INT(11) NULL DEFAULT NULL ,
  `name` VARCHAR(15) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,
  `group_id` INT(11) NULL DEFAULT NULL ,
  `competition_id` INT(11) NULL DEFAULT NULL ,
  `generation` VARCHAR(15) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL COMMENT 'First team, U21, U19, so on' ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_team_id_team_idx` (`team_id` ASC) ,
  INDEX `fk_squad_group1_idx` (`group_id` ASC) ,
  INDEX `fk_squad_competition1_idx` (`competition_id` ASC) ,
  CONSTRAINT `fk_squad_competition1`
    FOREIGN KEY (`competition_id` )
    REFERENCES `footballun`.`competition` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_squad_group1`
    FOREIGN KEY (`group_id` )
    REFERENCES `footballun`.`group` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_team_id_team`
    FOREIGN KEY (`team_id` )
    REFERENCES `footballun`.`team` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 41
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `footballun`.`squad_member`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `footballun`.`squad_member` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `hero_id` INT(11) NULL DEFAULT NULL ,
  `position_id` INT(2) NULL DEFAULT NULL ,
  `hero_role_id` TINYINT(4) NULL DEFAULT NULL ,
  `squad_id` INT(11) NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_squad_hero1_idx` (`hero_id` ASC) ,
  INDEX `fk_squad_position1_idx` (`position_id` ASC) ,
  INDEX `fk_squad_hero_role1_idx` (`hero_role_id` ASC) ,
  INDEX `fk_squad_detail_squad1_idx` (`squad_id` ASC) ,
  CONSTRAINT `fk_squad_member_hero1`
    FOREIGN KEY (`hero_id` )
    REFERENCES `footballun`.`hero` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_squad_member_hero_role1`
    FOREIGN KEY (`hero_role_id` )
    REFERENCES `footballun`.`hero_role` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_squad_member_position1`
    FOREIGN KEY (`position_id` )
    REFERENCES `footballun`.`position` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_squad_member_squad1`
    FOREIGN KEY (`squad_id` )
    REFERENCES `footballun`.`squad` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
AUTO_INCREMENT = 92
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `footballun`.`matchup_register`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `footballun`.`matchup_register` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `is_lineup` TINYINT(1) NULL DEFAULT NULL ,
  `squad_detail_id` INT(11) NOT NULL ,
  `position_id` INT(2) NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_lineup_substitue_squad1_idx` (`squad_detail_id` ASC) ,
  INDEX `fk_lineup_substitue_position1_idx` (`position_id` ASC) ,
  CONSTRAINT `fk_lineup_substitue_position1`
    FOREIGN KEY (`position_id` )
    REFERENCES `footballun`.`position` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_lineup_substitue_squad1`
    FOREIGN KEY (`squad_detail_id` )
    REFERENCES `footballun`.`squad_member` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `footballun`.`matchup_live`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `footballun`.`matchup_live` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `matchup_id` INT(11) NOT NULL ,
  `event_id` SMALLINT(6) NOT NULL ,
  `timestamp` TIMESTAMP NULL DEFAULT NULL ,
  `updated_minute` TINYINT(4) NULL DEFAULT NULL ,
  `matchup_register_id` INT(11) NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_matchup_detail_matchup1_idx` (`matchup_id` ASC) ,
  INDEX `fk_matchup_detail_event1_idx` (`event_id` ASC) ,
  INDEX `fk_matchup_detail_matchup_register1_idx` (`matchup_register_id` ASC) ,
  CONSTRAINT `fk_matchup_detail_event1`
    FOREIGN KEY (`event_id` )
    REFERENCES `footballun`.`event` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_matchup_detail_matchup1`
    FOREIGN KEY (`matchup_id` )
    REFERENCES `footballun`.`matchup` (`id` )
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


-- -----------------------------------------------------
-- Table `footballun`.`matchup_squad`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `footballun`.`matchup_squad` (
  `squad_id` INT(11) NOT NULL ,
  `matchup_id` INT(11) NOT NULL ,
  PRIMARY KEY (`squad_id`, `matchup_id`) ,
  INDEX `fk_matchup_squad_squad1_idx` (`squad_id` ASC) ,
  INDEX `fk_matchup_squad_matchup1_idx` (`matchup_id` ASC) ,
  CONSTRAINT `fk_matchup_squad_matchup1`
    FOREIGN KEY (`matchup_id` )
    REFERENCES `footballun`.`matchup` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_matchup_squad_squad1`
    FOREIGN KEY (`squad_id` )
    REFERENCES `footballun`.`squad` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `footballun`.`standing`
-- -----------------------------------------------------
CREATE  TABLE IF NOT EXISTS `footballun`.`standing` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `point` INT(11) NULL DEFAULT NULL ,
  `squad_id` INT(11) NOT NULL ,
  PRIMARY KEY (`id`) ,
  INDEX `fk_standing_competing_team1_idx` (`squad_id` ASC) ,
  CONSTRAINT `fk_standing_competing_team1`
    FOREIGN KEY (`squad_id` )
    REFERENCES `footballun`.`squad` (`id` )
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;


-- -----------------------------------------------------
-- Table `footballun`.`stats`
-- -----------------------------------------------------
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
CREATE  TABLE IF NOT EXISTS `footballun`.`stats_type` (
  `id` INT(11) NOT NULL AUTO_INCREMENT ,
  `type` VARCHAR(45) CHARACTER SET 'utf8' COLLATE 'utf8_unicode_ci' NULL DEFAULT NULL ,
  PRIMARY KEY (`id`) )
ENGINE = InnoDB
DEFAULT CHARACTER SET = utf8
COLLATE = utf8_unicode_ci;

USE `footballun` ;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
