CREATE TABLE user_permissions (
                                  user_id       BIGINT NOT NULL,
                                  permission_id BIGINT NOT NULL,

                                  CONSTRAINT fk_user FOREIGN KEY (user_id) REFERENCES personal_users(id) ON DELETE CASCADE,
                                  CONSTRAINT fk_permission FOREIGN KEY (permission_id) REFERENCES permissions(id) ON DELETE CASCADE,

                                  PRIMARY KEY (user_id, permission_id)
);