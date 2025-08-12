ALTER TABLE "_user"
    ADD COLUMN IF NOT EXISTS auth_provider VARCHAR(32) NOT NULL DEFAULT 'FIREBASE',
    ADD COLUMN IF NOT EXISTS auth_provider_id VARCHAR(191) NOT NULL,
    ADD COLUMN IF NOT EXISTS email VARCHAR(320);

-- Unique indexes (idempotent creation)
DO $$
BEGIN
  IF NOT EXISTS (
    SELECT 1 FROM pg_indexes WHERE schemaname='public' AND indexname='ux__user_auth_provider_provider_id'
  ) THEN
CREATE UNIQUE INDEX ux__user_auth_provider_provider_id
    ON "_user"(auth_provider, auth_provider_id);
END IF;

  IF NOT EXISTS (
    SELECT 1 FROM pg_indexes WHERE schemaname='public' AND indexname='ux__user_email_lower'
  ) THEN
CREATE UNIQUE INDEX ux__user_email_lower
    ON "_user"(LOWER(email));
END IF;
END $$;