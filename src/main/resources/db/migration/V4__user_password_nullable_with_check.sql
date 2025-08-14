-- Allow null passwords for non-LOCAL providers (e.g., FIREBASE)
ALTER TABLE "_user"
    ALTER COLUMN "password" DROP NOT NULL;

-- Enforce password presence only for LOCAL users
DO $$
BEGIN
  IF NOT EXISTS (
    SELECT 1
    FROM pg_constraint
    WHERE conname = 'user_password_required_for_local'
      AND conrelid = '"_user"'::regclass
  ) THEN
ALTER TABLE "_user"
    ADD CONSTRAINT user_password_required_for_local
        CHECK ((auth_provider <> 'LOCAL') OR (password IS NOT NULL));
END IF;
END$$;

-- Ensure unique pair for provider+provider_id
CREATE UNIQUE INDEX IF NOT EXISTS ux_user_provider_pid
    ON "_user"(auth_provider, auth_provider_id);
