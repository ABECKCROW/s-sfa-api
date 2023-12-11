DROP TABLE IF EXISTS sales_persons;
DROP TABLE IF EXISTS companies;
DROP TABLE IF EXISTS histories;
DROP TABLE IF EXISTS appointments;

CREATE TABLE sales_persons (
    sales_person_id INT unsigned AUTO_INCREMENT,
    sales_person_name VARCHAR(10) NOT NULL,
    PRIMARY KEY(sales_person_id)
);

CREATE TABLE companies (
    company_id INT unsigned AUTO_INCREMENT,
    company_name VARCHAR(40) NOT NULL,
    company_phone VARCHAR(12) NOT NULL,
    region VARCHAR(4) NOT NULL,
    city VARCHAR(6) NOT NULL,
    address VARCHAR(40),
    company_rank CHAR(1),
    sales_person_id INT unsigned NOT NULL,
    PRIMARY KEY(company_id),
    FOREIGN KEY(sales_person_id) REFERENCES sales_persons(sales_person_id)
);

CREATE TABLE histories (
    history_id INT unsigned AUTO_INCREMENT,
    history_date TIMESTAMP NOT NULL,
    approach_type VARCHAR(10) NOT NULL,
    approach_results TEXT,
    company_id INT unsigned NOT NULL,
    PRIMARY KEY(history_id),
    FOREIGN KEY(company_id) REFERENCES companies(company_id)
);

CREATE TABLE appointments (
    appointment_id INT unsigned AUTO_INCREMENT,
    appointment_date TIMESTAMP NOT NULL,
    history_id INT unsigned NOT NULL,
    PRIMARY KEY(appointment_id),
    FOREIGN KEY(history_id) REFERENCES histories(history_id)
);

INSERT INTO sales_persons (sales_person_name) VALUES('安部'),('山田'),('佐藤'),('鈴木');

INSERT INTO companies (company_name, company_phone, region, city, address, company_rank, sales_person_id) VALUES
("ABECK株式会社", "03-1234-5678","東京都", "千代田区",  "1-1-1", "S", 1),
("Disk株式会社", "046-123-4567","神奈川県", "厚木市",  "2-2-2", "A", 2),
("マウンテン株式会社", "0460-12-3456","神奈川県", "箱根町",  "3-3-3", "B", 3),
("シュガー株式会社", "0467-12-3456","神奈川県", "綾瀬市",  "4-4-4", "C", 4);

INSERT INTO histories (history_date, approach_type, approach_results, company_id) VALUES
('2023-11-10', '架電', '担当者不在', 1),
('2023-11-11', '架電', '担当者不在', 2),
('2023-11-12', '飛込', '担当者不在', 1),
('2023-11-13', '飛込', '担当者不在', 2),
('2023-11-14', '架電', '挨拶のアポイント取得', 1),
('2023-11-15', '架電', '提案のアポイント取得', 2),
('2023-11-16', '訪問', '挨拶', 1),
('2023-11-17', '訪問', '携帯電話の提案。', 2),
('2023-11-18', 'info', '担当者不在', 1),
('2023-11-19', 'info', '担当者不在', 2),
('2023-11-20', '架電', '担当者不在', 3),
('2023-11-20', '架電', '複合機のアポイント取得', 4),
('2023-11-21', '訪問', '複合機の提案', 4);

INSERT INTO appointments (appointment_date, history_id) VALUES
('2023-11-16', 5),
('2023-11-17', 6),
('2023-11-21', 12);
