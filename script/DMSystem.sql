SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0;
SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0;
SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='TRADITIONAL,ALLOW_INVALID_DATES';

CREATE SCHEMA IF NOT EXISTS `mydb` DEFAULT CHARACTER SET utf8 COLLATE utf8_general_ci ;
USE `mydb` ;

-- -----------------------------------------------------
-- Table `mydb`.`DocumentType`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`DocumentType` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`User`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`User` (
  `id` INT NOT NULL,
  `username` VARCHAR(45) NULL,
  `password` VARCHAR(45) NULL,
  `name` VARCHAR(45) NULL,
  `authority` INT NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Document`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Document` (
  `id` INT NOT NULL,
  `title` VARCHAR(45) NULL,
  `author` VARCHAR(45) NULL,
  `year` DATE NULL,
  `pages` INT NULL,
  `abstract` VARCHAR(400) NULL,
  `keywords` VARCHAR(100) NULL,
  `publisher` VARCHAR(100) NULL,
  `url` VARCHAR(45) NULL,
  `createTime` VARCHAR(45) NULL,
  `docTypeId` INT NOT NULL,
  `userId` INT NOT NULL,
  PRIMARY KEY (`id`, `docTypeId`, `userId`),
  INDEX `fk_Document_DocumentType1_idx` (`docTypeId` ASC),
  INDEX `fk_Document_User1_idx` (`userId` ASC),
  CONSTRAINT `fk_Document_DocumentType1`
    FOREIGN KEY (`docTypeId`)
    REFERENCES `mydb`.`DocumentType` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Document_User1`
    FOREIGN KEY (`userId`)
    REFERENCES `mydb`.`User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Evaluation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Evaluation` (
  `id` INT NOT NULL,
  `content` VARCHAR(200) NULL,
  `type` INT NULL,
  `point` INT NULL,
  `published` TINYINT(1) NULL,
  `documentId` INT NOT NULL,
  `userId` INT NOT NULL,
  PRIMARY KEY (`id`, `documentId`, `userId`),
  INDEX `fk_Evaluation_Document1_idx` (`documentId` ASC),
  INDEX `fk_Evaluation_User1_idx` (`userId` ASC),
  CONSTRAINT `fk_Evaluation_Document1`
    FOREIGN KEY (`documentId`)
    REFERENCES `mydb`.`Document` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Evaluation_User1`
    FOREIGN KEY (`userId`)
    REFERENCES `mydb`.`User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`DocumentExtraProperty`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`DocumentExtraProperty` (
  `id` INT NOT NULL,
  `propertyName` VARCHAR(45) NULL,
  `docTypeId` INT NOT NULL,
  PRIMARY KEY (`id`, `docTypeId`),
  INDEX `fk_DocumentExtraProperty_DocumentType_idx` (`docTypeId` ASC),
  CONSTRAINT `fk_DocumentExtraProperty_DocumentType`
    FOREIGN KEY (`docTypeId`)
    REFERENCES `mydb`.`DocumentType` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Tag`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Tag` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Operation`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Operation` (
  `id` INT NOT NULL,
  `type` INT NULL,
  `time` DATE NULL,
  `expression` VARCHAR(45) NULL,
  `userId` INT NOT NULL,
  PRIMARY KEY (`id`, `userId`),
  INDEX `fk_Operation_User1_idx` (`userId` ASC),
  CONSTRAINT `fk_Operation_User1`
    FOREIGN KEY (`userId`)
    REFERENCES `mydb`.`User` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`EvaluationExtraPropertyType`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`EvaluationExtraPropertyType` (
  `id` INT NOT NULL,
  `propertyName` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Document_has_DocumentExtraProperty`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Document_has_DocumentExtraProperty` (
  `docId` INT NOT NULL,
  `docExtraPropertyId` INT NOT NULL,
  `propertyValue` VARCHAR(45) NULL,
  PRIMARY KEY (`docId`, `docExtraPropertyId`),
  INDEX `fk_Document_has_DocumentExtraProperty_DocumentExtraProperty_idx` (`docExtraPropertyId` ASC),
  INDEX `fk_Document_has_DocumentExtraProperty_Document1_idx` (`docId` ASC),
  CONSTRAINT `fk_Document_has_DocumentExtraProperty_Document1`
    FOREIGN KEY (`docId`)
    REFERENCES `mydb`.`Document` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Document_has_DocumentExtraProperty_DocumentExtraProperty1`
    FOREIGN KEY (`docExtraPropertyId`)
    REFERENCES `mydb`.`DocumentExtraProperty` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Document_has_Tag`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Document_has_Tag` (
  `docId` INT NOT NULL,
  `tagId` INT NOT NULL,
  PRIMARY KEY (`docId`, `tagId`),
  INDEX `fk_Document_has_Tag_Tag1_idx` (`tagId` ASC),
  INDEX `fk_Document_has_Tag_Document1_idx` (`docId` ASC),
  CONSTRAINT `fk_Document_has_Tag_Document1`
    FOREIGN KEY (`docId`)
    REFERENCES `mydb`.`Document` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Document_has_Tag_Tag1`
    FOREIGN KEY (`tagId`)
    REFERENCES `mydb`.`Tag` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`RelationType`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`RelationType` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  PRIMARY KEY (`id`))
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Document_has_Document`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Document_has_Document` (
  `refereeDocId` INT NOT NULL,
  `refererDocId` INT NOT NULL,
  `relationTypeId` INT NOT NULL,
  PRIMARY KEY (`refereeDocId`, `refererDocId`, `relationTypeId`),
  INDEX `fk_Document_has_Document_Document2_idx` (`refererDocId` ASC),
  INDEX `fk_Document_has_Document_Document1_idx` (`refereeDocId` ASC),
  INDEX `fk_Document_has_Document_RelationType1_idx` (`relationTypeId` ASC),
  CONSTRAINT `fk_Document_has_Document_Document1`
    FOREIGN KEY (`refereeDocId`)
    REFERENCES `mydb`.`Document` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Document_has_Document_Document2`
    FOREIGN KEY (`refererDocId`)
    REFERENCES `mydb`.`Document` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Document_has_Document_RelationType1`
    FOREIGN KEY (`relationTypeId`)
    REFERENCES `mydb`.`RelationType` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Evaluation_has_EvaluationExtraPropertyType`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Evaluation_has_EvaluationExtraPropertyType` (
  `evaluationId` INT NOT NULL,
  `evaluationExtraPropertyTypeId` INT NOT NULL,
  `propertyValue` VARCHAR(100) NULL,
  PRIMARY KEY (`evaluationId`, `evaluationExtraPropertyTypeId`),
  INDEX `fk_Evaluation_has_EvaluationExtraPropertyType_EvaluationExt_idx` (`evaluationExtraPropertyTypeId` ASC),
  INDEX `fk_Evaluation_has_EvaluationExtraPropertyType_Evaluation1_idx` (`evaluationId` ASC),
  CONSTRAINT `fk_Evaluation_has_EvaluationExtraPropertyType_Evaluation1`
    FOREIGN KEY (`evaluationId`)
    REFERENCES `mydb`.`Evaluation` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION,
  CONSTRAINT `fk_Evaluation_has_EvaluationExtraPropertyType_EvaluationExtra1`
    FOREIGN KEY (`evaluationExtraPropertyTypeId`)
    REFERENCES `mydb`.`EvaluationExtraPropertyType` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


-- -----------------------------------------------------
-- Table `mydb`.`Attachment`
-- -----------------------------------------------------
CREATE TABLE IF NOT EXISTS `mydb`.`Attachment` (
  `id` INT NOT NULL,
  `name` VARCHAR(45) NULL,
  `url` VARCHAR(45) NULL,
  `docId` INT NOT NULL,
  PRIMARY KEY (`id`, `docId`),
  INDEX `fk_Attachment_Document1_idx` (`docId` ASC),
  CONSTRAINT `fk_Attachment_Document1`
    FOREIGN KEY (`docId`)
    REFERENCES `mydb`.`Document` (`id`)
    ON DELETE NO ACTION
    ON UPDATE NO ACTION)
ENGINE = InnoDB;


SET SQL_MODE=@OLD_SQL_MODE;
SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS;
SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS;
