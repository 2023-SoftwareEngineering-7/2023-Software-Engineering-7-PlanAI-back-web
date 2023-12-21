INSERT INTO user (id, created_time, last_modified_time, name, email, phone_number, profile_image_path)
values (1, now(), now(), '정민규4', 'asbasd@gmail.com', '010-1234-5678', null);

INSERT INTO user (id, created_time, last_modified_time, name, email, phone_number, profile_image_path)
values (2, now(), now(), '정민규2', 'asbasd2@gmail.com', '010-4324-5678', null);

-- INSERT INTO user (id, created_time, last_modified_time, name, email, phone_number, profile_image_path)
-- values (115, now(), now(), '정민규', 'fastdodge7@gmail.com', '010-4324-5678', null);

INSERT INTO tag (id, created_time, last_modified_time, tag_name, owner_id)
values (1, now(), now(), 'Work', 1);

INSERT INTO tag (id, created_time, last_modified_time, tag_name, owner_id)
values (2, now(), now(), 'Study', 2);