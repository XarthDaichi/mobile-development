/** Role **/
INSERT INTO public.role (id, name) VALUES (1, 'ROLE_ADMIN');
INSERT INTO public.role (id, name) VALUES (2, 'ROLE_ADOPTEE');
INSERT INTO public.role (id, name) VALUES (3, 'ROLE_ADOPTER');

/** Privilege **/
INSERT INTO public.privilege (id, name) VALUES (1, 'REPORT_PRIVILEGE');
INSERT INTO public.privilege (id, name) VALUES (2, 'WRITE_PRIVILEGE');
INSERT INTO public.privilege (id, name) VALUES (3, 'LIKE_PRIVILEGE');

/** Role-Privilege **/
INSERT INTO public.role_privilege (role_id, privilege_id) VALUES (1, 1);
INSERT INTO public.role_privilege (role_id, privilege_id) VALUES (1, 2);
INSERT INTO public.role_privilege (role_id, privilege_id) VALUES (1, 3);
INSERT INTO public.role_privilege (role_id, privilege_id) VALUES (2, 2);
INSERT INTO public.role_privilege (role_id, privilege_id) VALUES (2, 3);
INSERT INTO public.role_privilege (role_id, privilege_id) VALUES (3, 3);

/** Users **/
INSERT INTO public.users (username, create_date, phone, email, enabled, first_name, last_name, password, token_expired) VALUES ('1', '2020-08-30 18:23:52.000000', '+506 1111-1111', 'admin@guzmanalan.com', true, 'Maikol', 'Guzman', '$2a$10$6hg/QTw8Th1EmYtg9/5HhOmRdg320A6J8DumNUqx.4AltXZAjp0VK', false);
INSERT INTO public.users (username, create_date, phone, email, enabled, first_name, last_name, password, token_expired) VALUES ('2', '2024-05-17 00:00:00.000000', '+506 2222-2222', 'diego@test.com', true, 'Diego', 'Test', 'uaQQoF7Ib4_06Bm9vDnw9kl0hfPenCaOIYrJZP/Sgl.Ft.FfmkG/qeMxVr.H', false);
INSERT INTO public.users (username, create_date, phone, email, enabled, first_name, last_name, password, token_expired) VALUES ('3', '2024-05-17 00:00:00.000000', '+506 3333-3333', 'luis@test.com', true, 'Luis', 'Test', '4weWG7SWSFpIxjfA07FRCu1RF/Cjb/dU_JgcPwAjDLcyLMbVvOMWa2ZzBuZV', false);
INSERT INTO public.users (username, create_date, phone, email, enabled, first_name, last_name, password, token_expired) VALUES ('4', '2024-05-17 00:00:00.000000', '+506 4444-4444', 'jorge@test.com', true, 'Jorge', 'Test', 'URsCzwm0%672s%Jd7iDxym/VflNigUgGWYREgbDx%8EHtw1Eg2WUT3FUIW2O', false);

/** User-Role **/
INSERT INTO public.user_role (user_username, role_id) VALUES ('1', 1);
INSERT INTO public.user_role (user_username, role_id) VALUES ('2', 2);
INSERT INTO public.user_role (user_username, role_id) VALUES ('3', 2);
INSERT INTO public.user_role (user_username, role_id) VALUES ('4', 3);

/** BREED **/
INSERT INTO public.breed (id, name) VALUES (1, 'Siberin Husky');

INSERT INTO public.species (id, name) VALUES (1, 'DOG');

/** Animal **/
insert into animal (id, name, likes, description, species_breed_id, user_username) VALUES (1, 'Max', 'Tennis Balls', 'Sing-Songy', 1, '2');

/** Likes **/
INSERT INTO public.likes (username, animal_id) VALUES ('3', 1);
INSERT INTO public.likes (username, animal_id) VALUES ('4', 1);

/** Reports **/
INSERT INTO public.report (id, description, animal_id) VALUES (1, 'This is a Snapchat Filter!', 1);

ALTER SEQUENCE report_seq RESTART WITH 2; /** tiene que ser n + 1 a la cantidad de inserts (n). **/
ALTER SEQUENCE animal_seq RESTART WITH 2;
ALTER SEQUENCE users_seq RESTART WITH 5;
ALTER SEQUENCE role_seq RESTART WITH 4;
ALTER SEQUENCE privilege_seq RESTART WITH 4;
ALTER SEQUENCE species_seq RESTART WITH 2;
ALTER SEQUENCE breed_seq RESTART WITH 2;
