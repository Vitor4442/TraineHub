INSERT INTO personal_users (
    owner_id,
    name,
    email,
    password,
    phone,
    active,
    birth_date,
    gender,
    account_non_expired,
    account_non_locked,
    credentials_non_expired
) VALUES (
             1,
             'Vitor Treinador',
             'vitor@email.com',
             '$2a$10$ByI6U19UPi.YpX.o.3qSreE.6vVpT.uY4G1t7L6YvFhG7yJ7i6.2K', -- Hash para '123456'
             '11999998888',
             TRUE,
             '1995-10-20',
             'MASCULINO',
             TRUE, -- account_non_expired
             TRUE, -- account_non_locked
             TRUE  -- credentials_non_expired
         );