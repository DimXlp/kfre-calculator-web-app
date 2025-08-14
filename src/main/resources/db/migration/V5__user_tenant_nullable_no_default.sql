-- Make tenant_id optional and remove any default
ALTER TABLE "_user"
    ALTER COLUMN tenant_id DROP NOT NULL;

ALTER TABLE "_user"
    ALTER COLUMN tenant_id DROP DEFAULT;

-- If you had set 'default' earlier, clear it
UPDATE "_user"
SET tenant_id = NULL
WHERE tenant_id = 'default';

-- If there is any FK to a tenants table, drop it defensively
ALTER TABLE "_user" DROP CONSTRAINT IF EXISTS fk_user_tenant;

-- Enforce unique emails globally (only when not null)
CREATE UNIQUE INDEX IF NOT EXISTS ux_user_email_nonnull
    ON "_user"(email)
    WHERE email IS NOT NULL;
