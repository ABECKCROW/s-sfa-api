DROP TABLE IF EXISTS sales_person;
DROP TABLE IF EXISTS company;
DROP TABLE IF EXISTS history;
DROP TABLE IF EXISTS appointment;

CREATE TABLE sales_persons (
    sales_person_id unsigned AUTO_INCREMENT,
    sales_person_name VARCHAR NOT NULL,
    PRIMARY KEY(sales_person_id)
);

CREATE TABLE companies (
    company_id SERIAL unsigned AUTO_INCREMENT,
    company_name VARCHAR　NOT NULL,
    company_region VARCHAR(4)　NOT NULL,
    company_city VARCHAR　NOT NULL,
    company_address VARCHAR,
    company_phone INTEGER　NOT NULL,
    company_rank CHAR(1),
    sales_person_id INTEGER NOT NULL,
    PRIMARY KEY(company_id),
    FOREIGN KEY(sales_person_id) REFERENCES sales_person(sales_person_id)
);

CREATE TABLE histories (
    history_id SERIAL unsigned AUTO_INCREMENT,
    history_date TIMESTAMP NOT NULL,
    approach_type VARCHAR NOT NULL,
    approach_results VARCHAR NOT NULL,
    company_id　INTEGER　NOT NULL,
    PRIMARY KEY(history_id),
    FOREIGN KEY(company_id) REFERENCES company(company_id),
);

CREATE TABLE appointments (
    appointment_id unsigned AUTO_INCREMENT,
    appointment_date TIMESTAMP NOT NULL,
    history_id　INTEGER　NOT NULL,
    PRIMARY KEY(appointment_id),
    FOREIGN KEY(history_id) REFERENCES history(history_id)
);

INSERT INTO sales_person (sales_person_name)
VALUES ('安部');
VALUES ('山田');
VALUES ('佐藤');
VALUES ('鈴木');

INSERT INTO company (company_name, company_region, company_city, company_phone, company_rank, sales_person_id)
VALUES ('ABECK株式会社', '神奈川県', '川崎市', 1234567890, "S", 1);
VALUES ('Disk株式会社', '神奈川県', '厚木市', 1234567890, "A", 1);
VALUES ('マウンテン株式会社', '神奈川県', '箱根町', 1234567890, "B", 2);
VALUES ('シュガー株式会社', '神奈川県', '綾瀬市', 1234567890, "C", 3);

INSERT INTO history (history_date, approach_type, approach_results, company_id)
VALUES ('2023-11-10', '架電', '担当者不在', 1);
VALUES ('2023-11-11', '架電', '担当者不在', 2);
VALUES ('2023-11-12', '飛込', '担当者不在', 1);
VALUES ('2023-11-13', '飛込', '担当者不在', 2);
VALUES ('2023-11-14', '架電', '挨拶のアポイント取得', 1);
VALUES ('2023-11-15', '架電', '提案のアポイント取得', 2);
VALUES ('2023-11-16', '訪問', '挨拶', 1);
VALUES ('2023-11-17', '訪問', '携帯電話の提案。', 2);
VALUES ('2023-11-18', 'info', '担当者不在', 1);
VALUES ('2023-11-19', 'info', '担当者不在', 2);
VALUES ('2023-11-20', '架電', '担当者不在', 3);
VALUES ('2023-11-20', '架電', '複合機のアポイント取得', 4);
VALUES ('2023-11-21', '訪問', '複合機の提案', 4);




INSERT INTO appointment (appointment_date, history_id)
VALUES ('2023-11-16', 5);
VALUES ('2023-11-17', 6);
VALUES ('2023-11-21', 12);
