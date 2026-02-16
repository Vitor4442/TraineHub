CREATE TYPE role_type AS ENUM ('ADMIN', 'PERSONAL', 'STUDENT');

ALTER TABLE personal_users
ALTER COLUMN role TYPE role_type USING role::role_type;