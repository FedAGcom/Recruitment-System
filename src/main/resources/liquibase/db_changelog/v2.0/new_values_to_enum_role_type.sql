--liquibase formatted sql
--changeset Artem:new-values-to-enum-role-type
-- ALTER TYPE enum_role_type ADD VALUE 'USER_INACTIVE';
-- ALTER TYPE enum_role_type ADD VALUE 'ADMIN_INACTIVE';
-- ALTER TYPE enum_role_type ADD VALUE 'COMPANY';
-- ALTER TYPE enum_role_type ADD VALUE 'COMPANY_INACTIVE';

