CREATE TYPE user_gender AS ENUM ('MASCULINO', 'FEMININO', 'OUTRO', 'NAO_INFORMADO');
ALTER TABLE personal_users
ALTER COLUMN gender TYPE user_gender USING gender::user_gender;