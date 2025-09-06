CREATE TABLE user_core
(
    id            BIGSERIAL PRIMARY KEY,
    username      VARCHAR(150) NOT NULL,
    national_code VARCHAR(10)  NOT NULL,
    email         VARCHAR(150),
    phone_number  VARCHAR(15),
    password      VARCHAR(255) NOT NULL,
    first_name    VARCHAR(255) NOT NULL,
    last_name     VARCHAR(255) NOT NULL,
    created_at    TIMESTAMP    NOT NULL DEFAULT CURRENT_TIMESTAMP,
    updated_at    TIMESTAMP    DEFAULT CURRENT_TIMESTAMP,
    login_type    SMALLINT     NOT NULL DEFAULT 0,
    enabled       BOOLEAN      NOT NULL DEFAULT TRUE,
    state         SMALLINT     NOT NULL,
    CONSTRAINT uq_user_core_username UNIQUE (username),
    CONSTRAINT uq_user_core_national_code UNIQUE (national_code),
    CONSTRAINT uq_user_core_email UNIQUE (email),
    CONSTRAINT uq_user_core_phone_number UNIQUE (phone_number)
);

CREATE TABLE role_core
(
    id        BIGSERIAL PRIMARY KEY,
    role_name VARCHAR(100) NOT NULL,
    description VARCHAR(255),
    CONSTRAINT uq_role_core_name UNIQUE (role_name)
);

CREATE TABLE user_role_core
(
    id      BIGSERIAL PRIMARY KEY,
    user_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,
    CONSTRAINT uq_user_role_core UNIQUE (user_id, role_id),
    CONSTRAINT fk_user_role_user_core_id FOREIGN KEY (user_id) REFERENCES user_core (id),
    CONSTRAINT fk_user_role_role_core_id FOREIGN KEY (role_id) REFERENCES role_core (id)
);

CREATE TABLE permission_core
(
    id              BIGSERIAL PRIMARY KEY,
    permission_name VARCHAR(100) NOT NULL,
    description     VARCHAR(1500),
    CONSTRAINT uq_permission_core UNIQUE (permission_name)
);

CREATE TABLE permission_role_core
(
    id            BIGSERIAL PRIMARY KEY,
    role_id       BIGINT NOT NULL,
    permission_id BIGINT NOT NULL,
    CONSTRAINT uq_permission_role_core UNIQUE (role_id, permission_id),
    CONSTRAINT fk_permission_role_role_core_id FOREIGN KEY (role_id) REFERENCES role_core (id),
    CONSTRAINT fk_permission_role_permission_core_id FOREIGN KEY (permission_id) REFERENCES permission_core (id)
);