DROP TABLE IF EXISTS products CASCADE;
DROP TABLE IF EXISTS variants CASCADE;
DROP TABLE IF EXISTS images CASCADE;
DROP TABLE IF EXISTS sizes CASCADE;
DROP TABLE IF EXISTS cart_items CASCADE;

CREATE TABLE IF NOT EXISTS products (
    id BIGSERIAL PRIMARY KEY,
	category TEXT NOT NULL,
    title TEXT NOT NULL,
    description TEXT NOT NULL,
    preview_image TEXT
);

CREATE TABLE IF NOT EXISTS variants (
	id BIGSERIAL PRIMARY KEY,
	color_name TEXT NOT NULL,
	product_id INT NOT NULL,
	FOREIGN KEY (product_id) REFERENCES products (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS images (
	id BIGSERIAL PRIMARY KEY,
	url TEXT NOT NULL,
	variant_id INT,
	FOREIGN KEY (variant_id) REFERENCES variants (id) ON DELETE CASCADE
);

CREATE TABLE IF NOT EXISTS sizes (
	id BIGSERIAL PRIMARY KEY,
	size TEXT NOT NULL,
	stock INT NOT NULL,
	variant_id INT,
	FOREIGN KEY (variant_id) REFERENCES variants (id) ON DELETE CASCADE
);

INSERT INTO products (category, title, description, preview_image) VALUES ('hats', 'Some Hat 1', 'This is some hat.', 'images/hats/01.jpg');
INSERT INTO products (category, title, description, preview_image) VALUES ('hats', 'Some Hat 2', 'This is some hat 2.', 'images/hats/02.jpg');
INSERT INTO products (category, title, description, preview_image) VALUES ('tshirts', 'Some T-shirt 1', 'This is some t-shirt.', 'images/tshirts/black-01.jpg');
INSERT INTO products (category, title, description, preview_image) VALUES ('jackets', 'Some Jacket 1', 'This is some jacket.', 'images/jackets/01.jpg');
INSERT INTO products (category, title, description, preview_image) VALUES ('bags', 'Some Bag 1', 'This is some bag.', 'images/bags/01/01.jpg');
INSERT INTO products (category, title, description, preview_image) VALUES ('bags', 'Some Bag 2', 'This is some bag 2.', 'images/bags/02/01.jpg');

INSERT INTO variants (color_name, product_id) VALUES ('Black', 1); -- hat 1
INSERT INTO sizes (size, stock, variant_id) VALUES ('onesize', 14, 1); -- black hat 1
INSERT INTO images (url, variant_id) VALUES ('images/hats/01.jpg', 1); -- black hat 1
INSERT INTO images (url, variant_id) VALUES ('images/hats/02.jpg', 1); -- black hat 1

INSERT INTO variants (color_name, product_id) VALUES ('White', 1); -- hat 1
INSERT INTO sizes (size, stock, variant_id) VALUES ('onesize', 8, 2); -- white hat 1
INSERT INTO images (url, variant_id) VALUES ('images/hats/02.jpg', 2); -- white hat 1

INSERT INTO variants (color_name, product_id) VALUES ('Blue', 2); -- hat 2
INSERT INTO sizes (size, stock, variant_id) VALUES ('onesize', 11, 3); -- blue hat 2
INSERT INTO images (url, variant_id) VALUES ('images/hats/01.jpg', 3); -- blue hat 2

INSERT INTO variants (color_name, product_id) VALUES ('Black', 3); -- tshirt 1
INSERT INTO sizes (size, stock, variant_id) VALUES ('S', 10, 4); -- black tshirt 1
INSERT INTO sizes (size, stock, variant_id) VALUES ('M', 15, 4); -- black tshirt 1
INSERT INTO sizes (size, stock, variant_id) VALUES ('L', 12, 4); -- black tshirt 1
INSERT INTO sizes (size, stock, variant_id) VALUES ('XL', 6, 4); -- black tshirt 1
INSERT INTO images (url, variant_id) VALUES ('images/tshirts/black-01.jpg', 4); -- black tshirt 1
INSERT INTO images (url, variant_id) VALUES ('images/tshirts/black-02.jpg', 4); -- black tshirt 1
INSERT INTO images (url, variant_id) VALUES ('images/tshirts/black-03.jpg', 4); -- black tshirt 1

INSERT INTO variants (color_name, product_id) VALUES ('Blue', 3); -- tshirt 1
INSERT INTO sizes (size, stock, variant_id) VALUES ('S', 7, 5); -- blue tshirt 1
INSERT INTO sizes (size, stock, variant_id) VALUES ('M', 12, 5); -- blue tshirt 1
INSERT INTO sizes (size, stock, variant_id) VALUES ('L', 10, 5); -- blue tshirt 1
INSERT INTO sizes (size, stock, variant_id) VALUES ('XL', 3, 5); -- blue tshirt 1
INSERT INTO images (url, variant_id) VALUES ('images/tshirts/psyche-01.jpg', 5); -- blue tshirt 1

INSERT INTO variants (color_name, product_id) VALUES ('White', 3); -- tshirt 1
INSERT INTO sizes (size, stock, variant_id) VALUES ('S', 9, 6); -- white tshirt 1
INSERT INTO sizes (size, stock, variant_id) VALUES ('M', 14, 6); -- white tshirt 1
INSERT INTO sizes (size, stock, variant_id) VALUES ('L', 10, 6); -- white tshirt 1
INSERT INTO sizes (size, stock, variant_id) VALUES ('XL', 2, 6); -- white tshirt 1
INSERT INTO images (url, variant_id) VALUES ('images/tshirts/psyche-01.jpg', 6); -- blue tshirt 1

INSERT INTO variants (color_name, product_id) VALUES ('Black', 4); -- jacket 1
INSERT INTO images (url, variant_id) VALUES ('images/jackets/01.jpg', 7); -- black jacket 1
INSERT INTO sizes (size, stock, variant_id) VALUES ('S', 5, 7); -- black jacket 1
INSERT INTO sizes (size, stock, variant_id) VALUES ('M', 8, 7); -- black jacket 1
INSERT INTO sizes (size, stock, variant_id) VALUES ('L', 3, 7); -- black jacket 1

INSERT INTO variants (color_name, product_id) VALUES ('Blue', 4); -- jacket 1
INSERT INTO images (url, variant_id) VALUES ('images/jackets/02.jpg', 8); -- blue jacket 1
INSERT INTO sizes (size, stock, variant_id) VALUES ('S', 0, 8); -- blue jacket 1
INSERT INTO sizes (size, stock, variant_id) VALUES ('M', 2, 8); -- blue jacket 1
INSERT INTO sizes (size, stock, variant_id) VALUES ('L', 1, 8); -- blue jacket 1

INSERT INTO variants (color_name, product_id) VALUES ('Black', 5); -- bag 1
INSERT INTO images (url, variant_id) VALUES ('images/bags/01/01.jpg', 9); -- black bag 1
INSERT INTO sizes (size, stock, variant_id) VALUES ('onesize', 2, 9); -- black bag 1

INSERT INTO variants (color_name, product_id) VALUES ('Black', 6); -- bag 2
INSERT INTO images (url, variant_id) VALUES ('images/bags/02/01.jpg', 10); -- black bag 2
INSERT INTO sizes (size, stock, variant_id) VALUES ('onesize', 4, 10); -- black bag 2

CREATE TABLE cart_items (
    id BIGSERIAL PRIMARY KEY,
    product_id INT NOT NULL,
    title TEXT NOT NULL,
    color TEXT NOT NULL,
    size TEXT NOT NULL,
    quantity INT NOT NULL,
    preview_image TEXT NOT NULL
);
