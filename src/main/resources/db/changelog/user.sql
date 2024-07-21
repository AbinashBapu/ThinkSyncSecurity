
--changeset abinash(generate):1721485182972
--preconditions onFail:WARN

--comment: Create User Table
CREATE TABLE users (
    pk_user_id UUID PRIMARY KEY NOT NULL ,
    email_id VARCHAR(255) NOT NULL UNIQUE,
    hash VARCHAR(255) NOT NULL,
    authorities VARCHAR(255) NOT NULL,
    created_on TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_on TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_by UUID
);

--changeset abinash(generate):1721485532181
--preconditions onFail:WARN
--comment: Create userprofile table
CREATE TABLE user_profiles (
    pk_user_profile_id UUID PRIMARY KEY NOT NULL ,
    firstname VARCHAR(255),
    lastname VARCHAR(255),
    age INT,
    salary FLOAT,
    fk_user_id UUID UNIQUE REFERENCES users(pk_user_id) ON DELETE CASCADE,
    created_on TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_on TIMESTAMP WITH TIME ZONE DEFAULT CURRENT_TIMESTAMP,
    updated_by UUID
);