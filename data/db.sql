create database hackathon;
create schema products;
-- products.product definition

-- Drop table

-- DROP TABLE products.product;

CREATE TABLE products.product (
	id varchar NOT NULL,
	title varchar NOT NULL,
	brandname varchar NOT NULL,
	gtin varchar NOT NULL,
	defaultimageurl text NOT NULL,
	aisleid varchar NULL,
	shelfid varchar NULL,
	aislename varchar NULL,
	shelfname varchar NULL,
	price varchar NULL,
	unitprice varchar NULL,
	unitofmeasure varchar NULL,
	status varchar NULL
);

-- Permissions

ALTER TABLE products.product OWNER TO postgres;
GRANT ALL ON TABLE products.product TO postgres;

-- products.promotions definition

-- Drop table

-- DROP TABLE products.promotions;

CREATE TABLE products.promotions (
	promotionid varchar NOT NULL,
	promotiontype varchar NULL,
	startdate timestamp NULL,
	enddate timestamp NULL,
	unitsellinginfo varchar NULL,
	offertext varchar NULL,
	beforediscount varchar NULL,
	afterdiscount varchar NULL,
	"attributes" varchar NULL,
	productid varchar NULL
);

-- Permissions

ALTER TABLE products.promotions OWNER TO postgres;
GRANT ALL ON TABLE products.promotions TO postgres;