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
INSERT INTO categories (created_at, updated_at, name, parent_id) VALUES (NOW()::timestamp, NOW()::timestamp, 'Color pen',       6);
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

INSERT INTO items (created_at, updated_at, name, description, price, manufacturer, stock, category_id, brand_id)
VALUES (NOW()::timestamp, NOW()::timestamp, 'RIDE 9 HYPER MEN''S RUN', '', 1499.0, '', 100, 7, 1);
INSERT INTO images (created_at, updated_at, uri, description, extension, item_id)
VALUES (NOW()::timestamp, NOW()::timestamp, 'storage/images/ride-9-hyper-mens-run', 'RIDE 9 HYPER MEN''S RUN', 'jpg', 1);

INSERT INTO items (created_at, updated_at, name, description, price, manufacturer, stock, category_id, brand_id)
VALUES (NOW()::timestamp, NOW()::timestamp, 'Puma Smash Pro', '', 499.0, '', 100, 7, 2);
INSERT INTO images (created_at, updated_at, uri, description, extension, item_id)
VALUES (NOW()::timestamp, NOW()::timestamp, 'storage/images/puma-smash-pro', 'Puma Smash Pro', 'jpg', 2);

INSERT INTO items (created_at, updated_at, name, description, price, manufacturer, stock, category_id, brand_id)
VALUES (NOW()::timestamp, NOW()::timestamp, 'Men''s Regular Fit Stretchable Checked Suit Blazer (Blue)', '', 3000.0, '', 100, 8, 3);
INSERT INTO images (created_at, updated_at, uri, description, extension, item_id)
VALUES (NOW()::timestamp, NOW()::timestamp, 'storage/images/men-regular-fit-stretchable-checked-suit-blazer-blue.jpg', 'Men''s Regular Fit Stretchable Checked Suit Blazer (Blue)', 'jpg', 3);


INSERT INTO items (created_at, updated_at, name, description, price, manufacturer, stock, category_id, brand_id)
VALUES (NOW()::timestamp, NOW()::timestamp, 'Regular Fit Navy Pure Wool Suit', '', 1500.0, '', 100, 8, 4);
INSERT INTO images (created_at, updated_at, uri, description, extension, item_id)
VALUES (NOW()::timestamp, NOW()::timestamp, 'storage/images/m&s-regular-fit-navy-pure-wool-suit.jpg', 'Regular Fit NAvy Pure Woil Suit', 'jpg', 4);

INSERT INTO items (created_at, updated_at, name, description, price, manufacturer, stock, category_id, brand_id)
VALUES (NOW()::timestamp, NOW()::timestamp, 'Women''s Coolmax Fit & Flare Broken Striped Dress (Navy)', '', 599.0, '', 100, 9, 3);
INSERT INTO images (created_at, updated_at, uri, description, extension, item_id)
VALUES (NOW()::timestamp, NOW()::timestamp, 'storage/images/womens-coolmax-fit&flare-broken-striped-dress-navy.jpg', 'Women''s Coolmax Fit & Flare Broken Striped Dress (Navy)', 'jpg', 5);

INSERT INTO items (created_at, updated_at, name, description, price, manufacturer, stock, category_id, brand_id)
VALUES (NOW()::timestamp, NOW()::timestamp, 'Women''s G-Motion Sorona Short-sleeve Dress', '', 139.0, '', 100, 9, 5);
INSERT INTO images (created_at, updated_at, uri, description, extension, item_id)
VALUES (NOW()::timestamp, NOW()::timestamp, 'storage/images/womens-g-motion-sorona-short-sleeve-dress.jpg', 'Women''s G-Motion Sorona Short-sleeve Dress', 'jpg', 6);

INSERT INTO items (created_at, updated_at, name, description, price, manufacturer, stock, category_id, brand_id)
VALUES (NOW()::timestamp, NOW()::timestamp, 'Women''s Pocket Short Skirt', '', 550.0, '', 100, 10, 6);
INSERT INTO images (created_at, updated_at, uri, description, extension, item_id)
VALUES (NOW()::timestamp, NOW()::timestamp, 'storage/images/womens-pocket-short-skirt.jpg', 'Women''s Pocket Short Skirt', 'jpg', 7);

INSERT INTO items (created_at, updated_at, name, description, price, manufacturer, stock, category_id, brand_id)
VALUES (NOW()::timestamp, NOW()::timestamp, 'Women''s FILA Logo Skirt', '', 880.0, '', 100, 10, 7);
INSERT INTO images (created_at, updated_at, uri, description, extension, item_id)
VALUES (NOW()::timestamp, NOW()::timestamp, 'storage/images/womens-fila-logo-skirt.jpg', 'Women''s FILA Logo Skirt', 'jpg', 8);

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
