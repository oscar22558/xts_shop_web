INSERT INTO categories (created_at, updated_at, name, parent_id) VALUES (NOW()::timestamp, NOW()::timestamp, 'Fashion', null);
INSERT INTO categories (created_at, updated_at, name, parent_id) VALUES (NOW()::timestamp, NOW()::timestamp, 'Tools', null);

INSERT INTO categories (created_at, updated_at, name, parent_id) VALUES (NOW()::timestamp, NOW()::timestamp, 'Men''s Fashion', 1);
INSERT INTO categories (created_at, updated_at, name, parent_id) VALUES (NOW()::timestamp, NOW()::timestamp, 'Women''s Fashion', 1);
INSERT INTO categories (created_at, updated_at, name, parent_id) VALUES (NOW()::timestamp, NOW()::timestamp, 'Mobile device peripheral', 2);
INSERT INTO categories (created_at, updated_at, name, parent_id) VALUES (NOW()::timestamp, NOW()::timestamp, 'Drawing tools', 2);

INSERT INTO categories (created_at, updated_at, name, parent_id) VALUES (NOW()::timestamp, NOW()::timestamp, 'Shoes', 3);
INSERT INTO categories (created_at, updated_at, name, parent_id) VALUES (NOW()::timestamp, NOW()::timestamp, 'Suits', 3);
INSERT INTO categories (created_at, updated_at, name, parent_id) VALUES (NOW()::timestamp, NOW()::timestamp, 'Dresses', 4);
INSERT INTO categories (created_at, updated_at, name, parent_id) VALUES (NOW()::timestamp, NOW()::timestamp, 'Skirts', 4);
INSERT INTO categories (created_at, updated_at, name, parent_id) VALUES (NOW()::timestamp, NOW()::timestamp, 'USB cable',               5);
INSERT INTO categories (created_at, updated_at, name, parent_id) VALUES (NOW()::timestamp, NOW()::timestamp, 'Mobile phone charger',    5);
INSERT INTO categories (created_at, updated_at, name, parent_id) VALUES (NOW()::timestamp, NOW()::timestamp, 'Color pencil',       6);
INSERT INTO categories (created_at, updated_at, name, parent_id) VALUES (NOW()::timestamp, NOW()::timestamp, 'Drawing paper',   6);

INSERT INTO brands(created_at,updated_at,name)
VALUES (NOW()::timestamp, NOW()::timestamp, 'SKECHERS');

INSERT INTO brands(created_at, updated_at, name)
VALUES (NOW()::timestamp, NOW()::timestamp, 'Puma');

INSERT INTO brands(created_at, updated_at, name)
VALUES (NOW()::timestamp, NOW()::timestamp, 'G2000');

INSERT INTO brands(created_at, updated_at, name)
VALUES (NOW()::timestamp, NOW()::timestamp, 'M&S');

INSERT INTO brands(created_at, updated_at, name)
VALUES (NOW()::timestamp, NOW()::timestamp, 'Giordano');

INSERT INTO brands(created_at, updated_at, name)
VALUES (NOW()::timestamp, NOW()::timestamp, 'National Geographic');

INSERT INTO brands(created_at, updated_at, name)
VALUES (NOW()::timestamp, NOW()::timestamp, 'FILA');

INSERT INTO brands(created_at, updated_at, name)
VALUES (NOW()::timestamp, NOW()::timestamp, 'AINOPE');

INSERT INTO brands(created_at, updated_at, name)
VALUES (NOW()::timestamp, NOW()::timestamp, 'Ugreen');

INSERT INTO brands(created_at, updated_at, name)
VALUES (NOW()::timestamp, NOW()::timestamp, 'HonShoop');

INSERT INTO brands(created_at, updated_at, name)
VALUES (NOW()::timestamp, NOW()::timestamp, 'Kruidvat');

INSERT INTO brands(created_at, updated_at, name)
VALUES (NOW()::timestamp, NOW()::timestamp, 'Prismacolor');

INSERT INTO brands(created_at, updated_at, name)
VALUES (NOW()::timestamp, NOW()::timestamp, 'Crayola');

INSERT INTO brands(created_at, updated_at, name)
VALUES (NOW()::timestamp, NOW()::timestamp, 'School Smart');

INSERT INTO brands(created_at, updated_at, name)
VALUES (NOW()::timestamp, NOW()::timestamp, 'BATKEV');

INSERT INTO items (created_at, updated_at, name, description, price, manufacturer, stock, category_id, brand_id)
VALUES (NOW()::timestamp, NOW()::timestamp, 'RIDE 9 HYPER MEN''S RUN', '', 1499.0, '', 100, 7, 1);
INSERT INTO images (created_at, updated_at, uri, description, extension, item_id)
VALUES (NOW()::timestamp, NOW()::timestamp, 'storage/images/ride-9-hyper-mens-run.jpg', 'RIDE 9 HYPER MEN''S RUN', 'jpg', 1);
INSERT INTO price_histories (created_at, updated_at, value, item_id)
VALUES (NOW()::timestamp, NOW()::timestamp, 1499.0, 1);

INSERT INTO items (created_at, updated_at, name, description, price, manufacturer, stock, category_id, brand_id)
VALUES (NOW()::timestamp, NOW()::timestamp, 'Puma Smash Pro', '', 499.0, '', 100, 7, 2);
INSERT INTO images (created_at, updated_at, uri, description, extension, item_id)
VALUES (NOW()::timestamp, NOW()::timestamp, 'storage/images/puma-smash-pro.jpg', 'Puma Smash Pro', 'jpg', 2);
INSERT INTO price_histories (created_at, updated_at, value, item_id)
VALUES (NOW()::timestamp, NOW()::timestamp, 499.0, 2);

INSERT INTO items (created_at, updated_at, name, description, price, manufacturer, stock, category_id, brand_id)
VALUES (NOW()::timestamp, NOW()::timestamp, 'Men''s Regular Fit Stretchable Checked Suit Blazer (Blue)', '', 3000.0, '', 100, 8, 3);
INSERT INTO images (created_at, updated_at, uri, description, extension, item_id)
VALUES (NOW()::timestamp, NOW()::timestamp, 'storage/images/men-regular-fit-stretchable-checked-suit-blazer-blue.jpg', 'Men''s Regular Fit Stretchable Checked Suit Blazer (Blue)', 'jpg', 3);
INSERT INTO price_histories (created_at, updated_at, value, item_id)
VALUES (NOW()::timestamp, NOW()::timestamp, 3000.0, 3);

INSERT INTO items (created_at, updated_at, name, description, price, manufacturer, stock, category_id, brand_id)
VALUES (NOW()::timestamp, NOW()::timestamp, 'Regular Fit Navy Pure Wool Suit', '', 1500.0, '', 100, 8, 4);
INSERT INTO images (created_at, updated_at, uri, description, extension, item_id)
VALUES (NOW()::timestamp, NOW()::timestamp, 'storage/images/m&s-regular-fit-navy-pure-wool-suit.jpg', 'Regular Fit NAvy Pure Woil Suit', 'jpg', 4);
INSERT INTO price_histories (created_at, updated_at, value, item_id)
VALUES (NOW()::timestamp, NOW()::timestamp, 1500.0, 4);

INSERT INTO items (created_at, updated_at, name, description, price, manufacturer, stock, category_id, brand_id)
VALUES (NOW()::timestamp, NOW()::timestamp, 'Women''s Coolmax Fit & Flare Broken Striped Dress (Navy)', '', 599.0, '', 100, 9, 3);
INSERT INTO images (created_at, updated_at, uri, description, extension, item_id)
VALUES (NOW()::timestamp, NOW()::timestamp, 'storage/images/womens-coolmax-fit&flare-broken-striped-dress-navy.jpg', 'Women''s Coolmax Fit & Flare Broken Striped Dress (Navy)', 'jpg', 5);
INSERT INTO price_histories (created_at, updated_at, value, item_id)
VALUES (NOW()::timestamp, NOW()::timestamp, 599.0, 5);

INSERT INTO items (created_at, updated_at, name, description, price, manufacturer, stock, category_id, brand_id)
VALUES (NOW()::timestamp, NOW()::timestamp, 'Women''s G-Motion Sorona Short-sleeve Dress', '', 139.0, '', 100, 9, 5);
INSERT INTO images (created_at, updated_at, uri, description, extension, item_id)
VALUES (NOW()::timestamp, NOW()::timestamp, 'storage/images/womens-g-motion-sorona-short-sleeve-dress.jpg', 'Women''s G-Motion Sorona Short-sleeve Dress', 'jpg', 6);
INSERT INTO price_histories (created_at, updated_at, value, item_id)
VALUES (NOW()::timestamp, NOW()::timestamp, 139.0, 6);

INSERT INTO items (created_at, updated_at, name, description, price, manufacturer, stock, category_id, brand_id)
VALUES (NOW()::timestamp, NOW()::timestamp, 'Women''s Pocket Short Skirt', '', 550.0, '', 100, 10, 6);
INSERT INTO images (created_at, updated_at, uri, description, extension, item_id)
VALUES (NOW()::timestamp, NOW()::timestamp, 'storage/images/womens-pocket-short-skirt.jpg', 'Women''s Pocket Short Skirt', 'jpg', 7);
INSERT INTO price_histories (created_at, updated_at, value, item_id)
VALUES (NOW()::timestamp, NOW()::timestamp, 550.0, 7);

INSERT INTO items (created_at, updated_at, name, description, price, manufacturer, stock, category_id, brand_id)
VALUES (NOW()::timestamp, NOW()::timestamp, 'Women''s FILA Logo Skirt', '', 880.0, '', 100, 10, 7);
INSERT INTO images (created_at, updated_at, uri, description, extension, item_id)
VALUES (NOW()::timestamp, NOW()::timestamp, 'storage/images/womens-fila-logo-skirt.jpg', 'Women''s FILA Logo Skirt', 'jpg', 8);
INSERT INTO price_histories (created_at, updated_at, value, item_id)
VALUES (NOW()::timestamp, NOW()::timestamp, 880.0, 8);

INSERT INTO items (created_at, updated_at, name, description, price, manufacturer, stock, category_id, brand_id)
VALUES (
        NOW()::timestamp,
        NOW()::timestamp,
        'AINOPE USB C Cable Type C Charger Fast Charging 3.1A 10FT iPad Pro Charger Cable Durable Nylon Braided USB A/C to USB-C Cable for iPad Pro/AIR/Mini 6 MacBook Galaxy S22 21 S10 Note',
        '',
        85.0,
        '',
        100,
        11,
        8
        );
INSERT INTO images (created_at, updated_at, uri, description, extension, item_id)
VALUES (NOW()::timestamp, NOW()::timestamp, 'storage/images/ainope-usb-c-cable.jpg', 'Ainope USB c cable', 'jpg', 9);
INSERT INTO price_histories (created_at, updated_at, value, item_id)
VALUES (NOW()::timestamp, NOW()::timestamp, 85.0, 9);

INSERT INTO items (created_at, updated_at, name, description, price, manufacturer, stock, category_id, brand_id)
VALUES (
        NOW()::timestamp,
        NOW()::timestamp,
        'UGREEN 2 Pack USB Extension Cable, (3 FT+ 3 FT) USB Extender USB 3.0 Extension Cable Nylon Braided Compatible with Webcam, Camera, Phone, USB hub, Mouse, Keyboard, Printer, Hard Drive, Headset, Xbox',
        '',
        75.0,
        '',
        100,
        11,
        9);
INSERT INTO images (created_at, updated_at, uri, description, extension, item_id)
VALUES (NOW()::timestamp, NOW()::timestamp, 'storage/images/ugreen-2-pack-usb-3.0-cable.jpg', 'Women''s G-Motion Sorona Short-sleeve Dress', 'jpg', 10);
INSERT INTO price_histories (created_at, updated_at, value, item_id)
VALUES (NOW()::timestamp, NOW()::timestamp, 75.0, 10);

INSERT INTO items (created_at, updated_at, name, description, price, manufacturer, stock, category_id, brand_id)
VALUES (
           NOW()::timestamp,
           NOW()::timestamp,
           '30W Fast USB C Charger, Foldable 2-Port PD Type c Charger, HonShoop USB C Charger Block Compatible for iPhone 12/12 Mini / 12 Pro / 12 Pro Max / 11 / X/XR/XS, iPad Pro, Pixel, Galaxy, and More',
           '',
           110.0,
           '',
           100,
           12,
           10
       );
INSERT INTO images (created_at, updated_at, uri, description, extension, item_id)
VALUES (NOW()::timestamp, NOW()::timestamp, 'storage/images/30w-fast-usb-c-charger.jpg', '30W Fast USB C Charger', 'jpg', 11);
INSERT INTO price_histories (created_at, updated_at, value, item_id)
VALUES (NOW()::timestamp, NOW()::timestamp, 110.0, 11);

INSERT INTO items (created_at, updated_at, name, description, price, manufacturer, stock, category_id, brand_id)
VALUES (
           NOW()::timestamp,
           NOW()::timestamp,
           'iPhone 12 13 Fast Charger,Fast iPhone Charger [Apple MFi Certified]Lightning Cable 20W Type C Charger USB C Fasting Charging Plug Adapter Compatible with iPhone13/13 Pro Max/12/12 Pro/11/XS/XR/X,iPad',
           '',
           120.0,
           '',
           100,
           12,
           11);
INSERT INTO images (created_at, updated_at, uri, description, extension, item_id)
VALUES (NOW()::timestamp, NOW()::timestamp, 'storage/images/iphone-12-13-faster-charger.jpg', 'Iphone 12 13 Fast Charger', 'jpg', 12);
INSERT INTO price_histories (created_at, updated_at, value, item_id)
VALUES (NOW()::timestamp, NOW()::timestamp, 120.0, 12);

INSERT INTO items (created_at, updated_at, name, description, price, manufacturer, stock, category_id, brand_id)
VALUES (
           NOW()::timestamp,
           NOW()::timestamp,
           'Prismacolor Premier Colored Pencils | Art Supplies for Drawing, Sketching, Adult Coloring | Soft Core Color Pencils, 72 Pack',
           '',
           450.0,
           '',
           100,
           13,
           12);
INSERT INTO images (created_at, updated_at, uri, description, extension, item_id)
VALUES (NOW()::timestamp, NOW()::timestamp, 'storage/images/prismacolor-premier-colored-pencils.jpg', 'Prismacolor Premier Colored Pencils', 'jpg', 13);
INSERT INTO price_histories (created_at, updated_at, value, item_id)
VALUES (NOW()::timestamp, NOW()::timestamp, 450.0, 13);

INSERT INTO items (created_at, updated_at, name, description, price, manufacturer, stock, category_id, brand_id)
VALUES (
           NOW()::timestamp,
           NOW()::timestamp,
           'Crayola Colored Pencil Set, School Supplies, Assorted Colors, 36 Count, Long',
           '',
           50.0,
           '',
           100,
           13,
           13);
INSERT INTO images (created_at, updated_at, uri, description, extension, item_id)
VALUES (NOW()::timestamp, NOW()::timestamp, 'storage/images/crayola-colored-pencil-set.jpg', 'Crayola Colored Pencil Set', 'jpg', 14);
INSERT INTO price_histories (created_at, updated_at, value, item_id)
VALUES (NOW()::timestamp, NOW()::timestamp, 50.0, 14);

INSERT INTO items (created_at, updated_at, name, description, price, manufacturer, stock, category_id, brand_id)
VALUES (
           NOW()::timestamp,
           NOW()::timestamp,
           'School Smart - 85604 Value Drawing Paper, 50 lb., 9 x 12 Inches, Soft White, Pack of 500',
           '',
           135.0,
           '',
           100,
           14,
           14);
INSERT INTO images (created_at, updated_at, uri, description, extension, item_id)
VALUES (NOW()::timestamp, NOW()::timestamp, 'storage/images/school-smart-drawing-paper.jpg', 'School Smart 85604 Value Drawing Paper', 'jpg', 15);
INSERT INTO price_histories (created_at, updated_at, value, item_id)
VALUES (NOW()::timestamp, NOW()::timestamp, 135.0, 15);

INSERT INTO items (created_at, updated_at, name, description, price, manufacturer, stock, category_id, brand_id)
VALUES (
           NOW()::timestamp,
           NOW()::timestamp,
           'BATKEV 9 x 12 inches Sketchbook 100 Sheets, Thick Drawing Paper Sketch Drawing Paper Sketch Pad, Art Paper for Drawing and Painting for Kids',
           '',
           102.0,
           '',
           100,
           14,
           15);
INSERT INTO images (created_at, updated_at, uri, description, extension, item_id)
VALUES (NOW()::timestamp, NOW()::timestamp, 'storage/images/batkev-9x12-inches-sketchbook.jpg', 'BATKEV 9 x 12 inches Sketchbook 100 Sheets', 'jpg', 16);
INSERT INTO price_histories (created_at, updated_at, value, item_id)
VALUES (NOW()::timestamp, NOW()::timestamp, 102.0, 16);

INSERT INTO roles (created_at, updated_at, name)
VALUES (NOW()::timestamp, NOW()::timestamp, 'ROLE_ADMIN');
INSERT INTO roles (created_at, updated_at, name)
VALUES (NOW()::timestamp, NOW()::timestamp, 'ROLE_USER');

INSERT INTO privileges (created_at, updated_at, name)
VALUES (NOW()::timestamp, NOW()::timestamp, 'READ_PRIVILEGE');
INSERT INTO privileges (created_at, updated_at, name)
VALUES (NOW()::timestamp, NOW()::timestamp, 'WRITE_PRIVILEGE');

INSERT INTO roles_privileges (role_id, privilege_id) VALUES (1, 1);
INSERT INTO roles_privileges (role_id, privilege_id) VALUES (1, 2);
INSERT INTO roles_privileges (role_id, privilege_id) VALUES (2, 1);
INSERT INTO roles_privileges (role_id, privilege_id) VALUES (2, 2);

INSERT INTO users (created_at, updated_at, username, password, password_encrypted_at, email, phone)
VALUES (NOW()::timestamp, NOW()::timestamp, 'ken1234', '123', null,'ken1234@gmail.com', '87654321');

INSERT INTO users (created_at, updated_at, username, password, password_encrypted_at, email, phone)
VALUES (NOW()::timestamp, NOW()::timestamp, 'marry1234', '123', null,'marry1234@gmail.com', '12345678');

INSERT INTO users_roles (user_id, role_id) VALUES (1, 1);
INSERT INTO users_roles (user_id, role_id) VALUES (2, 2);

INSERT INTO addresses (created_at, updated_at, row1, row2, area, district, city, country, user_id)
VALUES (NOW()::timestamp, NOW()::timestamp, 'MB164, Main Building, HKU', '', 'Sai Ying Pun', 'Hong Kong', 'Hong Kong', 'China', 2);

INSERT INTO orders
    (created_at, updated_at, recipient_last_name, recipient_first_name, recipient_phone, recipient_email, status, payment_intent_id, user_id)
VALUES
    (NOW()::timestamp, NOW()::timestamp, 'Wong', 'Marry', '12345678', 'marry1234@gmail.com', 'PAID', 'mdevlkv1924´´£5j34otigmefdgoiv19241312', '2');

INSERT INTO ordered_items
    (created_at, updated_at, quantity, price, item_id, order_id)
VALUES
    (NOW()::timestamp, NOW()::timestamp, 3, 1499.0, 1, 1);

INSERT INTO ordered_items
    (created_at, updated_at, quantity, price, item_id, order_id)
VALUES
    (NOW()::timestamp, NOW()::timestamp, 2, 1500.0, 4, 1);

INSERT INTO invoices
    (created_at, updated_at, items_total, shipping_fee, total, order_id)
VALUES
    (NOW()::timestamp, NOW()::timestamp, 7497.0, 20.0, 7517.0, 1);

INSERT INTO shipping_addresses
    (created_at ,updated_at, row1, row2, area, district, city, country, order_id)
VALUES
    (NOW()::timestamp, NOW()::timestamp, 'MB164, Main Building, HKU', '', 'Sai Ying Pun', 'Hong Kong', 'Hong Kong', 'China', 1);

