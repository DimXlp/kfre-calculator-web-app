-- Add missing columns to support full Android→backend sync (idempotent)
ALTER TABLE "_user"
    ADD COLUMN IF NOT EXISTS profile_image_url TEXT,
    ADD COLUMN IF NOT EXISTS clinic            VARCHAR(255),
    ADD COLUMN IF NOT EXISTS full_name         VARCHAR(512),
    ADD COLUMN IF NOT EXISTS android_created_at TIMESTAMPTZ,
    ADD COLUMN IF NOT EXISTS android_last_login TIMESTAMPTZ;